// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {
  /** Creates a new ShooterCommand. */

private ShooterSubsystem m_shooterSubsystem;
private ArmSubsystem m_armSubsystem;
  private final NetworkTableInstance m_inst = NetworkTableInstance.getDefault();
  private final NetworkTable m_limelighTable;

  public ShooterCommand(ShooterSubsystem shooterSubsystem, ArmSubsystem armSubsystem) {
  m_shooterSubsystem = shooterSubsystem;
  m_armSubsystem = armSubsystem;
  m_limelighTable = m_inst.getTable("limelight-boombox");
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double YValue = m_limelighTable.getEntry("ty").getDouble(0);

    // if(YValue > 13 || YValue == 0) {
    //   m_shooterSubsystem.shooterOn(0.4);
    // } else m_shooterSubsystem.shooterOn(0.5);

    if(YValue < 21 && YValue >= 15) {
      m_shooterSubsystem.shooterOn(0.4);
      m_armSubsystem.setReference(-2.5);
    } 
    
    if (YValue < 15 && YValue > 10) {
      m_shooterSubsystem.shooterOn(0.45);
      m_armSubsystem.setReference(-2.1);
    } 
    
    if (YValue == 0) {
      m_shooterSubsystem.shooterOn(0.4);
      m_armSubsystem.setReference(-3);
  }

}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooterSubsystem.shooterOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
