// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.BogeyPresets;
import frc.robot.Constants.ElevatorPresets;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.WristPresets;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.auto.AutonomousCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.subsystems.bogey.BogeySubsystem;
import frc.robot.subsystems.bogey.BogeyPolicy;

import frc.robot.commands.bogey.ManualBogeyCommand;
import frc.robot.commands.bogey.ResetBogeyCommand;
import frc.robot.commands.bogey.SetBogeyCommand;

import frc.robot.commands.bogey.StopBogeyCommand;

import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.elevator.ElevatorPolicy;

import frc.robot.commands.elevator.ManualElevatorCommand;
import frc.robot.commands.elevator.ResetElevatorCommand;
import frc.robot.commands.elevator.SetElevatorCommand;
import frc.robot.commands.elevator.StopElevatorCommand;

import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.intake.IntakePolicy;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.StopIntakeCommand;

import frc.robot.subsystems.wrist.WristSubsystem;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.commands.wrist.ResetWristCommand;
import frc.robot.commands.wrist.SetWristCommand;
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

    //m_operatorController.blankTrigger().whileTrue(new CrapMyselfCommand(m_iHateLife));
    m_operatorController.leftTrigger().whileTrue(new SpitCommand(m_intakeSubsystem)); //bind spit command while left trig pressed
    m_operatorController.leftBumper().whileTrue(new SpinCommand(m_intakeSubsystem)); //bind spin command while left bumper pressed
    m_operatorController.rightTrigger().whileTrue(new SetWristCommand(m_wristSubsystem, WristPresets.DOWN)); //bind drop wrist while right trig pressed

    m_operatorController.b().onTrue(Commands.parallel(new ResetWristCommand(m_wristSubsystem), new ResetElevatorCommand(m_elevatorSubsystem), new ResetBogeyCommand(m_bogeySubsystem))); //runs all reset commands at once

    m_operatorController.rightStick().onTrue(new ResetBogeyCommand(m_bogeySubsystem)); 
    m_operatorController.leftStick().onTrue(new ResetElevatorCommand(m_elevatorSubsystem));

    m_operatorController.povDown().onTrue(Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.LOW), new SetBogeyCommand(m_bogeySubsystem, BogeyPresets.LOW)));
    m_operatorController.povLeft().onTrue(Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.MID), new SetBogeyCommand(m_bogeySubsystem, BogeyPresets.MID)));
    m_operatorController.povUp().onTrue(Commands.parallel(new SetElevatorCommand(m_elevatorSubsystem, ElevatorPresets.HIGH), new SetBogeyCommand(m_bogeySubsystem, BogeyPresets.HIGH)));

    new Trigger(()->{return m_operatorController.getRightY() > 0.05;}).whileTrue(new ManualBogeyCommand(m_bogeySubsystem, m_operatorController::getRightY));
    new Trigger(()->{return m_operatorController.getLeftY() > 0.05;}).whileTrue(new ManualElevatorCommand(m_elevatorSubsystem, m_operatorController::getLeftY));
  }
    

    //  new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    

  

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
