package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IDS.Wrist;
import frc.robot.subsystems.wrist.WristPolicy;
import frc.robot.subsystems.wrist.WristSubsystem;
import java.util.function.DoubleSupplier;


public class ManualWristCommand extends CommandBase
{

  private final WristSubsystem wristSubsystem;
  private final DoubleSupplier supplier;

  public ManualWristCommand(WristSubsystem wristSubsystem, DoubleSupplier wristSupplier)
  {
    this.wristSubsystem = wristSubsystem;
    this.supplier = wristSupplier;
    // each subsystem used by the command must be passed into the
    // addRequirements() method (which takes a vararg of Subsystem)
    addRequirements(this.wristSubsystem);
  }

  /**
   * The initial subroutine of a command.  Called once when the command is initially scheduled.
   */
  @Override
  public void initialize()
  {
    wristSubsystem.stopMotor();
  }

  /**
   * The main body of a command.  Called repeatedly while the command is scheduled. (That is, it is called repeatedly
   * until {@link #isFinished()}) returns true.)
   */
  @Override
  public void execute()
  {
    wristSubsystem.runMotor(supplier.getAsDouble());
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
    //return WristPolicy.upLimit || WristPolicy.lowLimit;
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
    if (interrupted)
    {
      WristPolicy.setPosition = WristPolicy.encoderPosition;
    }
  }
}
