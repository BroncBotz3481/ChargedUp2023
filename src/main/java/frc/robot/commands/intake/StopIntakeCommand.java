// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSubsystem;

/**
 * Intake Command to stop the Intake
 */
public class StopIntakeCommand extends CommandBase
{

  /**
   * An object of the IntakeSubsystem
   */
  private final IntakeSubsystem m_IntakeSubsystem;

  /**
   * Initializes the IntakeSubsystem and adds requirements
   *
   * @param subsystem A IntakeSubsystem subsystem used to initialize the instance IntakeSubsystem
   */
  public StopIntakeCommand(IntakeSubsystem subsystem)
  {
    m_IntakeSubsystem = subsystem;
    addRequirements(subsystem);
  }

  /**
   * Stops the intake when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_IntakeSubsystem.stopIntake();
  }

  /**
   * Stops the intake every 20 ms while the command is scheduled
   */
  @Override
  public void execute()
  {
    m_IntakeSubsystem.stopIntake();
  }

  /**
   * Stops the intake when the command is removed from the command scheduler
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_IntakeSubsystem.stopIntake();
  }


  @Override
  public boolean isFinished()
  {
    return false;
  }
}
