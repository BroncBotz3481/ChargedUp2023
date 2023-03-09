// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.wrist.WristPolicy.PIDF;

/**
 * The wrist subsystem on the robot
 */
public class WristSubsystem extends SubsystemBase {

    /**
     * SparkMax for the wrist motor
     */
    private final CANSparkMax wristMotor;
    /**
     * Relative encoder from the SparkMax
     */
    private final RelativeEncoder encoder;
    /**
     * SparkMaxPIDController from the SparkMax
     */
    private final SparkMaxPIDController PIDController;
    /**
     * Prevents wrist from extending past upper limit
     */
    private final DigitalInput upperLimitSwitch;
    /**
     * Prevents wrist from extending past lower limit
     */
    private final DigitalInput lowerLimitSwitch;

    /**
     * The constructor motor initializes the wristMotor, encoder, and PIDController.
     */

    public WristSubsystem() {
        wristMotor = new CANSparkMax(WristPolicy.WRIST_ID_PORT, MotorType.kBrushless);
        wristMotor.restoreFactoryDefaults();
        encoder = wristMotor.getEncoder();
        encoder.setPositionConversionFactor(1 / WristPolicy.wristGearRatio);
        PIDController = wristMotor.getPIDController();
        PIDController.setFeedbackDevice(encoder);
        setPIDF(PIDF.PROPORTION, PIDF.INTEGRAL, PIDF.DERIVATIVE, PIDF.FEEDFORWARD, PIDF.INTEGRAL_ZONE);
        upperLimitSwitch = new DigitalInput(WristPolicy.UPPER_LIMIT_CHANNEL);
        lowerLimitSwitch = new DigitalInput(WristPolicy.LOWER_LIMIT_CHANNEL);
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
    public void setPIDF(double p, double i, double d, double f, double iz) {
        PIDController.setP(p);
        PIDController.setI(i);
        PIDController.setD(d);
        PIDController.setFF(f);
        PIDController.setIZone(iz);
    }

    /**
     * Moves the arm with voltage
     *
     * @param power The power used to move the wrist motor
     */
    public void runMotor(double power) {
        System.out.println("This is the power of the Wrist before algorithms: " + power);
        WristPolicy.power = WristPolicy.getWristPower(power, upperLimitSwitch.get(), lowerLimitSwitch.get());
        System.out.println("This is the power of the Wrist after algorithm: " + WristPolicy.power);
        wristMotor.set(WristPolicy.power);
    }

    /**
     * Runs PID control loop
     *
     * @param targetPosition The set position for the PIDF loop
     */
    public void setMotor(double targetPosition) {
        System.out.println("This is the set position of the PID for Wrist before algorithms: " + targetPosition);
        WristPolicy.setPosition = targetPosition;
        WristPolicy.setPosition = WristPolicy.getWristPosition(targetPosition,
                upperLimitSwitch.get(),
                lowerLimitSwitch.get());
        System.out.println("This is the set position of the PID for Wrist after algorithms: " + WristPolicy.setPosition);
        PIDController.setReference(WristPolicy.setPosition, ControlType.kPosition);
    }

    /**
     * Stops the wrist with voltage
     */
    public void stopMotor() {
        runMotor(0);
    }

    /**
     * Periodically gets the encoder velocity and position and puts it inside the policy class
     */
    @Override
    public void periodic() {
        WristPolicy.encoderVelocity = encoder.getVelocity();
        WristPolicy.encoderPosition = encoder.getPosition();
        WristPolicy.lowLimit = lowerLimitSwitch.get();
        WristPolicy.upLimit = upperLimitSwitch.get();
    }

}
