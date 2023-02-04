// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristSubsystem;;

//creates DropWristCommand class and gets the methods and other attributes from CommandBase
public class DropWristCommand extends CommandBase { 
  /** Creates a new WristCommand. */
  WristSubsystem m_WristSubsystem = new WristSubsystem();   //creates new object m_WristSubsystem
  public DropWristCommand(WristSubsystem subsystem) {     //constructor
    m_WristSubsystem = subsystem;                       //allows object m_WristSubsystem to be used in other methods in this class
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_WristSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override             //creates method that functions outside of the parent function's method of the same name
  public void initialize() {
    m_WristSubsystem.stopMotor();         //do not drop wrist when initialized
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_WristSubsystem.setMotor(-5000);;      //sets wrist motor to -5000 so that it drops
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_WristSubsystem.stopMotor();       //stops motor     
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;       //returns false
  }
}
