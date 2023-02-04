// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.auto.AutonomousCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.subsystems.bogey.BogeySubsystem;
import frc.robot.subsystems.bogey.BogeyPolicy;
import frc.robot.commands.bogey.BogeyHighCommand;
import frc.robot.commands.bogey.BogeyMidCommand;
import frc.robot.commands.bogey.BogeyLowCommand;
import frc.robot.commands.bogey.StopBogeyCommand;

import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.elevator.ElevatorPolicy;
import frc.robot.commands.elevator.ElevatorHighCommand;
import frc.robot.commands.elevator.ElevatorMidCommand;
import frc.robot.commands.elevator.ManualElevatorCommand;
import frc.robot.commands.elevator.ElevatorLowCommand;
import frc.robot.commands.elevator.StopElevatorCommand;

import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.intake.IntakePolicy;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.StopIntakeCommand;

import frc.robot.subsystems.wrist.WristSubsystem;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.commands.wrist.DropWristCommand;
import frc.robot.commands.wrist.RaiseWristCommand;
import frc.robot.commands.wrist.StopWristCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final BogeySubsystem m_bogeySubsystem = new BogeySubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final WristSubsystem m_wristSubsystem = new WristSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    m_bogeySubsystem.setDefaultCommand(new StopBogeyCommand(m_bogeySubsystem));//Replace with turtle command
    m_elevatorSubsystem.setDefaultCommand(new StopElevatorCommand(m_elevatorSubsystem));//Replace with turtle command
    m_intakeSubsystem.setDefaultCommand(new StopIntakeCommand(m_intakeSubsystem));
    m_wristSubsystem.setDefaultCommand(new StopWristCommand(m_wristSubsystem));//Replace with turtle command

    

    //  new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    new Trigger(()->{return m_operatorController.getLeftTriggerAxis() > 0.05;}).whileTrue(new ManualElevatorCommand(m_elevatorSubsystem, m_operatorController::getLeftY));
    

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
