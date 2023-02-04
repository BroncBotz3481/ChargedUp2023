// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.bogey;

import frc.robot.subsystems.elevator.ElevatorPolicy;

/** Add your docs here. */
public final class BogeyPolicy {
    public static final int BOGEY_ID_PORT = 0;
    public static double bogeyPower;
    public static double targetSpeed;
    public static double encoderVelocity;
    public static double encoderPosition;
    public static double elevatorPosition;
    public static double ratioPosition;

    public static final double low = 50;
    public static final double mid = 100;
    public static final double high = 150;

    public static boolean setPointDefault(){

        if(encoderPosition >= 0)
        {
            return true;
        }

        return false;
     }
     
    public static boolean isHigh(){
        return encoderPosition<high;
    }
    
    public static boolean isMid(){
        return encoderPosition<mid;
    }

    public static boolean isLow(){
        return encoderPosition<low;
    }
}
