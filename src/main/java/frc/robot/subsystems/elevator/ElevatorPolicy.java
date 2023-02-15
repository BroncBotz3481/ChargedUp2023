// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

/**
 * This is the Elevator policy, holding all algorithmic functions for the Bogey Subsystem and Commands.
 * Holds all important values from the subsystem
 **/

/**
 * Add your docs here.
 */
public final class ElevatorPolicy {
    /**
     * CAN ID for left elevator motor
     */
    public static final int LEFT_ELEV_ID_PORT = 0;
    /**
     * CAN ID for right elevator motor
     */
    public static final int RIGHT_ELEV_ID_PORT = 1;
    /**
     * Pin channel for upper limit on roborio
     */
    public static final int UPPER_LIMIT_CHANNEL = 3;
    /**
     * Pin channel for lower limit on roborio
     */
    public static final int LOWER_LIMIT_CHANNEL = 4;
    /**
     * The left elevator motor is inversed
     */
    public static final boolean INV_LEFT = true;
    /**
     * The right elevator motor is not inversed
     */
    public static final boolean INV_RIGHT = false;
    /**
     * The power set to the elevator
     */
    public static double elevatorPower;
    /**
     * How fast the right elevator motor is spinning
     */
    public static double rightEncoderVelocity;
    /**
     * How fast the left elevator is spinning
     */
    public static double leftEncoderVelocity;
    /**
     * The position of the right elevator motor
     */
    public static double rightEncoderPosition;
    /**
     * The position of the left elevator motor
     */
    public static double leftEncoderPosition;
    /**
     * Target position for PID control loop
     */
    public static double setPosition;
    /**
     * This is the gear ratio for the number of times the motor has spin for the elevator to rotate once
     */
    public static final double elevatorGearRatio = 1;

    /**
     * Holds all PIDF constants for ElevatorSubsystem
     */
    public static class PIDF {
        /**
         * Feedforward constant for PID loop
         */
        public static final double FEEDFORWARD = 0;
        /**
         * Proportion constant for PID loop
         */
        public static final double PROPORTION = 0.01;
        /**
         * Integral constant for PID loop
         */
        public static final double INTEGRAL = 0.0;
        /**
         * Derivative constant for PID loop
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
     * @param desiredPower The desired power of the elevator motors
     * @param upperLimit   The upper limit
     * @param lowerLimit   The lower limit
     * @return returns the power depending on the state of the limits
     */
    public static double getElevatorPower(double desiredPower, boolean upperLimit, boolean lowerLimit) {
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
     * @param desiredPosition The desired position of the elevator
     * @param upperLimit      The upper limit position
     * @param lowerLimit      The lower limit position
     * @return returns the position depending on the state of the limits
     */
    public static double getElevatorPosition(double desiredPosition, boolean upperLimit, boolean lowerLimit) {
        if (upperLimit && desiredPosition > setPosition) {
            return setPosition - 1;
        } else if (lowerLimit && desiredPosition < setPosition) {
            return setPosition + 1;
        }
        return desiredPosition;
    }
}



