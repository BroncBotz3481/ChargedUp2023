// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;

/**
 * Elevator command that uses a PID Control Loop to bring the Elevator to a target position
 */
public class SetElevatorCommand extends CommandBase
{

  /**
   * Is used as the set position for the PID Control Loop
   */
  private final double  targetPosition;
  /**
   * Boolean to determine the isFinished() behavior for autonomous use
   */
  private final boolean waitForSetpoint;

    /**
     * Initializes the elevator subsystem, initializes targetPosition, and adds
     * reqiurements
     *
     * @param target target position in meters
     */
    public SetElevatorCommand(double target, boolean waitForSetpoint)
    {
      targetPosition = target;
      this.waitForSetpoint = waitForSetpoint;
    }

  /**
   * Stops the elevator and starts PID Control Loop when the command is scheduled
   */
  @Override
  public void initialize()
  {
  }

  @Override
  public void execute()
  {
    ElevatorPolicy.setPosition = targetPosition;
  }

  /**
   * Stops the elevator when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
  }

  /**
   * returns true if either limit switch is pressed or if encoder position is greater than targetPosition, removing the
   * command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    if (waitForSetpoint)
    {
      return Math.abs(ElevatorPolicy.rightEncoderPosition - ElevatorPolicy.setPosition) <
             ElevatorPolicy.acceptableTolerance;
    } else {
            return false;
    }
    }
}
