// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


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
    public static final int kThrottleControllerPort = 1;
    public static final int kOperatorControllerPort = 2;
    public static final int k_m_DriverControllerPort = 3;
    


    public static final double LEFT_X_DEADBAND = 0.1;

    public static final double LEFT_Y_DEADBAND = 0.1;
  }

  public static class BogeyPresets
  {

    public static final double HIGH = 2;
    public static final double MID  = 150;
    public static final double LOW  = 20;

    public static final double HOME = 0;
  }

  public static class ElevatorPresets
  {

    public static final double HIGH = -18;
    public static final double MID  = -10;
    public static final double LOW = 0;

    public static final double TRAY_HEIGHT  = 125;
    public static final double SLIDE_HEIGHT = 75;

    public static final double HOME = 0;

  }

  public static class WristPresets
  {

    public static final double HOME        = 90;
    public static final double FLAT        = 0;
    public static final double MID         = 0.35;
    public static final double SLIDE_ANGLE = 33.69;
  }

  public static class IDS
  {

    public static class Elevator
    {

      public static class CAN
      {


        public static final int left  = 1;
        public static final int right = 8;
      }

      public static class DIO
      {

        public static int upper = 1;
        public static int lower = 7;
      }
    }

    public static class Bogey
    {

      public static class CAN
      {

        public static final int main = 17;
      }

      public static class DIO
      {

        public static int upper = 2;
        public static int lower = 0;
      }
    }

    public static class Intake
    {

      public static class CAN
      {

        public static final int main = 15;
      }

    }

    public static class Wrist
    {

      public static class CAN
      {

        public static final int main = 16;
      }

      public static class DIO
      {

        public static int upper = 4;
        public static int lower = 5;
      }
    }

    public static class LED
    {

      public static class PWM
      {

        public static int main = 0;

      }
    }
  }

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
    public static final double THROTTLE_MIN    = 0.4; // possibly up the min
    public static final double THROTTLE_MAX    = 1.0;
    // min, max
  }

}
