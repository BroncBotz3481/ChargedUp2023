// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.Constants.*;
import frc.robot.commands.auto.AutonomousCommand;
import frc.robot.commands.bogey.ControlBogeyCommand;
import frc.robot.commands.bogey.ManualBogeyCommand;
import frc.robot.commands.bogey.SetBogeyCommand;
import frc.robot.commands.drivebase.AbsoluteDrive;
import frc.robot.commands.drivebase.TeleopDrive;
import frc.robot.commands.elevator.ControlElevatorCommand;
import frc.robot.commands.elevator.ManualElevatorCommand;
import frc.robot.commands.elevator.SetElevatorCommand;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.StopIntakeCommand;
import frc.robot.commands.leds.ConeLEDCommand;
import frc.robot.commands.leds.CubeLEDCommand;
import frc.robot.commands.leds.TurnOffLEDCommand;
import frc.robot.commands.wrist.ControlWristCommand;
import frc.robot.commands.wrist.SetWristCommand;
import frc.robot.subsystems.bogey.BogeySubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.leds.LEDSubsystem;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.wrist.WristSubsystem;

import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic
 * methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * trigger mappings) should be declared here.
 */
public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
    private final BogeySubsystem m_bogeySubsystem = new BogeySubsystem();
    private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
    private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
    private final WristSubsystem m_wristSubsystem = new WristSubsystem();
    private final LEDSubsystem m_ledSubsystem = new LEDSubsystem();

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandJoystick driverController = new CommandJoystick(OperatorConstants.kDriverControllerPort);
    private final CommandJoystick throttleController = new CommandJoystick(OperatorConstants.kThrottleControllerPort);
    private final CommandXboxController m_operatorController = new CommandXboxController(
            OperatorConstants.kOperatorControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary predicate, or via the
     * named factories in
     * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
     * for
     * {@link CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
     * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
     * Flight joysticks}.
     */
    private void configureBindings() {

        // Default Swerve Drive
        TeleopDrive closedFieldRel = new TeleopDrive(
                drivebase,
                () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
                        ? driverController.getRawAxis(1)
                                * RobotContainer.convertThrottleInput(throttleController.getRawAxis(0))
                        : 0,
                () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
                        ? driverController.getRawAxis(0)
                                * RobotContainer.convertThrottleInput(throttleController.getRawAxis(0))
                        : 0,
                () -> (Math.abs(driverController.getRawAxis(4)) > .12)
                        ? -driverController.getRawAxis(4)
                                * RobotContainer.convertThrottleInput(throttleController.getRawAxis(0))
                        : 0,
                () -> true, false);

        // Swerve Absolute Positioning Control
        new Trigger(
                () -> Math.abs(throttleController.getRawAxis(3)) > 0.5
                        || (Math.abs(throttleController.getRawAxis(4)) > 0.5))
                .whileTrue(new AbsoluteDrive(drivebase,
                        // Applies deadbands and inverts controls because joysticks
                        // are back-right positive while robot
                        // controls are front-left positive
                        () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
                                ? driverController.getRawAxis(1)
                                        * RobotContainer.convertThrottleInput(throttleController.getRawAxis(0))
                                : 0,
                        () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
                                ? driverController.getRawAxis(0)
                                        * RobotContainer.convertThrottleInput(throttleController.getRawAxis(0))
                                : 0,
                        () -> -throttleController.getRawAxis(4),
                        () -> -throttleController.getRawAxis(3),
                        false));

        // Reset the robot gyroscope
        new JoystickButton(driverController.getHID(), 3).onTrue((new InstantCommand(drivebase::zeroGyro)));

        // Point all modules toward the robot center, thus making the robot very
        // difficult to move. Forcing the robot to keep the current pose
        new JoystickButton(driverController.getHID(), 2)
                .whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));

        drivebase.setDefaultCommand(closedFieldRel);
        m_bogeySubsystem.setDefaultCommand(new ControlBogeyCommand(m_bogeySubsystem));
        m_elevatorSubsystem.setDefaultCommand(new ControlElevatorCommand(m_elevatorSubsystem));
        m_wristSubsystem.setDefaultCommand(new ControlWristCommand(m_wristSubsystem));
        m_intakeSubsystem.setDefaultCommand(new StopIntakeCommand(m_intakeSubsystem));
        m_ledSubsystem.setDefaultCommand(new TurnOffLEDCommand(m_ledSubsystem));

        new Trigger(() -> Math.abs(m_operatorController.getRawAxis(2)) > 0.5)
                .whileTrue(new SpitCommand(m_intakeSubsystem));
        new JoystickButton(m_operatorController.getHID(), 5).whileTrue(new SpinCommand(m_intakeSubsystem));

        new Trigger(() -> Math.abs(m_operatorController.getRawAxis(3)) > 0.5)
                .whileTrue(new SetWristCommand(WristPresets.FLAT));
        new JoystickButton(m_operatorController.getHID(), 2)
                .whileTrue(Commands.parallel(new SetWristCommand(WristPresets.FLAT),
                        new SetElevatorCommand(ElevatorPresets.HOME), new SetBogeyCommand(BogeyPresets.HOME)));

        new JoystickButton(m_operatorController.getHID(), 4).whileTrue(Commands
                .parallel(new SetElevatorCommand(ElevatorPresets.TRAY_HEIGHT), new SpinCommand(m_intakeSubsystem)));

        new JoystickButton(m_operatorController.getHID(), 3)
                .whileTrue(Commands.parallel(new SetElevatorCommand(ElevatorPresets.SLIDE_HEIGHT),
                        new SetWristCommand(WristPresets.SLIDE_ANGLE)));

        new JoystickButton(m_operatorController.getHID(), 10).whileTrue(new SetBogeyCommand(BogeyPresets.HOME));

        new JoystickButton(m_operatorController.getHID(), 9).whileTrue(new SetElevatorCommand(ElevatorPresets.HOME));

        new Trigger(() -> Math.abs(m_operatorController.getRawAxis(5)) > 0.08)
                .whileTrue(new ManualBogeyCommand(m_bogeySubsystem, () -> m_operatorController.getRawAxis(5)));

        new Trigger(() -> Math.abs(m_operatorController.getRawAxis(1)) > 0.08)
                .whileTrue(new ManualElevatorCommand(m_elevatorSubsystem, () -> m_operatorController.getRawAxis(1)));

        new POVButton(m_operatorController.getHID(), 180).whileTrue(
                Commands.parallel(new SetElevatorCommand(ElevatorPresets.LOW), new SetBogeyCommand(BogeyPresets.LOW)));
        new POVButton(m_operatorController.getHID(), 270).whileTrue(
                Commands.parallel(new SetElevatorCommand(ElevatorPresets.MID), new SetBogeyCommand(BogeyPresets.MID)));
        new POVButton(m_operatorController.getHID(), 0).whileTrue(Commands
                .parallel(new SetElevatorCommand(ElevatorPresets.HIGH), new SetBogeyCommand(BogeyPresets.HIGH)));

        new JoystickButton(m_operatorController.getHID(), 11).onTrue(new CubeLEDCommand(m_ledSubsystem));

        new JoystickButton(m_operatorController.getHID(), 12).onTrue(new ConeLEDCommand(m_ledSubsystem));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return new AutonomousCommand();

    }

    /**
     * Helper method to convert the throttle input from [-1,1] to [MIN, MAX] where
     * both MIN and MAX are on the interval (0,1]
     * See https://www.desmos.com/calculator/k7xyhg56vn for further explanation
     * 
     * @param input Input from throttle on the interval [-1,1]
     * @return Output on the interval (0,1]
     */
    private static double convertThrottleInput(double input) {
        double output = ((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2) * (-input + 1)
                + Drivebase.THROTTLE_MIN; // input value is negative because the throttle input is reversed by
                                          // default;
        return output;
    }
}
