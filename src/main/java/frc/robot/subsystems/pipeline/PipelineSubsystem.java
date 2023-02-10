package frc.robot.subsystems.pipeline;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

public class PipelineSubsystem extends SubsystemBase
{

  private PhotonCamera camera;

  private PhotonPipelineResult result;

  private PhotonTrackedTarget target;

  public PipelineSubsystem()
  {
    camera = new PhotonCamera("Integrated_Webcam");
  }

  @Override
  public void periodic()
  {
    result = camera.getLatestResult();
    PipelinePolicy.targetExists = result.hasTargets();
    target = result.getBestTarget();
    PipelinePolicy.pipeline = new double[]{target.getYaw(), target.getPitch(), target.getArea(), target.getSkew()};
    PipelinePolicy.transformation = target.getBestCameraToTarget();
    PipelinePolicy.corners = target.getDetectedCorners();
  }
}

