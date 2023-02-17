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

/**
 * This is the Pipeline policy, holding all algorithmic functions for the psuedo Pipeline Subsystem and Commands.
 * Holds all important values from the subsystem
 **/
public final class PipelinePolicy {
    /**
     * Is true if Apriltag is in camera's sight, false if not
     */
    public static boolean targetExists;
    /**
     * This double array holds yaw,pitch,area,and skew respectively in that order of the Apriltag
     */
    public static double[] pipeline;
    /**
     * A Transform3d object that represents a transformation for a Pose3D
     */
    public static Transform3d transformation;
    /**
     * List that holds the corners of the Apriltag
     */
    public static List corners;

    public void aVisionProcessingAlgorithmThatAutomagicallyWorks() {
        //magic ensues...
    }
}
