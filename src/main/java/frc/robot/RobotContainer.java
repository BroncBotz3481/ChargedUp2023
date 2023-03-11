// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.BogeyPresets;
import frc.robot.Constants.Drivebase;
import frc.robot.Constants.ElevatorPresets;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.WristPresets;
import frc.robot.commands.auto.AutonomousCommand;
import frc.robot.commands.bogey.ControlBogeyCommand;
import frc.robot.commands.bogey.ManualBogeyCommand;
import frc.robot.commands.bogey.SetBogeyCommand;
import frc.robot.commands.elevator.ControlElevatorCommand;
import frc.robot.commands.elevator.ManualElevatorCommand;
import frc.robot.commands.elevator.SetElevatorCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.StopIntakeCommand;
import frc.robot.commands.wrist.ControlWristCommand;
import frc.robot.commands.wrist.SetWristCommand;
import frc.robot.subsystems.bogey.BogeySubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.wrist.WristSubsystem;
import frc.robot.commands.drivebase.*;

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
  private final BogeySubsystem m_bogeySubsystem = new BogeySubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final WristSubsystem m_wristSubsystem = new WristSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  // private final CommandXboxController m_driverController = new
  // CommandXboxController(
  // OperatorConstants.kDriverControllerPort);
  private final CommandJoystick driverController = new CommandJoystick(OperatorConstants.kDriverControllerPort);
  private final CommandJoystick throttleController = new CommandJoystick(OperatorConstants.kThrottleControllerPort);
  private final CommandXboxController m_operatorController = new CommandXboxController(
      OperatorConstants.kOperatorControllerPort);

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
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
    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
            ? driverController.getRawAxis(1) * (((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2)
                * ((-1) * throttleController.getRawAxis(0) + 1) + Drivebase.THROTTLE_MIN)
            : 0,
        () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
            ? driverController.getRawAxis(0) * (((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2)
                * ((-1) * throttleController.getRawAxis(0) + 1) + Drivebase.THROTTLE_MIN)
            : 0,
        () -> (Math.abs(driverController.getRawAxis(4)) > .12) ? -driverController.getRawAxis(4)
            * (((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2) * ((-1) * throttleController.getRawAxis(0) + 1)
                + Drivebase.THROTTLE_MIN)
            : 0,
        () -> true, false);

    new Trigger(
        () -> Math.abs(throttleController.getRawAxis(3)) > 0.5 || (Math.abs(throttleController.getRawAxis(4)) > 0.5))
        .whileTrue((new AbsoluteDrive(drivebase,
            // Applies deadbands and inverts controls because joysticks
            // are back-right positive while robot
            // controls are front-left positive
            () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
                ? driverController.getRawAxis(1) * (((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2)
                    * ((-1) * throttleController.getRawAxis(0) + 1) + Drivebase.THROTTLE_MIN)
                : 0,
            () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
                ? driverController.getRawAxis(0) * (((Drivebase.THROTTLE_MAX - Drivebase.THROTTLE_MIN) / 2)
                    * ((-1) * throttleController.getRawAxis(0) + 1) + Drivebase.THROTTLE_MIN)
                : 0,
            () -> -throttleController.getRawAxis(4),
            () -> -throttleController.getRawAxis(3),
            false)));

    drivebase.setDefaultCommand(closedFieldRel);
    m_bogeySubsystem.setDefaultCommand(new ControlBogeyCommand(m_bogeySubsystem));
    m_elevatorSubsystem.setDefaultCommand(new ControlElevatorCommand(m_elevatorSubsystem));
    m_wristSubsystem.setDefaultCommand(new ControlWristCommand(m_wristSubsystem));
    m_intakeSubsystem.setDefaultCommand(new StopIntakeCommand(m_intakeSubsystem));

    new Trigger(
        () -> Math.abs(m_operatorController.getRawAxis(2)) > 0.5).whileTrue(new SpitCommand(m_intakeSubsystem));
    new JoystickButton(m_operatorController.getHID(), 5).whileTrue(new SpinCommand(m_intakeSubsystem));

    new Trigger(
        () -> Math.abs(m_operatorController.getRawAxis(3)) > 0.5).whileTrue(new SetWristCommand(WristPresets.FLAT));
    new JoystickButton(m_operatorController.getHID(), 2)
        .whileTrue((Commands.parallel(new SetWristCommand(WristPresets.FLAT),
            new SetElevatorCommand(ElevatorPresets.HOME), new SetBogeyCommand(BogeyPresets.HOME))));

    new JoystickButton(m_operatorController.getHID(), 4).whileTrue(
        ((Commands.parallel(new SetElevatorCommand(ElevatorPresets.TRAY_HEIGHT), new SpinCommand(m_intakeSubsystem)))));

    new JoystickButton(m_operatorController.getHID(), 3)
        .whileTrue((Commands.parallel(new SetElevatorCommand(ElevatorPresets.SLIDE_HEIGHT),
            new SetWristCommand(WristPresets.SLIDE_ANGLE))));


    new JoystickButton(m_operatorController.getHID(), 10).whileTrue((new SetBogeyCommand(BogeyPresets.HOME)));

    new JoystickButton(m_operatorController.getHID(), 9).whileTrue((new SetElevatorCommand(ElevatorPresets.HOME)));

    new Trigger(() -> Math.abs(m_operatorController.getRawAxis(5)) > 0.08).whileTrue(
      (new ManualBogeyCommand(m_bogeySubsystem, () -> m_operatorController.getRawAxis(5))));

    new Trigger(() -> Math.abs(m_operatorController.getRawAxis(1)) > 0.08).whileTrue(
        (new ManualElevatorCommand(m_elevatorSubsystem, () -> m_operatorController.getRawAxis(1))));

    new POVButton(m_operatorController.getHID(), 180).whileTrue(Commands.parallel(new SetElevatorCommand(ElevatorPresets.LOW), new SetBogeyCommand(BogeyPresets.LOW)));
    new POVButton(m_operatorController.getHID(), 270).whileTrue(Commands.parallel(new SetElevatorCommand(ElevatorPresets.MID), new SetBogeyCommand(BogeyPresets.MID)));
    new POVButton(m_operatorController.getHID(), 0).whileTrue(Commands.parallel(new SetElevatorCommand(ElevatorPresets.HIGH), new SetBogeyCommand(BogeyPresets.HIGH)));


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
}
