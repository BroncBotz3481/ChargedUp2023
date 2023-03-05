// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.parser.PIDFConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static class OperatorConstants
  {

    public static final int kDriverControllerPort   = 0;
    public static final int kOperatorControllerPort = 1;

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND = 0.01;
    public static final double LEFT_Y_DEADBAND = 0.01;
  }

  public static class BogeyPresets
  {

    public static final double HIGH = 200;
    public static final double MID  = 150;
    public static final double LOW  = 100;
  }

  public static class ElevatorPresets
  {

    public static final double HIGH = 200;
    public static final double MID  = 150;
    public static final double LOW  = 100;

    public static final double TRAY_HEIGHT  = 125;
    public static final double SLIDE_HEIGHT = 75;
  }

  public static class WristPresets
  {

    public static final double HOME = 0;
    public static final double FLAT = 50;
    public static final double DOWN = 100;

    public static final double SLIDE_HEIGHT = 75;
  }

  public static class IDS
  {

    public static class Elevator
    {

      public static class CAN
      {

        public static final int left  = 0;
        public static final int right = 1;
      }

      public static class DIO
      {

        public static int upper = 0;
        public static int lower = 1;
      }
    }

    public static class Bogey
    {

      public static class CAN
      {

        public static final int main = 2;
      }

      public static class DIO
      {

        public static int upper = 2;
        public static int lower = 3;
      }
    }

    public static class Intake
    {

      public static class CAN
      {

        public static final int main = 3;
      }

    }

    public static class Wrist
    {

      public static class CAN
      {

        public static final int main = 4;
      }

      public static class DIO
      {

        public static int upper = 4;
        public static int lower = 5;
      }
    }
  }

  //SwerveDrive
  public static final double        ROBOT_MASS   = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final double        CHASSIS_MASS = ROBOT_MASS;
  public static final Translation3d CHASSIS_CG   = new Translation3d(0, 0, Units.inchesToMeters(8));
  public static final double        LOOP_TIME    = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static final class Auton
  {

    public static final PIDFConfig xAutoPID     = new PIDFConfig(0.7, 0, 0);
    public static final PIDFConfig yAutoPID     = new PIDFConfig(0.7, 0, 0);
    public static final PIDFConfig angleAutoPID = new PIDFConfig(0.4, 0, 0.01);

    public static final double MAX_SPEED        = 2;
    public static final double MAX_ACCELERATION = 2;
  }

  public static final class Drivebase
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

}
