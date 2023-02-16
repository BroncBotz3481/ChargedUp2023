// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

public class StopBogeyCommand extends CommandBase
{

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new bogey subsystem
   */
  private final BogeySubsystem m_BogeySubsystem;

  /**
   * @param subsystem Creates a new StopBogeyCommand.
   */
  public StopBogeyCommand(BogeySubsystem subsystem)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BogeySubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * Stops the arm among initialization
   */
  @Override
  public void initialize()
  {
    m_BogeySubsystem.stopArm();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * Stops the arm while the command is run
   */
  @Override
  public void execute()
  {
    m_BogeySubsystem.stopArm();
  }

  // Called once the command ends or is interrupted.

  /**
   * Stops the arm when the command ends
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_BogeySubsystem.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return BogeyPolicy.upLimit && BogeyPolicy.lowLimit;
  }
}
