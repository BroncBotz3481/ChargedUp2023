// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist;

/**
 * This is the Wrist policy, holding all algorithmic functions for the Wrist Subsystem and Commands.
 * Holds all important values from the subsystem
 **/
public final class WristPolicy {
    /**
     * CAN ID Port for Wrist Motor
     */
    public static final int WRIST_ID_PORT = 10;
    /**
     * Pin channel for upper limit on roborio
     */
    public static final int UPPER_LIMIT_CHANNEL = 5;
    /**
     * Pin channel for lower limit on roborio
     */
    public static final int LOWER_LIMIT_CHANNEL = 6;
    /**
     * This is the gear ratio for the number of times the motor has spin for the wrist to rotate once
     */
    public static final double wristGearRatio = 1;
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
            return setPosition - 1;
        } else if (lowerLimit && desiredPosition < setPosition) {
            return setPosition + 1;
        }
        return desiredPosition;
    }

}
