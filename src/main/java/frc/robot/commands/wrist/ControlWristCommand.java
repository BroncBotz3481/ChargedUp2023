// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.subsystems.wrist.WristSubsystem;

/**
 * Wrist Command that brings the Wrist back to home position
 */
public class ControlWristCommand extends CommandBase
{

  /**
   * An object of the WristSubsystem
   */
  private final WristSubsystem m_WristSubsystem;
  /**
   * Old setpoint.
   */
  private       double         oldSetpoint = 0;

  /**
   * Initializes the WristSubsystem and adds requirements
   *
   * @param subsystem A WristSubsystem object used to initialize the instance WristSubsystem
   */
  public ControlWristCommand(WristSubsystem subsystem)
  {
    m_WristSubsystem = subsystem;
    addRequirements(subsystem);
  }

  /**
   * Returns the WristSubsystem to position 0 (home) when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_WristSubsystem.stopMotor();
    WristPolicy.setPosition = WristPolicy.encoderPosition;
    WristPolicy.offSet = WristPolicy.encoderPosition;
  }

  @Override
  public void execute()
  {
    if (oldSetpoint != WristPolicy.setPosition)
    {
      m_WristSubsystem.setMotor(WristPolicy.setPosition);
      oldSetpoint = WristPolicy.setPosition;
    }
  }

  /**
   * Stops the wrist when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_WristSubsystem.stopMotor();
  }

  /**
   * Returns true if either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
