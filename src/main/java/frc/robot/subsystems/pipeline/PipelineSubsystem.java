package frc.robot.subsystems.pipeline;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonUtils;

import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform3d;

/**
 * The Psuedo Pipeline subsystem on the robot
 */
public class PipelineSubsystem extends SubsystemBase {
  /**
   * Initializes the camera, best/alt photon position estimator, apriltag field
   * layout
   */
  private PipelineSubsystem() {
    PipelinePolicy.camera = new PhotonCamera("Integrated_Webcam");
    PipelinePolicy.bestPhotonPoseEstimator = new PhotonPoseEstimator(PipelinePolicy.aprilTagFieldLayout,
        PoseStrategy.CLOSEST_TO_REFERENCE_POSE, PipelinePolicy.camera, PipelinePolicy.robotToCam);

    PipelinePolicy.altPhotonPoseEstimator = new PhotonPoseEstimator(PipelinePolicy.aprilTagFieldLayout,
        PoseStrategy.CLOSEST_TO_REFERENCE_POSE, PipelinePolicy.camera, PipelinePolicy.robotToCam);
    try {
      PipelinePolicy.aprilTagFieldLayout = AprilTagFields.kDefaultField.loadAprilTagLayoutField();
    } catch (Exception ignored) {

    }

  }

  /**
   * Periodically gets the pipeline result and pipeline target, then sends a
   * boolean to the pipeline policy if the
   * camera sees a AprilTag, then sends the position of the target , then sends
   * the translation of the camera, then
   * sends the corners of the AprilTag all to the policy class
   * updates location
   */
  @Override
  public void periodic() {
    PipelinePolicy.result = PipelinePolicy.camera.getLatestResult();
    PipelinePolicy.targetExists = PipelinePolicy.result.hasTargets();
    PipelinePolicy.target = PipelinePolicy.result.getBestTarget();
    PipelinePolicy.pipeline = new double[] { PipelinePolicy.target.getYaw(), PipelinePolicy.target.getPitch(),
    PipelinePolicy.target.getArea(), PipelinePolicy.target.getSkew() };
    PipelinePolicy.cameraToTargetTransformation = PipelinePolicy.target.getBestCameraToTarget();
    PipelinePolicy.corners = PipelinePolicy.target.getDetectedCorners();
    PipelinePolicy.updateLocation(PipelinePolicy.target.getFiducialId());
  }
}
