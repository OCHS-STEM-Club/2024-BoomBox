// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.setpoints;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class AmpSetpoint extends Command {
  /** Creates a new AmpSetpoint. */

  public final ArmSubsystem m_armSubsystem;

  public AmpSetpoint(ArmSubsystem armSubsystem) {
    m_armSubsystem = armSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armSubsystem.ampSetpoint();

  //   if (m_armSubsystem.upperHardStop() == false) {
  //     m_armSubsystem.armOff();
  //   } else 
  //       m_armSubsystem.ampSetpoint();
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
