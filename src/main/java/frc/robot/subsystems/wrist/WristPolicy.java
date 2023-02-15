// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

/**
 * This is the Wrist policy, holding all algorithmic functions for the Wrist Subsystem and Commands.
 * Holds all important values from the subsystem
 **/
public final class WristPolicy
{

  /**
   * CAN ID Port for Wrist Motor
   */
  public static final int    WRIST_ID_PORT = 10;
  /**
   * Power set to Wrist Motor
   */
  public static       double power;
  /**
   * Position set to Wrist Motor
   */
  public static       double setPosition;
  /**
   * Velocity set to encoder
   */
  public static       double encoderVelocity;
  /**
   * Position set to encoder
   */
  public static       double encoderPosition;
  /**
   * Holds all PIDF constants for WristSubsystem
   */
  public static class PIDF {
    /**
     * Feedforward constant for PID Loop
     */
    public static final double FEEDFORWARD = 0.0;
    /**
     * Proportion constant for PID Loop
     */
    public static final double PROPORTION = 0.01;
    /**
     * Integral constant for PID Loop
     */
    public static final double INTEGRAL = 0.0;
    /**
     * Derivative constant for PID Loop
     */
    public static final double DERIVATIVE = 0.0;
    /**
     * Integral zone constant for PID loop
     */
    public static final double INTEGRAL_ZONE = 0.0;
  }
}
