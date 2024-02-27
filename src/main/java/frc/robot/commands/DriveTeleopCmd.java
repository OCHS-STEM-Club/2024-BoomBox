// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveTeleopCmd extends Command {
  private final SwerveSubsystem m_swerveSubsystem;

  private final CommandXboxController m_controller;

  private final CommandJoystick m_driveJoystick;
  private final CommandJoystick m_rotJoystick;

  /** Creates a new DriveTeleopCmd. */
  public DriveTeleopCmd(SwerveSubsystem swerveSubsystem, CommandXboxController controller, CommandJoystick driveJoystick, CommandJoystick rotJoystick) {
    m_swerveSubsystem = swerveSubsystem;
    m_controller = controller;
    m_driveJoystick = driveJoystick;
    m_rotJoystick = rotJoystick;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double xSpeed = -MathUtil.applyDeadband(m_driveJoystick.getRawAxis(1), OperatorConstants.kDriverControllerDeadband * 0.9);
    double ySpeed = MathUtil.applyDeadband(m_driveJoystick.getRawAxis(0), OperatorConstants.kDriverControllerDeadband * 0.9);
    double rotSpeed = MathUtil.applyDeadband(m_rotJoystick.getRawAxis(0), OperatorConstants.kDriverControllerDeadband * 0.9);

    m_swerveSubsystem.drive(xSpeed, ySpeed, rotSpeed, true);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_swerveSubsystem.stopModules();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
