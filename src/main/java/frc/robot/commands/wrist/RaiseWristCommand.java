// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristSubsystem;;

public class RaiseWristCommand extends CommandBase {
  /** Creates a new WristCommand. */
  WristSubsystem m_WristSubsystem = new WristSubsystem();
  public RaiseWristCommand(WristSubsystem subsystem) {
    m_WristSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_WristSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_WristSubsystem.stopMotor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_WristSubsystem.setMotor(5000);;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_WristSubsystem.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
