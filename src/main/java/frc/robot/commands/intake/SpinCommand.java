// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class SpinCommand extends CommandBase
{
  /**
   * Uses IntakeSubsystem
   */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_IntakeSubsystem;

  /**
   * Initializes the intakeSubsystem
   * @param subsystem creates a new SpinCommand
   */
  public SpinCommand(IntakeSubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_IntakeSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  /**
   * stops the intake at the beginning of the command running
   */
  @Override
  public void initialize()
  {
    m_IntakeSubsystem.stopIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  /**
   * moves the intake in the direction toward the robot
   */
  @Override
  public void execute()
  {
    m_IntakeSubsystem.runIntake(-1);
  }

  // Called once the command ends or is interrupted.
  /**
   * stops the intake at the end of the command
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_IntakeSubsystem.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
