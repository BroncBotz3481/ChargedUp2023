// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class StopIntakeCommand extends CommandBase
{

  /**
   * Uses IntakeSubsystem
   */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_IntakeSubsystem;

  /**
   * Initializes the intakeSubsystem
   *
   * @param subsystem Creates a new StopIntakeCommand.
   */
  public StopIntakeCommand(IntakeSubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_IntakeSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * stops the intake at the beginning of the command running. Power is set to 0
   */
  @Override
  public void initialize()
  {
    m_IntakeSubsystem.stopIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * Stops the intake when executed. Power is set to 0.
   */
  @Override
  public void execute()
  {
    m_IntakeSubsystem.stopIntake();
  }

  // Called once the command ends or is interrupted.

  /**
   * intake is stopped at the end. Power is set to 0.
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
