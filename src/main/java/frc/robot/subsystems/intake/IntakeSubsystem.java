// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final CANSparkMax motorIntake;
  
  public IntakeSubsystem() {
    motorIntake = new CANSparkMax(IntakePolicy.INTAKE_ID_PORT, MotorType.kBrushless);
  }

  public void runIntake(double power){
    IntakePolicy.power = power;
    motorIntake.set(IntakePolicy.power);
  }

  public void stopIntake(){
    runIntake(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
