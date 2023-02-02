// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class SpitCommand extends CommandBase {
  /** Creates a new SpitCommand. */
  IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();

  public SpitCommand(IntakeSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_IntakeSubsystem = subsystem;
    addRequirements(m_IntakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_IntakeSubsystem.stopIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IntakeSubsystem.runIntake(1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeSubsystem.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
