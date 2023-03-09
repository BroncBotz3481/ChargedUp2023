// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;
import java.util.function.DoubleSupplier;

/**
 * Bogey Command to manually control the Bogey using the operator's controller's y-axis
 */
public class ManualBogeyCommand extends CommandBase
{

  /**
   * A BogeySubsystem object
   */
  private final BogeySubsystem m_bogeySubsystem;
  /**
   * A DoubleSupplier object
   */
  private final DoubleSupplier powSupplier;

  /**
   * Initializes the bogey subsystem, power supplier, and adds requirements
   *
   * @param subsystem         used to initialize the bogey subsystem
   * @param powDoubleSupplier used to initialize the power supplier
   */
  public ManualBogeyCommand(BogeySubsystem subsystem, DoubleSupplier powDoubleSupplier)
  {
    m_bogeySubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_bogeySubsystem);
  }


  /**
   * Stops the bogey when the command is scheduled
   */
  @Override
  public void initialize()
  {
    m_bogeySubsystem.stopArm();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * Moves the bogey every 20 ms using the operator's y-axis as a voltage while the command is scheduled
   */
  @Override
  public void execute()
  {
    m_bogeySubsystem.moveArm(powSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.

  /**
   * Stops the bogey when the command is removed from the command scheduler
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_bogeySubsystem.stopArm();
  }

  /**
   * returns true when either limit switch is pressed, removing the command from the command scheduler
   */
  @Override
  public boolean isFinished()
  {
    return BogeyPolicy.upLimit || BogeyPolicy.lowLimit;
  }
}
