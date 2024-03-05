// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  /** Creates a new LimelightSubsystem. */

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-boombox");

  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tl = table.getEntry("tl");

  static double limelightMountAngleDegrees = 0;
  static double limelightHeightInches = 0;
  static double goalHeightInches = 0;

  public LimelightSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
