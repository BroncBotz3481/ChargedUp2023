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
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

import java.util.List;
import java.util.Optional;

import frc.robot.subsystems.pipeline.GlobalMeasurement;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.EstimatedRobotPose;



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

  public static GlobalMeasurement bestMeasurement;

  public static PhotonPoseEstimator bestPhotonPoseEstimator;

  public static PhotonPoseEstimator altPhotonPoseEstimator;

  
  public static PhotonCamera cam;

  public static boolean isConnected;

  public static Pose2d pEST;

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


  public static final Transform3d robotToCam =
  new Transform3d(
      new Translation3d(0.0, Units.inchesToMeters(0), Units.inchesToMeters(0)),
      new Rotation3d(0.0, 0.0, 0.0)); 

  /**
   * List that holds the corners of the Apriltag
   */
  public static List                corners;


  /**
   * Updates the currentPosition of the robot based on
   *
   * @param bestCameraToTarget relative location of the robot to the AprilTag target
   * @param fiducialId         AprilTag ID
   */
  public static void updateLocation(Transform3d bestCameraToTarget, PhotonPoseEstimator ppe, int fiducialId)
  {
    Pose3d aprilTag = aprilTagFieldLayout.getTagPose(fiducialId).get();
    currentPosition = aprilTag.getTranslation().minus(bestCameraToTarget.getTranslation());
    bestPhotonPoseEstimator = new PhotonPoseEstimator(PipelinePolicy.aprilTagFieldLayout, PoseStrategy.CLOSEST_TO_REFERENCE_POSE, cam, robotToCam);
    altPhotonPoseEstimator = new PhotonPoseEstimator(PipelinePolicy.aprilTagFieldLayout, PoseStrategy.CLOSEST_TO_REFERENCE_POSE, cam, robotToCam);

    /**TODO:
     * I need to pass in pEST into bestPhotonPoseEstimator
     * I need to add in GlobalMeasurement
     * OMAR PLS DO NOT JUDGE THIS, IT IS RAW!!
     */

    /*public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose, Pose2d pEST) {
      pEST.setReferencePose(prevEstimatedRobotPose);
      return pEST.update();
    }
    */




  }
  

  public void aVisionProcessingAlgorithmThatAutomagicallyWorks()
  {
    //magic ensues...
  }

}
