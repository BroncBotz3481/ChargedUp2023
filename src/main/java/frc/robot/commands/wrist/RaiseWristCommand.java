// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristSubsystem;;

public class RaiseWristCommand extends CommandBase {
  /** Creates a new WristCommand. */
  WristSubsystem m_WristSubsystem = new WristSubsystem(); //creates new object of class WristSubsystem
  public RaiseWristCommand(WristSubsystem subsystem) { //constructor for RaiseWristCommand class; WristSubsystem object as parameter
    m_WristSubsystem = subsystem; //sets command object to parameter, so it can be used throughout this class
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_WristSubsystem); //ensures subsystem can only be used by one command at a time
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_WristSubsystem.stopMotor(); //stops the wrist motor when robot initialized
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_WristSubsystem.setMotor(5000);; //when RaiseWristCommand is executed, wrist motor set to 5000. (+) so wrist raises
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_WristSubsystem.stopMotor();  //stops motor; when boolean parameter is true, motor stops. 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
