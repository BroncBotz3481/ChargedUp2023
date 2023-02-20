// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

}
