// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.subsystems.wrist.WristSubsystem;

public class SetWristCommand extends CommandBase
{

    /**
     * An object of WristSubsystem
     */
    private final WristSubsystem m_WristSubsystem; //creates new object of class WristSubsystem
    /**
     * Is used as the set position for the PID Control Loop
     */
    private final double         targetPosition;

    /**
     * Initializes the WristSubsystem, targetPosition, and adds requirements
     *
     * @param subsystem A WristSubsystem object used to initialize the instance WristSubsystem
     * @param target    Holds the value of the targetPosition of this specific command instance
     */
    public SetWristCommand(WristSubsystem subsystem, double target)
    {
        m_WristSubsystem = subsystem;
        targetPosition = target;
        addRequirements(m_WristSubsystem);
    }

    /**
     * Stops the wrist at when the command is scheduled, and it starts the PID Control Loop with the targetPosition
     * as the
     * setPosition
     */
    @Override
    public void initialize()
    {
        m_WristSubsystem.stopMotor();
        m_WristSubsystem.setMotor(targetPosition);
    }

    @Override
    public void execute()
    {
    }

    /**
     * Stops the wrist when the command is removed from the command
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        m_WristSubsystem.stopMotor();
    }

    /**
     * Returns true if either limit switch is pressed or if the encoderPosition exceeds the setPosition, removing the
     * command from the command scheduler
     */
    @Override
    public boolean isFinished() {
        return WristPolicy.encoderPosition >= WristPolicy.setPosition || BogeyPolicy.lowLimit || BogeyPolicy.upLimit;
    }
}
