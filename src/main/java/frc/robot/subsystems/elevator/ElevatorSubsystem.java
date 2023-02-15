// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorPolicy.PIDF;

public class ElevatorSubsystem extends SubsystemBase
{
  /**
   * SparkMax for the left elevator motor
   */
  private final CANSparkMax           leftElevatorMotor;
  /**
   * SparkMax for the right elevator motor
   */
  private final CANSparkMax           rightElevatorMotor;
  /**
   * SparkMaxPIDController from the SparkMax
   */
  private final SparkMaxPIDController PIDController;
  /**
   * Right relative encoder from the SparkMax
   */
  private final RelativeEncoder       rightEncoder;
  /**
   * Left relative encoder from the SparkMax
   */
  private final RelativeEncoder       leftEncoder;

  /**
   * The constructor initializes the elevator motors and the encoders, as well as the
   * PID Controller, and it sets the right motor and encoder as inverted, and sets the PID constants
   */
  public ElevatorSubsystem()
  {
    leftElevatorMotor = new CANSparkMax(ElevatorPolicy.LEFT_ELEV_ID_PORT, MotorType.kBrushless);
    rightElevatorMotor = new CANSparkMax(ElevatorPolicy.RIGHT_ELEV_ID_PORT, MotorType.kBrushless);
    leftElevatorMotor.restoreFactoryDefaults();
    rightElevatorMotor.restoreFactoryDefaults();
    PIDController = rightElevatorMotor.getPIDController();
    leftElevatorMotor.setInverted(ElevatorPolicy.INV_LEFT);//True
    rightElevatorMotor.setInverted(ElevatorPolicy.INV_RIGHT);//False
    leftElevatorMotor.follow(rightElevatorMotor);
    rightEncoder = rightElevatorMotor.getEncoder();
    leftEncoder = leftElevatorMotor.getEncoder();
    set(PIDF.PROPORTION,PIDF.INTEGRAL,PIDF.DERIVATIVE,
            PIDF.FEEDFORWARD,PIDF.INTEGRAL_ZONE);
  }

  /**
   * sets the spark max closed loop PID values
   * @param p proportional gain constant
   * @param i integral gain constant
   * @param d derivative constant
   * @param f feedforward constant
   * @param iz integral zone constant
   */
  public void set(double p, double i, double d, double f, double iz)
  {
    PIDController.setP(p);
    PIDController.setI(i);
    PIDController.setD(d);
    PIDController.setFF(f);
    PIDController.setIZone(iz);
  }

  /**
   * moves the elevator with voltage
   * @param power the power used to move the elevator
   */
  public void moveElevator(double power)
  {
    ElevatorPolicy.elevatorPower = power;
    rightElevatorMotor.set(ElevatorPolicy.elevatorPower);
  }

  /**
   * runs PID to move the elevator
   * @param targetPosition the target position for the PIDF loop, depending on the setpoint
   */
  public void runPID(double targetPosition)
  {
    ElevatorPolicy.setPosition = targetPosition;
    PIDController.setReference(ElevatorPolicy.setPosition, ControlType.kPosition);
  }

  /**
   * stops the elevator with voltage
   */
  public void stopEle()
  {
    moveElevator(0);
  }

  /**
   * Periodically fetches the right and left encoder velocities and positions and puts them
   * inside the policy class
   */
  @Override
  public void periodic()
  {
    // This method will be called once per scheduler run
    ElevatorPolicy.rightEncoderVelocity = rightEncoder.getVelocity();
    ElevatorPolicy.rightEncoderPosition = rightEncoder.getPosition();
    ElevatorPolicy.leftEncoderVelocity = leftEncoder.getVelocity();
    ElevatorPolicy.leftEncoderPosition = leftEncoder.getPosition();
  }
}
