// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new LimelightSubsystem. */

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-boombox");

  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tl = table.getEntry("tl");

  static double limelightMountAngleDegrees = 0;
  static double limelightLensHeightInches = 0;
  static double goalHeightInches = 0;

  double targetOffsetAngleVertical = ty.getDouble(0.0);
  double targetOffsetAngleHorozontal = tx.getDouble(0.0);
  double targetArea = ta.getDouble(0.0);
  double targetSkew = tl.getDouble(0.0);

  private double targetValue;
  private double turnOutput;
  private double driveOutput;
  private final double MAX_STEER = 0.1;
  private final double STEER_K = 0.075;

  public LimelightSubsystem() {
    NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tl = table.getEntry("tl");

        double targetOffsetAngle_Vertical = ty.getDouble(0.0);
        double targetOffsetAngle_Horizontal = tx.getDouble(0.0);
        double targetArea = ta.getDouble(0.0);
        double targetSkew = tl.getDouble(0.0);

        SmartDashboard.putNumber("tx", targetOffsetAngle_Horizontal);
        SmartDashboard.putNumber("ty", targetOffsetAngle_Vertical);
        SmartDashboard.putNumber("ta", targetArea);
        SmartDashboard.putNumber("tl", targetSkew);

        
        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);
        double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        SmartDashboard.putNumber("Distance to Hub", distanceFromLimelightToGoalInches);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getDistance() {
    double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngleVertical;
    double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    System.out.print(distanceFromLimelightToGoalInches);
    return distanceFromLimelightToGoalInches;

}


private double clamp(double in, double minval, double maxval) {
    if (in > maxval) {
      return maxval;
    }
    else if (in < minval) {
      return minval;
    }
    else {
      return in;
    }
}

public double trackTurn() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-khonsu");
    targetOffsetAngleHorozontal = table.getEntry("tx").getDouble(0.0);
    targetValue = table.getEntry("tv").getDouble(0.0);
    SmartDashboard.putNumber("tv", targetValue);

    if (targetValue == 1) {
        turnOutput = targetOffsetAngleHorozontal * STEER_K; //or divid by max value (27 degrees)
        turnOutput = clamp(turnOutput, -MAX_STEER, MAX_STEER);
        return turnOutput;
    }
    else {
        return 0;
    }
}

public void flash() {
  LimelightHelpers.setLEDMode_ForceBlink("limelight-boombox");
}

}
