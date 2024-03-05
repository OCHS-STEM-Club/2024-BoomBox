// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class IntakeInCommand extends Command {

  IntakeSubsystem m_intakeSubsystem;
  LimelightSubsystem m_limelightSubsystem;
  /** Creates a new IntakeCommand. */
  public IntakeInCommand(IntakeSubsystem intakeSubsystem, LimelightSubsystem limelight) {
  m_intakeSubsystem = intakeSubsystem;
  m_limelightSubsystem = limelight;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   if (m_intakeSubsystem.beamBreakSensor() == true) {
     m_intakeSubsystem.intakeOn();
   } else  m_intakeSubsystem.intakeOff();
          m_limelightSubsystem.flash();
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeSubsystem.intakeOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
