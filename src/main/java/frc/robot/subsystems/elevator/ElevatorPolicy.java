// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

/** Add your docs here. */
public final class ElevatorPolicy {
    public static final int LEFT_ELEV_ID_PORT = 0;
    public static final int RIGHT_ELEV_ID_PORT = 1;
    public static final boolean INV_LEFT = true;
    public static final boolean INV_RIGHT = false;
    public static double elevatorPower;
    public static double rightEncoderVelocity, leftEncoderVelocity;
    public static double rightEncoderPosition, leftEncoderPosition;
    public static double setPosition;
    /**Constants Proportion, Integral, Derivative, FeedForward, and IntegralZone (PIDF) for Elevator */
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
}



