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
package com.amazonaws.services.autoscaling.model;

import java.io.Serializable;

import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.autoscaling.AmazonAutoScaling#updateAutoScalingGroup(UpdateAutoScalingGroupRequest) UpdateAutoScalingGroup operation}.
 * <p>
 * Updates the configuration for the specified Auto Scaling group.
 * </p>
 * <p>
 * To update an Auto Scaling group with a launch configuration with
 * <code>InstanceMonitoring</code> set to <code>False</code> ,
 * you must first disable the collection of group metrics.
 * Otherwise, you will get an error. If you have previously enabled the
 * collection of group metrics, you can disable it using
 * DisableMetricsCollection.
 * </p>
 * <p>
 * The new settings are registered upon the completion of this call. Any
 * launch configuration settings take effect on any triggers after this
 * call returns. Scaling activities that are currently in progress aren't
 * affected.
 * </p>
 * <p>
 * Note the following:
 * </p>
 * 
 * <ul>
 * <li> <p>
 * If you specify a new value for <code>MinSize</code> without specifying
 * a value for <code>DesiredCapacity</code> ,
 * and the new <code>MinSize</code> is larger than
 * the current size of the group, we implicitly call SetDesiredCapacity
 * to set the size of the group to the new value of <code>MinSize</code>
 * .
 * </p>
 * </li>
 * <li> <p>
 * If you specify a new value for <code>MaxSize</code> without specifying
 * a value for <code>DesiredCapacity</code> ,
 * and the new <code>MaxSize</code> is smaller
 * than the current size of the group, we implicitly call
 * SetDesiredCapacity to set the size of the group to the new value of
 * <code>MaxSize</code> .
 * </p>
 * </li>
 * <li> <p>
 * All other optional parameters are left unchanged if not specified.
 * </p>
 * </li>
 * 
 * </ul>
 *
 * @see com.amazonaws.services.autoscaling.AmazonAutoScaling#updateAutoScalingGroup(UpdateAutoScalingGroupRequest)
 */
public class UpdateAutoScalingGroupRequest extends AmazonWebServiceRequest implements Serializable {

    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     */
    private String autoScalingGroupName;

    /**
     * The name of the launch configuration.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     */
    private String launchConfigurationName;

    /**
     * The minimum size of the Auto Scaling group.
     */
    private Integer minSize;

    /**
     * The maximum size of the Auto Scaling group.
     */
    private Integer maxSize;

    /**
     * The number of EC2 instances that should be running in the Auto Scaling
     * group. This number must be greater than or equal to the minimum size
     * of the group and less than or equal to the maximum size of the group.
     */
    private Integer desiredCapacity;

    /**
     * The amount of time, in seconds, after a scaling activity completes
     * before another scaling activity can start. The default is 300. <p>For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     * Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     */
    private Integer defaultCooldown;

    /**
     * One or more Availability Zones for the group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     */
    private com.amazonaws.internal.ListWithAutoConstructFlag<String> availabilityZones;

    /**
     * The service to use for the health checks. The valid values are
     * <code>EC2</code> and <code>ELB</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 32<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     */
    private String healthCheckType;

    /**
     * The amount of time, in seconds, that Auto Scaling waits before
     * checking the health status of an EC2 instance that has come into
     * service. The default is 300. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     * Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     * Guide</i>.
     */
    private Integer healthCheckGracePeriod;

    /**
     * The name of the placement group into which you'll launch your
     * instances, if any. For more information, see <a
     * href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     * Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     */
    private String placementGroup;

    /**
     * The ID of the subnet, if you are launching into a VPC. You can specify
     * several subnets in a comma-separated list. <p>When you specify
     * <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     * ensure that the subnets' Availability Zones match the values you
     * specify for <code>AvailabilityZones</code>. <p>For more information,
     * see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     * Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     * Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     */
    private String vPCZoneIdentifier;

    /**
     * A standalone termination policy or a list of termination policies used
     * to select the instance to terminate. The policies are executed in the
     * order that they are listed. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     * a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     * Scaling Developer Guide</i>.
     */
    private com.amazonaws.internal.ListWithAutoConstructFlag<String> terminationPolicies;

    /**
     * Indicates whether newly launched instances are protected from
     * termination by Auto Scaling when scaling in.
     */
    private Boolean newInstancesProtectedFromScaleIn;

    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @return The name of the Auto Scaling group.
     */
    public String getAutoScalingGroupName() {
        return autoScalingGroupName;
    }
    
    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the Auto Scaling group.
     */
    public void setAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
    }
    
    /**
     * The name of the Auto Scaling group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the Auto Scaling group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
        return this;
    }

    /**
     * The name of the launch configuration.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @return The name of the launch configuration.
     */
    public String getLaunchConfigurationName() {
        return launchConfigurationName;
    }
    
    /**
     * The name of the launch configuration.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param launchConfigurationName The name of the launch configuration.
     */
    public void setLaunchConfigurationName(String launchConfigurationName) {
        this.launchConfigurationName = launchConfigurationName;
    }
    
    /**
     * The name of the launch configuration.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param launchConfigurationName The name of the launch configuration.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withLaunchConfigurationName(String launchConfigurationName) {
        this.launchConfigurationName = launchConfigurationName;
        return this;
    }

    /**
     * The minimum size of the Auto Scaling group.
     *
     * @return The minimum size of the Auto Scaling group.
     */
    public Integer getMinSize() {
        return minSize;
    }
    
    /**
     * The minimum size of the Auto Scaling group.
     *
     * @param minSize The minimum size of the Auto Scaling group.
     */
    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }
    
    /**
     * The minimum size of the Auto Scaling group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param minSize The minimum size of the Auto Scaling group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withMinSize(Integer minSize) {
        this.minSize = minSize;
        return this;
    }

    /**
     * The maximum size of the Auto Scaling group.
     *
     * @return The maximum size of the Auto Scaling group.
     */
    public Integer getMaxSize() {
        return maxSize;
    }
    
    /**
     * The maximum size of the Auto Scaling group.
     *
     * @param maxSize The maximum size of the Auto Scaling group.
     */
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    
    /**
     * The maximum size of the Auto Scaling group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param maxSize The maximum size of the Auto Scaling group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * The number of EC2 instances that should be running in the Auto Scaling
     * group. This number must be greater than or equal to the minimum size
     * of the group and less than or equal to the maximum size of the group.
     *
     * @return The number of EC2 instances that should be running in the Auto Scaling
     *         group. This number must be greater than or equal to the minimum size
     *         of the group and less than or equal to the maximum size of the group.
     */
    public Integer getDesiredCapacity() {
        return desiredCapacity;
    }
    
    /**
     * The number of EC2 instances that should be running in the Auto Scaling
     * group. This number must be greater than or equal to the minimum size
     * of the group and less than or equal to the maximum size of the group.
     *
     * @param desiredCapacity The number of EC2 instances that should be running in the Auto Scaling
     *         group. This number must be greater than or equal to the minimum size
     *         of the group and less than or equal to the maximum size of the group.
     */
    public void setDesiredCapacity(Integer desiredCapacity) {
        this.desiredCapacity = desiredCapacity;
    }
    
    /**
     * The number of EC2 instances that should be running in the Auto Scaling
     * group. This number must be greater than or equal to the minimum size
     * of the group and less than or equal to the maximum size of the group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param desiredCapacity The number of EC2 instances that should be running in the Auto Scaling
     *         group. This number must be greater than or equal to the minimum size
     *         of the group and less than or equal to the maximum size of the group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withDesiredCapacity(Integer desiredCapacity) {
        this.desiredCapacity = desiredCapacity;
        return this;
    }

    /**
     * The amount of time, in seconds, after a scaling activity completes
     * before another scaling activity can start. The default is 300. <p>For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     * Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     *
     * @return The amount of time, in seconds, after a scaling activity completes
     *         before another scaling activity can start. The default is 300. <p>For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     *         Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     */
    public Integer getDefaultCooldown() {
        return defaultCooldown;
    }
    
    /**
     * The amount of time, in seconds, after a scaling activity completes
     * before another scaling activity can start. The default is 300. <p>For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     * Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     *
     * @param defaultCooldown The amount of time, in seconds, after a scaling activity completes
     *         before another scaling activity can start. The default is 300. <p>For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     *         Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     */
    public void setDefaultCooldown(Integer defaultCooldown) {
        this.defaultCooldown = defaultCooldown;
    }
    
    /**
     * The amount of time, in seconds, after a scaling activity completes
     * before another scaling activity can start. The default is 300. <p>For
     * more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     * Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param defaultCooldown The amount of time, in seconds, after a scaling activity completes
     *         before another scaling activity can start. The default is 300. <p>For
     *         more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/Cooldown.html">Understanding
     *         Auto Scaling Cooldowns</a> in the <i>Auto Scaling Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withDefaultCooldown(Integer defaultCooldown) {
        this.defaultCooldown = defaultCooldown;
        return this;
    }

    /**
     * One or more Availability Zones for the group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @return One or more Availability Zones for the group.
     */
    public java.util.List<String> getAvailabilityZones() {
        if (availabilityZones == null) {
              availabilityZones = new com.amazonaws.internal.ListWithAutoConstructFlag<String>();
              availabilityZones.setAutoConstruct(true);
        }
        return availabilityZones;
    }
    
    /**
     * One or more Availability Zones for the group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param availabilityZones One or more Availability Zones for the group.
     */
    public void setAvailabilityZones(java.util.Collection<String> availabilityZones) {
        if (availabilityZones == null) {
            this.availabilityZones = null;
            return;
        }
        com.amazonaws.internal.ListWithAutoConstructFlag<String> availabilityZonesCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(availabilityZones.size());
        availabilityZonesCopy.addAll(availabilityZones);
        this.availabilityZones = availabilityZonesCopy;
    }
    
    /**
     * One or more Availability Zones for the group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param availabilityZones One or more Availability Zones for the group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withAvailabilityZones(String... availabilityZones) {
        if (getAvailabilityZones() == null) setAvailabilityZones(new java.util.ArrayList<String>(availabilityZones.length));
        for (String value : availabilityZones) {
            getAvailabilityZones().add(value);
        }
        return this;
    }
    
    /**
     * One or more Availability Zones for the group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param availabilityZones One or more Availability Zones for the group.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withAvailabilityZones(java.util.Collection<String> availabilityZones) {
        if (availabilityZones == null) {
            this.availabilityZones = null;
        } else {
            com.amazonaws.internal.ListWithAutoConstructFlag<String> availabilityZonesCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(availabilityZones.size());
            availabilityZonesCopy.addAll(availabilityZones);
            this.availabilityZones = availabilityZonesCopy;
        }

        return this;
    }

    /**
     * The service to use for the health checks. The valid values are
     * <code>EC2</code> and <code>ELB</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 32<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @return The service to use for the health checks. The valid values are
     *         <code>EC2</code> and <code>ELB</code>.
     */
    public String getHealthCheckType() {
        return healthCheckType;
    }
    
    /**
     * The service to use for the health checks. The valid values are
     * <code>EC2</code> and <code>ELB</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 32<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param healthCheckType The service to use for the health checks. The valid values are
     *         <code>EC2</code> and <code>ELB</code>.
     */
    public void setHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
    }
    
    /**
     * The service to use for the health checks. The valid values are
     * <code>EC2</code> and <code>ELB</code>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 32<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param healthCheckType The service to use for the health checks. The valid values are
     *         <code>EC2</code> and <code>ELB</code>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
        return this;
    }

    /**
     * The amount of time, in seconds, that Auto Scaling waits before
     * checking the health status of an EC2 instance that has come into
     * service. The default is 300. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     * Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     * Guide</i>.
     *
     * @return The amount of time, in seconds, that Auto Scaling waits before
     *         checking the health status of an EC2 instance that has come into
     *         service. The default is 300. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     *         Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     *         Guide</i>.
     */
    public Integer getHealthCheckGracePeriod() {
        return healthCheckGracePeriod;
    }
    
    /**
     * The amount of time, in seconds, that Auto Scaling waits before
     * checking the health status of an EC2 instance that has come into
     * service. The default is 300. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     * Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     * Guide</i>.
     *
     * @param healthCheckGracePeriod The amount of time, in seconds, that Auto Scaling waits before
     *         checking the health status of an EC2 instance that has come into
     *         service. The default is 300. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     *         Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     *         Guide</i>.
     */
    public void setHealthCheckGracePeriod(Integer healthCheckGracePeriod) {
        this.healthCheckGracePeriod = healthCheckGracePeriod;
    }
    
    /**
     * The amount of time, in seconds, that Auto Scaling waits before
     * checking the health status of an EC2 instance that has come into
     * service. The default is 300. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     * Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     * Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param healthCheckGracePeriod The amount of time, in seconds, that Auto Scaling waits before
     *         checking the health status of an EC2 instance that has come into
     *         service. The default is 300. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/healthcheck.html">Health
     *         Checks For Auto Scaling Instances</a> in the <i>Auto Scaling Developer
     *         Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withHealthCheckGracePeriod(Integer healthCheckGracePeriod) {
        this.healthCheckGracePeriod = healthCheckGracePeriod;
        return this;
    }

    /**
     * The name of the placement group into which you'll launch your
     * instances, if any. For more information, see <a
     * href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     * Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @return The name of the placement group into which you'll launch your
     *         instances, if any. For more information, see <a
     *         href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     *         Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     */
    public String getPlacementGroup() {
        return placementGroup;
    }
    
    /**
     * The name of the placement group into which you'll launch your
     * instances, if any. For more information, see <a
     * href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     * Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param placementGroup The name of the placement group into which you'll launch your
     *         instances, if any. For more information, see <a
     *         href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     *         Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     */
    public void setPlacementGroup(String placementGroup) {
        this.placementGroup = placementGroup;
    }
    
    /**
     * The name of the placement group into which you'll launch your
     * instances, if any. For more information, see <a
     * href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     * Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param placementGroup The name of the placement group into which you'll launch your
     *         instances, if any. For more information, see <a
     *         href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html">Placement
     *         Groups</a> in the <i>Amazon Elastic Compute Cloud User Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withPlacementGroup(String placementGroup) {
        this.placementGroup = placementGroup;
        return this;
    }

    /**
     * The ID of the subnet, if you are launching into a VPC. You can specify
     * several subnets in a comma-separated list. <p>When you specify
     * <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     * ensure that the subnets' Availability Zones match the values you
     * specify for <code>AvailabilityZones</code>. <p>For more information,
     * see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     * Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     * Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @return The ID of the subnet, if you are launching into a VPC. You can specify
     *         several subnets in a comma-separated list. <p>When you specify
     *         <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     *         ensure that the subnets' Availability Zones match the values you
     *         specify for <code>AvailabilityZones</code>. <p>For more information,
     *         see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     *         Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     *         Developer Guide</i>.
     */
    public String getVPCZoneIdentifier() {
        return vPCZoneIdentifier;
    }
    
    /**
     * The ID of the subnet, if you are launching into a VPC. You can specify
     * several subnets in a comma-separated list. <p>When you specify
     * <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     * ensure that the subnets' Availability Zones match the values you
     * specify for <code>AvailabilityZones</code>. <p>For more information,
     * see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     * Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     * Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param vPCZoneIdentifier The ID of the subnet, if you are launching into a VPC. You can specify
     *         several subnets in a comma-separated list. <p>When you specify
     *         <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     *         ensure that the subnets' Availability Zones match the values you
     *         specify for <code>AvailabilityZones</code>. <p>For more information,
     *         see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     *         Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     *         Developer Guide</i>.
     */
    public void setVPCZoneIdentifier(String vPCZoneIdentifier) {
        this.vPCZoneIdentifier = vPCZoneIdentifier;
    }
    
    /**
     * The ID of the subnet, if you are launching into a VPC. You can specify
     * several subnets in a comma-separated list. <p>When you specify
     * <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     * ensure that the subnets' Availability Zones match the values you
     * specify for <code>AvailabilityZones</code>. <p>For more information,
     * see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     * Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     * Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     * <b>Pattern: </b>[&#92;u0020-&#92;uD7FF&#92;uE000-&#92;uFFFD&#92;uD800&#92;uDC00-&#92;uDBFF&#92;uDFFF\r\n\t]*<br/>
     *
     * @param vPCZoneIdentifier The ID of the subnet, if you are launching into a VPC. You can specify
     *         several subnets in a comma-separated list. <p>When you specify
     *         <code>VPCZoneIdentifier</code> with <code>AvailabilityZones</code>,
     *         ensure that the subnets' Availability Zones match the values you
     *         specify for <code>AvailabilityZones</code>. <p>For more information,
     *         see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/autoscalingsubnets.html">Auto
     *         Scaling and Amazon Virtual Private Cloud</a> in the <i>Auto Scaling
     *         Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withVPCZoneIdentifier(String vPCZoneIdentifier) {
        this.vPCZoneIdentifier = vPCZoneIdentifier;
        return this;
    }

    /**
     * A standalone termination policy or a list of termination policies used
     * to select the instance to terminate. The policies are executed in the
     * order that they are listed. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     * a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     * Scaling Developer Guide</i>.
     *
     * @return A standalone termination policy or a list of termination policies used
     *         to select the instance to terminate. The policies are executed in the
     *         order that they are listed. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     *         a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     *         Scaling Developer Guide</i>.
     */
    public java.util.List<String> getTerminationPolicies() {
        if (terminationPolicies == null) {
              terminationPolicies = new com.amazonaws.internal.ListWithAutoConstructFlag<String>();
              terminationPolicies.setAutoConstruct(true);
        }
        return terminationPolicies;
    }
    
    /**
     * A standalone termination policy or a list of termination policies used
     * to select the instance to terminate. The policies are executed in the
     * order that they are listed. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     * a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     * Scaling Developer Guide</i>.
     *
     * @param terminationPolicies A standalone termination policy or a list of termination policies used
     *         to select the instance to terminate. The policies are executed in the
     *         order that they are listed. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     *         a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     *         Scaling Developer Guide</i>.
     */
    public void setTerminationPolicies(java.util.Collection<String> terminationPolicies) {
        if (terminationPolicies == null) {
            this.terminationPolicies = null;
            return;
        }
        com.amazonaws.internal.ListWithAutoConstructFlag<String> terminationPoliciesCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(terminationPolicies.size());
        terminationPoliciesCopy.addAll(terminationPolicies);
        this.terminationPolicies = terminationPoliciesCopy;
    }
    
    /**
     * A standalone termination policy or a list of termination policies used
     * to select the instance to terminate. The policies are executed in the
     * order that they are listed. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     * a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     * Scaling Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param terminationPolicies A standalone termination policy or a list of termination policies used
     *         to select the instance to terminate. The policies are executed in the
     *         order that they are listed. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     *         a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     *         Scaling Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withTerminationPolicies(String... terminationPolicies) {
        if (getTerminationPolicies() == null) setTerminationPolicies(new java.util.ArrayList<String>(terminationPolicies.length));
        for (String value : terminationPolicies) {
            getTerminationPolicies().add(value);
        }
        return this;
    }
    
    /**
     * A standalone termination policy or a list of termination policies used
     * to select the instance to terminate. The policies are executed in the
     * order that they are listed. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     * a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     * Scaling Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param terminationPolicies A standalone termination policy or a list of termination policies used
     *         to select the instance to terminate. The policies are executed in the
     *         order that they are listed. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/AutoScaling/latest/DeveloperGuide/us-termination-policy.html">Choosing
     *         a Termination Policy for Your Auto Scaling Group</a> in the <i>Auto
     *         Scaling Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withTerminationPolicies(java.util.Collection<String> terminationPolicies) {
        if (terminationPolicies == null) {
            this.terminationPolicies = null;
        } else {
            com.amazonaws.internal.ListWithAutoConstructFlag<String> terminationPoliciesCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(terminationPolicies.size());
            terminationPoliciesCopy.addAll(terminationPolicies);
            this.terminationPolicies = terminationPoliciesCopy;
        }

        return this;
    }

    /**
     * Indicates whether newly launched instances are protected from
     * termination by Auto Scaling when scaling in.
     *
     * @return Indicates whether newly launched instances are protected from
     *         termination by Auto Scaling when scaling in.
     */
    public Boolean isNewInstancesProtectedFromScaleIn() {
        return newInstancesProtectedFromScaleIn;
    }
    
    /**
     * Indicates whether newly launched instances are protected from
     * termination by Auto Scaling when scaling in.
     *
     * @param newInstancesProtectedFromScaleIn Indicates whether newly launched instances are protected from
     *         termination by Auto Scaling when scaling in.
     */
    public void setNewInstancesProtectedFromScaleIn(Boolean newInstancesProtectedFromScaleIn) {
        this.newInstancesProtectedFromScaleIn = newInstancesProtectedFromScaleIn;
    }
    
    /**
     * Indicates whether newly launched instances are protected from
     * termination by Auto Scaling when scaling in.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param newInstancesProtectedFromScaleIn Indicates whether newly launched instances are protected from
     *         termination by Auto Scaling when scaling in.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public UpdateAutoScalingGroupRequest withNewInstancesProtectedFromScaleIn(Boolean newInstancesProtectedFromScaleIn) {
        this.newInstancesProtectedFromScaleIn = newInstancesProtectedFromScaleIn;
        return this;
    }

    /**
     * Indicates whether newly launched instances are protected from
     * termination by Auto Scaling when scaling in.
     *
     * @return Indicates whether newly launched instances are protected from
     *         termination by Auto Scaling when scaling in.
     */
    public Boolean getNewInstancesProtectedFromScaleIn() {
        return newInstancesProtectedFromScaleIn;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAutoScalingGroupName() != null) sb.append("AutoScalingGroupName: " + getAutoScalingGroupName() + ",");
        if (getLaunchConfigurationName() != null) sb.append("LaunchConfigurationName: " + getLaunchConfigurationName() + ",");
        if (getMinSize() != null) sb.append("MinSize: " + getMinSize() + ",");
        if (getMaxSize() != null) sb.append("MaxSize: " + getMaxSize() + ",");
        if (getDesiredCapacity() != null) sb.append("DesiredCapacity: " + getDesiredCapacity() + ",");
        if (getDefaultCooldown() != null) sb.append("DefaultCooldown: " + getDefaultCooldown() + ",");
        if (getAvailabilityZones() != null) sb.append("AvailabilityZones: " + getAvailabilityZones() + ",");
        if (getHealthCheckType() != null) sb.append("HealthCheckType: " + getHealthCheckType() + ",");
        if (getHealthCheckGracePeriod() != null) sb.append("HealthCheckGracePeriod: " + getHealthCheckGracePeriod() + ",");
        if (getPlacementGroup() != null) sb.append("PlacementGroup: " + getPlacementGroup() + ",");
        if (getVPCZoneIdentifier() != null) sb.append("VPCZoneIdentifier: " + getVPCZoneIdentifier() + ",");
        if (getTerminationPolicies() != null) sb.append("TerminationPolicies: " + getTerminationPolicies() + ",");
        if (isNewInstancesProtectedFromScaleIn() != null) sb.append("NewInstancesProtectedFromScaleIn: " + isNewInstancesProtectedFromScaleIn() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getAutoScalingGroupName() == null) ? 0 : getAutoScalingGroupName().hashCode()); 
        hashCode = prime * hashCode + ((getLaunchConfigurationName() == null) ? 0 : getLaunchConfigurationName().hashCode()); 
        hashCode = prime * hashCode + ((getMinSize() == null) ? 0 : getMinSize().hashCode()); 
        hashCode = prime * hashCode + ((getMaxSize() == null) ? 0 : getMaxSize().hashCode()); 
        hashCode = prime * hashCode + ((getDesiredCapacity() == null) ? 0 : getDesiredCapacity().hashCode()); 
        hashCode = prime * hashCode + ((getDefaultCooldown() == null) ? 0 : getDefaultCooldown().hashCode()); 
        hashCode = prime * hashCode + ((getAvailabilityZones() == null) ? 0 : getAvailabilityZones().hashCode()); 
        hashCode = prime * hashCode + ((getHealthCheckType() == null) ? 0 : getHealthCheckType().hashCode()); 
        hashCode = prime * hashCode + ((getHealthCheckGracePeriod() == null) ? 0 : getHealthCheckGracePeriod().hashCode()); 
        hashCode = prime * hashCode + ((getPlacementGroup() == null) ? 0 : getPlacementGroup().hashCode()); 
        hashCode = prime * hashCode + ((getVPCZoneIdentifier() == null) ? 0 : getVPCZoneIdentifier().hashCode()); 
        hashCode = prime * hashCode + ((getTerminationPolicies() == null) ? 0 : getTerminationPolicies().hashCode()); 
        hashCode = prime * hashCode + ((isNewInstancesProtectedFromScaleIn() == null) ? 0 : isNewInstancesProtectedFromScaleIn().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof UpdateAutoScalingGroupRequest == false) return false;
        UpdateAutoScalingGroupRequest other = (UpdateAutoScalingGroupRequest)obj;
        
        if (other.getAutoScalingGroupName() == null ^ this.getAutoScalingGroupName() == null) return false;
        if (other.getAutoScalingGroupName() != null && other.getAutoScalingGroupName().equals(this.getAutoScalingGroupName()) == false) return false; 
        if (other.getLaunchConfigurationName() == null ^ this.getLaunchConfigurationName() == null) return false;
        if (other.getLaunchConfigurationName() != null && other.getLaunchConfigurationName().equals(this.getLaunchConfigurationName()) == false) return false; 
        if (other.getMinSize() == null ^ this.getMinSize() == null) return false;
        if (other.getMinSize() != null && other.getMinSize().equals(this.getMinSize()) == false) return false; 
        if (other.getMaxSize() == null ^ this.getMaxSize() == null) return false;
        if (other.getMaxSize() != null && other.getMaxSize().equals(this.getMaxSize()) == false) return false; 
        if (other.getDesiredCapacity() == null ^ this.getDesiredCapacity() == null) return false;
        if (other.getDesiredCapacity() != null && other.getDesiredCapacity().equals(this.getDesiredCapacity()) == false) return false; 
        if (other.getDefaultCooldown() == null ^ this.getDefaultCooldown() == null) return false;
        if (other.getDefaultCooldown() != null && other.getDefaultCooldown().equals(this.getDefaultCooldown()) == false) return false; 
        if (other.getAvailabilityZones() == null ^ this.getAvailabilityZones() == null) return false;
        if (other.getAvailabilityZones() != null && other.getAvailabilityZones().equals(this.getAvailabilityZones()) == false) return false; 
        if (other.getHealthCheckType() == null ^ this.getHealthCheckType() == null) return false;
        if (other.getHealthCheckType() != null && other.getHealthCheckType().equals(this.getHealthCheckType()) == false) return false; 
        if (other.getHealthCheckGracePeriod() == null ^ this.getHealthCheckGracePeriod() == null) return false;
        if (other.getHealthCheckGracePeriod() != null && other.getHealthCheckGracePeriod().equals(this.getHealthCheckGracePeriod()) == false) return false; 
        if (other.getPlacementGroup() == null ^ this.getPlacementGroup() == null) return false;
        if (other.getPlacementGroup() != null && other.getPlacementGroup().equals(this.getPlacementGroup()) == false) return false; 
        if (other.getVPCZoneIdentifier() == null ^ this.getVPCZoneIdentifier() == null) return false;
        if (other.getVPCZoneIdentifier() != null && other.getVPCZoneIdentifier().equals(this.getVPCZoneIdentifier()) == false) return false; 
        if (other.getTerminationPolicies() == null ^ this.getTerminationPolicies() == null) return false;
        if (other.getTerminationPolicies() != null && other.getTerminationPolicies().equals(this.getTerminationPolicies()) == false) return false; 
        if (other.isNewInstancesProtectedFromScaleIn() == null ^ this.isNewInstancesProtectedFromScaleIn() == null) return false;
        if (other.isNewInstancesProtectedFromScaleIn() != null && other.isNewInstancesProtectedFromScaleIn().equals(this.isNewInstancesProtectedFromScaleIn()) == false) return false; 
        return true;
    }
    
}
    