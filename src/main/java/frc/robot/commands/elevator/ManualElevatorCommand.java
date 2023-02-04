// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class ManualElevatorCommand extends CommandBase {
  /** Creates a new ManualElevatorCommand. */
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final DoubleSupplier powSupplier;
  public ManualElevatorCommand(ElevatorSubsystem subsystem, DoubleSupplier powDoubleSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevatorSubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_elevatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_elevatorSubsystem.stopEle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorSubsystem.moveElevator(powSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.stopEle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
