// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.subsystems.wrist.WristSubsystem;

public class ResetWristCommand extends CommandBase
{

  /**
   * Initializes the WristSubsystem
   * @param subsystem creates a new ResetWristCommand
   */
  private final WristSubsystem m_WristSubsystem;

  public ResetWristCommand(WristSubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_WristSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  /**
   * stops the wrist at the beginning of the command running
   */
  @Override
  public void initialize()
  {
    m_WristSubsystem.setMotor(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
  }

  // Called once the command ends or is interrupted.
  /**
   * stops the intake at the end of the command running
   */
  @Override
  public void end(boolean interrupted)
  {
    m_WristSubsystem.stopMotor();
  }

  /**
   *  Returns true when the command should end reaching the proper position
   */
  @Override
  public boolean isFinished()
  {
    if (WristPolicy.encoderPosition <= 0)
    {
      return true;
    }
    return false;
  }
}
