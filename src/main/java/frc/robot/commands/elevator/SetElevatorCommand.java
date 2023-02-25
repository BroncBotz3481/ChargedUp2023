// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class SetElevatorCommand extends CommandBase {

    /**
     * An ElevatorSubsystem object
     */
    private final ElevatorSubsystem m_ElevatorSubsystem;

    /**
     * Is used as the set position for the PID Control Loop
     */
    private final double targetPosition;

    /**
     * Initializes the elevator subsystem, initializes targetPosition, and adds reqiurements
     *
     * @param subsystem initializes the elevator subsystem
     * @param target    target position in meters
     */
    public SetElevatorCommand(ElevatorSubsystem subsystem, double target) {
        m_ElevatorSubsystem = subsystem;
        targetPosition = target;
        addRequirements(subsystem);
    }

    /**
     * Stops the elevator and starts PID Control Loop when the command is scheduled
     */
    @Override
    public void initialize() {
        m_ElevatorSubsystem.stopEle();
        m_ElevatorSubsystem.runPID(targetPosition);
    }

    @Override
    public void execute() {

    }

    /**
     *Stops the
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        m_ElevatorSubsystem.stopEle();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return ElevatorPolicy.setPosition >= targetPosition || ElevatorPolicy.lowLimit || ElevatorPolicy.upLimit;
    }
}
