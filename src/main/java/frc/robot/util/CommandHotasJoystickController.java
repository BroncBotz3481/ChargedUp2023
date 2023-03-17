package frc.robot.util;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * A version of {@link XboxController} with {@link Trigger} factories for command-based.
 *
 * @see XboxController
 */
@SuppressWarnings("MethodName")
public class CommandHotasJoystickController extends CommandGenericHID
{

  public CommandHotasJoystickController(int port)
  {
    super(port);
  }

  /**
   * Constructs a Trigger instance based around this angle of the default (index 0) POV on the HID, attached to
   * {@link CommandScheduler#getDefaultButtonLoop() the default command scheduler button loop}.
   *
   * <p>
   * The POV angles start at 0 in the up direction, and increase clockwise (e.g. right is 90, upper-left is 315).
   *
   * @param angle POV angle in degrees, or -1 for the center / not pressed.
   * @return a Trigger instance based around this angle of a POV on the HID.
   */
  public Trigger pov(int povId, int angle)
  {
    return pov(povId, angle, CommandScheduler.getInstance().getDefaultButtonLoop());
  }
}
