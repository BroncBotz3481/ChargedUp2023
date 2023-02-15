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
import frc.robot.subsystems.bogey.BogeyPolicy.PIDF;

public class BogeySubsystem extends SubsystemBase {
    /**
     * SparkMax for the bogey motor
     */
    private final CANSparkMax bogeyMotor;
    /**
     * SparxMaxPIDController from SparkMax
     */
    private final SparkMaxPIDController PIDController;
    /**
     * Relative encoder from the bogey motor
     */
    private final RelativeEncoder bogeyEncoder;

    /**
     * The constructor initializes the motor, pidcontroller, encoder, and the PIDF constants
     */
    public BogeySubsystem() {
        bogeyMotor = new CANSparkMax(BogeyPolicy.BOGEY_ID_PORT, MotorType.kBrushless);
        bogeyMotor.restoreFactoryDefaults();
        PIDController = bogeyMotor.getPIDController();
        bogeyEncoder = bogeyMotor.getEncoder();
        bogeyMotor.setIdleMode(IdleMode.kBrake);

        set(PIDF.PROPORTION, PIDF.INTEGRAL, PIDF.DERIVATIVE, PIDF.FEEDFORWARD, PIDF.INTEGRAL_ZONE);
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
    public void set(double p, double i, double d, double f, double iz) {
        PIDController.setP(p);
        PIDController.setI(i);
        PIDController.setD(d);
        PIDController.setFF(f);
        PIDController.setIZone(iz);
    }

    /**
     * Moves the arm with voltage
     *
     * @param power The power used to move the bogey motor
     */
    public void moveArm(double power) {
        BogeyPolicy.bogeyPower = power;
        bogeyMotor.set(BogeyPolicy.bogeyPower);
    }

    /**
     * @param targetPosition The set position for the PIDF Loop
     */
    public void runPID(double targetPosition) {
        BogeyPolicy.setPosition = targetPosition;
        PIDController.setReference(BogeyPolicy.setPosition, ControlType.kPosition);
    }

    /**
     * Stops the arm with voltage
     */
    public void stopArm() {
        moveArm(0);
    }

    /**
     * Periodically gets the encoder velocity and position and puts it inside the policy class
     */
    @Override
    public void periodic() {
        BogeyPolicy.encoderVelocity = bogeyEncoder.getVelocity();
        BogeyPolicy.encoderPosition = bogeyEncoder.getPosition();
    }
}

    
    
    

