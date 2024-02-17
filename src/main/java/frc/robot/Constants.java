// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DriveConstants {
    // Constants that are not the same across all swerve modules

    // Motor CAN IDs
    public static final int kFrontLeftDriveID = 11;
    public static final int kFrontRightDriveID = 15;
    public static final int kRearLeftDriveID = 13;
    public static final int kRearRightDriveID = 17;
    public static final int kFrontLeftTurnID = 10;
    public static final int kFrontRightTurnID = 14;
    public static final int kRearLeftTurnID = 12;
    public static final int kRearRightTurnID = 16;

    // CANCoder CAN IDs
    public static final int kFrontLeftEncoderID = 6;
    public static final int kFrontRightEncoderID = 8;
    public static final int kRearLeftEncoderID = 7;
    public static final int kRearRightEncoderID = 9;

    // CANCoder magnetic offsets
    public static final double kFrontLeftMagneticOffset = -0.068848; //0.074951; //0.139404; //0.215332; //0.423584; //Version 1: 0.086670
    public static final double kFrontRightMagneticOffset = -0.379639; //-0.379639; //Version 1: 0.163086
    public static final double kRearLeftMagneticOffset = -0.055664; //-0.055664; //Version 1: -0.362793
    public static final double kRearRightMagneticOffset = -0.090820; //Version 1: 0.296387

    // Motor inversions
    public static final boolean kFrontLeftDriveInverted = true;
    public static final boolean kFrontRightDriveInverted = true;
    public static final boolean kRearLeftDriveInverted = true;
    public static final boolean kRearRightDriveInverted = true;

    // Distance between centers of right and left wheels on robot
    public static final double kTrackWidth = Units.inchesToMeters(20.25);
    // Distance between centers of front and back wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(20.25);

    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
      new Translation2d(kWheelBase / 2, -kTrackWidth / 2), // Front right
      new Translation2d(kWheelBase / 2, kTrackWidth / 2), // Front left
      new Translation2d(-kWheelBase / 2, -kTrackWidth / 2), // Rear right
      new Translation2d(-kWheelBase / 2, kTrackWidth / 2) // Rear left
    );
  }

  public static class ModuleConstants {
    // Constants that are the same across all swerve modules

    // PID values
    public static final double kTurnP = 0.4;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;

    // Motor conversion factors
    public static final double kDriveEncoderPositionFactor = 0.0434782608695652;
    public static final double kDriveEncoderVelocityFactor = kDriveEncoderPositionFactor / 60.0;
  }

  public static class AutoConstants {


    public static final double kMaxSpeedMetersPerSecond = 2;
   
  //  public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 5;
    public static final double kPYController = 5;
    public static final double kPThetaController = 2;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);

    public static final HolonomicPathFollowerConfig kHolonomicPathFollowerConfig = new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
      new PIDConstants(0.0001, 0.0, 0.0), // Translation PID constants
      new PIDConstants(0.0006, 0.0, 0.0), // Rotation PID constants
      0.1, // Max module speed, in m/s
      0.51, // Drive base radius in meters. Distance from robot center to furthest module.
      new ReplanningConfig() // Default path replanning config. See the API for the options here
    );
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final double kDriverControllerDeadband = 0.05;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorID = 18;
  }

  public static class ArmConstants {
    public static final int kArmMotorLeftID = 19;
    public static final int kArmMotorRightID = 20;
  }

  public static class ShooterConstants {
    public static final int kShooterMotorLeftID = 21;
    public static final int kShooterMotorRightID = 22;
  }
}
