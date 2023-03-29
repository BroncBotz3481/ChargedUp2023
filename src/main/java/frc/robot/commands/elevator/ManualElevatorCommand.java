// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import java.util.function.DoubleSupplier;

/**
 * Elevator command that allows the operator to manually control the elevator using the controllers y-axis
 */
public class ManualElevatorCommand extends CommandBase
{

  /**
   * A ElevatorSubsystem object
   */
  private final ElevatorSubsystem m_elevatorSubsystem;
  /**
   * A DoubleSupplier object
   */
  private final DoubleSupplier    powSupplier;

  /**
   * Initializes the elevator subsystem and power supplier
   *
   * @param subsystem         used to initialize the elevator subsystem
   * @param powDoubleSupplier used to initialize the power supplier
   */
  public ManualElevatorCommand(ElevatorSubsystem subsystem, DoubleSupplier powDoubleSupplier)
  {
    m_elevatorSubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_elevatorSubsystem);
  }

  /**
   * Stops the elevator when the command is scheduled
   */
  @Override
  public void initialize()
  {
    // m_elevatorSubsystem.stopEle();
  }

  /**
   * Moves elevator based off the axis of the XBox controller
   */
  @Override
  public void execute()
  {
    m_elevatorSubsystem.moveElevator(powSupplier.getAsDouble()*.5);
    //System.out.println("\nIs this running??\n");
  }

  /**
   * Stops the elevator when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    if (interrupted)
    {
      ElevatorPolicy.setPosition = ElevatorPolicy.rightEncoderPosition;
    }
  }

  /**
   * returns true when either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return ElevatorPolicy.lowLimit || ElevatorPolicy.upLimit;
  }
}
