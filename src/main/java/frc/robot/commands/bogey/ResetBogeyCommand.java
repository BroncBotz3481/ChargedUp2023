// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

public class ResetBogeyCommand extends CommandBase
{

  private final BogeySubsystem m_BogeySubsystem;

  /**
   * Creates a new ResetBogeyCommand.
   */
  public ResetBogeyCommand(BogeySubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
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
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if (BogeyPolicy.encoderPosition <= 0)
    {
      return true;
    }
    return false;
  }
}
