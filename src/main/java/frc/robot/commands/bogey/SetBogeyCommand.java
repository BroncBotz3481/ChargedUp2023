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
  /**
   * Uses a new bogey subsystem
   */
  private final BogeySubsystem m_BogeySubsystem;
  /**
   * The target position for the bogey depending on the setpoint
   */
  private       double         targetPosition;

  /**
   * Initializes a new SetBogeyCommand
   * @param subsystem Creates a new BogeyHighCommand.
   * @param target sets the target position depending on the setpoint
   */
  public SetBogeyCommand(BogeySubsystem subsystem, double target)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    targetPosition = target;
  }

  // Called when the command is initially scheduled.

  /**
   * Stops the arm, then runs PID for the arm to reach target position upon initialization
   */
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

  /**
   * Stops the arm when target position is reached
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  // Returns true when the command should end.

  /**
   * @return true or false depending on whether the bogey has reached its set position
   */
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
