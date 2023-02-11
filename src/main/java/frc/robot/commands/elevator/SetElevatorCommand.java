// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class SetElevatorCommand extends CommandBase
{

  /**
   * Creates a new ElevatorCommand.
   */
  private final ElevatorSubsystem m_ElevatorSubsystem;
  private       double            targetPosition;

  public SetElevatorCommand(ElevatorSubsystem subsystem, double target)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ElevatorSubsystem = subsystem;
    targetPosition = target;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_ElevatorSubsystem.stopEle();
    m_ElevatorSubsystem.runPID(targetPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    m_ElevatorSubsystem.stopEle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if (ElevatorPolicy.rightEncoderPosition >= ElevatorPolicy.setPosition
        && ElevatorPolicy.leftEncoderPosition >= ElevatorPolicy.setPosition)
    {
      return true;
    }
    return false;
  }
}
