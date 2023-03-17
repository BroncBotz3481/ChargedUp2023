// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;

/**
 * Bogey Command that uses a PID Control Loop to bring the Bogey to a target
 * position
 */
public class SetBogeyCommand extends CommandBase {
    /**
     * Is used as the set position for the PID Control Loop
     */
    private final double targetPosition;
    /**
     * Boolean to determine the isFinished() behavior for autonomous use
     */
    private final boolean waitForSetpoint;

    /**
     * Initializes the BogeySubsystem, targetPosition, and adds requirements
     *
     * @param target used to initialize the targetPosition
     */
    public SetBogeyCommand(double target, boolean waitForSetpoint) {
        targetPosition = target;
        this.waitForSetpoint = waitForSetpoint;
    }

    /**
     * Stops the arm, then runs PID for the arm to reach target position upon
     * initialization when the command is
     * scheduled
     */
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        BogeyPolicy.setPosition = targetPosition;
    }

    /**
     * Stops the bogey when the command is removed from the command scheduler
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
    }

    /**
     * returns true if either limit switch is pressed or the encoder position is
     * greater than target position,
     * removing the command from the command scheduler
     */
    @Override
    public boolean isFinished() {
        if (waitForSetpoint) {
            if (Math.abs(BogeyPolicy.encoderPosition - BogeyPolicy.setPosition) < BogeyPolicy.acceptableTolerance) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
