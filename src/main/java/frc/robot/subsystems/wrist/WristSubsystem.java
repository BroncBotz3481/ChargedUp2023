// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.wrist.WristPolicy;

public class WristSubsystem extends SubsystemBase {
  /** Creates a new WristSubsystem. */
  private CANSparkMax wristMotor;
  public WristSubsystem() {
    wristMotor = new CANSparkMax(0, null);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
