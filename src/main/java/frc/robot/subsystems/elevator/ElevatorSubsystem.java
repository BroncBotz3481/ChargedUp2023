// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorPolicy.PIDF;

/**
 * The Elevator subsystem on the robot
 */
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
   * Prevents elevator from extending past upper limit
   */
  private final DigitalInput          upperLimitSwitch;
  /**
   * Prevents elevator from extending past lower limit
   */
  private final DigitalInput          lowerLimitSwitch;

  /**
   * The constructor initializes the elevator motors and the encoders, as well as the PID Controller, and it sets the
   * right motor and encoder as inverted, and sets the PID constants
   */
  public ElevatorSubsystem()
  {
    leftElevatorMotor = new CANSparkMax(ElevatorPolicy.LEFT_ELEV_ID_PORT, MotorType.kBrushless);
    rightElevatorMotor = new CANSparkMax(ElevatorPolicy.RIGHT_ELEV_ID_PORT, MotorType.kBrushless);
    leftElevatorMotor.restoreFactoryDefaults();
    rightElevatorMotor.restoreFactoryDefaults();
    PIDController = rightElevatorMotor.getPIDController();
    leftElevatorMotor.setInverted(ElevatorPolicy.INV_LEFT);
    rightElevatorMotor.setInverted(ElevatorPolicy.INV_RIGHT);
    leftElevatorMotor.follow(rightElevatorMotor);
    leftElevatorMotor.setIdleMode(IdleMode.kBrake);
    rightElevatorMotor.setIdleMode(IdleMode.kBrake);
    rightEncoder = rightElevatorMotor.getEncoder();
    leftEncoder = leftElevatorMotor.getEncoder();
    //rightEncoder.setPositionConversionFactor(1 / ElevatorPolicy.elevatorGearRatio);
    PIDController.setFeedbackDevice(rightEncoder);
    upperLimitSwitch = new DigitalInput(ElevatorPolicy.UPPER_LIMIT_CHANNEL);
    lowerLimitSwitch = new DigitalInput(ElevatorPolicy.LOWER_LIMIT_CHANNEL);

    set(PIDF.PROPORTION, PIDF.INTEGRAL, PIDF.DERIVATIVE,
        PIDF.FEEDFORWARD, PIDF.INTEGRAL_ZONE);

    if (RobotBase.isSimulation())
    {
      REVPhysicsSim.getInstance().addSparkMax(rightElevatorMotor, 2.6f, 5676);
      REVPhysicsSim.getInstance().addSparkMax(leftElevatorMotor, 2.6f, 5676);

    }
  }

  /**
   * Sets the spark max closed loop PID values
   *
   * @param p  proportional gain constant
   * @param i  integral gain constant
   * @param d  derivative constant
   * @param f  feedforward constant
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
   * Moves the elevator with voltage
   *
   * @param power the power used to move the elevator
   */
  public void moveElevator(double power)
  {
    //System.out.println("This is the power of the Elevator before algorithms: " + power);
    ElevatorPolicy.elevatorPower = ElevatorPolicy.getElevatorPower(power,
                                                                   ElevatorPolicy.upLimit,
                                                                   ElevatorPolicy.lowLimit);
    //System.out.println("This is the power of the Elevator after algorithms: " + power);
    rightElevatorMotor.set(power);
  }

  /**
   * Runs PID to move the elevator
   *
   * @param targetPosition the target position for the PIDF loop, depending on the setpoint
   */
  public void runPID(double targetPosition)
  {
    //System.out.println("This is the Elevator PID set position before algorithms: " + targetPosition);
    ElevatorPolicy.setPosition = targetPosition;
    ElevatorPolicy.setPosition = ElevatorPolicy.getElevatorPosition(targetPosition,
                                                                    ElevatorPolicy.upLimit,
                                                                    ElevatorPolicy.lowLimit);
    //System.out.println("This is the Elevator PID set position after alogrithms: " + ElevatorPolicy.setPosition);
    PIDController.setReference(ElevatorPolicy.setPosition, ControlType.kPosition);
  }

  /**
   * Stops the elevator with voltage
   */
  public void stopEle()
  {
    moveElevator(0);
  }

  /**
   * Periodically fetches the right and left encoder velocities and positions and puts them inside the policy class
   */
  @Override
  public void periodic()
  {
    // This method will be called once per scheduler run
    System.out.println(rightElevatorMotor.get());
    ElevatorPolicy.rightEncoderVelocity = rightEncoder.getVelocity();
    ElevatorPolicy.rightEncoderPosition = rightEncoder.getPosition();
    ElevatorPolicy.leftEncoderVelocity = leftEncoder.getVelocity();
    ElevatorPolicy.leftEncoderPosition = leftEncoder.getPosition();
    if (RobotBase.isSimulation())
    {
      ElevatorPolicy.lowLimit = false;
      ElevatorPolicy.upLimit = false;
    } else
    {
      ElevatorPolicy.lowLimit = !lowerLimitSwitch.get();
      ElevatorPolicy.upLimit = !upperLimitSwitch.get();
    }


  }
}
