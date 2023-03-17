// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

import frc.robot.Constants.IDS.Wrist.CAN;
import frc.robot.Constants.IDS.Wrist.DIO;

/**
 * This is the Wrist policy, holding all algorithmic functions for the Wrist
 * Subsystem and Commands. Holds all important
 * values from the subsystem
 **/
public final class WristPolicy {

    /**
     * CAN ID Port for Wrist Motor
     */
    public static final int WRIST_ID_PORT = CAN.main;
    /**
     * Pin channel for upper limit on roborio
     */
    public static final int UPPER_LIMIT_CHANNEL = DIO.upper;
    /**
     * Pin channel for lower limit on roborio
     */
    public static final int LOWER_LIMIT_CHANNEL = DIO.lower;
    /**
     * This is the gear ratio for the number of times the motor has spin for the
     * wrist to rotate once
     */
    public static final double wristGearRatio = 81;
    /**
     * Power set to Wrist Motor
     */
    public static double power;
    /**
     * Position set to Wrist Motor
     */
    public static double setPosition;
    /**
     * How fast the motor is spinning
     */
    public static double encoderVelocity;
    /**
     * The position of the motor
     */
    public static double encoderPosition;
    /**
     * This tells us whether the upperLimit switch has been hit or not
     */
    public static boolean upLimit = false;
    /**
     * This tell us whether the lowerLimit switch has been hit or not
     */
    public static boolean lowLimit = false;
    /**
     * This is the lowest set point of the wrist
     */
    public static final double lowestSetPoint = 0;
    /**
     * This is the highest set point of the bogey
     */
    public static final double highestSetPoint = 90;
    /**
     * Acceptable tolerance for ending the SetWristCommand in autonomous
     */
    public static final double acceptableTolerance = 2;

    /**
     * Returns the power depending on the state of the limits
     *
     * @param desiredPower The desired power of the wrist motor
     * @param upperLimit   The upper limit
     * @param lowerLimit   The lower limit
     * @return returns the power depending on the state of the limits
     */
    public static double getWristPower(double desiredPower, boolean upperLimit, boolean lowerLimit) {
        if (upperLimit) {
            return 0;
        } else if (lowerLimit) {
            return 0;
        }
        return desiredPower;
    }

    /**
     * Returns the position depending on the state of the limits
     *
     * @param desiredPosition The desired position of the wrist
     * @param upperLimit      The upper limit position
     * @param lowerLimit      The lower limit position
     * @return returns the position depending on the state of the limits
     */
    public static double getWristPosition(double desiredPosition, boolean upperLimit, boolean lowerLimit) {
        if (upperLimit && desiredPosition > setPosition) {
            return lowestSetPoint;
        } else if (lowerLimit && desiredPosition < setPosition) {
            return highestSetPoint;
        }
        return desiredPosition;
    }

    /**
     * Holds all PIDF constants for WristSubsystem
     */
    public static class PIDF {

        /**
         * Feedforward constant for PID Loop
         */
        public static final double FEEDFORWARD = 0.0;
        /**
         * Proportion constant for PID Loop
         */
        public static final double PROPORTION = 0.01;
        /**
         * Integral constant for PID Loop
         */
        public static final double INTEGRAL = 0.0;
        /**
         * Derivative constant for PID Loop
         */
        public static final double DERIVATIVE = 0.0;
        /**
         * Integral zone constant for PID loop
         */
        public static final double INTEGRAL_ZONE = 0.0;
    }

}
