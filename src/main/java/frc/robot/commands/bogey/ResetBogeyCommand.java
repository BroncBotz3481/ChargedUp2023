// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

public class ResetBogeyCommand extends CommandBase
{
  /**
   * Uses bogey subsystem
   */
  private final BogeySubsystem m_BogeySubsystem;

  /**
   * Creates a new ResetBogeyCommand
   * @param subsystem initializes the Bogey subsystem
   */
  public ResetBogeyCommand(BogeySubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * Sets the target position of the bogey to zero
   */
  @Override
  public void initialize()
  {
    m_BogeySubsystem.runPID(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {

  }

  // Called once the command ends or is interrupted.

  /**
   * Ends the bogey command when the arm reaches the target position
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  // Returns true when the command should end.

  /**
   * @return true if the bogey has reached position 0 and false if not
   */
  @Override
  public boolean isFinished()
  {
    if (BogeyPolicy.upLimit || BogeyPolicy.lowerLiimit)
    {
      return true;
    }
    return false;
  }
}
