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
    public static double position;
    public static double rightEncoderVelocity,leftEncoderVelocity;
    public static double rightEncoderPosition,leftEncoderPosition;
    public static double setPosition;
    // public static final double low = 50;
    // public static final double mid = 100;
    // public static final double high = 150;

    // public static boolean isHigh(){
    //     return rightEncoderPosition > high;
    // }

    // public static boolean isMid(){
    //     return rightEncoderPosition > mid;
    // }

    // public static boolean isLow(){
    //     return rightEncoderPosition > mid;
    // }
}
