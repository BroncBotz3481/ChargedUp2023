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
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

/**
 * This is the Pipeline policy, holding all algorithmic functions for the psuedo Pipeline Subsystem and Commands. Holds
 * all important values from the subsystem
 **/
public final class PipelinePolicy
{

  /**
   * Camera to robot position
   */
  public static final Transform3d          robotToCam = new Transform3d(
      new Translation3d(0.0, Units.inchesToMeters(0), Units.inchesToMeters(0)),
      new Rotation3d(0.0, 0.0, 0.0));
  /**
   * The result of the pipeline
   */
  public static       PhotonPipelineResult result;
  /**
   * The AprilTag target
   */
  public static       PhotonTrackedTarget  target;
  /**
   * First choice photon position from camera
   */
  public static       PhotonPoseEstimator  bestPhotonPoseEstimator;
  /**
   * Alternative second choice photon position from camera
   */
  public static       PhotonPoseEstimator  altPhotonPoseEstimator;
  /**
   * The current position of the robot on the field
   */
  public static       Translation3d        currentPosition;
  /**
   * The camera the robot uses
   */
  public static PhotonCamera camera;
  /**
   * The april tag locations on the field.
   */
  public static AprilTagFieldLayout aprilTagFieldLayout;
  /**
   * Is true if Apriltag is in camera's sight, false if not
   */
  public static boolean targetExists;
  /**
   * This double array holds yaw,pitch,area,and skew respectively in that order of the Apriltag
   */

  public static       double[]             pipeline;
  /**
   * A Transform3d object that represents a transformation for a Pose3D
   */
  public static Transform3d cameraToTargetTransformation;
  /**
   * List that holds the corners of the Apriltag
   */
  public static List corners;

  /**
   * Updates the currentPosition of the robot based on
   *
   * @param fiducialId AprilTag ID
   */
  public static void updateLocation(int fiducialId)
  {
    Pose3d aprilTag = new Pose3d();
    if (aprilTagFieldLayout.getTagPose(fiducialId).isPresent())
    {
      aprilTag = aprilTagFieldLayout.getTagPose(fiducialId).get();
    }
    currentPosition = aprilTag.getTranslation().minus(cameraToTargetTransformation.getTranslation());

  }

  /**
   * Recieves previous estimated robot position and sets reference positions
   *
   * @param prevEstimatedRobotPose Previous estimated robot position
   * @return updated best photon position
   */
  public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose)
  {
    bestPhotonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
    return bestPhotonPoseEstimator.update();

  }

}
