// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class StopElevatorCommand extends CommandBase
{

  /**
   * Uses ElevatorSubsystem
   */
  private final ElevatorSubsystem m_ElevatorSubsystem;

  /**
   * Initializes the elevator subsystem
   *
   * @param subsystem initializes the elevator subsystem
   */
  public StopElevatorCommand(ElevatorSubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ElevatorSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * Stops elevator at the beginning of the command
   */
  @Override
  public void initialize()
  {
    m_ElevatorSubsystem.stopEle();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * Stops Elevator
   */
  @Override
  public void execute()
  {
    m_ElevatorSubsystem.stopEle();
  }

  // Called once the command ends or is interrupted.

  /**
   * Stops Elevator
   */
  @Override
  public void end(boolean interrupted)
  {
    m_ElevatorSubsystem.stopEle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
