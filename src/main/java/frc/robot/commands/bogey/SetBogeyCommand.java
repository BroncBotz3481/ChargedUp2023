// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

/**
 * Bogey Command that uses a PID Control Loop to bring the Bogey to a target position
 */
public class SetBogeyCommand extends CommandBase
{

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * A BogeySubsystem object
   */
  private final BogeySubsystem m_BogeySubsystem;
  /**
   * Is used as the set position for the PID Control Loop
   */
  private final double         targetPosition;

  /**
   * Initializes the BogeySubsystem, targetPosition, and adds requirements
   *
   * @param subsystem used to initialize the BogeySubsystem
   * @param target    used to initialize the targetPosition
   */
  public SetBogeyCommand(BogeySubsystem subsystem, double target)
  {
    m_BogeySubsystem = subsystem;
    targetPosition = target;
  }

  /**
   * Stops the arm, then runs PID for the arm to reach target position upon initialization when the command is
   * scheduled
   */
  @Override
  public void initialize()
  {
    m_BogeySubsystem.stopArm();
    m_BogeySubsystem.runPID(targetPosition);
  }

  @Override
  public void execute()
  {
  }

  /**
   * Stops the bogey when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  /**
   * returns true if either limit switch is pressed or the encoder position is greater than target position, removing
   * the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return BogeyPolicy.encoderPosition >= targetPosition || BogeyPolicy.lowLimit || BogeyPolicy.upLimit;
  }
}
