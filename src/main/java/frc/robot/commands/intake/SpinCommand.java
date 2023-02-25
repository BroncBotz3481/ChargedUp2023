// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class SpinCommand extends CommandBase
{

  /**
   * An object of IntakeSubsystem
   */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_IntakeSubsystem;

  /**
   * Initializes the IntakeSubsystem and adds requirments
   *
   * @param subsystem A IntakeSubsystem used to initialize the instance IntakeSubsystem
   */
  public SpinCommand(IntakeSubsystem subsystem)
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
   * Runs the intake at -100% power every 20 ms while the command is scheduled
   */
  @Override
  public void execute()
  {
    m_IntakeSubsystem.runIntake(-1);
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
