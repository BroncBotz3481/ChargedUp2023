// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeySubsystem;

public class ManualBogeyCommand extends CommandBase {
  /** Creates a new ManualElevatorCommand. */
  private final BogeySubsystem m_bogeySubsystem;
  private final DoubleSupplier powSupplier;
  public ManualBogeyCommand(BogeySubsystem subsystem, DoubleSupplier powDoubleSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_bogeySubsystem = subsystem;
    powSupplier = powDoubleSupplier;
    addRequirements(m_bogeySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_bogeySubsystem.stopArm();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_bogeySubsystem.moveArm(powSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_bogeySubsystem.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
