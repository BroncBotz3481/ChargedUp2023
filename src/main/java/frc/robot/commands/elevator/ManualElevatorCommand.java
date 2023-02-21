// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

import java.util.function.DoubleSupplier;

public class ManualElevatorCommand extends CommandBase
{

  /**
   * Uses ElevatorSubsystem
   */
  private final ElevatorSubsystem m_elevatorSubsystem;
  /**
   * Uses DoubleSupplier
   */
  private final DoubleSupplier    powSupplier;

  /**
   * Initializes the elevator subsystem and power supplier
   *
   * @param subsystem         initializes the elevator subsystem
   * @param powDoubleSupplier initializes the power supplier
   */
  public ManualElevatorCommand(ElevatorSubsystem subsystem, DoubleSupplier powDoubleSupplier)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevatorSubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_elevatorSubsystem);
  }

  // Called when the command is initially scheduled.

  /**
   * stops the elevator at the beginning of the command running
   */
  @Override
  public void initialize()
  {
    m_elevatorSubsystem.stopEle();
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * moves the arm
   */
  @Override
  public void execute()
  {
    m_elevatorSubsystem.moveElevator(powSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.

  /**
   * stops the elevator at the end of the command
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {
    m_elevatorSubsystem.stopEle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return ElevatorPolicy.lowLimit || ElevatorPolicy.upLimit;
  }
}
