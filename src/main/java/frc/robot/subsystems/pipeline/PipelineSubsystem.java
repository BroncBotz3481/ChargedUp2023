package frc.robot.subsystems.pipeline;


import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

/**
 * The Psuedo Pipeline subsystem on the robot
 */
public class PipelineSubsystem extends SubsystemBase
{

  /**
   * The camera the robot uses
   */
  private final PhotonCamera         camera;
  /**
   * The result of the pipeline
   */
  private       PhotonPipelineResult result;
  /**
   * The AprilTag target
   */
  private       PhotonTrackedTarget  target;

  /**
   * Initializes the camera the robot uses
   */
  private PipelineSubsystem()
  {
    camera = new PhotonCamera("Integrated_Webcam");
    try
    {
      PipelinePolicy.aprilTagFieldLayout = AprilTagFields.kDefaultField.loadAprilTagLayoutField();
    } catch (Exception ignored)
    {

    }
  }

  /**
   * Periodically gets the pipeline result and pipeline target, then sends a boolean to the pipeline policy if the
   * camera sees a AprilTag, then sends the position of the target , then sends the translation of the camera, then
   * sends the corners of the AprilTag all to the policy class
   */
  @Override
  public void periodic()
  {
    result = camera.getLatestResult();
    PipelinePolicy.targetExists = result.hasTargets();
    target = result.getBestTarget();
    PipelinePolicy.pipeline = new double[]{target.getYaw(), target.getPitch(), target.getArea(), target.getSkew()};
    PipelinePolicy.transformation = target.getBestCameraToTarget();
    PipelinePolicy.corners = target.getDetectedCorners();
    PipelinePolicy.updateLocation(target.getBestCameraToTarget(), target.getFiducialId());
  }
}

