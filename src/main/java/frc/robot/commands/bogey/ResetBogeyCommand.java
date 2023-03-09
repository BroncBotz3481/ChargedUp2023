// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

/**
 * Bogey Command that brings the bogey to home position
 */
public class ResetBogeyCommand extends CommandBase
{

  /**
   * A BogeySubsystem object
   */
  private final BogeySubsystem m_BogeySubsystem;

  /**
   * Initializes the BogeySubsystem and adds requirements
   *
   * @param subsystem used to initialize the BogeySubsystem
   */
  public ResetBogeyCommand(BogeySubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    addRequirements(subsystem);
  }

  /**
   * Runs the PID Control Loop with the target position being 0 when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_BogeySubsystem.runPID(0);
  }

  @Override
  public void execute()
  {
  }

  /**
   * Stops the Bogey when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  /**
   * returns true if either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return BogeyPolicy.upLimit || BogeyPolicy.lowLimit;
  }
}
