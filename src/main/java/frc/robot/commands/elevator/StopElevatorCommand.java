// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class StopElevatorCommand extends CommandBase
{
  /**
   * An ElevatorSubsystem object
   */
  private final ElevatorSubsystem m_ElevatorSubsystem;

  /**
   * Initializes the elevator subsystem and adds requirements
   * @param subsystem used to initialize the elevator subsystem
   */
  public StopElevatorCommand(ElevatorSubsystem subsystem)
  {
    m_ElevatorSubsystem = subsystem;
    addRequirements(subsystem);
  }

  /**
   * Stops the elevator when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_ElevatorSubsystem.stopEle();
  }

  /**
   * Stops the elevator every 20 ms while the command is scheduled
   */
  @Override
  public void execute()
  {
    m_ElevatorSubsystem.stopEle();
  }

  /**
   * Stops the elevator when the command is removed from the command scheduled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_ElevatorSubsystem.stopEle();
  }

  /**
   *returns true when either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return ElevatorPolicy.lowLimit || ElevatorPolicy.upLimit;
  }
}
