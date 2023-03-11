// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.wrist.WristPolicy;

/**
 * Wrist command that uses a PID Control Loop to bring the wrist to a target position
 */
public class SetWristCommand extends CommandBase
{

    /**
     * Is used as the set position for the PID Control Loop
     */
    private final double         targetPosition;

    /**
     * Initializes the WristSubsystem, targetPosition, and adds requirements
     *
     * @param target Holds the value of the targetPosition of this specific command instance
     */
    public SetWristCommand(double target) {
        targetPosition = target;
    }

    /**
     * Stops the wrist at when the command is scheduled, and it starts the PID Control Loop with the targetPosition as the setPosition
     */
    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        WristPolicy.setPosition = targetPosition;
    }

    /**
     * Stops the wrist when the command is removed from the command
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
    }

    /**
     * Returns true if either limit switch is pressed or if the encoderPosition exceeds the setPosition, removing the
     * command from the command scheduler
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
