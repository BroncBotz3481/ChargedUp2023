// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.BogeyPresets;
import frc.robot.Constants.ElevatorPresets;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.WristPresets;
import frc.robot.commands.auto.Autos;
import frc.robot.commands.bogey.ManualBogeyCommand;
import frc.robot.commands.bogey.ResetBogeyCommand;
import frc.robot.commands.bogey.SetBogeyCommand;
import frc.robot.commands.drivebase.AbsoluteDrive;
import frc.robot.commands.drivebase.TeleopDrive;
import frc.robot.commands.elevator.ManualElevatorCommand;
import frc.robot.commands.elevator.ResetElevatorCommand;
import frc.robot.commands.elevator.SetElevatorCommand;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.StopIntakeCommand;
import frc.robot.commands.wrist.ResetWristCommand;
import frc.robot.commands.wrist.SetWristCommand;
import frc.robot.subsystems.bogey.BogeySubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import frc.robot.subsystems.wrist.WristSubsystem;
import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final BogeySubsystem    m_bogeySubsystem    = new BogeySubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final IntakeSubsystem   m_intakeSubsystem   = new IntakeSubsystem();
  private final WristSubsystem    m_wristSubsystem    = new WristSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController   =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandJoystick driverController = new CommandJoystick(1);

  // CommandJoystick driverController   = new CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();

    AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(drivebase,
                                                          // Applies deadbands and inverts controls because joysticks
                                                          // are back-right positive while robot
                                                          // controls are front-left positive
                                                          () -> (Math.abs(driverXbox.getLeftY()) >
                                                                 OperatorConstants.LEFT_Y_DEADBAND)
                                                                ? -driverXbox.getLeftY() : 0,
                                                          () -> (Math.abs(driverXbox.getLeftX()) >
                                                                 OperatorConstants.LEFT_X_DEADBAND)
                                                                ? -driverXbox.getLeftX() : 0,
                                                          () -> -driverXbox.getRightX(),
                                                          () -> -driverXbox.getRightY(),
                                                          false);
    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> (Math.abs(driverController.getRawAxis(1)) > OperatorConstants.LEFT_Y_DEADBAND)
              ? driverController.getRawAxis(1) : 0,
        () -> (Math.abs(driverController.getRawAxis(0)) > OperatorConstants.LEFT_X_DEADBAND)
              ? driverController.getRawAxis(0) : 0,
        () -> (Math.abs(driverController.getRawAxis(4)) > .12) ? -driverController.getRawAxis(4) : 0,
        () -> true,
        false);

    drivebase.setDefaultCommand(closedFieldRel);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    m_bogeySubsystem.setDefaultCommand(new ResetBogeyCommand(m_bogeySubsystem)); //Brings bogey all the way in
    m_elevatorSubsystem.setDefaultCommand(
        new ResetElevatorCommand(m_elevatorSubsystem)); //Brings elevator all the way down
    m_intakeSubsystem.setDefaultCommand(new StopIntakeCommand(m_intakeSubsystem)); //Stops intake from running
    m_wristSubsystem.setDefaultCommand(new ResetWristCommand(m_wristSubsystem)); //Brings wrist all the way up

    m_operatorController.leftTrigger().whileTrue(
        new SpitCommand(m_intakeSubsystem)); //Spits the element when left Trigger is pressed
    m_operatorController.leftBumper().whileTrue(
        new SpinCommand(m_intakeSubsystem)); //Spins the element in when left Bumper is pressed
    m_operatorController.rightTrigger().whileTrue(
        new SetWristCommand(m_wristSubsystem, WristPresets.DOWN)); //Drops wrist when right Trigger is pressed

    m_operatorController.b().whileTrue(
        Commands.parallel(new ResetWristCommand(m_wristSubsystem), new ResetElevatorCommand(m_elevatorSubsystem),
                          new ResetBogeyCommand(
                              m_bogeySubsystem))); //Brings in all fragile subsystems when B is pressed
    m_operatorController.y().whileTrue(
        Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.TRAY_HEIGHT),
                          new SpinCommand(m_intakeSubsystem)));
    m_operatorController.x().whileTrue(
        Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.SLIDE_HEIGHT),
                          new SetWristCommand(m_wristSubsystem, WristPresets.SLIDE_HEIGHT)));

    m_operatorController.rightStick().whileTrue(
        new ResetBogeyCommand(m_bogeySubsystem)); //When right stick is pressed the bogey is brought in
    m_operatorController.leftStick().whileTrue(new ResetElevatorCommand(
        m_elevatorSubsystem)); //When left stick is pressed the elevator is goes all the way down

    m_operatorController.povDown().whileTrue(
        Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.LOW),
                          new SetBogeyCommand(m_bogeySubsystem,
                                              BogeyPresets.LOW))); //When the down dpad is pressed the elevator and
    // bogey will go to a preset position to score low
    m_operatorController.povLeft().whileTrue(
        Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.MID),
                          new SetBogeyCommand(m_bogeySubsystem,
                                              BogeyPresets.MID))); //When the left dpad is pressed the elevator and
    // bogey will go to a preset position to score mid
    m_operatorController.povUp().whileTrue(
        Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.HIGH),
                          new SetBogeyCommand(m_bogeySubsystem,
                                              BogeyPresets.HIGH))); //When the up dpad is pressed the elevator and
    // bogey will go to a preset position to score high

    new Trigger(() -> m_operatorController.getRightY() > 0.05).whileTrue(new ManualBogeyCommand(m_bogeySubsystem,
                                                                                                m_operatorController::getRightY));//Manually control bogey using right
    // joysticks x-axis
    new Trigger(() -> m_operatorController.getLeftY() > 0.05).whileTrue(new ManualElevatorCommand(m_elevatorSubsystem,
                                                                                                  m_operatorController::getLeftY));//Manually control elevator using left
    // joysticks y-axis

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
  }

  //  new Trigger(m_exampleSubsystem::exampleCondition)
  //     .whileTrue(new ExampleCommand(m_exampleSubsystem));

  // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
  // cancelling on release.
  //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return Autos.exampleAuto(drivebase);
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
