// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  private final CANSparkMax leftElevatorMotor;
  private final CANSparkMax rightElevatorMotor;
  private final SparkMaxPIDController PIDController;
  private final RelativeEncoder rightEncoder;
  private final RelativeEncoder leftEncoder;
  public ElevatorSubsystem() {
    leftElevatorMotor = new CANSparkMax(ElevatorPolicy.LEFT_ELEV_ID_PORT, MotorType.kBrushless);
    rightElevatorMotor = new CANSparkMax(ElevatorPolicy.RIGHT_ELEV_ID_PORT, MotorType.kBrushless);
    PIDController = rightElevatorMotor.getPIDController();
    leftElevatorMotor.setInverted(ElevatorPolicy.INV_LEFT);//True
    rightElevatorMotor.setInverted(!ElevatorPolicy.INV_RIGHT);//False
    leftElevatorMotor.follow(rightElevatorMotor);
    rightEncoder = rightElevatorMotor.getEncoder();
    leftEncoder = leftElevatorMotor.getEncoder();
    set(0,0,0,0,0);
  }

  public void set(double p, double i, double d, double f, double iz) {
    PIDController.setP(p);
    PIDController.setI(i);
    PIDController.setD(d);
    PIDController.setFF(f);
    PIDController.setIZone(iz);
  }

  public void moveElevator(double power) {
    ElevatorPolicy.elevatorPower = power;
    rightElevatorMotor.set(ElevatorPolicy.elevatorPower);
  }

  public void pidMove(double targetSpeed) {
    ElevatorPolicy.targetSpeed = targetSpeed;
    PIDController.setReference(ElevatorPolicy.targetSpeed, ControlType.kVelocity);
    //ArmPolicy.presets();
  }

  public void stopEle() {
    moveElevator(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ElevatorPolicy.rightEncoderVelocity = rightEncoder.getVelocity();
    ElevatorPolicy.rightEncoderPosition = rightEncoder.getPosition();
    ElevatorPolicy.leftEncoderVelocity = leftEncoder.getVelocity();
    ElevatorPolicy.leftEncoderPosition = leftEncoder.getPosition();
  }
}
