// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class SetElevatorCommand extends CommandBase {

    /**
     * Uses ElevatorSubsystem
     */
    private final ElevatorSubsystem m_ElevatorSubsystem;

    /**
     * Target Position for Elevator
     */
    private final double targetPosition;


    /**
     * Initializes the elevator subsystem
     *
     * @param subsystem initializes the elevator subsystem
     * @param target    target position in meters
     */
    public SetElevatorCommand(ElevatorSubsystem subsystem, double target) {
        // Use addRequirements() here to declare subsystem dependencies.
        m_ElevatorSubsystem = subsystem;
        targetPosition = target;
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.

    /**
     * stops elevator at start of command runs PID to targetPosition at the start of command
     */
    @Override
    public void initialize() {
        m_ElevatorSubsystem.stopEle();
        m_ElevatorSubsystem.runPID(targetPosition);
    }

    // Called every time the scheduler runs while the command is scheduled.

    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.

    /**
     * stops Elevator at the end of the command
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
