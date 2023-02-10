// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristSubsystem;

public class StopWristCommand extends CommandBase
{

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final WristSubsystem m_WristSubsystem;

  /**
   * @param subsystem Creates a new StopWristCommand.
   */
  public StopWristCommand(WristSubsystem subsystem)
  {                //constructor method
    // Use addRequirements() here to declare subsystem dependencies.
    m_WristSubsystem = subsystem;         //allows private variable to be used in other methods in the class
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_WristSubsystem.stopMotor();          //calls stop motor when command is initialized
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_WristSubsystem.stopMotor();              //calls stops motor when command is executed
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    m_WristSubsystem.stopMotor();               //stops wrist motor
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;                     //returns false
  }
}
