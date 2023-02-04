// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WristSubsystem extends SubsystemBase {
  /** Creates a new WristSubsystem. */
  private final CANSparkMax wristMotor;
  private final RelativeEncoder encoder;
  private final SparkMaxPIDController PIDController;

  public WristSubsystem() {
    wristMotor = new CANSparkMax(WristPolicy.WRIST_ID_PORT,MotorType.kBrushless);
    encoder = wristMotor.getEncoder();
    PIDController = wristMotor.getPIDController();
    setPIDF(0, 0, 0, 0, 0);
  }

  public void setPIDF(double P, double I, double D, double F, double integralZone){
    PIDController.setP(P);
    PIDController.setI(I);
    PIDController.setD(D);
    PIDController.setFF(F);
    PIDController.setIZone(integralZone);
}

  public void runMotor(double power){
    WristPolicy.power = power;
    wristMotor.set(WristPolicy.power);
  }

  public void setMotor(double targetPosition){
    WristPolicy.position = targetPosition;
    PIDController.setReference(WristPolicy.position, ControlType.kPosition);
  }

  public void stopMotor(){
    runMotor(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    WristPolicy.encoderVelocity = encoder.getVelocity();
    WristPolicy.encoderPosition = encoder.getPosition();
  }

}
