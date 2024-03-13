// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AprilTag;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveStraightTagCmd extends Command {
  private final SwerveSubsystem m_SwerveSubsystem;

  private final NetworkTableInstance m_inst = NetworkTableInstance.getDefault();
  private final NetworkTable m_limelighTable;
  private final PIDController m_yvalPidController = new PIDController(0.008, 0, 0);
  /** Creates a new TestAprilTag. */
  public DriveStraightTagCmd(SwerveSubsystem swerveSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelighTable = m_inst.getTable("limelight-boombox");
    m_SwerveSubsystem = swerveSubsystem;

    addRequirements(m_SwerveSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // SmartDashboard.putNumber("tx", targetX);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double targetX = m_limelighTable.getEntry("tx").getDouble(0);
    double targetY = m_limelighTable.getEntry("ty").getDouble(0);
        // double targetY = m_limelighTable.getEntry("tY").getDouble(0);
    // SmartDashboard.putNumber("tx", targetX);
    // NetworkTableInstance.getDefault().getTable("<limelight-boombox>").getEntry("priorityid").setNumber(7);

    m_SwerveSubsystem.drive(m_yvalPidController.calculate(targetY,14), 0,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
