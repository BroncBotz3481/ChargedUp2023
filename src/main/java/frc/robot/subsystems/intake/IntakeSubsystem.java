// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The intake subsystem on the robot
 */
public class IntakeSubsystem extends SubsystemBase
{

  /**
   * SparkMax for the intake motor
   */
  private final CANSparkMax motorIntake;

  /**
   * The constructor initializes the intake motor
   */
  public IntakeSubsystem()
  {
    motorIntake = new CANSparkMax(IntakePolicy.INTAKE_ID_PORT, MotorType.kBrushless);
    motorIntake.restoreFactoryDefaults();
    motorIntake.setIdleMode(IdleMode.kCoast);
    motorIntake.setInverted(true);
  }

  /**
   * Moves the intake based on voltage passed in
   *
   * @param power the voltage passed in to move the intake motor
   */
  public void runIntake(double power)
  {
    IntakePolicy.power = power;
    System.out.println("This is the power of the Intake: " + IntakePolicy.power);
    motorIntake.set(IntakePolicy.power);
  }

  /**
   * Sets 0 voltage to the intake
   */
  public void stopIntake()
  {
    runIntake(0.0);
  }

  @Override
  public void periodic()
  {
  }
}
