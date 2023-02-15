// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WristSubsystem extends SubsystemBase
{

  /**
   * Creates a new WristSubsystem.
   * SPARKMax for Wrist Motor
   * Relative Encoder for encoder
   * PIDController declared
   */
  private final CANSparkMax           wristMotor;
  private final RelativeEncoder       encoder;
  private final SparkMaxPIDController PIDController;

  /**
   * The constructor motor initializes the wristMotor, encoder, and PIDController.
   */
  public WristSubsystem()
  {
    wristMotor = new CANSparkMax(WristPolicy.WRIST_ID_PORT, MotorType.kBrushless);
    encoder = wristMotor.getEncoder();
    PIDController = wristMotor.getPIDController();
    setPIDF(0.01, 0, 0, 0, 0);
  }
  /**
   * Sets the spark max closed loop PIDF values
   *
   * @param p  Proportional gain constant
   * @param i  Integral gain constant
   * @param d  Derivative constant
   * @param f  Feedforward constant
   * @param iz Integral Zone
   */
  public void setPIDF(double p, double i, double d, double f, double iz)
  {
    PIDController.setP(p);
    PIDController.setI(i);
    PIDController.setD(d);
    PIDController.setFF(f);
    PIDController.setIZone(iz);
  }

  /**
   * moves the Wrist with power
   */
  public void runMotor(double power)
  {
    WristPolicy.power = power;
    wristMotor.set(WristPolicy.power);
  }

  /**
   * Sets a specific position to the Wrist
   */
  public void setMotor(double targetPosition)
  {
    WristPolicy.setPosition = targetPosition;
    PIDController.setReference(WristPolicy.setPosition, ControlType.kPosition);
  }

  /**
   * Sets the motor power to be 0
   */
  public void stopMotor()
  {
    runMotor(0);
  }

  /**
   * Periodically gets the encoder velocity and position and puts it inside the policy class
   */
  @Override
  public void periodic()
  {
    // This method will be called once per scheduler run
    WristPolicy.encoderVelocity = encoder.getVelocity();
    WristPolicy.encoderPosition = encoder.getPosition();
  }

}
