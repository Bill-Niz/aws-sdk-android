/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.simpleemail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

import com.amazonaws.services.simpleemail.model.*;

/**
 * Asynchronous client for accessing AmazonSimpleEmailService.
 * All asynchronous calls made using this client are non-blocking. Callers could either
 * process the result and handle the exceptions in the worker thread by providing a callback handler
 * when making the call, or use the returned Future object to check the result of the call in the calling thread.
 * Amazon Simple Email Service <p>
 * This is the API Reference for Amazon Simple Email Service (Amazon
 * SES). This documentation is intended to be used in conjunction with
 * the
 * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/Welcome.html"> Amazon SES Developer Guide </a>
 * .
 * </p>
 * <p>
 * <b>NOTE:</b>For a list of Amazon SES endpoints to use in service
 * requests, see Regions and Amazon SES in the Amazon SES Developer
 * Guide.
 * </p>
 */
public class AmazonSimpleEmailServiceAsyncClient extends AmazonSimpleEmailServiceClient
        implements AmazonSimpleEmailServiceAsync {

    /**
     * Executor service for executing asynchronous requests.
     */
    private ExecutorService executorService;

    private static final int DEFAULT_THREAD_POOL_SIZE = 10;

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService.  A credentials provider chain will be used
     * that searches for credentials in this order:
     * <ul>
     *  <li> Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY </li>
     *  <li> Java System Properties - aws.accessKeyId and aws.secretKey </li>
     *  <li> Instance profile credentials delivered through the Amazon EC2 metadata service </li>
     * </ul>
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @see DefaultAWSCredentialsProviderChain
     */
    @Deprecated
    public AmazonSimpleEmailServiceAsyncClient() {
        this(new DefaultAWSCredentialsProviderChain());
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService.  A credentials provider chain will be used
     * that searches for credentials in this order:
     * <ul>
     *  <li> Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY </li>
     *  <li> Java System Properties - aws.accessKeyId and aws.secretKey </li>
     *  <li> Instance profile credentials delivered through the Amazon EC2 metadata service </li>
     * </ul>
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonSimpleEmailService
     *                       (ex: proxy settings, retry counts, etc.).
     *
     * @see DefaultAWSCredentialsProviderChain
     */
    @Deprecated
    public AmazonSimpleEmailServiceAsyncClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration, Executors.newFixedThreadPool(clientConfiguration.getMaxConnections()));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials.
     * Default client settings will be used, and a fixed size thread pool will be
     * created for executing the asynchronous tasks.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentials awsCredentials) {
        this(awsCredentials, Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials
     * and executor service.  Default client settings will be used.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentials
     *            The AWS credentials (access key ID and secret key) to use
     *            when authenticating with AWS services.
     * @param executorService
     *            The executor service by which all asynchronous requests will
     *            be executed.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentials awsCredentials, ExecutorService executorService) {
        super(awsCredentials);
        this.executorService = executorService;
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials,
     * executor service, and client configuration options.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentials
     *            The AWS credentials (access key ID and secret key) to use
     *            when authenticating with AWS services.
     * @param clientConfiguration
     *            Client configuration options (ex: max retry limit, proxy
     *            settings, etc).
     * @param executorService
     *            The executor service by which all asynchronous requests will
     *            be executed.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentials awsCredentials,
                ClientConfiguration clientConfiguration, ExecutorService executorService) {
        super(awsCredentials, clientConfiguration);
        this.executorService = executorService;
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials provider.
     * Default client settings will be used, and a fixed size thread pool will be
     * created for executing the asynchronous tasks.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentialsProvider awsCredentialsProvider) {
        this(awsCredentialsProvider, Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials provider
     * and executor service.  Default client settings will be used.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param executorService
     *            The executor service by which all asynchronous requests will
     *            be executed.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentialsProvider awsCredentialsProvider, ExecutorService executorService) {
        this(awsCredentialsProvider, new ClientConfiguration(), executorService);
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials
     * provider and client configuration options.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param clientConfiguration
     *            Client configuration options (ex: max retry limit, proxy
     *            settings, etc).
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentialsProvider awsCredentialsProvider,
                ClientConfiguration clientConfiguration) {
        this(awsCredentialsProvider, clientConfiguration, Executors.newFixedThreadPool(clientConfiguration.getMaxConnections()));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on
     * AmazonSimpleEmailService using the specified AWS account credentials
     * provider, executor service, and client configuration options.
     *
     * <p>
     * All calls made using this new client object are non-blocking, and will immediately
     * return a Java Future object that the caller can later check to see if the service
     * call has actually completed.
     *
     * @param awsCredentialsProvider
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param clientConfiguration
     *            Client configuration options (ex: max retry limit, proxy
     *            settings, etc).
     * @param executorService
     *            The executor service by which all asynchronous requests will
     *            be executed.
     */
    public AmazonSimpleEmailServiceAsyncClient(AWSCredentialsProvider awsCredentialsProvider,
                ClientConfiguration clientConfiguration, ExecutorService executorService) {
        super(awsCredentialsProvider, clientConfiguration);
        this.executorService = executorService;
    }

    /**
     * Returns the executor service used by this async client to execute
     * requests.
     *
     * @return The executor service used by this async client to execute
     *         requests.
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Shuts down the client, releasing all managed resources. This includes
     * forcibly terminating all pending asynchronous service calls. Clients who
     * wish to give pending asynchronous service calls time to complete should
     * call getExecutorService().shutdown() followed by
     * getExecutorService().awaitTermination() prior to calling this method.
     */
    @Override
    public void shutdown() {
        super.shutdown();
        executorService.shutdownNow();
    }
            
    /**
     * <p>
     * Deletes the specified identity (email address or domain) from the
     * list of verified identities.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteIdentityRequest Container for the necessary parameters to
     *           execute the DeleteIdentity operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteIdentityResult> deleteIdentityAsync(final DeleteIdentityRequest deleteIdentityRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteIdentityResult>() {
            public DeleteIdentityResult call() throws Exception {
                return deleteIdentity(deleteIdentityRequest);
        }
    });
    }

    /**
     * <p>
     * Deletes the specified identity (email address or domain) from the
     * list of verified identities.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteIdentityRequest Container for the necessary parameters to
     *           execute the DeleteIdentity operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteIdentityResult> deleteIdentityAsync(
            final DeleteIdentityRequest deleteIdentityRequest,
            final AsyncHandler<DeleteIdentityRequest, DeleteIdentityResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteIdentityResult>() {
            public DeleteIdentityResult call() throws Exception {
              DeleteIdentityResult result;
                try {
                result = deleteIdentity(deleteIdentityRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteIdentityRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Updates a receipt rule.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param updateReceiptRuleRequest Container for the necessary parameters
     *           to execute the UpdateReceiptRule operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         UpdateReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<UpdateReceiptRuleResult> updateReceiptRuleAsync(final UpdateReceiptRuleRequest updateReceiptRuleRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<UpdateReceiptRuleResult>() {
            public UpdateReceiptRuleResult call() throws Exception {
                return updateReceiptRule(updateReceiptRuleRequest);
        }
    });
    }

    /**
     * <p>
     * Updates a receipt rule.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param updateReceiptRuleRequest Container for the necessary parameters
     *           to execute the UpdateReceiptRule operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         UpdateReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<UpdateReceiptRuleResult> updateReceiptRuleAsync(
            final UpdateReceiptRuleRequest updateReceiptRuleRequest,
            final AsyncHandler<UpdateReceiptRuleRequest, UpdateReceiptRuleResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<UpdateReceiptRuleResult>() {
            public UpdateReceiptRuleResult call() throws Exception {
              UpdateReceiptRuleResult result;
                try {
                result = updateReceiptRule(updateReceiptRuleRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(updateReceiptRuleRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns a list containing all of the email addresses that have been
     * verified.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The ListVerifiedEmailAddresses action is deprecated
     * as of the May 15, 2012 release of Domain Verification. The
     * ListIdentities action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listVerifiedEmailAddressesRequest Container for the necessary
     *           parameters to execute the ListVerifiedEmailAddresses operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ListVerifiedEmailAddresses service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListVerifiedEmailAddressesResult> listVerifiedEmailAddressesAsync(final ListVerifiedEmailAddressesRequest listVerifiedEmailAddressesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListVerifiedEmailAddressesResult>() {
            public ListVerifiedEmailAddressesResult call() throws Exception {
                return listVerifiedEmailAddresses(listVerifiedEmailAddressesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns a list containing all of the email addresses that have been
     * verified.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The ListVerifiedEmailAddresses action is deprecated
     * as of the May 15, 2012 release of Domain Verification. The
     * ListIdentities action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listVerifiedEmailAddressesRequest Container for the necessary
     *           parameters to execute the ListVerifiedEmailAddresses operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ListVerifiedEmailAddresses service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListVerifiedEmailAddressesResult> listVerifiedEmailAddressesAsync(
            final ListVerifiedEmailAddressesRequest listVerifiedEmailAddressesRequest,
            final AsyncHandler<ListVerifiedEmailAddressesRequest, ListVerifiedEmailAddressesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListVerifiedEmailAddressesResult>() {
            public ListVerifiedEmailAddressesResult call() throws Exception {
              ListVerifiedEmailAddressesResult result;
                try {
                result = listVerifiedEmailAddresses(listVerifiedEmailAddressesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(listVerifiedEmailAddressesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Deletes the specified receipt rule.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptRuleRequest Container for the necessary parameters
     *           to execute the DeleteReceiptRule operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptRuleResult> deleteReceiptRuleAsync(final DeleteReceiptRuleRequest deleteReceiptRuleRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptRuleResult>() {
            public DeleteReceiptRuleResult call() throws Exception {
                return deleteReceiptRule(deleteReceiptRuleRequest);
        }
    });
    }

    /**
     * <p>
     * Deletes the specified receipt rule.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptRuleRequest Container for the necessary parameters
     *           to execute the DeleteReceiptRule operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptRuleResult> deleteReceiptRuleAsync(
            final DeleteReceiptRuleRequest deleteReceiptRuleRequest,
            final AsyncHandler<DeleteReceiptRuleRequest, DeleteReceiptRuleResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptRuleResult>() {
            public DeleteReceiptRuleResult call() throws Exception {
              DeleteReceiptRuleResult result;
                try {
                result = deleteReceiptRule(deleteReceiptRuleRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteReceiptRuleRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Verifies an email address. This action causes a confirmation email
     * message to be sent to the specified address.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyEmailIdentityRequest Container for the necessary
     *           parameters to execute the VerifyEmailIdentity operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyEmailIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyEmailIdentityResult> verifyEmailIdentityAsync(final VerifyEmailIdentityRequest verifyEmailIdentityRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyEmailIdentityResult>() {
            public VerifyEmailIdentityResult call() throws Exception {
                return verifyEmailIdentity(verifyEmailIdentityRequest);
        }
    });
    }

    /**
     * <p>
     * Verifies an email address. This action causes a confirmation email
     * message to be sent to the specified address.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyEmailIdentityRequest Container for the necessary
     *           parameters to execute the VerifyEmailIdentity operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyEmailIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyEmailIdentityResult> verifyEmailIdentityAsync(
            final VerifyEmailIdentityRequest verifyEmailIdentityRequest,
            final AsyncHandler<VerifyEmailIdentityRequest, VerifyEmailIdentityResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyEmailIdentityResult>() {
            public VerifyEmailIdentityResult call() throws Exception {
              VerifyEmailIdentityResult result;
                try {
                result = verifyEmailIdentity(verifyEmailIdentityRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(verifyEmailIdentityRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Given a list of verified identities (email addresses and/or domains),
     * returns a structure describing identity notification attributes.
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * notification attributes for up to 100 identities at a time.
     * </p>
     * <p>
     * For more information about using notifications with Amazon SES, see
     * the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param getIdentityNotificationAttributesRequest Container for the
     *           necessary parameters to execute the GetIdentityNotificationAttributes
     *           operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityNotificationAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityNotificationAttributesResult> getIdentityNotificationAttributesAsync(final GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityNotificationAttributesResult>() {
            public GetIdentityNotificationAttributesResult call() throws Exception {
                return getIdentityNotificationAttributes(getIdentityNotificationAttributesRequest);
        }
    });
    }

    /**
     * <p>
     * Given a list of verified identities (email addresses and/or domains),
     * returns a structure describing identity notification attributes.
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * notification attributes for up to 100 identities at a time.
     * </p>
     * <p>
     * For more information about using notifications with Amazon SES, see
     * the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param getIdentityNotificationAttributesRequest Container for the
     *           necessary parameters to execute the GetIdentityNotificationAttributes
     *           operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityNotificationAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityNotificationAttributesResult> getIdentityNotificationAttributesAsync(
            final GetIdentityNotificationAttributesRequest getIdentityNotificationAttributesRequest,
            final AsyncHandler<GetIdentityNotificationAttributesRequest, GetIdentityNotificationAttributesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityNotificationAttributesResult>() {
            public GetIdentityNotificationAttributesResult call() throws Exception {
              GetIdentityNotificationAttributesResult result;
                try {
                result = getIdentityNotificationAttributes(getIdentityNotificationAttributesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getIdentityNotificationAttributesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns a set of DKIM tokens for a domain. DKIM <i>tokens</i> are
     * character strings that represent your domain's identity. Using these
     * tokens, you will need to create DNS CNAME records that point to DKIM
     * public keys hosted by Amazon SES. Amazon Web Services will eventually
     * detect that you have updated your DNS records; this detection process
     * may take up to 72 hours. Upon successful detection, Amazon SES will be
     * able to DKIM-sign email originating from that domain.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * To enable or disable Easy DKIM signing for a domain, use the
     * <code>SetIdentityDkimEnabled</code> action.
     * </p>
     * <p>
     * For more information about creating DNS records using DKIM tokens, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim-dns-records.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param verifyDomainDkimRequest Container for the necessary parameters
     *           to execute the VerifyDomainDkim operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyDomainDkim service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyDomainDkimResult> verifyDomainDkimAsync(final VerifyDomainDkimRequest verifyDomainDkimRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyDomainDkimResult>() {
            public VerifyDomainDkimResult call() throws Exception {
                return verifyDomainDkim(verifyDomainDkimRequest);
        }
    });
    }

    /**
     * <p>
     * Returns a set of DKIM tokens for a domain. DKIM <i>tokens</i> are
     * character strings that represent your domain's identity. Using these
     * tokens, you will need to create DNS CNAME records that point to DKIM
     * public keys hosted by Amazon SES. Amazon Web Services will eventually
     * detect that you have updated your DNS records; this detection process
     * may take up to 72 hours. Upon successful detection, Amazon SES will be
     * able to DKIM-sign email originating from that domain.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * To enable or disable Easy DKIM signing for a domain, use the
     * <code>SetIdentityDkimEnabled</code> action.
     * </p>
     * <p>
     * For more information about creating DNS records using DKIM tokens, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim-dns-records.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param verifyDomainDkimRequest Container for the necessary parameters
     *           to execute the VerifyDomainDkim operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyDomainDkim service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyDomainDkimResult> verifyDomainDkimAsync(
            final VerifyDomainDkimRequest verifyDomainDkimRequest,
            final AsyncHandler<VerifyDomainDkimRequest, VerifyDomainDkimResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyDomainDkimResult>() {
            public VerifyDomainDkimResult call() throws Exception {
              VerifyDomainDkimResult result;
                try {
                result = verifyDomainDkim(verifyDomainDkimRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(verifyDomainDkimRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the current status of Easy DKIM signing for an entity. For
     * domain name identities, this action also returns the DKIM tokens that
     * are required for Easy DKIM signing, and whether Amazon SES has
     * successfully verified that these tokens have been published.
     * </p>
     * <p>
     * This action takes a list of identities as input and returns the
     * following information for each:
     * </p>
     * 
     * <ul>
     * <li>Whether Easy DKIM signing is enabled or disabled.</li>
     * <li>A set of DKIM tokens that represent the identity. If the identity
     * is an email address, the tokens represent the domain of that
     * address.</li>
     * <li>Whether Amazon SES has successfully verified the DKIM tokens
     * published in the domain's DNS. This information is only returned for
     * domain name identities, not for email addresses.</li>
     * 
     * </ul>
     * <p>
     * This action is throttled at one request per second and can only get
     * DKIM attributes for up to 100 identities at a time.
     * </p>
     * <p>
     * For more information about creating DNS records using DKIM tokens, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim-dns-records.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param getIdentityDkimAttributesRequest Container for the necessary
     *           parameters to execute the GetIdentityDkimAttributes operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityDkimAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityDkimAttributesResult> getIdentityDkimAttributesAsync(final GetIdentityDkimAttributesRequest getIdentityDkimAttributesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityDkimAttributesResult>() {
            public GetIdentityDkimAttributesResult call() throws Exception {
                return getIdentityDkimAttributes(getIdentityDkimAttributesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the current status of Easy DKIM signing for an entity. For
     * domain name identities, this action also returns the DKIM tokens that
     * are required for Easy DKIM signing, and whether Amazon SES has
     * successfully verified that these tokens have been published.
     * </p>
     * <p>
     * This action takes a list of identities as input and returns the
     * following information for each:
     * </p>
     * 
     * <ul>
     * <li>Whether Easy DKIM signing is enabled or disabled.</li>
     * <li>A set of DKIM tokens that represent the identity. If the identity
     * is an email address, the tokens represent the domain of that
     * address.</li>
     * <li>Whether Amazon SES has successfully verified the DKIM tokens
     * published in the domain's DNS. This information is only returned for
     * domain name identities, not for email addresses.</li>
     * 
     * </ul>
     * <p>
     * This action is throttled at one request per second and can only get
     * DKIM attributes for up to 100 identities at a time.
     * </p>
     * <p>
     * For more information about creating DNS records using DKIM tokens, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim-dns-records.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param getIdentityDkimAttributesRequest Container for the necessary
     *           parameters to execute the GetIdentityDkimAttributes operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityDkimAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityDkimAttributesResult> getIdentityDkimAttributesAsync(
            final GetIdentityDkimAttributesRequest getIdentityDkimAttributesRequest,
            final AsyncHandler<GetIdentityDkimAttributesRequest, GetIdentityDkimAttributesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityDkimAttributesResult>() {
            public GetIdentityDkimAttributesResult call() throws Exception {
              GetIdentityDkimAttributesResult result;
                try {
                result = getIdentityDkimAttributes(getIdentityDkimAttributesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getIdentityDkimAttributesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the details of the specified receipt rule set.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DescribeReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeReceiptRuleSetResult> describeReceiptRuleSetAsync(final DescribeReceiptRuleSetRequest describeReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeReceiptRuleSetResult>() {
            public DescribeReceiptRuleSetResult call() throws Exception {
                return describeReceiptRuleSet(describeReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the details of the specified receipt rule set.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DescribeReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeReceiptRuleSetResult> describeReceiptRuleSetAsync(
            final DescribeReceiptRuleSetRequest describeReceiptRuleSetRequest,
            final AsyncHandler<DescribeReceiptRuleSetRequest, DescribeReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeReceiptRuleSetResult>() {
            public DescribeReceiptRuleSetResult call() throws Exception {
              DescribeReceiptRuleSetResult result;
                try {
                result = describeReceiptRuleSet(describeReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(describeReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Verifies an email address. This action causes a confirmation email
     * message to be sent to the specified address.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The VerifyEmailAddress action is deprecated as of
     * the May 15, 2012 release of Domain Verification. The
     * VerifyEmailIdentity action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyEmailAddressRequest Container for the necessary
     *           parameters to execute the VerifyEmailAddress operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyEmailAddress service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<Void> verifyEmailAddressAsync(final VerifyEmailAddressRequest verifyEmailAddressRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
                verifyEmailAddress(verifyEmailAddressRequest);
                return null;
        }
    });
    }

    /**
     * <p>
     * Verifies an email address. This action causes a confirmation email
     * message to be sent to the specified address.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The VerifyEmailAddress action is deprecated as of
     * the May 15, 2012 release of Domain Verification. The
     * VerifyEmailIdentity action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyEmailAddressRequest Container for the necessary
     *           parameters to execute the VerifyEmailAddress operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyEmailAddress service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<Void> verifyEmailAddressAsync(
            final VerifyEmailAddressRequest verifyEmailAddressRequest,
            final AsyncHandler<VerifyEmailAddressRequest, Void> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
              try {
                verifyEmailAddress(verifyEmailAddressRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(verifyEmailAddressRequest, null);
                 return null;
        }
    });
    }
    
    /**
     * <p>
     * Returns a list of sending authorization policies that are attached to
     * the given identity (email address or domain). This API returns only a
     * list. If you want the actual policy content, you can use
     * <code>GetIdentityPolicies</code> .
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listIdentityPoliciesRequest Container for the necessary
     *           parameters to execute the ListIdentityPolicies operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ListIdentityPolicies service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListIdentityPoliciesResult> listIdentityPoliciesAsync(final ListIdentityPoliciesRequest listIdentityPoliciesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListIdentityPoliciesResult>() {
            public ListIdentityPoliciesResult call() throws Exception {
                return listIdentityPolicies(listIdentityPoliciesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns a list of sending authorization policies that are attached to
     * the given identity (email address or domain). This API returns only a
     * list. If you want the actual policy content, you can use
     * <code>GetIdentityPolicies</code> .
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listIdentityPoliciesRequest Container for the necessary
     *           parameters to execute the ListIdentityPolicies operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ListIdentityPolicies service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListIdentityPoliciesResult> listIdentityPoliciesAsync(
            final ListIdentityPoliciesRequest listIdentityPoliciesRequest,
            final AsyncHandler<ListIdentityPoliciesRequest, ListIdentityPoliciesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListIdentityPoliciesResult>() {
            public ListIdentityPoliciesResult call() throws Exception {
              ListIdentityPoliciesResult result;
                try {
                result = listIdentityPolicies(listIdentityPoliciesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(listIdentityPoliciesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns a list containing all of the identities (email addresses and
     * domains) for a specific AWS Account, regardless of verification
     * status.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listIdentitiesRequest Container for the necessary parameters to
     *           execute the ListIdentities operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ListIdentities service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListIdentitiesResult> listIdentitiesAsync(final ListIdentitiesRequest listIdentitiesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListIdentitiesResult>() {
            public ListIdentitiesResult call() throws Exception {
                return listIdentities(listIdentitiesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns a list containing all of the identities (email addresses and
     * domains) for a specific AWS Account, regardless of verification
     * status.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listIdentitiesRequest Container for the necessary parameters to
     *           execute the ListIdentities operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ListIdentities service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListIdentitiesResult> listIdentitiesAsync(
            final ListIdentitiesRequest listIdentitiesRequest,
            final AsyncHandler<ListIdentitiesRequest, ListIdentitiesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListIdentitiesResult>() {
            public ListIdentitiesResult call() throws Exception {
              ListIdentitiesResult result;
                try {
                result = listIdentities(listIdentitiesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(listIdentitiesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Given a list of identities (email addresses and/or domains), returns
     * the verification status and (for domain identities) the verification
     * token for each identity.
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * verification attributes for up to 100 identities at a time.
     * </p>
     *
     * @param getIdentityVerificationAttributesRequest Container for the
     *           necessary parameters to execute the GetIdentityVerificationAttributes
     *           operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityVerificationAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityVerificationAttributesResult> getIdentityVerificationAttributesAsync(final GetIdentityVerificationAttributesRequest getIdentityVerificationAttributesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityVerificationAttributesResult>() {
            public GetIdentityVerificationAttributesResult call() throws Exception {
                return getIdentityVerificationAttributes(getIdentityVerificationAttributesRequest);
        }
    });
    }

    /**
     * <p>
     * Given a list of identities (email addresses and/or domains), returns
     * the verification status and (for domain identities) the verification
     * token for each identity.
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * verification attributes for up to 100 identities at a time.
     * </p>
     *
     * @param getIdentityVerificationAttributesRequest Container for the
     *           necessary parameters to execute the GetIdentityVerificationAttributes
     *           operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityVerificationAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityVerificationAttributesResult> getIdentityVerificationAttributesAsync(
            final GetIdentityVerificationAttributesRequest getIdentityVerificationAttributesRequest,
            final AsyncHandler<GetIdentityVerificationAttributesRequest, GetIdentityVerificationAttributesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityVerificationAttributesResult>() {
            public GetIdentityVerificationAttributesResult call() throws Exception {
              GetIdentityVerificationAttributesResult result;
                try {
                result = getIdentityVerificationAttributes(getIdentityVerificationAttributesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getIdentityVerificationAttributesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Enables or disables the custom MAIL FROM domain setup for a verified
     * identity (email address or domain).
     * </p>
     * <p>
     * <b>IMPORTANT:</b>To send emails using the specified MAIL FROM domain,
     * you must add an MX record to your MAIL FROM domain's DNS settings. If
     * you want your emails to pass Sender Policy Framework (SPF) checks, you
     * must also add or update an SPF record. For more information, see the
     * Amazon SES Developer Guide.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setIdentityMailFromDomainRequest Container for the necessary
     *           parameters to execute the SetIdentityMailFromDomain operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityMailFromDomain service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityMailFromDomainResult> setIdentityMailFromDomainAsync(final SetIdentityMailFromDomainRequest setIdentityMailFromDomainRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityMailFromDomainResult>() {
            public SetIdentityMailFromDomainResult call() throws Exception {
                return setIdentityMailFromDomain(setIdentityMailFromDomainRequest);
        }
    });
    }

    /**
     * <p>
     * Enables or disables the custom MAIL FROM domain setup for a verified
     * identity (email address or domain).
     * </p>
     * <p>
     * <b>IMPORTANT:</b>To send emails using the specified MAIL FROM domain,
     * you must add an MX record to your MAIL FROM domain's DNS settings. If
     * you want your emails to pass Sender Policy Framework (SPF) checks, you
     * must also add or update an SPF record. For more information, see the
     * Amazon SES Developer Guide.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setIdentityMailFromDomainRequest Container for the necessary
     *           parameters to execute the SetIdentityMailFromDomain operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityMailFromDomain service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityMailFromDomainResult> setIdentityMailFromDomainAsync(
            final SetIdentityMailFromDomainRequest setIdentityMailFromDomainRequest,
            final AsyncHandler<SetIdentityMailFromDomainRequest, SetIdentityMailFromDomainResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityMailFromDomainResult>() {
            public SetIdentityMailFromDomainResult call() throws Exception {
              SetIdentityMailFromDomainResult result;
                try {
                result = setIdentityMailFromDomain(setIdentityMailFromDomainRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setIdentityMailFromDomainRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Given an identity (email address or domain), enables or disables
     * whether Amazon SES forwards bounce and complaint notifications as
     * email. Feedback forwarding can only be disabled when Amazon Simple
     * Notification Service (Amazon SNS) topics are specified for both
     * bounces and complaints.
     * </p>
     * <p>
     * <b>NOTE:</b>Feedback forwarding does not apply to delivery
     * notifications. Delivery notifications are only available through
     * Amazon SNS.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about using notifications with Amazon SES, see
     * the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityFeedbackForwardingEnabledRequest Container for the
     *           necessary parameters to execute the
     *           SetIdentityFeedbackForwardingEnabled operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityFeedbackForwardingEnabled service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityFeedbackForwardingEnabledResult> setIdentityFeedbackForwardingEnabledAsync(final SetIdentityFeedbackForwardingEnabledRequest setIdentityFeedbackForwardingEnabledRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityFeedbackForwardingEnabledResult>() {
            public SetIdentityFeedbackForwardingEnabledResult call() throws Exception {
                return setIdentityFeedbackForwardingEnabled(setIdentityFeedbackForwardingEnabledRequest);
        }
    });
    }

    /**
     * <p>
     * Given an identity (email address or domain), enables or disables
     * whether Amazon SES forwards bounce and complaint notifications as
     * email. Feedback forwarding can only be disabled when Amazon Simple
     * Notification Service (Amazon SNS) topics are specified for both
     * bounces and complaints.
     * </p>
     * <p>
     * <b>NOTE:</b>Feedback forwarding does not apply to delivery
     * notifications. Delivery notifications are only available through
     * Amazon SNS.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about using notifications with Amazon SES, see
     * the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityFeedbackForwardingEnabledRequest Container for the
     *           necessary parameters to execute the
     *           SetIdentityFeedbackForwardingEnabled operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityFeedbackForwardingEnabled service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityFeedbackForwardingEnabledResult> setIdentityFeedbackForwardingEnabledAsync(
            final SetIdentityFeedbackForwardingEnabledRequest setIdentityFeedbackForwardingEnabledRequest,
            final AsyncHandler<SetIdentityFeedbackForwardingEnabledRequest, SetIdentityFeedbackForwardingEnabledResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityFeedbackForwardingEnabledResult>() {
            public SetIdentityFeedbackForwardingEnabledResult call() throws Exception {
              SetIdentityFeedbackForwardingEnabledResult result;
                try {
                result = setIdentityFeedbackForwardingEnabled(setIdentityFeedbackForwardingEnabledRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setIdentityFeedbackForwardingEnabledRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Reorders the receipt rules within a receipt rule set.
     * </p>
     * <p>
     * <b>NOTE:</b>All of the rules in the rule set must be represented in
     * this request. That is, this API will return an error if the reorder
     * request doesn't explicitly position all of the rules.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param reorderReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the ReorderReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ReorderReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ReorderReceiptRuleSetResult> reorderReceiptRuleSetAsync(final ReorderReceiptRuleSetRequest reorderReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ReorderReceiptRuleSetResult>() {
            public ReorderReceiptRuleSetResult call() throws Exception {
                return reorderReceiptRuleSet(reorderReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Reorders the receipt rules within a receipt rule set.
     * </p>
     * <p>
     * <b>NOTE:</b>All of the rules in the rule set must be represented in
     * this request. That is, this API will return an error if the reorder
     * request doesn't explicitly position all of the rules.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param reorderReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the ReorderReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ReorderReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ReorderReceiptRuleSetResult> reorderReceiptRuleSetAsync(
            final ReorderReceiptRuleSetRequest reorderReceiptRuleSetRequest,
            final AsyncHandler<ReorderReceiptRuleSetRequest, ReorderReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ReorderReceiptRuleSetResult>() {
            public ReorderReceiptRuleSetResult call() throws Exception {
              ReorderReceiptRuleSetResult result;
                try {
                result = reorderReceiptRuleSet(reorderReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(reorderReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Deletes the specified IP address filter.
     * </p>
     * <p>
     * For information about managing IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptFilterRequest Container for the necessary
     *           parameters to execute the DeleteReceiptFilter operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptFilter service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptFilterResult> deleteReceiptFilterAsync(final DeleteReceiptFilterRequest deleteReceiptFilterRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptFilterResult>() {
            public DeleteReceiptFilterResult call() throws Exception {
                return deleteReceiptFilter(deleteReceiptFilterRequest);
        }
    });
    }

    /**
     * <p>
     * Deletes the specified IP address filter.
     * </p>
     * <p>
     * For information about managing IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptFilterRequest Container for the necessary
     *           parameters to execute the DeleteReceiptFilter operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptFilter service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptFilterResult> deleteReceiptFilterAsync(
            final DeleteReceiptFilterRequest deleteReceiptFilterRequest,
            final AsyncHandler<DeleteReceiptFilterRequest, DeleteReceiptFilterResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptFilterResult>() {
            public DeleteReceiptFilterResult call() throws Exception {
              DeleteReceiptFilterResult result;
                try {
                result = deleteReceiptFilter(deleteReceiptFilterRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteReceiptFilterRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Sets the position of the specified receipt rule in the receipt rule
     * set.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setReceiptRulePositionRequest Container for the necessary
     *           parameters to execute the SetReceiptRulePosition operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetReceiptRulePosition service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetReceiptRulePositionResult> setReceiptRulePositionAsync(final SetReceiptRulePositionRequest setReceiptRulePositionRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetReceiptRulePositionResult>() {
            public SetReceiptRulePositionResult call() throws Exception {
                return setReceiptRulePosition(setReceiptRulePositionRequest);
        }
    });
    }

    /**
     * <p>
     * Sets the position of the specified receipt rule in the receipt rule
     * set.
     * </p>
     * <p>
     * For information about managing receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setReceiptRulePositionRequest Container for the necessary
     *           parameters to execute the SetReceiptRulePosition operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetReceiptRulePosition service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetReceiptRulePositionResult> setReceiptRulePositionAsync(
            final SetReceiptRulePositionRequest setReceiptRulePositionRequest,
            final AsyncHandler<SetReceiptRulePositionRequest, SetReceiptRulePositionResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetReceiptRulePositionResult>() {
            public SetReceiptRulePositionResult call() throws Exception {
              SetReceiptRulePositionResult result;
                try {
                result = setReceiptRulePosition(setReceiptRulePositionRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setReceiptRulePositionRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the details of the specified receipt rule.
     * </p>
     * <p>
     * For information about setting up receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeReceiptRuleRequest Container for the necessary
     *           parameters to execute the DescribeReceiptRule operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeReceiptRuleResult> describeReceiptRuleAsync(final DescribeReceiptRuleRequest describeReceiptRuleRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeReceiptRuleResult>() {
            public DescribeReceiptRuleResult call() throws Exception {
                return describeReceiptRule(describeReceiptRuleRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the details of the specified receipt rule.
     * </p>
     * <p>
     * For information about setting up receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeReceiptRuleRequest Container for the necessary
     *           parameters to execute the DescribeReceiptRule operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeReceiptRuleResult> describeReceiptRuleAsync(
            final DescribeReceiptRuleRequest describeReceiptRuleRequest,
            final AsyncHandler<DescribeReceiptRuleRequest, DescribeReceiptRuleResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeReceiptRuleResult>() {
            public DescribeReceiptRuleResult call() throws Exception {
              DescribeReceiptRuleResult result;
                try {
                result = describeReceiptRule(describeReceiptRuleRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(describeReceiptRuleRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Lists the IP address filters associated with your account.
     * </p>
     * <p>
     * For information about managing IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listReceiptFiltersRequest Container for the necessary
     *           parameters to execute the ListReceiptFilters operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ListReceiptFilters service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListReceiptFiltersResult> listReceiptFiltersAsync(final ListReceiptFiltersRequest listReceiptFiltersRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListReceiptFiltersResult>() {
            public ListReceiptFiltersResult call() throws Exception {
                return listReceiptFilters(listReceiptFiltersRequest);
        }
    });
    }

    /**
     * <p>
     * Lists the IP address filters associated with your account.
     * </p>
     * <p>
     * For information about managing IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listReceiptFiltersRequest Container for the necessary
     *           parameters to execute the ListReceiptFilters operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ListReceiptFilters service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListReceiptFiltersResult> listReceiptFiltersAsync(
            final ListReceiptFiltersRequest listReceiptFiltersRequest,
            final AsyncHandler<ListReceiptFiltersRequest, ListReceiptFiltersResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListReceiptFiltersResult>() {
            public ListReceiptFiltersResult call() throws Exception {
              ListReceiptFiltersResult result;
                try {
                result = listReceiptFilters(listReceiptFiltersRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(listReceiptFiltersRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Creates a new IP address filter.
     * </p>
     * <p>
     * For information about setting up IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptFilterRequest Container for the necessary
     *           parameters to execute the CreateReceiptFilter operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptFilter service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptFilterResult> createReceiptFilterAsync(final CreateReceiptFilterRequest createReceiptFilterRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptFilterResult>() {
            public CreateReceiptFilterResult call() throws Exception {
                return createReceiptFilter(createReceiptFilterRequest);
        }
    });
    }

    /**
     * <p>
     * Creates a new IP address filter.
     * </p>
     * <p>
     * For information about setting up IP address filters, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-ip-filters.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptFilterRequest Container for the necessary
     *           parameters to execute the CreateReceiptFilter operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptFilter service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptFilterResult> createReceiptFilterAsync(
            final CreateReceiptFilterRequest createReceiptFilterRequest,
            final AsyncHandler<CreateReceiptFilterRequest, CreateReceiptFilterResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptFilterResult>() {
            public CreateReceiptFilterResult call() throws Exception {
              CreateReceiptFilterResult result;
                try {
                result = createReceiptFilter(createReceiptFilterRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(createReceiptFilterRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the user's sending statistics. The result is a list of data
     * points, representing the last two weeks of sending activity.
     * </p>
     * <p>
     * Each data point in the list contains statistics for a 15-minute
     * interval.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getSendStatisticsRequest Container for the necessary parameters
     *           to execute the GetSendStatistics operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetSendStatistics service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetSendStatisticsResult> getSendStatisticsAsync(final GetSendStatisticsRequest getSendStatisticsRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetSendStatisticsResult>() {
            public GetSendStatisticsResult call() throws Exception {
                return getSendStatistics(getSendStatisticsRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the user's sending statistics. The result is a list of data
     * points, representing the last two weeks of sending activity.
     * </p>
     * <p>
     * Each data point in the list contains statistics for a 15-minute
     * interval.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getSendStatisticsRequest Container for the necessary parameters
     *           to execute the GetSendStatistics operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetSendStatistics service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetSendStatisticsResult> getSendStatisticsAsync(
            final GetSendStatisticsRequest getSendStatisticsRequest,
            final AsyncHandler<GetSendStatisticsRequest, GetSendStatisticsResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetSendStatisticsResult>() {
            public GetSendStatisticsResult call() throws Exception {
              GetSendStatisticsResult result;
                try {
                result = getSendStatistics(getSendStatisticsRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getSendStatisticsRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Sets the specified receipt rule set as the active receipt rule set.
     * </p>
     * <p>
     * <b>NOTE:</b>To disable your email-receiving through Amazon SES
     * completely, you can call this API with RuleSetName set to null.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setActiveReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the SetActiveReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetActiveReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetActiveReceiptRuleSetResult> setActiveReceiptRuleSetAsync(final SetActiveReceiptRuleSetRequest setActiveReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetActiveReceiptRuleSetResult>() {
            public SetActiveReceiptRuleSetResult call() throws Exception {
                return setActiveReceiptRuleSet(setActiveReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Sets the specified receipt rule set as the active receipt rule set.
     * </p>
     * <p>
     * <b>NOTE:</b>To disable your email-receiving through Amazon SES
     * completely, you can call this API with RuleSetName set to null.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param setActiveReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the SetActiveReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetActiveReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetActiveReceiptRuleSetResult> setActiveReceiptRuleSetAsync(
            final SetActiveReceiptRuleSetRequest setActiveReceiptRuleSetRequest,
            final AsyncHandler<SetActiveReceiptRuleSetRequest, SetActiveReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetActiveReceiptRuleSetResult>() {
            public SetActiveReceiptRuleSetResult call() throws Exception {
              SetActiveReceiptRuleSetResult result;
                try {
                result = setActiveReceiptRuleSet(setActiveReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setActiveReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the metadata and receipt rules for the receipt rule set that
     * is currently active.
     * </p>
     * <p>
     * For information about setting up receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeActiveReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DescribeActiveReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeActiveReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeActiveReceiptRuleSetResult> describeActiveReceiptRuleSetAsync(final DescribeActiveReceiptRuleSetRequest describeActiveReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeActiveReceiptRuleSetResult>() {
            public DescribeActiveReceiptRuleSetResult call() throws Exception {
                return describeActiveReceiptRuleSet(describeActiveReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the metadata and receipt rules for the receipt rule set that
     * is currently active.
     * </p>
     * <p>
     * For information about setting up receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param describeActiveReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DescribeActiveReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DescribeActiveReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DescribeActiveReceiptRuleSetResult> describeActiveReceiptRuleSetAsync(
            final DescribeActiveReceiptRuleSetRequest describeActiveReceiptRuleSetRequest,
            final AsyncHandler<DescribeActiveReceiptRuleSetRequest, DescribeActiveReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DescribeActiveReceiptRuleSetResult>() {
            public DescribeActiveReceiptRuleSetResult call() throws Exception {
              DescribeActiveReceiptRuleSetResult result;
                try {
                result = describeActiveReceiptRuleSet(describeActiveReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(describeActiveReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Sends an email message, with header and content specified by the
     * client. The <code>SendRawEmail</code> action is useful for sending
     * multipart MIME emails. The raw text of the message must comply with
     * Internet email standards; otherwise, the message cannot be sent.
     * </p>
     * <p>
     * There are several important points to know about
     * <code>SendRawEmail</code> :
     * </p>
     * 
     * <ul>
     * <li>You can only send email from verified email addresses and
     * domains; otherwise, you will get an "Email address not verified"
     * error. If your account is still in the Amazon SES sandbox, you must
     * also verify every recipient email address except for the recipients
     * provided by the Amazon SES mailbox simulator. For more information, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/verify-addresses-and-domains.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>The total size of the message cannot exceed 10 MB. This includes
     * any attachments that are part of the message.</li>
     * <li>Amazon SES has a limit on the total number of recipients per
     * message. The combined number of To:, CC: and BCC: email addresses
     * cannot exceed 50. If you need to send an email message to a larger
     * audience, you can divide your recipient list into groups of 50 or
     * fewer, and then call Amazon SES repeatedly to send the message to each
     * group.</li>
     * <li>The To:, CC:, and BCC: headers in the raw message can contain a
     * group list. Note that each recipient in a group list counts towards
     * the 50-recipient limit.</li>
     * <li>For every message that you send, the total number of recipients
     * (To:, CC: and BCC:) is counted against your sending quota - the
     * maximum number of emails you can send in a 24-hour period. For
     * information about your sending quota, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/manage-sending-limits.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>If you are using sending authorization to send on behalf of
     * another user, <code>SendRawEmail</code> enables you to specify the
     * cross-account identity for the email's "Source," "From," and
     * "Return-Path" parameters in one of two ways: you can pass optional
     * parameters <code>SourceArn</code> , <code>FromArn</code> , and/or
     * <code>ReturnPathArn</code> to the API, or you can include the
     * following X-headers in the header of your raw email:
     * <ul>
     * <li> <code>X-SES-SOURCE-ARN</code> </li>
     * <li> <code>X-SES-FROM-ARN</code> </li>
     * <li> <code>X-SES-RETURN-PATH-ARN</code> </li>
     * 
     * </ul>
     * <p>
     * <b>IMPORTANT:</b>Do not include these X-headers in the DKIM
     * signature, because they are removed by Amazon SES before sending the
     * email.
     * </p>
     * For the most common sending authorization use case, we recommend that
     * you specify the <code>SourceIdentityArn</code> and do not specify
     * either the <code>FromIdentityArn</code> or
     * <code>ReturnPathIdentityArn</code> . (The same note applies to the
     * corresponding X-headers.) If you only specify the
     * <code>SourceIdentityArn</code> , Amazon SES will simply set the "From"
     * address and the "Return Path" address to the identity specified in
     * <code>SourceIdentityArn</code> . For more information about sending
     * authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .</li>
     * 
     * </ul>
     *
     * @param sendRawEmailRequest Container for the necessary parameters to
     *           execute the SendRawEmail operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SendRawEmail service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendRawEmailResult> sendRawEmailAsync(final SendRawEmailRequest sendRawEmailRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendRawEmailResult>() {
            public SendRawEmailResult call() throws Exception {
                return sendRawEmail(sendRawEmailRequest);
        }
    });
    }

    /**
     * <p>
     * Sends an email message, with header and content specified by the
     * client. The <code>SendRawEmail</code> action is useful for sending
     * multipart MIME emails. The raw text of the message must comply with
     * Internet email standards; otherwise, the message cannot be sent.
     * </p>
     * <p>
     * There are several important points to know about
     * <code>SendRawEmail</code> :
     * </p>
     * 
     * <ul>
     * <li>You can only send email from verified email addresses and
     * domains; otherwise, you will get an "Email address not verified"
     * error. If your account is still in the Amazon SES sandbox, you must
     * also verify every recipient email address except for the recipients
     * provided by the Amazon SES mailbox simulator. For more information, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/verify-addresses-and-domains.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>The total size of the message cannot exceed 10 MB. This includes
     * any attachments that are part of the message.</li>
     * <li>Amazon SES has a limit on the total number of recipients per
     * message. The combined number of To:, CC: and BCC: email addresses
     * cannot exceed 50. If you need to send an email message to a larger
     * audience, you can divide your recipient list into groups of 50 or
     * fewer, and then call Amazon SES repeatedly to send the message to each
     * group.</li>
     * <li>The To:, CC:, and BCC: headers in the raw message can contain a
     * group list. Note that each recipient in a group list counts towards
     * the 50-recipient limit.</li>
     * <li>For every message that you send, the total number of recipients
     * (To:, CC: and BCC:) is counted against your sending quota - the
     * maximum number of emails you can send in a 24-hour period. For
     * information about your sending quota, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/manage-sending-limits.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>If you are using sending authorization to send on behalf of
     * another user, <code>SendRawEmail</code> enables you to specify the
     * cross-account identity for the email's "Source," "From," and
     * "Return-Path" parameters in one of two ways: you can pass optional
     * parameters <code>SourceArn</code> , <code>FromArn</code> , and/or
     * <code>ReturnPathArn</code> to the API, or you can include the
     * following X-headers in the header of your raw email:
     * <ul>
     * <li> <code>X-SES-SOURCE-ARN</code> </li>
     * <li> <code>X-SES-FROM-ARN</code> </li>
     * <li> <code>X-SES-RETURN-PATH-ARN</code> </li>
     * 
     * </ul>
     * <p>
     * <b>IMPORTANT:</b>Do not include these X-headers in the DKIM
     * signature, because they are removed by Amazon SES before sending the
     * email.
     * </p>
     * For the most common sending authorization use case, we recommend that
     * you specify the <code>SourceIdentityArn</code> and do not specify
     * either the <code>FromIdentityArn</code> or
     * <code>ReturnPathIdentityArn</code> . (The same note applies to the
     * corresponding X-headers.) If you only specify the
     * <code>SourceIdentityArn</code> , Amazon SES will simply set the "From"
     * address and the "Return Path" address to the identity specified in
     * <code>SourceIdentityArn</code> . For more information about sending
     * authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .</li>
     * 
     * </ul>
     *
     * @param sendRawEmailRequest Container for the necessary parameters to
     *           execute the SendRawEmail operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SendRawEmail service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendRawEmailResult> sendRawEmailAsync(
            final SendRawEmailRequest sendRawEmailRequest,
            final AsyncHandler<SendRawEmailRequest, SendRawEmailResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendRawEmailResult>() {
            public SendRawEmailResult call() throws Exception {
              SendRawEmailResult result;
                try {
                result = sendRawEmail(sendRawEmailRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(sendRawEmailRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Creates a receipt rule.
     * </p>
     * <p>
     * For information about setting up receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptRuleRequest Container for the necessary parameters
     *           to execute the CreateReceiptRule operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptRuleResult> createReceiptRuleAsync(final CreateReceiptRuleRequest createReceiptRuleRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptRuleResult>() {
            public CreateReceiptRuleResult call() throws Exception {
                return createReceiptRule(createReceiptRuleRequest);
        }
    });
    }

    /**
     * <p>
     * Creates a receipt rule.
     * </p>
     * <p>
     * For information about setting up receipt rules, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rules.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptRuleRequest Container for the necessary parameters
     *           to execute the CreateReceiptRule operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptRule service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptRuleResult> createReceiptRuleAsync(
            final CreateReceiptRuleRequest createReceiptRuleRequest,
            final AsyncHandler<CreateReceiptRuleRequest, CreateReceiptRuleResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptRuleResult>() {
            public CreateReceiptRuleResult call() throws Exception {
              CreateReceiptRuleResult result;
                try {
                result = createReceiptRule(createReceiptRuleRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(createReceiptRuleRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Deletes the specified receipt rule set and all of the receipt rules
     * it contains.
     * </p>
     * <p>
     * <b>NOTE:</b>The currently active rule set cannot be deleted.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DeleteReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptRuleSetResult> deleteReceiptRuleSetAsync(final DeleteReceiptRuleSetRequest deleteReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptRuleSetResult>() {
            public DeleteReceiptRuleSetResult call() throws Exception {
                return deleteReceiptRuleSet(deleteReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Deletes the specified receipt rule set and all of the receipt rules
     * it contains.
     * </p>
     * <p>
     * <b>NOTE:</b>The currently active rule set cannot be deleted.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the DeleteReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteReceiptRuleSetResult> deleteReceiptRuleSetAsync(
            final DeleteReceiptRuleSetRequest deleteReceiptRuleSetRequest,
            final AsyncHandler<DeleteReceiptRuleSetRequest, DeleteReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteReceiptRuleSetResult>() {
            public DeleteReceiptRuleSetResult call() throws Exception {
              DeleteReceiptRuleSetResult result;
                try {
                result = deleteReceiptRuleSet(deleteReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Adds or updates a sending authorization policy for the specified
     * identity (email address or domain).
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param putIdentityPolicyRequest Container for the necessary parameters
     *           to execute the PutIdentityPolicy operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         PutIdentityPolicy service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<PutIdentityPolicyResult> putIdentityPolicyAsync(final PutIdentityPolicyRequest putIdentityPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<PutIdentityPolicyResult>() {
            public PutIdentityPolicyResult call() throws Exception {
                return putIdentityPolicy(putIdentityPolicyRequest);
        }
    });
    }

    /**
     * <p>
     * Adds or updates a sending authorization policy for the specified
     * identity (email address or domain).
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param putIdentityPolicyRequest Container for the necessary parameters
     *           to execute the PutIdentityPolicy operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         PutIdentityPolicy service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<PutIdentityPolicyResult> putIdentityPolicyAsync(
            final PutIdentityPolicyRequest putIdentityPolicyRequest,
            final AsyncHandler<PutIdentityPolicyRequest, PutIdentityPolicyResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<PutIdentityPolicyResult>() {
            public PutIdentityPolicyResult call() throws Exception {
              PutIdentityPolicyResult result;
                try {
                result = putIdentityPolicy(putIdentityPolicyRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(putIdentityPolicyRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the requested sending authorization policies for the given
     * identity (email address or domain). The policies are returned as a map
     * of policy names to policy contents. You can retrieve a maximum of 20
     * policies at a time.
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getIdentityPoliciesRequest Container for the necessary
     *           parameters to execute the GetIdentityPolicies operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityPolicies service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityPoliciesResult> getIdentityPoliciesAsync(final GetIdentityPoliciesRequest getIdentityPoliciesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityPoliciesResult>() {
            public GetIdentityPoliciesResult call() throws Exception {
                return getIdentityPolicies(getIdentityPoliciesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the requested sending authorization policies for the given
     * identity (email address or domain). The policies are returned as a map
     * of policy names to policy contents. You can retrieve a maximum of 20
     * policies at a time.
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getIdentityPoliciesRequest Container for the necessary
     *           parameters to execute the GetIdentityPolicies operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityPolicies service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityPoliciesResult> getIdentityPoliciesAsync(
            final GetIdentityPoliciesRequest getIdentityPoliciesRequest,
            final AsyncHandler<GetIdentityPoliciesRequest, GetIdentityPoliciesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityPoliciesResult>() {
            public GetIdentityPoliciesResult call() throws Exception {
              GetIdentityPoliciesResult result;
                try {
                result = getIdentityPolicies(getIdentityPoliciesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getIdentityPoliciesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Enables or disables Easy DKIM signing of email sent from an identity:
     * </p>
     * 
     * <ul>
     * <li>If Easy DKIM signing is enabled for a domain name identity (e.g.,
     * <code>example.com</code> ), then Amazon SES will DKIM-sign all email
     * sent by addresses under that domain name (e.g.,
     * <code>user@example.com</code> ).</li>
     * <li>If Easy DKIM signing is enabled for an email address, then Amazon
     * SES will DKIM-sign all email sent by that email address.</li>
     * 
     * </ul>
     * <p>
     * For email addresses (e.g., <code>user@example.com</code> ), you can
     * only enable Easy DKIM signing if the corresponding domain (e.g.,
     * <code>example.com</code> ) has been set up for Easy DKIM using the AWS
     * Console or the <code>VerifyDomainDkim</code> action.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about Easy DKIM signing, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityDkimEnabledRequest Container for the necessary
     *           parameters to execute the SetIdentityDkimEnabled operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityDkimEnabled service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityDkimEnabledResult> setIdentityDkimEnabledAsync(final SetIdentityDkimEnabledRequest setIdentityDkimEnabledRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityDkimEnabledResult>() {
            public SetIdentityDkimEnabledResult call() throws Exception {
                return setIdentityDkimEnabled(setIdentityDkimEnabledRequest);
        }
    });
    }

    /**
     * <p>
     * Enables or disables Easy DKIM signing of email sent from an identity:
     * </p>
     * 
     * <ul>
     * <li>If Easy DKIM signing is enabled for a domain name identity (e.g.,
     * <code>example.com</code> ), then Amazon SES will DKIM-sign all email
     * sent by addresses under that domain name (e.g.,
     * <code>user@example.com</code> ).</li>
     * <li>If Easy DKIM signing is enabled for an email address, then Amazon
     * SES will DKIM-sign all email sent by that email address.</li>
     * 
     * </ul>
     * <p>
     * For email addresses (e.g., <code>user@example.com</code> ), you can
     * only enable Easy DKIM signing if the corresponding domain (e.g.,
     * <code>example.com</code> ) has been set up for Easy DKIM using the AWS
     * Console or the <code>VerifyDomainDkim</code> action.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about Easy DKIM signing, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/easy-dkim.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityDkimEnabledRequest Container for the necessary
     *           parameters to execute the SetIdentityDkimEnabled operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityDkimEnabled service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityDkimEnabledResult> setIdentityDkimEnabledAsync(
            final SetIdentityDkimEnabledRequest setIdentityDkimEnabledRequest,
            final AsyncHandler<SetIdentityDkimEnabledRequest, SetIdentityDkimEnabledResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityDkimEnabledResult>() {
            public SetIdentityDkimEnabledResult call() throws Exception {
              SetIdentityDkimEnabledResult result;
                try {
                result = setIdentityDkimEnabled(setIdentityDkimEnabledRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setIdentityDkimEnabledRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the user's current sending limits.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getSendQuotaRequest Container for the necessary parameters to
     *           execute the GetSendQuota operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetSendQuota service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetSendQuotaResult> getSendQuotaAsync(final GetSendQuotaRequest getSendQuotaRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetSendQuotaResult>() {
            public GetSendQuotaResult call() throws Exception {
                return getSendQuota(getSendQuotaRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the user's current sending limits.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param getSendQuotaRequest Container for the necessary parameters to
     *           execute the GetSendQuota operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetSendQuota service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetSendQuotaResult> getSendQuotaAsync(
            final GetSendQuotaRequest getSendQuotaRequest,
            final AsyncHandler<GetSendQuotaRequest, GetSendQuotaResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetSendQuotaResult>() {
            public GetSendQuotaResult call() throws Exception {
              GetSendQuotaResult result;
                try {
                result = getSendQuota(getSendQuotaRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getSendQuotaRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Deletes the specified sending authorization policy for the given
     * identity (email address or domain). This API returns successfully even
     * if a policy with the specified name does not exist.
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteIdentityPolicyRequest Container for the necessary
     *           parameters to execute the DeleteIdentityPolicy operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteIdentityPolicy service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteIdentityPolicyResult> deleteIdentityPolicyAsync(final DeleteIdentityPolicyRequest deleteIdentityPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteIdentityPolicyResult>() {
            public DeleteIdentityPolicyResult call() throws Exception {
                return deleteIdentityPolicy(deleteIdentityPolicyRequest);
        }
    });
    }

    /**
     * <p>
     * Deletes the specified sending authorization policy for the given
     * identity (email address or domain). This API returns successfully even
     * if a policy with the specified name does not exist.
     * </p>
     * <p>
     * <b>NOTE:</b>This API is for the identity owner only. If you have not
     * verified the identity, this API will return an error.
     * </p>
     * <p>
     * Sending authorization is a feature that enables an identity owner to
     * authorize other senders to use its identities. For information about
     * using sending authorization, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/sending-authorization.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteIdentityPolicyRequest Container for the necessary
     *           parameters to execute the DeleteIdentityPolicy operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteIdentityPolicy service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<DeleteIdentityPolicyResult> deleteIdentityPolicyAsync(
            final DeleteIdentityPolicyRequest deleteIdentityPolicyRequest,
            final AsyncHandler<DeleteIdentityPolicyRequest, DeleteIdentityPolicyResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<DeleteIdentityPolicyResult>() {
            public DeleteIdentityPolicyResult call() throws Exception {
              DeleteIdentityPolicyResult result;
                try {
                result = deleteIdentityPolicy(deleteIdentityPolicyRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteIdentityPolicyRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Lists the receipt rule sets that exist under your AWS account. If
     * there are additional receipt rule sets to be retrieved, you will
     * receive a <code>NextToken</code> that you can provide to the next call
     * to <code>ListReceiptRuleSets</code> to retrieve the additional
     * entries.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listReceiptRuleSetsRequest Container for the necessary
     *           parameters to execute the ListReceiptRuleSets operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         ListReceiptRuleSets service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListReceiptRuleSetsResult> listReceiptRuleSetsAsync(final ListReceiptRuleSetsRequest listReceiptRuleSetsRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListReceiptRuleSetsResult>() {
            public ListReceiptRuleSetsResult call() throws Exception {
                return listReceiptRuleSets(listReceiptRuleSetsRequest);
        }
    });
    }

    /**
     * <p>
     * Lists the receipt rule sets that exist under your AWS account. If
     * there are additional receipt rule sets to be retrieved, you will
     * receive a <code>NextToken</code> that you can provide to the next call
     * to <code>ListReceiptRuleSets</code> to retrieve the additional
     * entries.
     * </p>
     * <p>
     * For information about managing receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-managing-receipt-rule-sets.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param listReceiptRuleSetsRequest Container for the necessary
     *           parameters to execute the ListReceiptRuleSets operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         ListReceiptRuleSets service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<ListReceiptRuleSetsResult> listReceiptRuleSetsAsync(
            final ListReceiptRuleSetsRequest listReceiptRuleSetsRequest,
            final AsyncHandler<ListReceiptRuleSetsRequest, ListReceiptRuleSetsResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<ListReceiptRuleSetsResult>() {
            public ListReceiptRuleSetsResult call() throws Exception {
              ListReceiptRuleSetsResult result;
                try {
                result = listReceiptRuleSets(listReceiptRuleSetsRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(listReceiptRuleSetsRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Composes an email message based on input data, and then immediately
     * queues the message for sending.
     * </p>
     * <p>
     * There are several important points to know about
     * <code>SendEmail</code> :
     * </p>
     * 
     * <ul>
     * <li>You can only send email from verified email addresses and
     * domains; otherwise, you will get an "Email address not verified"
     * error. If your account is still in the Amazon SES sandbox, you must
     * also verify every recipient email address except for the recipients
     * provided by the Amazon SES mailbox simulator. For more information, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/verify-addresses-and-domains.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>The total size of the message cannot exceed 10 MB. This includes
     * any attachments that are part of the message.</li>
     * <li>Amazon SES has a limit on the total number of recipients per
     * message. The combined number of To:, CC: and BCC: email addresses
     * cannot exceed 50. If you need to send an email message to a larger
     * audience, you can divide your recipient list into groups of 50 or
     * fewer, and then call Amazon SES repeatedly to send the message to each
     * group.</li>
     * <li>For every message that you send, the total number of recipients
     * (To:, CC: and BCC:) is counted against your sending quota - the
     * maximum number of emails you can send in a 24-hour period. For
     * information about your sending quota, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/manage-sending-limits.html"> Amazon SES Developer Guide </a>
     * .</li>
     * 
     * </ul>
     *
     * @param sendEmailRequest Container for the necessary parameters to
     *           execute the SendEmail operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SendEmail service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendEmailResult> sendEmailAsync(final SendEmailRequest sendEmailRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendEmailResult>() {
            public SendEmailResult call() throws Exception {
                return sendEmail(sendEmailRequest);
        }
    });
    }

    /**
     * <p>
     * Composes an email message based on input data, and then immediately
     * queues the message for sending.
     * </p>
     * <p>
     * There are several important points to know about
     * <code>SendEmail</code> :
     * </p>
     * 
     * <ul>
     * <li>You can only send email from verified email addresses and
     * domains; otherwise, you will get an "Email address not verified"
     * error. If your account is still in the Amazon SES sandbox, you must
     * also verify every recipient email address except for the recipients
     * provided by the Amazon SES mailbox simulator. For more information, go
     * to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/verify-addresses-and-domains.html"> Amazon SES Developer Guide </a>
     * .</li>
     * <li>The total size of the message cannot exceed 10 MB. This includes
     * any attachments that are part of the message.</li>
     * <li>Amazon SES has a limit on the total number of recipients per
     * message. The combined number of To:, CC: and BCC: email addresses
     * cannot exceed 50. If you need to send an email message to a larger
     * audience, you can divide your recipient list into groups of 50 or
     * fewer, and then call Amazon SES repeatedly to send the message to each
     * group.</li>
     * <li>For every message that you send, the total number of recipients
     * (To:, CC: and BCC:) is counted against your sending quota - the
     * maximum number of emails you can send in a 24-hour period. For
     * information about your sending quota, go to the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/manage-sending-limits.html"> Amazon SES Developer Guide </a>
     * .</li>
     * 
     * </ul>
     *
     * @param sendEmailRequest Container for the necessary parameters to
     *           execute the SendEmail operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SendEmail service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendEmailResult> sendEmailAsync(
            final SendEmailRequest sendEmailRequest,
            final AsyncHandler<SendEmailRequest, SendEmailResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendEmailResult>() {
            public SendEmailResult call() throws Exception {
              SendEmailResult result;
                try {
                result = sendEmail(sendEmailRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(sendEmailRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Verifies a domain.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyDomainIdentityRequest Container for the necessary
     *           parameters to execute the VerifyDomainIdentity operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyDomainIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyDomainIdentityResult> verifyDomainIdentityAsync(final VerifyDomainIdentityRequest verifyDomainIdentityRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyDomainIdentityResult>() {
            public VerifyDomainIdentityResult call() throws Exception {
                return verifyDomainIdentity(verifyDomainIdentityRequest);
        }
    });
    }

    /**
     * <p>
     * Verifies a domain.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param verifyDomainIdentityRequest Container for the necessary
     *           parameters to execute the VerifyDomainIdentity operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         VerifyDomainIdentity service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<VerifyDomainIdentityResult> verifyDomainIdentityAsync(
            final VerifyDomainIdentityRequest verifyDomainIdentityRequest,
            final AsyncHandler<VerifyDomainIdentityRequest, VerifyDomainIdentityResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<VerifyDomainIdentityResult>() {
            public VerifyDomainIdentityResult call() throws Exception {
              VerifyDomainIdentityResult result;
                try {
                result = verifyDomainIdentity(verifyDomainIdentityRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(verifyDomainIdentityRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Given an identity (email address or domain), sets the Amazon Simple
     * Notification Service (Amazon SNS) topic to which Amazon SES will
     * publish bounce, complaint, and/or delivery notifications for emails
     * sent with that identity as the <code>Source</code> .
     * </p>
     * <p>
     * <b>NOTE:</b>Unless feedback forwarding is enabled, you must specify
     * Amazon SNS topics for bounce and complaint notifications. For more
     * information, see SetIdentityFeedbackForwardingEnabled.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about feedback notification, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityNotificationTopicRequest Container for the necessary
     *           parameters to execute the SetIdentityNotificationTopic operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityNotificationTopic service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityNotificationTopicResult> setIdentityNotificationTopicAsync(final SetIdentityNotificationTopicRequest setIdentityNotificationTopicRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityNotificationTopicResult>() {
            public SetIdentityNotificationTopicResult call() throws Exception {
                return setIdentityNotificationTopic(setIdentityNotificationTopicRequest);
        }
    });
    }

    /**
     * <p>
     * Given an identity (email address or domain), sets the Amazon Simple
     * Notification Service (Amazon SNS) topic to which Amazon SES will
     * publish bounce, complaint, and/or delivery notifications for emails
     * sent with that identity as the <code>Source</code> .
     * </p>
     * <p>
     * <b>NOTE:</b>Unless feedback forwarding is enabled, you must specify
     * Amazon SNS topics for bounce and complaint notifications. For more
     * information, see SetIdentityFeedbackForwardingEnabled.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     * <p>
     * For more information about feedback notification, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/notifications.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     *
     * @param setIdentityNotificationTopicRequest Container for the necessary
     *           parameters to execute the SetIdentityNotificationTopic operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SetIdentityNotificationTopic service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SetIdentityNotificationTopicResult> setIdentityNotificationTopicAsync(
            final SetIdentityNotificationTopicRequest setIdentityNotificationTopicRequest,
            final AsyncHandler<SetIdentityNotificationTopicRequest, SetIdentityNotificationTopicResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SetIdentityNotificationTopicResult>() {
            public SetIdentityNotificationTopicResult call() throws Exception {
              SetIdentityNotificationTopicResult result;
                try {
                result = setIdentityNotificationTopic(setIdentityNotificationTopicRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(setIdentityNotificationTopicRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Deletes the specified email address from the list of verified
     * addresses.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The DeleteVerifiedEmailAddress action is deprecated
     * as of the May 15, 2012 release of Domain Verification. The
     * DeleteIdentity action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteVerifiedEmailAddressRequest Container for the necessary
     *           parameters to execute the DeleteVerifiedEmailAddress operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteVerifiedEmailAddress service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<Void> deleteVerifiedEmailAddressAsync(final DeleteVerifiedEmailAddressRequest deleteVerifiedEmailAddressRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
                deleteVerifiedEmailAddress(deleteVerifiedEmailAddressRequest);
                return null;
        }
    });
    }

    /**
     * <p>
     * Deletes the specified email address from the list of verified
     * addresses.
     * </p>
     * <p>
     * <b>IMPORTANT:</b>The DeleteVerifiedEmailAddress action is deprecated
     * as of the May 15, 2012 release of Domain Verification. The
     * DeleteIdentity action is now preferred.
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param deleteVerifiedEmailAddressRequest Container for the necessary
     *           parameters to execute the DeleteVerifiedEmailAddress operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         DeleteVerifiedEmailAddress service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<Void> deleteVerifiedEmailAddressAsync(
            final DeleteVerifiedEmailAddressRequest deleteVerifiedEmailAddressRequest,
            final AsyncHandler<DeleteVerifiedEmailAddressRequest, Void> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<Void>() {
            public Void call() throws Exception {
              try {
                deleteVerifiedEmailAddress(deleteVerifiedEmailAddressRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(deleteVerifiedEmailAddressRequest, null);
                 return null;
        }
    });
    }
    
    /**
     * <p>
     * Creates a receipt rule set by cloning an existing one. All receipt
     * rules and configurations are copied to the new receipt rule set and
     * are completely independent of the source rule set.
     * </p>
     * <p>
     * For information about setting up rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param cloneReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the CloneReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         CloneReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CloneReceiptRuleSetResult> cloneReceiptRuleSetAsync(final CloneReceiptRuleSetRequest cloneReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CloneReceiptRuleSetResult>() {
            public CloneReceiptRuleSetResult call() throws Exception {
                return cloneReceiptRuleSet(cloneReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Creates a receipt rule set by cloning an existing one. All receipt
     * rules and configurations are copied to the new receipt rule set and
     * are completely independent of the source rule set.
     * </p>
     * <p>
     * For information about setting up rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param cloneReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the CloneReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         CloneReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CloneReceiptRuleSetResult> cloneReceiptRuleSetAsync(
            final CloneReceiptRuleSetRequest cloneReceiptRuleSetRequest,
            final AsyncHandler<CloneReceiptRuleSetRequest, CloneReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CloneReceiptRuleSetResult>() {
            public CloneReceiptRuleSetResult call() throws Exception {
              CloneReceiptRuleSetResult result;
                try {
                result = cloneReceiptRuleSet(cloneReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(cloneReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Returns the custom MAIL FROM attributes for a list of identities
     * (email addresses and/or domains).
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * custom MAIL FROM attributes for up to 100 identities at a time.
     * </p>
     *
     * @param getIdentityMailFromDomainAttributesRequest Container for the
     *           necessary parameters to execute the
     *           GetIdentityMailFromDomainAttributes operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityMailFromDomainAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityMailFromDomainAttributesResult> getIdentityMailFromDomainAttributesAsync(final GetIdentityMailFromDomainAttributesRequest getIdentityMailFromDomainAttributesRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityMailFromDomainAttributesResult>() {
            public GetIdentityMailFromDomainAttributesResult call() throws Exception {
                return getIdentityMailFromDomainAttributes(getIdentityMailFromDomainAttributesRequest);
        }
    });
    }

    /**
     * <p>
     * Returns the custom MAIL FROM attributes for a list of identities
     * (email addresses and/or domains).
     * </p>
     * <p>
     * This action is throttled at one request per second and can only get
     * custom MAIL FROM attributes for up to 100 identities at a time.
     * </p>
     *
     * @param getIdentityMailFromDomainAttributesRequest Container for the
     *           necessary parameters to execute the
     *           GetIdentityMailFromDomainAttributes operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         GetIdentityMailFromDomainAttributes service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<GetIdentityMailFromDomainAttributesResult> getIdentityMailFromDomainAttributesAsync(
            final GetIdentityMailFromDomainAttributesRequest getIdentityMailFromDomainAttributesRequest,
            final AsyncHandler<GetIdentityMailFromDomainAttributesRequest, GetIdentityMailFromDomainAttributesResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<GetIdentityMailFromDomainAttributesResult>() {
            public GetIdentityMailFromDomainAttributesResult call() throws Exception {
              GetIdentityMailFromDomainAttributesResult result;
                try {
                result = getIdentityMailFromDomainAttributes(getIdentityMailFromDomainAttributesRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(getIdentityMailFromDomainAttributesRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Generates and sends a bounce message to the sender of an email you
     * received through Amazon SES. You can only use this API on an email up
     * to 24 hours after you receive it.
     * </p>
     * <p>
     * <b>NOTE:</b>You cannot use this API to send generic bounces for mail
     * that was not received by Amazon SES.
     * </p>
     * <p>
     * For information about receiving email through Amazon SES, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param sendBounceRequest Container for the necessary parameters to
     *           execute the SendBounce operation on AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         SendBounce service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendBounceResult> sendBounceAsync(final SendBounceRequest sendBounceRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendBounceResult>() {
            public SendBounceResult call() throws Exception {
                return sendBounce(sendBounceRequest);
        }
    });
    }

    /**
     * <p>
     * Generates and sends a bounce message to the sender of an email you
     * received through Amazon SES. You can only use this API on an email up
     * to 24 hours after you receive it.
     * </p>
     * <p>
     * <b>NOTE:</b>You cannot use this API to send generic bounces for mail
     * that was not received by Amazon SES.
     * </p>
     * <p>
     * For information about receiving email through Amazon SES, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param sendBounceRequest Container for the necessary parameters to
     *           execute the SendBounce operation on AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         SendBounce service method, as returned by AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<SendBounceResult> sendBounceAsync(
            final SendBounceRequest sendBounceRequest,
            final AsyncHandler<SendBounceRequest, SendBounceResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<SendBounceResult>() {
            public SendBounceResult call() throws Exception {
              SendBounceResult result;
                try {
                result = sendBounce(sendBounceRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(sendBounceRequest, result);
                 return result;
        }
    });
    }
    
    /**
     * <p>
     * Creates an empty receipt rule set.
     * </p>
     * <p>
     * For information about setting up receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the CreateReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptRuleSetResult> createReceiptRuleSetAsync(final CreateReceiptRuleSetRequest createReceiptRuleSetRequest) 
            throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptRuleSetResult>() {
            public CreateReceiptRuleSetResult call() throws Exception {
                return createReceiptRuleSet(createReceiptRuleSetRequest);
        }
    });
    }

    /**
     * <p>
     * Creates an empty receipt rule set.
     * </p>
     * <p>
     * For information about setting up receipt rule sets, see the
     * <a href="http://docs.aws.amazon.com/ses/latest/DeveloperGuide/receiving-email-receipt-rule-set.html"> Amazon SES Developer Guide </a>
     * .
     * </p>
     * <p>
     * This action is throttled at one request per second.
     * </p>
     *
     * @param createReceiptRuleSetRequest Container for the necessary
     *           parameters to execute the CreateReceiptRuleSet operation on
     *           AmazonSimpleEmailService.
     * @param asyncHandler Asynchronous callback handler for events in the
     *           life-cycle of the request. Users could provide the implementation of
     *           the four callback methods in this interface to process the operation
     *           result or handle the exception.
     * 
     * @return A Java Future object containing the response from the
     *         CreateReceiptRuleSet service method, as returned by
     *         AmazonSimpleEmailService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonSimpleEmailService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public Future<CreateReceiptRuleSetResult> createReceiptRuleSetAsync(
            final CreateReceiptRuleSetRequest createReceiptRuleSetRequest,
            final AsyncHandler<CreateReceiptRuleSetRequest, CreateReceiptRuleSetResult> asyncHandler)
                    throws AmazonServiceException, AmazonClientException {
        return executorService.submit(new Callable<CreateReceiptRuleSetResult>() {
            public CreateReceiptRuleSetResult call() throws Exception {
              CreateReceiptRuleSetResult result;
                try {
                result = createReceiptRuleSet(createReceiptRuleSetRequest);
              } catch (Exception ex) {
                  asyncHandler.onError(ex);
            throw ex;
              }
              asyncHandler.onSuccess(createReceiptRuleSetRequest, result);
                 return result;
        }
    });
    }
    
}
        