// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeySubsystem;
import java.util.function.DoubleSupplier;

public class ManualBogeyCommand extends CommandBase
{
  /**
   * Uses bogey subsystem
   */
  private final BogeySubsystem m_bogeySubsystem;
  /**
   * Uses a power supplier
   */
  private final DoubleSupplier powSupplier;

  /**
   * Initializes the bogey subsystem and power supplier
   * @param subsystem initializes the bogey subsystem
   * @param powDoubleSupplier initializes the power supplier
   */
  public ManualBogeyCommand(BogeySubsystem subsystem, DoubleSupplier powDoubleSupplier)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_bogeySubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_bogeySubsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * stops the arm at the beginning of the command running
   */
  @Override
  public void initialize()
  {
    m_bogeySubsystem.stopArm();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * moves the arm
   */
  @Override
  public void execute()
  {
    m_bogeySubsystem.moveArm(powSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.

  /**
   * stops the arm at the end of the command
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_bogeySubsystem.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
