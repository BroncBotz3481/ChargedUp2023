// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.bogey;
/**
 * This is the Bogey policy, holding all algorithmic functions for the Bogey Subsystem and Commands.
 * Holds all important values from the subsystem
 **/
public final class BogeyPolicy {
    /**
     * CAN ID for the bogey motor
     */
    public static final int BOGEY_ID_PORT = 0;
    /**
     * The power set to Bogey Motor
     */
    public static double bogeyPower;
    /**
     * How fast the motor is spinning
     */
    public static double encoderVelocity;
    /**
     * The position of the motor
     */
    public static double encoderPosition;
    /**
     * Used to linearize the bogey with the elevator
     */
    public static double ratioPosition;
    /**
     * Target position for PID Control loop
     */
    public static double setPosition;

    /**
     * Holds all PIDF constants for Bogey
     */
    public static class PIDF {
        /**
         * Feedforward constant for PID Loop
         */
        public static final double FEEDFORWARD = 0.05;
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
