// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.Auton;
import frc.robot.Constants.BogeyPresets;
import frc.robot.Constants.ElevatorPresets;
import frc.robot.Constants.WristPresets;
import frc.robot.commands.bogey.SetBogeyCommand;
import frc.robot.commands.elevator.SetElevatorCommand;
import frc.robot.commands.intake.SpinCommand;
import frc.robot.commands.intake.SpitCommand;
import frc.robot.commands.intake.StopIntakeCommand;
import frc.robot.commands.wrist.SetWristCommand;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.swerve.SwerveSubsystem;

import java.util.HashMap;
import java.util.List;

public final class Autos {

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    public static CommandBase BasicBlueAutoHigh1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("BasicBlueAutoHigh1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_high", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HIGH),
                new SetBogeyCommand(BogeyPresets.HIGH),
                new SetWristCommand(WristPresets.MID),
                new StopIntakeCommand(intake)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase BasicBlueAutoMid1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("BasicBlueAutoMid1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_mid", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.MID),
                new SetBogeyCommand(BogeyPresets.MID),
                new SetWristCommand(WristPresets.MID),
                new StopIntakeCommand(intake)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase BasicBlueAutoLow1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("BasicBlueAutoLow1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_low", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.LOW),
                new SetBogeyCommand(BogeyPresets.LOW),
                new SetWristCommand(WristPresets.FLAT),
                new StopIntakeCommand(intake)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }


    /**
     * Example static factory for an autonomous command.
     */
    public static CommandBase exampleAuto(SwerveSubsystem swerve) {
        PathPlannerTrajectory example = PathPlanner.loadPath("AdvancedBlueAuto2",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));
        // Simple path with holonomic rotation. Stationary start/end. Max velocity of 4
        // m/s and max accel of 3 m/s^2
        // PathPlannerTrajectory example = PathPlanner.generatePath(
        // new PathConstraints(4, 3),
        // new PathPoint(new Translation2d(0, 0), Rotation2d.fromDegrees(0),
        // Rotation2d.fromDegrees(0)),
        //// position, heading(direction of travel), holonomic rotation
        // new PathPoint(new Translation2d(1.0, 1.0), Rotation2d.fromDegrees(0),
        // Rotation2d.fromDegrees(0)),
        //// position, heading(direction of travel), holonomic rotation
        // new PathPoint(new Translation2d(3.0, 3.0), Rotation2d.fromDegrees(45),
        // Rotation2d.fromDegrees(-90))
        // // position, heading(direction of travel), holonomic rotation
        // );
        return Commands.sequence(new FollowTrajectory(swerve, example, true));
    }

    public static CommandBase AdvancedBlueAutoLow2(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoLow2",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_low", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.LOW),
                new SetBogeyCommand(BogeyPresets.LOW),
                new SetWristCommand(WristPresets.FLAT)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase AdvancedBlueAutoMid2(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoMid2",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_mid", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.MID),
                new SetBogeyCommand(BogeyPresets.MID),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase AdvancedBlueAutoHigh2(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoHigh2",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_high", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HIGH),
                new SetBogeyCommand(BogeyPresets.HIGH),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }


    public static CommandBase AdvancedBlueAutoHigh1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoHigh1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_high", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HIGH),
                new SetBogeyCommand(BogeyPresets.HIGH),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase AdvancedBlueAutoMid1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoMid1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_mid", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.MID),
                new SetBogeyCommand(BogeyPresets.MID),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase AdvancedBlueAutoLow1(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoLow1",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_low", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.LOW),
                new SetBogeyCommand(BogeyPresets.LOW),
                new SetWristCommand(WristPresets.FLAT)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }


    public static CommandBase AdvancedBlueAutoHigh1NoDock(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoHigh1NoDock",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_high", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HIGH),
                new SetBogeyCommand(BogeyPresets.HIGH),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }

    public static CommandBase AdvancedBlueAutoHigh2NoDock(SwerveSubsystem swerve, IntakeSubsystem intake) {
        List<PathPlannerTrajectory> example1 = PathPlanner.loadPathGroup("AdvancedBlueAutoHigh2NoDock",
                new PathConstraints(Auton.MAX_SPEED, Auton.MAX_ACCELERATION));

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("align_high", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HIGH),
                new SetBogeyCommand(BogeyPresets.HIGH),
                new SetWristCommand(WristPresets.MID)));
        eventMap.put("score", new SpitCommand(intake));
        eventMap.put("reset", new ParallelCommandGroup(
                new SetElevatorCommand(ElevatorPresets.HOME),
                new SetBogeyCommand(BogeyPresets.HOME),
                new SetWristCommand(WristPresets.HOME),
                new StopIntakeCommand(intake)));
        eventMap.put("drop_wrist_intake", new ParallelCommandGroup(new SetWristCommand(WristPresets.FLAT),
                new SpinCommand(intake)));
        eventMap.put("stop_outake", new ParallelCommandGroup(new StopIntakeCommand(intake),
                new SetWristCommand(WristPresets.HOME)));

        // Create the AutoBuilder. This only needs to be created once when robot code
        // starts, not every time you want
        // to create an auto command. A good place to put this is in RobotContainer
        // along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                swerve::getPose,
                // Pose2d supplier
                swerve::resetOdometry,
                // Pose2d consumer, used to reset odometry at the beginning of auto
                new PIDConstants(Auton.yAutoPID.p, Auton.yAutoPID.i, Auton.yAutoPID.d),
                // PID constants to correct for translation error (used to create the X and Y
                // PID controllers)
                new PIDConstants(Auton.angleAutoPID.p, Auton.angleAutoPID.i, Auton.angleAutoPID.d),
                // PID constants to correct for rotation error (used to create the rotation
                // controller)
                swerve::setChassisSpeeds,
                // Module states consumer used to output to the drive subsystem
                eventMap,
                false,
                // Should the path be automatically mirrored depending on alliance color.
                // Optional, defaults to true
                swerve
                // The drive subsystem. Used to properly set the requirements of path following
                // commands
        );
        return Commands.sequence(autoBuilder.fullAuto(example1));
    }


    public static CommandBase driveAndSpin(SwerveSubsystem swerve) {
        return Commands.sequence(
                new RepeatCommand(
                        new InstantCommand(() -> swerve.drive(new Translation2d(1, 0), 5, true, true), swerve)));
    }
}
