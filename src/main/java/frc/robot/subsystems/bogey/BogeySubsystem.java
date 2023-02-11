// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.bogey;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BogeySubsystem extends SubsystemBase
{

  /**
   * Creates a new BogeySubsystem.
   */
  private final CANSparkMax bogeyMotor;
  private final SparkMaxPIDController PIDController;
  private final RelativeEncoder bogeyEncoder;


  /** 
   * The Constructor initializes the motor, pidcontroller and encoder
  */
  public BogeySubsystem()
  {
    bogeyMotor = new CANSparkMax(BogeyPolicy.BOGEY_ID_PORT, MotorType.kBrushless);
    bogeyMotor.restoreFactoryDefaults();
    PIDController = bogeyMotor.getPIDController();
    bogeyEncoder = bogeyMotor.getEncoder();
    bogeyMotor.setIdleMode(IdleMode.kBrake);
    
    set(BogeyPolicy.PROPORTION,BogeyPolicy.INTEGRAL,BogeyPolicy.DERIVATIVE,BogeyPolicy.FEEDFORWARD,BogeyPolicy.INTEGRAL_ZONE);
  }
  /**
    Initializes all PIDF constants for the PIDController 
  */
  public void set(double p, double i, double d, double f, double iz) {
    PIDController.setP(p);
    PIDController.setI(i);
    PIDController.setD(d);
    PIDController.setFF(f);
    PIDController.setIZone(iz);
  }

  /** 
   * Moves the arm with power as the parameter 
   * */
  public void moveArm(double power)
  {
    BogeyPolicy.bogeyPower = power;
    bogeyMotor.set(BogeyPolicy.bogeyPower);
  }

  /** 
   * Moves the bogey using a PID control loop 
   * */
  public void runPID(double targetPosition)
  {
    BogeyPolicy.setPosition = targetPosition;
    PIDController.setReference(BogeyPolicy.setPosition, ControlType.kPosition);
  }
  /** 
   * Stops the arm by passing in 0 power
  */

  public void stopArm()
  {
    moveArm(0);
  }

  @Override
  public void periodic()
  {
    BogeyPolicy.encoderVelocity = bogeyEncoder.getVelocity();
    BogeyPolicy.encoderPosition = bogeyEncoder.getPosition();
  }
}

    
    
    

