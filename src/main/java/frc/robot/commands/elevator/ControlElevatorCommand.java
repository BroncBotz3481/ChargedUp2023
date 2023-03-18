// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

/**
 * Elevator Commands that brings Elevator back to home position
 */
public class ControlElevatorCommand extends CommandBase
{

  /**
   * A ElevatorSubsystem object
   */
  private final ElevatorSubsystem m_ElevatorSubsystem;
  /**
   * The old position.
   */
  private       double            oldPosition = 0;

  /**
   * Initializes the ElevatorSubsystem and adds requirements
   *
   * @param subsystem used to initialize the elevator subsystem
   */
  public ControlElevatorCommand(ElevatorSubsystem subsystem)
  {
    m_ElevatorSubsystem = subsystem;
    addRequirements(subsystem);
  }

  /**
   * Runs PID Control Loop with the set position being 0 (home) when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_ElevatorSubsystem.stopEle();
    ElevatorPolicy.setPosition = ElevatorPolicy.rightEncoderPosition;
    ElevatorPolicy.offSet = ElevatorPolicy.rightEncoderPosition;
  }

  @Override
  public void execute()
  {
    // if (ElevatorPolicy.setPosition != oldPosition)
    // {
    //   m_ElevatorSubsystem.runPID(ElevatorPolicy.setPosition);
    //   oldPosition = ElevatorPolicy.setPosition;
    // }
    m_ElevatorSubsystem.moveElevator(-.1);
  }

  /**
   * Stops the elevator when the command is removed from the command scheduled
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_ElevatorSubsystem.stopEle();
  }

  /**
   * returns true when either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
