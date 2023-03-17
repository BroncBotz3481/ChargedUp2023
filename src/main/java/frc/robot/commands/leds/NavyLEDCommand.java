// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.leds;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.leds.LEDSubsystem;

// /**
//  * LED command that turns off LED strip
//  */
// public class NavyLEDCommand extends CommandBase
// {

//   /**
//    * An object of LEDSubsystem
//    */
//   @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
//   private final LEDSubsystem m_LedSubsystem;

//   /**
//    * Initializes the LEDSubsystem and adds requirments
//    *
//    * @param subsystem A LEDSubsystem used to initialize the instance LEDSubsystem
//    */
//   public NavyLEDCommand(LEDSubsystem subsystem)
//   {
//     m_LedSubsystem = subsystem;
//     addRequirements(subsystem);
//   }

//   /**
//    * Turns off the LED strip
//    */
//   @Override
//   public void initialize()
//   {
//     m_LedSubsystem.setLEDStrip(32, 42, 68);
//   }

//   @Override
//   public void execute()
//   {

//   }

//   @Override
//   public void end(boolean interrupted)
//   {

//   }

//   @Override
//   public boolean isFinished()
//   {
//     return false;
//   }
// }
