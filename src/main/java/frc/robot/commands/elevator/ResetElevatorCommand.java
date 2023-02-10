// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class ResetElevatorCommand extends CommandBase {
  private final ElevatorSubsystem m_ElevatorSubsystem;
  /** Creates a new ResetElevatorCommand. */
  public ResetElevatorCommand(ElevatorSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ElevatorSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ElevatorSubsystem.pidMove(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ElevatorSubsystem.stopEle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(ElevatorPolicy.rightEncoderPosition <= 0 && ElevatorPolicy.leftEncoderPosition <= 0){
      return true;
    }
    return false;
  }
}
