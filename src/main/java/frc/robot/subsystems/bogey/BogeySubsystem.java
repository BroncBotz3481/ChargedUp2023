// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.bogey;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BogeySubsystem extends SubsystemBase {
  /** Creates a new BogeySubsystem. */
  private final CANSparkMax bogeyMotor;
  private final SparkMaxPIDController PIDController;
  private final RelativeEncoder bogeyEncoder;

  public BogeySubsystem() {
    bogeyMotor = new CANSparkMax(BogeyPolicy.BOGEY_ID_PORT, MotorType.kBrushless);
    PIDController = bogeyMotor.getPIDController();
    bogeyEncoder = bogeyMotor.getEncoder();
    set(0,0,0,0,0);
  }
///
  public void set(double p, double i, double d, double f, double iz) {
    PIDController.setP(p);
    PIDController.setI(i);
    PIDController.setD(d);
    PIDController.setFF(f);
    PIDController.setIZone(iz);
  }

  public void moveArm(double power) {
    BogeyPolicy.bogeyPower = power;
    bogeyMotor.set(BogeyPolicy.bogeyPower);
  }

  public void pidMove(double targetPosition) {
    BogeyPolicy.position = targetPosition;
    PIDController.setReference(BogeyPolicy.position, ControlType.kPosition);
  }

  public void stopArm() {
    moveArm(0);
  }

  @Override
  public void periodic() {
    BogeyPolicy.encoderVelocity = bogeyEncoder.getVelocity();
    BogeyPolicy.encoderPosition = bogeyEncoder.getPosition();
  }
}

    
    
    

