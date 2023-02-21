// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.subsystems.wrist.WristSubsystem;

public class SetWristCommand extends CommandBase {

    /**
     * Initializes the WristSubsystem
     *
     * @param subsystem creates a new SetWristCommand
     */
    private final WristSubsystem m_WristSubsystem; //creates new object of class WristSubsystem
    private final double targetPosition;

    public SetWristCommand(WristSubsystem subsystem, double target) { //constructor for RaiseWristCommand class; WristSubsystem object as parameter
        m_WristSubsystem = subsystem; //sets command object to parameter, so it can be used throughout this class
        targetPosition = target;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_WristSubsystem); //ensures subsystem can only be used by one command at a time
    }

    // Called when the command is initially scheduled.

    /**
     * stops the wrist at the beginning of the command running, and it sets the motor to the target position
     */
    @Override
    public void initialize() {
        m_WristSubsystem.stopMotor(); //stops the wrist motor when robot initialized
        m_WristSubsystem.setMotor(targetPosition);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //when RaiseWristCommand is executed, wrist motor set to 5000. (+) so wrist raises
    }

    // Called once the command ends or is interrupted.

    /**
     * stops the intake at the end of the command running
     */
    @Override
    public void end(boolean interrupted) {
        m_WristSubsystem.stopMotor();  //stops motor; when boolean parameter is true, motor stops.
    }

    // Returns true when the command should end.

    /**
     * Returns true when the command should end reaching the proper position
     */
    @Override
    public boolean isFinished() {
        return WristPolicy.encoderPosition >= WristPolicy.setPosition || BogeyPolicy.lowLimit || BogeyPolicy.upLimit;
    }
}
