package frc.robot.commands.auto;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Auton;
import frc.robot.subsystems.swerve.SwerveSubsystem;

public class FollowTrajectory extends SequentialCommandGroup
{

  public FollowTrajectory(SwerveSubsystem drivebase, PathPlannerTrajectory trajectory, boolean resetOdometry)
  {
    addRequirements(drivebase);

    if (resetOdometry)
    {
      drivebase.resetOdometry(trajectory.getInitialHolonomicPose());
    }

    addCommands(
        new PPSwerveControllerCommand(
            trajectory,
            drivebase::getPose,
            Auton.xAutoPID.createPIDController(),
            Auton.yAutoPID.createPIDController(),
            Auton.angleAutoPID.createPIDController(),
            drivebase::setChassisSpeeds,
            drivebase)
        , new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
  }
}
