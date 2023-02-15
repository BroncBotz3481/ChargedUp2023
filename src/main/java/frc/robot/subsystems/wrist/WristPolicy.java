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
}
