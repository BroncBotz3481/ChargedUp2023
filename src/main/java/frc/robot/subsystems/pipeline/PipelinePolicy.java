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

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import java.util.List;

/**
 * This is the Pipeline policy, holding all algorithmic functions for the psuedo Pipeline Subsystem and Commands. Holds
 * all important values from the subsystem
 **/
public final class PipelinePolicy
{

  /**
   * The current position of the robot on the field
   */
  public static Translation3d       currentPosition;
  /**
   * The april tag locations on the field.
   */
  public static AprilTagFieldLayout aprilTagFieldLayout;
  /**
   * Is true if Apriltag is in camera's sight, false if not
   */
  public static boolean             targetExists;
  /**
   * This double array holds yaw,pitch,area,and skew respectively in that order of the Apriltag
   */
  public static double[]            pipeline;
  /**
   * A Transform3d object that represents a transformation for a Pose3D
   */
  public static Transform3d         transformation;
  /**
   * List that holds the corners of the Apriltag
   */
  public static List                corners;

  /**
   * Updates the currentPosition of the robot based o
   *
   * @param bestCameraToTarget relative location of the robot to the AprilTag target
   * @param fiducialId         AprilTag ID
   */
  public static void updateLocation(Transform3d bestCameraToTarget, int fiducialId)
  {
    Pose3d aprilTag = aprilTagFieldLayout.getTagPose(fiducialId).get();
    currentPosition = aprilTag.getTranslation().minus(bestCameraToTarget.getTranslation());
  }

  public void aVisionProcessingAlgorithmThatAutomagicallyWorks()
  {
    //magic ensues...
  }

}
