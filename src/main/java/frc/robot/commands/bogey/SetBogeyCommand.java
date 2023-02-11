// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

public class SetBogeyCommand extends CommandBase
{

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final BogeySubsystem m_BogeySubsystem;
  private       double         targetPosition;

  /**
   * @param subsystem Creates a new BogeyHighCommand.
   */
  public SetBogeyCommand(BogeySubsystem subsystem, double target)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    targetPosition = target;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_BogeySubsystem.stopArm();
    m_BogeySubsystem.runPID(targetPosition);
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
    if (BogeyPolicy.encoderPosition >= BogeyPolicy.setPosition)
    {
      return true;
    }
    return false;
  }
}
