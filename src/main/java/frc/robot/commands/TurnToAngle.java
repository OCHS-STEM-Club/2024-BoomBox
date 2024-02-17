// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This command will turn the robot to a specified angle.
 */
public class TurnToAngle extends Command {

    private SwerveSubsystem swerve;
    private boolean isRelative;
    private double goal;
    private HolonomicDriveController holonomicDriveController;
    private Pose2d startPos = new Pose2d();
    private Pose2d targetPose2d = new Pose2d();

    /**
     * Turns robot to specified angle. Uses absolute rotation on field.
     *
     * @param swerve Swerve subsystem
     * @param angle Requested angle to turn to
     * @param isRelative Whether the angle is relative to the current angle: true = relative, false
     *        = absolute
     */

    public TurnToAngle(SwerveSubsystem swerve, double angle, boolean isRelative) {
        addRequirements(swerve);
        this.swerve = swerve;
        this.goal = angle;
        this.isRelative = isRelative;

        PIDController xcontroller = new PIDController(0.1, 0, 0);
        PIDController ycontroller = new PIDController(0.1, 0, 0);
        ProfiledPIDController thetacontroller =
            new ProfiledPIDController(1, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetacontroller.enableContinuousInput(0, 360);
        holonomicDriveController =
            new HolonomicDriveController(xcontroller, ycontroller, thetacontroller);
        holonomicDriveController.setTolerance(new Pose2d(1, 1, Rotation2d.fromDegrees(2)));

    }

    @Override
    public void initialize() {
        startPos = swerve.getPose();
        if (isRelative) {
            targetPose2d = new Pose2d(startPos.getTranslation(),
                startPos.getRotation().rotateBy(Rotation2d.fromDegrees(goal)));
        } else {
            targetPose2d = new Pose2d(startPos.getTranslation(), Rotation2d.fromDegrees(goal));
        }
    }

    @Override
    public void execute() {
        Pose2d currPose2d = swerve.getPose();
        ChassisSpeeds chassisSpeeds = this.holonomicDriveController.calculate(currPose2d,
            targetPose2d, 0, targetPose2d.getRotation());
        SwerveModuleState[] swerveModuleStates =
            Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        swerve.setModuleStates(swerveModuleStates);
        System.out.println(targetPose2d.relativeTo(currPose2d));
    }

    @Override
    public void end(boolean interrupt) {
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 0, 0);
        SwerveModuleState[] swerveModuleStates =
            Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        swerve.setModuleStates(swerveModuleStates);
    }

    @Override
    public boolean isFinished() {
        return holonomicDriveController.atReference();

    }
}
