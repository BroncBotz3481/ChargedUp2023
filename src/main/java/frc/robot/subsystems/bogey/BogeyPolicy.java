// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.bogey;

/** This is the Bogey policy, holding all algorithmic functions for the Bogey Subsystem and Commands. 
 Holds all important values from the subsystem **/

public final class BogeyPolicy {
    public static final int BOGEY_ID_PORT = 0;/**CAN ID for the bogey motor */
    public static double bogeyPower; /**Power set to Bogey Motor */
    public static double encoderVelocity; /**How fast Motors are running */
    public static double encoderPosition; /** Position of Motor */
    public static double ratioPosition; /**Used linearize the Elevator Bogey */
    public static double setPosition; /**target position */

    /**Constants Proportion, Integral, Derivative, FeedForward, and IntegralZone (PIDF) for Bogey */
    public static final double FEEDFORWARD = 0.05; 
    public static final double PROPORTION = 0.01; 
    public static final double INTEGRAL = 0.0; 
    public static final double DERIVATIVE = 0.0; 
    public static final double INTEGRAL_ZONE = 0.0; 







}
