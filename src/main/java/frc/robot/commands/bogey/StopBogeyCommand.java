// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.bogey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.subsystems.bogey.BogeySubsystem;

/**
 * Bogey Command that stops the Bogey
 */

public class StopBogeyCommand extends CommandBase
{

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    /**
     * A BogeySubsystem object
     */
    private final BogeySubsystem m_BogeySubsystem;

    /**
     * Initializes the BogeySubsystem and adds requirements
     *
     * @param subsystem used to initialize the BogeySubsystem
     */
    public StopBogeyCommand(BogeySubsystem subsystem) {
        m_BogeySubsystem = subsystem;
        addRequirements(subsystem);
    }

    /**
     * Stops the Bogey when the command is scheduled
     */
    @Override
    public void initialize() {
        m_BogeySubsystem.stopArm();
    }

    /**
     * Stops the Bogey every 20 ms while the command is scheduled
     */
    @Override
    public void execute() {
        m_BogeySubsystem.stopArm();
    }

    /**
     * Stops the Bogey when the command is removed from the command scheduler
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted)
    {
        m_BogeySubsystem.stopArm();
    }

    /**
     * returns true if either limit switch is pressed, removing the command from the command scheduler
     */
    @Override
    public boolean isFinished()
    {
        return BogeyPolicy.upLimit || BogeyPolicy.lowLimit;
    }
}
