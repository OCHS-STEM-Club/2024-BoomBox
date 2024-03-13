// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommandTeleop extends Command {
  /** Creates a new ShooterCommand. */

private ShooterSubsystem m_shooterSubsystem;
private ArmSubsystem m_armSubsystem;
  private final NetworkTableInstance m_inst = NetworkTableInstance.getDefault();
  private final NetworkTable m_limelighTable;

  public ShooterCommandTeleop(ShooterSubsystem shooterSubsystem, ArmSubsystem armSubsystem) {
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

    if(YValue < 22 && YValue > 21) {
      m_shooterSubsystem.shooterOn(0.375);
      m_armSubsystem.setReference(-2.6);
    } 
    if(YValue < 21 && YValue > 20) {
      m_shooterSubsystem.shooterOn(0.375);
      m_armSubsystem.setReference(-2.7);
    } 
    if(YValue < 20 && YValue > 19) {
      m_shooterSubsystem.shooterOn(0.375);
      m_armSubsystem.setReference(-2.6);
    } 
    if(YValue < 19 && YValue > 18) {
      m_shooterSubsystem.shooterOn(0.375);
      m_armSubsystem.setReference(-2.55);
    } 
    if(YValue < 18 && YValue > 17) {
      m_shooterSubsystem.shooterOn(0.425);
      m_armSubsystem.setReference(-2.45);
    } 
    if(YValue < 17 && YValue > 16) {
      m_shooterSubsystem.shooterOn(0.425);
      m_armSubsystem.setReference(-2.4);
    } 
    if(YValue < 16 && YValue > 15) {
      m_shooterSubsystem.shooterOn(0.47);
      m_armSubsystem.setReference(-2.3);
    } 
    if(YValue < 15 && YValue > 14) {
      m_shooterSubsystem.shooterOn(0.435);
      m_armSubsystem.setReference(-2.3);
    } 
    if(YValue < 14 && YValue > 13) {
      m_shooterSubsystem.shooterOn(0.44);
      m_armSubsystem.setReference(-2.35);
    } 
    if(YValue < 13 && YValue > 12) {
      m_shooterSubsystem.shooterOn(0.44);
      m_armSubsystem.setReference(-2.3);
    }
    if(YValue < 12 && YValue > 11) {
      m_shooterSubsystem.shooterOn(0.44);
      m_armSubsystem.setReference(-2.15);
    }
    if(YValue < 11 && YValue > 10) {
      m_shooterSubsystem.shooterOn(0.44);
      m_armSubsystem.setReference(-2.1);
    }
    // if(YValue < 10 && YValue > 9) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.95);
    // }
    // if(YValue < 9 && YValue > 8) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.9);
    // }
    // if(YValue < 8 && YValue > 7) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.85);
    // }
    // if(YValue < 7 && YValue > 6) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.8);
    // }
    // if(YValue < 6 && YValue > 7) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.75);
    // }
    // if(YValue < 7 && YValue > 6) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.65);
    // }
    // if(YValue < 6 && YValue > 5) {
    //   m_shooterSubsystem.shooterOn(0.44);
    //   m_armSubsystem.setReference(-1.6);
    // }
    
  //   if (YValue < 15 && YValue > 10) {
  //     m_shooterSubsystem.shooterOn(0.425);
  //     m_armSubsystem.setReference(-2.3);
  //   } 

  //   if (YValue < 10 && YValue > 5) {
  //     m_shooterSubsystem.shooterOn(0.45);
  //     m_armSubsystem.setReference(-1.9);
  //   }
    
    if (YValue == 0) {
      m_shooterSubsystem.shooterOn(0.4);
      // m_armSubsystem.setReference(-3);
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