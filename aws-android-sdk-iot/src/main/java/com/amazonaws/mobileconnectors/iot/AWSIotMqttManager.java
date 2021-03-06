/**
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazonaws.mobileconnectors.iot;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.regions.Region;
import com.amazonaws.util.StringUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;

/**
 * The broker for applications allows receive and publish messages AWS IoT
 * MqttClient Amazon Internet of Things AWSIotMqttManager implements a broker
 * for applications and internet-connected things to publish and receive
 * messages.
 */
public class AWSIotMqttManager {

    /** API level 16. 16 = Build.VERSION_CODES.JELLY_BEAN. */
    private static final Integer ANDROID_API_LEVEL_16 = 16;
    /** Conversion seconds to milliseconds. */
    private static final Integer MILLIS_IN_ONE_SECOND = 1000;
    /** Constant for log prefix. */
    private static final String LOG_TAG = AWSIotMqttManager.class.getCanonicalName();

    /** Default value for starting delay in exponential backoff reconnect algorithm. */
    public static final Integer DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS = 4;
    /** Default value for maximum delay in exponential backoff reconnect algorithm. */
    public static final Integer DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS = 64;
    /** Default value for reconnect enabled. */
    public static final Boolean DEFAULT_AUTO_RECONNECT_ENABLED = true;
    /** Default value for number of auto reconnect attempts before giving up. */
    public static final Integer DEFAULT_AUTO_RECONNECT_ATTEMPTS = 10;
    /** Default value for MQTT keep alive. */
    public static final Integer DEFAULT_KEEP_ALIVE_SECONDS = 10;
    /** Default value for offline publish queue enabled. */
    public static final Boolean DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED = true;
    /** Default value for offline publish queue bound. */
    public static final Integer DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND = 100;
    /** Constant for milliseconds between queue publishes. */
    private static final Long DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES = 250L;
    /** Default value for "connection established" hysteresis timer. */
    private static final Integer DEFAULT_CONNECTION_STABILITY_TIME_SECONDS = 10;

    /** The underlying Paho Java MQTT client. */
    private MqttAsyncClient mqttClient;

    /** MQTT broker URL.  Built from region, customer endpoint. */
    private String mqttBrokerURL;

    /** Customer specific prefix for data endpoint. */
    private String accountEndpointPrefix;

    /** MQTT client ID, used for both initial connection and reconnections. */
    private String mqttClientId;
    /** AWS IoT region hosting the MQTT service. */
    private Region region;

    /**
     * Connection callback for the user.  This client receives the connection
     * lost callback and then calls this one on behalf of the user.
     */
    private AWSIotMqttClientStatusCallback userStatusCallback;
    /**
     * MQTT subscriptions. Used when resubscribing after a reconnect. Also used
     * to proved per-topic message arrived callbacks.
     */
    private final Map<String, AWSIotMqttTopic> topicListeners;
    /**
     * Queue for messages attempted to publish while MQTT client was offline.
     * Republished upon reconnect.
     */
    private final List<AWSIotMqttQueueMessage> mqttMessageQueue;
    /** KeepAlive interval specified by the user. */
    private int userKeepAlive;
    /** MQTT Will parameters. */
    private AWSIotMqttLastWillAndTestament mqttLWT;
    /** Are we automatically reconnecting upon (non-user) disconnect? */
    private boolean autoReconnect;
    /** Starting time between automatic reconnect attempts.  In seconds. */
    private int minReconnectRetryTime;
    /**
     * Reconnect backoff algorithm maximum time between automatic reconnect attempts. In seconds.
     */
    private int maxReconnectRetryTime;
    /**
     * The current reconnect time in the backoff algorithm.  This will increase
     * as successive reconnects fail.
     */
    private int currentReconnectRetryTime;
    /** Maximum number of automatic reconnect attempts done before giving up. */
    private int maxAutoReconnectAttepts;
    /** Reconnects attempted so far. */
    private int autoReconnectsAttempted;
    /** Is offline publish queueing enabled? */
    private boolean offlinePublishQueueEnabled;
    /** Offline publish queue bound. */
    private Integer offlinePublishQueueBound;
    /** Full queue behavior (keep oldest or keep newest)? */
    private boolean fullQueueKeepsOldest;
    /** Milliseconds between publishes when publishing queued messages (draining interval). */
    private long drainingInterval;
    /** Was this disconnect requested by the user? */
    private boolean userDisconnect;
    /** Do we need to resubscribe upon reconnecting? */
    private boolean needResubscribe;
    /**
     * Holds client socket factory. Set upon initial connect then reused on
     * reconnect.
     */
    private SocketFactory clientSocketFactory;
    /** Time to wait after CONNACK to declare the MQTT connection as stable.  In seconds. */
    private Integer connectionStabilityTime;
    /**
     * Timestamp of last successful MQTT connection (MQTT CONNACK).
     * Used to determine connection stability.
     */
    private Long lastConnackTime;
    /** The current connection status of the MQTT client. */
    private MqttManagerConnectionState connectionState;

    /** Override value for System.currentTimeInMillis.  Used for unit testing reconnect logic. */
    private Long unitTestMillisOverride;

    /**
     * Return the customer specific endpoint prefix.
     *
     * @return customer specific endpoint prefix.
     */
    public String getAccountEndpointPrefix() {
        return accountEndpointPrefix;
    }

    /**
     * Is auto-reconnect enabled?
     *
     * @return true if enabled, false if disabled.
     */
    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    /**
     * Enable / disable the auto-reconnect feature.
     *
     * @param enabled true if enabled, false if disabled.
     */
    public void setAutoReconnect(boolean enabled) {
        autoReconnect = enabled;
    }

    /**
     * Return the timeout value between reconnect attempts.
     *
     * @return the auto reconnect timeout value in seconds.
     */
    @Deprecated
    public int getReconnectTimeout() {
        return minReconnectRetryTime;
    }

    /**
     * Sets the timeout value for reconnect attempts.
     *
     * @param timeout timeout value in seconds.
     */
    @Deprecated
    public void setReconnectTimeout(int timeout) {
        this.minReconnectRetryTime = timeout;
    }

    /**
     * Sets the times to wait between autoreconnect attempts.
     * The autoreconnect backoff algorithm starts at the minimum value.
     * For each reconnect attempt that is unsuccessful the reconnect logic
     * will increase the time it waits to attempt the next reconnect.  The
     * maximum value limits the largest retry time.
     *
     * @param minTimeout minimum timeout value in seconds.
     * @param maxTimeout maximum timeout value in seconds.
     */
    public void setReconnectRetryLimits(int minTimeout, int maxTimeout) {
        if (minTimeout > maxTimeout) {
            throw new IllegalArgumentException("Minimum reconnect time needs to be less than Maximum.");
        }

        this.minReconnectRetryTime = minTimeout;
        this.maxReconnectRetryTime = maxTimeout;
    }

    /**
     * Gets the current starting value for time to wait between autoreconnect attempts.
     * @return the auto reconnect timeout value in seconds.
     */
    public int getMinReconnectRetryTime() {
        return minReconnectRetryTime;
    }

    /**
     * Gets the current maximum value for time to wait between autoreconnect attempts.
     * @return the auto reconnect wait time value in seconds.
     */
    public int getMaxReconnectRetryTime() {
        return maxReconnectRetryTime;
    }

    /**
     * Get the current setting of maximum reconnects attempted automatically before quitting.
     * @return number of reconnects to automatically attempt.  Retry forever = -1.
     */
    public int getMaxAutoReconnectAttempts() {
        return maxAutoReconnectAttepts;
    }

    /**
     * Set the maxium reconnects attempted automatically before quitting.
     *
     * @param attempts number of reconnects attempted automatically. Retry
     *            forever = -1.
     */
    public void setMaxAutoReconnectAttepts(int attempts) {
        if (attempts <= 0 && attempts != -1) {
            throw new IllegalArgumentException("Max reconnection attempts must be postive or -1");
        }
        maxAutoReconnectAttepts = attempts;
    }

    /**
     * Sets the connection stability time.  This is the time required to wait after receiving
     * the CONNACK to declare the connection to be stable and established.  When attempting to
     * reconnect after a disconnect the client uses backoff logic to wait longer on each successive
     * unsuccessful attempt.  We reset this logic after a connection has been maintained for the length
     * of connectionStabilityTime without disconnecting.
     * @param time time to wait to declare a connection to be stable (in seconds).
     */
    public void setConnectionStabilityTime(int time) {
        connectionStabilityTime = time;
    }

    /**
     * Gets the connection established time.
     * @return time the client will wait to declare a connection to be stable (in seconds).
     */
    public int getConnectionStabilityTime() {
        return connectionStabilityTime;
    }

    /**
     * Is the publish queue while offline feature enabled?
     *
     * @return boolean if offline queueing is enabled.
     */
    public boolean isOfflinePublishQueueEnabled() {
        return offlinePublishQueueEnabled;
    }

    /**
     * Enable or disable offline publish queueing.
     *
     * @param enabled boolean queueing feature is enabled.
     */
    public void setOfflinePublishQueueEnabled(boolean enabled) {
        offlinePublishQueueEnabled = enabled;
    }

    /**
     * Get the current value of the offline message queue bound.
     *
     * @return max number of messages stored in the message queue.
     */
    public Integer getOfflinePublishQueueBound() {
        return offlinePublishQueueBound;
    }

    /**
     * Set the bound for the number of messages queued while offline. Note: When
     * full queue will act as FIFO and shed oldest messages.
     *
     * @param bound max number of messages to queue while offline. Negative or 0
     *            values ignored.
     */
    public void setOfflinePublishQueueBound(Integer bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Offline queue bound must be > 0");
        }
        offlinePublishQueueBound = bound;
    }

    /**
     * Get the "draining interval" (the time between publish messages are sent from the offline queue when reconnected).
     * @return long containing the number of milliseconds between publishes.
     */
    public Long getDrainingInterval() {
        return drainingInterval;
    }

    /**
     * Set the "draining interval" (the time between publish messages are sent from the offline queue when reconnected).
     * @param interval milliseconds between offline queue publishes.
     */
    public void setDrainingInterval(Long interval) {
        drainingInterval = interval;
    }

    /**
     * Keep the oldest messages when publish queue is full?
     * @return boolean true if set to keep oldest messages, false if set to keep newest.
     */
    public boolean fullPublishQueueKeepsOldestMessages() {
        return fullQueueKeepsOldest;
    }

    /**
     * Set the queue behavior on a full queue to keep oldest messages.
     * Default is keep newest.
     */
    public void setFullQueueToKeepOldestMessages() {
        fullQueueKeepsOldest = true;
    }

    /**
     * Set the queue behavior on a full queue to keep newest messages.
     * Default is keep newest.
     */
    public void setFullQueueToKeepNewestMessages() {
        fullQueueKeepsOldest = false;
    }

    /**
     * Get the MQTT keep alive time.
     *
     * @return The MQTT keep alive time set by the user (in seconds).
     */
    public int getKeepAlive() {
        return userKeepAlive;
    }

    /**
     * Sets the MQTT keep alive time used by the underlying MQTT client to
     * determine connection status.
     *
     * @param keepAlive the MQTT keep alive time set by the user (in seconds). A
     *            value of 0 disables keep alive.
     */
    public void setKeepAlive(int keepAlive) {
        if (keepAlive < 0) {
            throw new IllegalArgumentException("Keep alive must be >= 0");
        }
        userKeepAlive = keepAlive;
    }

    /**
     * Get the currently configured Last Will and Testament.
     *
     * @return the current configure LWT.
     */
    public AWSIotMqttLastWillAndTestament getMqttLastWillAndTestament() {
        return mqttLWT;
    }

    /**
     * Set the client last will and testament.
     *
     * @param lwt the desired last will and testament.
     */
    public void setMqttLastWillAndTestament(AWSIotMqttLastWillAndTestament lwt) {
        mqttLWT = lwt;
    }

    /**
     * Sets the MQTT client.  Used for unit tests.
     * @param client - desired MQTT client.
     */
    void setMqttClient(MqttAsyncClient client) {
        mqttClient = client;
    }

    /**
     * Gets offline message queue. Used for unit tests.
     *
     * @return offline message queue.
     */
    List<AWSIotMqttQueueMessage> getMqttMessageQueue() {
        return mqttMessageQueue;
    }

    /**
     * Get MQTT client status.  Used for unit tests.
     * @return mqtt client status.
     */
    MqttManagerConnectionState getConnectionState() {
        return connectionState;
    }

    /**
     * Sets override value for System.currentTimeInMillis.
     * Used for unit testing reconnect logic.
     * @param timeMs desired time returned as milliseconds.
     */
    void setUnitTestMillisOverride(Long timeMs) {
        unitTestMillisOverride = timeMs;
    }

    /**
     * Get the current system time in milliseconds.
     * Allows for overriding the system time to test the auto reconnect logic.
     * @return a long with the system time in milliseconds or the unit test override.
     */
    private Long getSystemTimeMs() {
        if (unitTestMillisOverride == null) {
            return System.currentTimeMillis();
        } else {
            return unitTestMillisOverride;
        }
    }

    /**
     * Constructs a new AWSIotMqttManager.
     *
     * @param mqttClientId MQTT client ID to use with this client.
     * @param region The AWS region to use when creating endpoint.
     * @param accountEndpointPrefix Customer specific endpont prefix XXXXXXX in
     *            XXXXXX.iot.[region].amazonaws.com.
     */
    public AWSIotMqttManager(String mqttClientId, Region region, String accountEndpointPrefix) {

        if (mqttClientId == null || mqttClientId.isEmpty()) {
            throw new IllegalArgumentException("mqttClientId is null or empty");
        }

        if (region == null) {
            throw new IllegalArgumentException("region is null");
        }

        if (accountEndpointPrefix == null) {
            throw new IllegalArgumentException("accountEndpointPrefix is null");
        }

        this.accountEndpointPrefix = accountEndpointPrefix;
        this.mqttClientId = mqttClientId;
        this.region = region;

        connectionState = MqttManagerConnectionState.Disconnected;

        // Set client defaults
        autoReconnect = DEFAULT_AUTO_RECONNECT_ENABLED;
        minReconnectRetryTime = DEFAULT_MIN_RECONNECT_RETRY_TIME_SECONDS;
        maxReconnectRetryTime = DEFAULT_MAX_RECONNECT_RETRY_TIME_SECONDS;
        maxAutoReconnectAttepts = DEFAULT_AUTO_RECONNECT_ATTEMPTS;
        userKeepAlive = DEFAULT_KEEP_ALIVE_SECONDS;
        mqttLWT = null;
        offlinePublishQueueEnabled = DEFAULT_OFFLINE_PUBLISH_QUEUE_ENABLED;
        offlinePublishQueueBound = DEFAULT_OFFLINE_PUBLISH_QUEUE_BOUND;
        drainingInterval = DEFAULT_MILLIS_BETWEEN_QUEUE_PUBLISHES;
        setFullQueueToKeepNewestMessages();
        connectionStabilityTime = DEFAULT_CONNECTION_STABILITY_TIME_SECONDS;
        unitTestMillisOverride = null;

        this.topicListeners = new HashMap<String, AWSIotMqttTopic>();
        this.mqttMessageQueue = new LinkedList<AWSIotMqttQueueMessage>();
    }

    /**
     * Initializes the MQTT session and connects to the specified MQTT server
     * using certificate and private key in keystore. Keystore should be created
     * using IotKeystoreHelper to setup the certificate and key aliases as
     * expected by the underlying socket helper library.
     *
     * @param keyStore A keystore containing an keystore with a certificate and
     *            private key. Use IotKeystoreHelper to get keystore.
     * @param statusCallback When new MQTT session status is received the
     *            function of callback will be called with new connection
     *            status.
     */
    public void connect(KeyStore keyStore, final AWSIotMqttClientStatusCallback statusCallback) {

        if (Build.VERSION.SDK_INT < ANDROID_API_LEVEL_16) {
            throw new UnsupportedOperationException(
                    "API Level 16+ required for TLS 1.2 Mutual Auth");
        }

        if (keyStore == null) {
            throw new IllegalArgumentException("keyStore is null");
        }

        mqttBrokerURL = String.format("ssl://%s.iot.%s.%s:8883", accountEndpointPrefix, region.getName(), region.getDomain());

        try {
            if (mqttClient == null) {
                mqttClient = new MqttAsyncClient(mqttBrokerURL, mqttClientId, new MemoryPersistence());
            }

            SocketFactory socketFactory = AWSIotSslUtility.getSocketFactoryWithKeyStore(keyStore);
            MqttConnectOptions options = new MqttConnectOptions();

            if (mqttLWT != null) {
                options.setWill(mqttLWT.getTopic(), mqttLWT.getMessage().getBytes(), mqttLWT.getQos().asInt(), false);
            }

            clientSocketFactory = socketFactory;
            options.setSocketFactory(clientSocketFactory);

            mqttConnect(options, statusCallback);
        } catch (NoSuchAlgorithmException e) {
            throw new AWSIotCertificateException("A certificate error occurred.", e);
        } catch (KeyManagementException e) {
            throw new AWSIotCertificateException("A certificate error occurred.", e);
        } catch (KeyStoreException e) {
            throw new AWSIotCertificateException("A certificate error occurred.", e);
        } catch (UnrecoverableKeyException e) {
            throw new AWSIotCertificateException("A certificate error occurred.", e);
        } catch (MqttException e) {
            throw new AmazonClientException("An error occured in the MQTT client.", e);
        }
    }

    /**
     * Connect to the MQTT service.
     *
     * @param options        MQTT connect options containing a TLS socket factory for authentication.
     * @param statusCallback callback for status updates on connection.
     */
    private void mqttConnect(MqttConnectOptions options,
            final AWSIotMqttClientStatusCallback statusCallback) {

        this.userStatusCallback = statusCallback;

        // Do nothing if Connecting, Connected or Reconnecting
        if (connectionState != MqttManagerConnectionState.Disconnected) {
            userConnectionCallback();
            return;
        }

        // AWS IoT does not currently support persistent sessions
        options.setCleanSession(true);
        needResubscribe = true; // when cleanSession is added, this should
        // mirror cleanSession
        options.setKeepAliveInterval(userKeepAlive);

        topicListeners.clear();
        mqttMessageQueue.clear();

        resetReconnect();
        userDisconnect = false;

        setupCallbackForMqttClient();

        try {
            connectionState = MqttManagerConnectionState.Connecting;
            userConnectionCallback();

            mqttClient.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    connectionState = MqttManagerConnectionState.Connected;

                    lastConnackTime = getSystemTimeMs();

                    if (needResubscribe) {
                        resubscribeToTopics();
                    }
                    if (mqttMessageQueue.size() > 0) {
                        publishMessagesFromQueue();
                    }

                    userConnectionCallback();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable e) {
                    // Testing shows following reason codes:
                    // REASON_CODE_CLIENT_EXCEPTION = network unavailable / host unresolved
                    // REASON_CODE_CLIENT_EXCEPTION = deactivated certificate
                    // REASON_CODE_CLIENT_EXCEPTION = unknown certificate
                    // REASON_CODE_CONNECTION_LOST = no policy attached
                    // REASON_CODE_CONNECTION_LOST = bad connection policy

                    if (!userDisconnect && autoReconnect) {
                        connectionState = MqttManagerConnectionState.Reconnecting;
                        userConnectionCallback();
                        scheduleReconnect();
                    } else {
                        connectionState = MqttManagerConnectionState.Disconnected;
                        userConnectionCallback(e);
                    }
                }
            });
        } catch (MqttException e) {
            switch (e.getReasonCode()) {
                case MqttException.REASON_CODE_CLIENT_CONNECTED:
                    connectionState = MqttManagerConnectionState.Connected;
                    userConnectionCallback();
                    break;
                case MqttException.REASON_CODE_CONNECT_IN_PROGRESS:
                    connectionState = MqttManagerConnectionState.Connecting;
                    userConnectionCallback();
                    break;
                default:
                    connectionState = MqttManagerConnectionState.Disconnected;
                    userConnectionCallback(e);
                    break;
            }
        }
    }

    /**
     * Disconnect from a mqtt client (close current MQTT session).
     *
     * @return true if disconnect finished with success.
     */
    public boolean disconnect() {
        userDisconnect = true;
        reset();
        connectionState = MqttManagerConnectionState.Disconnected;
        userConnectionCallback();
        return true;
    }

    /**
     * Disconnect the MQTT client. Issues a disconnect request if the client is
     * connected.
     */
    void reset() {
        if (null != mqttClient) {
            if (mqttClient.isConnected()) {
                try {
                    mqttClient.disconnect(0);
                } catch (MqttException e) {
                    throw new AmazonClientException("Client error when disconnecting.", e);
                }
            }
        }
    }

    /**
     * Reconnect to MQTT broker.
     * Attempts to reconnect.  If unsuccessful schedules a reconnect attempt.
     */
    void reconnectToSession() {
        // status will be ConnectionLost if user calls disconnect() during reconnect logic
        if (null != mqttClient && connectionState != MqttManagerConnectionState.Disconnected) {

            MqttConnectOptions options = new MqttConnectOptions();

            options.setCleanSession(true);
            // when cleanSession is added, this should mirror cleanSession
            needResubscribe = true;
            options.setKeepAliveInterval(userKeepAlive);

            if (mqttLWT != null) {
                options.setWill(mqttLWT.getTopic(), mqttLWT.getMessage().getBytes(),
                        mqttLWT.getQos().asInt(), false);
            }

            options.setSocketFactory(clientSocketFactory);

            setupCallbackForMqttClient();

            try {
                ++autoReconnectsAttempted;
                mqttClient.connect(options, null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        connectionState = MqttManagerConnectionState.Connected;

                        lastConnackTime = getSystemTimeMs();

                        if (needResubscribe) {
                            resubscribeToTopics();
                        }
                        if (mqttMessageQueue.size() > 0) {
                            publishMessagesFromQueue();
                        }

                        userConnectionCallback();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable e) {
                        if (scheduleReconnect()) {
                            connectionState = MqttManagerConnectionState.Reconnecting;
                            userConnectionCallback();
                        } else {
                            connectionState = MqttManagerConnectionState.Disconnected;
                            userConnectionCallback();
                        }
                    }
                });
            } catch (MqttException e) {
                if (scheduleReconnect()) {
                    connectionState = MqttManagerConnectionState.Reconnecting;
                    userConnectionCallback();
                } else {
                    connectionState = MqttManagerConnectionState.Disconnected;
                    userConnectionCallback(e);
                }
            }
        }
    }

    /**
     * Schedule an auto-reconnect attempt using backoff logic.
     *
     * @return true if attempt was scheduled, false otherwise.
     */
    boolean scheduleReconnect() {
        // schedule a reconnect if unlimited or if we haven't yet hit the limit
        if (maxAutoReconnectAttepts == -1 || autoReconnectsAttempted < maxAutoReconnectAttepts) {

            (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mqttClient != null && !mqttClient.isConnected()) {
                        reconnectToSession();
                    }
                }
            }, MILLIS_IN_ONE_SECOND * currentReconnectRetryTime);

            currentReconnectRetryTime = Math.min(currentReconnectRetryTime * 2, maxReconnectRetryTime);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset the backoff logic to the inital values.
     */
    public void resetReconnect() {
        autoReconnectsAttempted = 0;
        currentReconnectRetryTime = minReconnectRetryTime;
    }

    /**
     * Subscribes to an MQTT topic.
     *
     * @param topic The topic to which to subscribe.
     * @param qos Quality of Service Level of the subscription.
     * @param callback Callback to be called when new message is received on
     *            this topic for this subscription.
     */
    public void subscribeToTopic(String topic, AWSIotMqttQos qos,
            AWSIotMqttNewMessageCallback callback) {

        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }

        if (qos == null) {
            throw new IllegalArgumentException("QoS cannot be null.");
        }

        if (null != mqttClient) {
            try {
                mqttClient.subscribe(topic, qos.asInt());
            } catch (MqttException e) {
                throw new AmazonClientException("Client error when subscribing.", e);
            }
            AWSIotMqttTopic topicModel = new AWSIotMqttTopic(topic, qos, callback);
            topicListeners.put(topic, topicModel);
        }
    }

    /**
     * Unsubscribe from an MQTT topic.
     *
     * @param topic topic from which to unsubscribe.
     */
    public void unsubscribeTopic(String topic) {

        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }

        if (mqttClient != null) {
            try {
                mqttClient.unsubscribe(topic);
            } catch (MqttException e) {
                throw new AmazonClientException("Client error while unsubscribing.", e);
            }
            topicListeners.remove(topic);
        }
    }

    /**
     * Resubscribe to previously subscribed topics on reconnecting.
     */
    void resubscribeToTopics() {
        needResubscribe = false;

        for (AWSIotMqttTopic topic : topicListeners.values()) {
            if (mqttClient != null) {
                try {
                    mqttClient.subscribe(topic.getTopic(), topic.getQos().asInt());
                } catch (MqttException e) {
                    Log.e(LOG_TAG, "Error while resubscribing to previously subscribed toipcs.", e);
                }
            }
        }
    }

    /**
     * Send a message to an MQTT topic.
     *
     * @param str The message payload to be sent (as a String).
     * @param topic The topic on which to publish.
     * @param qos The quality of service requested for this message.
     */
    public void publishString(String str, String topic, AWSIotMqttQos qos) {

        if (str == null) {
            throw new IllegalArgumentException("publish string is null");
        }

        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }

        if (qos == null) {
            throw new IllegalArgumentException("QoS cannot be null");
        }

        publishData(str.getBytes(StringUtils.UTF8), topic, qos);
    }

    /**
     * Publish data to an MQTT topic.
     *
     * @param data The message payload to be sent as a byte array.
     * @param topic The topic on which to publish.
     * @param qos The quality of service requested for this message.
     */
    public void publishData(byte[] data, String topic, AWSIotMqttQos qos) {

        if (topic == null || topic.isEmpty()) {
            throw new IllegalArgumentException("topic is null or empty");
        }

        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }

        if (qos == null) {
            throw new IllegalArgumentException("QoS cannot be null");
        }

        if (connectionState == MqttManagerConnectionState.Connected) {
            if (mqttMessageQueue.isEmpty()) {
                try {
                    mqttClient.publish(topic, data, qos.asInt(), false);
                } catch (MqttException e) {
                    throw new AmazonClientException("Client error while publishing.", e);
                }
            } else {
                // if the queue has messages we're making the assumption that offline queueing is enabled
                putMessageInQueue(data, topic, qos);
            }
        } else if (connectionState == MqttManagerConnectionState.Reconnecting) {
            if (offlinePublishQueueEnabled) {
                putMessageInQueue(data, topic, qos);
            }
        }
    }

    /**
     * Add a message to the publishing queue. A publish call adds to the queue
     * if the client is unable to publish (offline).
     * Behavior on a full queue is defined by fullQueueKeepsOldest.  If this is true
     * we keep the oldest values so we skip adding on a full queue.  If this is false
     * we want the queue to always have the latest values so pop the first element out
     * and append.
     *
     * @param data  byte array of message payload.
     * @param topic message topic.
     * @param qos   The quality of service requested for this message.
     */
    void putMessageInQueue(byte[] data, String topic, AWSIotMqttQos qos) {
        AWSIotMqttQueueMessage message = new AWSIotMqttQueueMessage(topic, data, qos);
        message.setTopic(topic);
        message.setMessage(data);

        if (mqttMessageQueue.size() >= offlinePublishQueueBound) {
            if (fullQueueKeepsOldest) {
                return;
            } else {
                mqttMessageQueue.remove(0);
            }
        }

        mqttMessageQueue.add(message);
    }

    /**
     * Publish messages from the message queue.
     * Called to handle publishing messages accumulated in the message queue when the client was unable to publish.
     */
    void publishMessagesFromQueue() {
        if (connectionState == MqttManagerConnectionState.Connected && mqttMessageQueue != null
                && !mqttMessageQueue.isEmpty()) {
            final AWSIotMqttQueueMessage message = mqttMessageQueue.get(0);
            if (message != null) {
                try {
                    mqttClient.publish(message.getTopic(), message.getMessage(), message.getQos()
                            .asInt(), false);
                    mqttMessageQueue.remove(0);
                } catch (MqttException e) {
                    Log.e(LOG_TAG, "Error while publishing from offline message queue.", e);
                }
            }

            (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!mqttMessageQueue.isEmpty()) {
                        if (connectionState == MqttManagerConnectionState.Connected) {
                            publishMessagesFromQueue();
                        }
                    }
                }
            }, drainingInterval);
        }
    }

    /**
     * Setup the MQTT client calbacks. The Paho MQTT client exposes callbacks
     * for connection status, publish status and incoming messages. The Android
     * MQTT client uses the single incoming message callback to map to per-topic
     * callbacks.
     */
    void setupCallbackForMqttClient() {
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                if (!userDisconnect && autoReconnect) {
                    connectionState = MqttManagerConnectionState.Reconnecting;
                    userConnectionCallback();

                    // If we have been connected longer than the connectionStabilityTime then
                    // restart the reconnect logic from minimum value before scheduling reconnect.
                    if ((lastConnackTime + (connectionStabilityTime * MILLIS_IN_ONE_SECOND)) < getSystemTimeMs()) {
                        resetReconnect();
                    }
                    scheduleReconnect();
                } else {
                    connectionState = MqttManagerConnectionState.Disconnected;
                    userConnectionCallback(cause);
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                byte[] data = mqttMessage.getPayload();

                for (String topicKey : topicListeners.keySet()) {
                    if (isTopicMatch(topicKey, topic)) {
                        AWSIotMqttTopic topicModel = topicListeners.get(topicKey);
                        if (topicModel != null) {
                            if (topicModel.getCallback() != null) {
                                topicModel.getCallback().onMessageArrived(topic, data);
                            }
                        }
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    /**
     * Is the MQTT client ready to publish messages? (Created and connected).
     *
     * @return true equals ready to publish, false equals offline.
     */
    boolean isReadyToPublish() {
        return mqttClient != null && mqttClient.isConnected();
    }

    /**
     * Wrapper method to notify user if they have specified a callback.
     * Maps internal state to user connection statuses.
     */
    void userConnectionCallback() {
        userConnectionCallback(null);
    }

    /**
     * Wrapper method to notify user if they have specified a callback.
     * Maps internal state to user connection statuses.
     * @param t a Throwable that may have originated from the status change.
     */
    void userConnectionCallback(Throwable t) {
        if (userStatusCallback != null) {
            switch (connectionState) {
                case Connected:
                    userStatusCallback.onStatusChanged(
                            AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected, t);
                    break;
                case Connecting:
                    userStatusCallback.onStatusChanged(
                            AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connecting, t);
                    break;
                case Reconnecting:
                    userStatusCallback.onStatusChanged(
                            AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting, t);
                    break;
                case Disconnected:
                    userStatusCallback.onStatusChanged(
                            AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost, t);
                    break;
                default:
                    throw new IllegalStateException("Unknown connection state.");
            }
        }
    }

    /**
     * Does the topic match the topic filter?
     *
     * @param topicFilter MQTT topic filter (subscriptions, including
     *            wildcards).
     * @param topic - the aboslute topic (no wildcards) on which a message was
     *            published.
     * @return true if the topic matches the filter, false otherwise.
     */
    static boolean isTopicMatch(String topicFilter, String topic) {
        String[] topicFilterTokens = topicFilter.split("/");
        String[] topicTokens = topic.split("/");

        if (topicFilterTokens.length > topicTokens.length) {
            return false;
        }

        for (int i = 0; i < topicFilterTokens.length; i++) {
            String topicFilterToken = topicFilterTokens[i];
            String topicToken = topicTokens[i];

            // we're equal up to this point, the # matches all that is left
            if (topicFilterToken.equals("#")) {
                return true;
            }

            // if the filter has a +, go to the next token
            // if the filter token matches the topic token, go to the next token
            // if neither are true then we've discovered a mismatch
            if (!topicFilterToken.equals("+") && !topicFilterToken.equals(topicToken)) {
                return false;
            }
        }

        // if we're here the strings matched token-for-token (including any
        // '+'s)
        // and we have reached the end of the topic filter
        // we have a match unless there are more tokens left in the topic
        // if we have filter 1/2/3 then:
        // 1/2/3 matches
        // 1/2/3/4 does not match
        //
        // if we have filter 1/2/+ then:
        // 1/2/3 matches
        // 1/2/3/4 does not match
        //
        // we already know the filter can't be longer than the topic (from
        // above)
        return (topicFilterTokens.length == topicTokens.length);
    }
}
