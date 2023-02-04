/****************************** Header ******************************\
 Class Name: IntakePolicyClass
 File Name: IntakePolicy.java
 Summary: Contains constant subclasses and variables for commands, subsystems, and utility methods
 Project: BroncBotzFRC2023
 Copyright (c) BroncBotz.
 All rights reserved.

 Author(s): Shruti Venkat and Samuel Zhao
 Email: Shruti.venkat05@gmail.com
 \********************************************************************/

package frc.robot.subsystems.pipeline;

import edu.wpi.first.math.geometry.Transform3d;

import java.util.List;

public final class PipelinePolicy {
    public static boolean targetExists;

    public static double[] pipeline; //This will hold yaw,pitch,area,and skew respectively in that order

    public static Transform3d transformation;

    public static List corners;

    public void aVisionProcessingAlgorithmThatAutomagicallyWorks(){
        //magic ensues...
    }
}
