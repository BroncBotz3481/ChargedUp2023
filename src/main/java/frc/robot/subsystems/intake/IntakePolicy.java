// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import frc.robot.Constants.IDS.Intake.CAN;

/**
 * This is the Intake policy, holding all algorithmic functions for the Intake Subsystem and Commands. Holds all
 * important values from the subsystem
 **/
public final class IntakePolicy
{

  /**
   * CAN ID for the intake motor
   */
  public static final int    INTAKE_ID_PORT = CAN.main;
  /**
   * The power set to the Intake
   */
  public static       double power;
}
