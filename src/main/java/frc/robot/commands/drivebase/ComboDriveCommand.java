package frc.robot.commands.drivebase;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.swerve.SwerveSubsystem;
import java.util.function.DoubleSupplier;
import swervelib.SwerveController;


public class ComboDriveCommand extends CommandBase
{

  private final SwerveSubsystem swerveSubsystem;
  private final DoubleSupplier  vX, vY;
  private final DoubleSupplier rightX, rightY;
  private final DoubleSupplier leftTrigger, rightTrigger;
  private final DoubleSupplier   throttle;
  private final boolean          isOpenLoop;
  private final SwerveController controller;
  private       double           headingOffset = 0;
  private final double           angularVelocity;

  /**
   * Combination Drive Command for Absolute Drive with some teleop-esq features.
   *
   * @param swerveSubsystem Swerve Drive subsystem.
   * @param vX              Translation velocity in the X direction (away from alliance wall)
   * @param vY              Translation velocity in the Y direction (left)
   * @param rightX          Joystick X for absolute drive.
   * @param rightY          Joystick Y for absolute drive.
   * @param leftTrigger     Left trigger value.
   * @param rightTrigger    Right trigger value.
   * @param throttle        Throttle value.
   * @param isOpenLoop      OPenloop controll.
   */
  public ComboDriveCommand(SwerveSubsystem swerveSubsystem, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier rightX,
                           DoubleSupplier rightY, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger,
                           DoubleSupplier throttle,
                           boolean isOpenLoop)
  {
    this.swerveSubsystem = swerveSubsystem;
    // each subsystem used by the command must be passed into the
    // addRequirements() method (which takes a vararg of Subsystem)
    this.vX = vX;
    this.vY = vY;
    this.rightX = rightX;
    this.rightY = rightY;
    this.leftTrigger = leftTrigger;
    this.rightTrigger = rightTrigger;
    this.isOpenLoop = isOpenLoop;
    this.throttle = throttle;
    this.controller = swerveSubsystem.getSwerveController();
    this.angularVelocity = controller.config.maxAngularVelocity;
    addRequirements(this.swerveSubsystem);
  }

  /**
   * The initial subroutine of a command.  Called once when the command is initially scheduled.
   */
  @Override
  public void initialize()
  {
  }

  /**
   * The main body of a command.  Called repeatedly while the command is scheduled. (That is, it is called repeatedly
   * until {@link #isFinished()}) returns true.)
   */
  @Override
  public void execute()
  {
    double xVelocity = (Math.pow(vX.getAsDouble(), 3) * controller.config.maxSpeed) * throttle.getAsDouble();
    double yVelocity = (Math.pow(vY.getAsDouble(), 3) * controller.config.maxSpeed) * throttle.getAsDouble();
//    double        angVelocity = Math.pow(rightX.getAsDouble(), 3) * controller.config.maxAngularVelocity;
    controller.config.maxAngularVelocity = angularVelocity * throttle.getAsDouble();
//    Translation2d translation = new Translation2d(xVelocity, yVelocity);
    if (Math.abs(leftTrigger.getAsDouble()) > 0.5 || rightTrigger.getAsDouble() > 0.5)
    {
      headingOffset += Math.toDegrees(0.5);
    } else
    {
      headingOffset = 0;
    }
//    if (absoluteDrive.getAsBoolean())
    {
      // Get the desired chassis speeds based on a 2 joystick module.
      ChassisSpeeds desiredSpeeds = controller.getTargetSpeeds(xVelocity, yVelocity,
                                                               controller.getAngleRadians(rightX.getAsDouble(),
                                                                                          rightY.getAsDouble()) +
                                                               headingOffset,
                                                               swerveSubsystem.getYaw().getRadians());

      // Limit velocity to prevent tippy
      Translation2d translation = SwerveController.getTranslation2d(desiredSpeeds);
//    translation = SwerveMath.limitVelocity(translation, swerve.getFieldVelocity(), swerve.getPose(),
//                                           Constants.LOOP_TIME,
//                                           Constants.CHASSIS_MASS, Constants.ROBOT_MASS, Constants.CHASSIS_CG,
//                                           swerve.getSwerveDriveConfiguration());
      SmartDashboard.putNumber("LimitedTranslation", translation.getX());
      SmartDashboard.putString("Translation", translation.toString());
      double angVelocity = desiredSpeeds.omegaRadiansPerSecond;
//    }

      // Make the robot move
      swerveSubsystem.drive(translation, angVelocity, true, isOpenLoop);
    }
  }

  /**
   * <p>
   * Returns whether this command has finished. Once a command finishes -- indicated by this method returning true --
   * the scheduler will call its {@link #end(boolean)} method.
   * </p><p>
   * Returning false will result in the command never ending automatically. It may still be cancelled manually or
   * interrupted by another command. Hard coding this command to always return true will result in the command executing
   * once and finishing immediately. It is recommended to use *
   * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand} for such an operation.
   * </p>
   *
   * @return whether this command has finished.
   */
  @Override
  public boolean isFinished()
  {
    // TODO: Make this return true when this Command no longer needs to run execute()
    return false;
  }

  /**
   * The action to take when the command ends. Called when either the command finishes normally -- that is it is called
   * when {@link #isFinished()} returns true -- or when  it is interrupted/canceled. This is where you may want to wrap
   * up loose ends, like shutting off a motor that was being used in the command.
   *
   * @param interrupted whether the command was interrupted/canceled
   */
  @Override
  public void end(boolean interrupted)
  {

  }
}
