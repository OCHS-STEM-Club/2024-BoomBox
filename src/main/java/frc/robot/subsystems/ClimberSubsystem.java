// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new climberSubsystem. */
  private CANSparkMax climberMotor;
  private RelativeEncoder climberEncoder;
  public boolean climberbool;

  public ClimberSubsystem() {
    climberMotor = new CANSparkMax(Constants.ClimberConstants.kClimberMotorID, MotorType.kBrushless);
    climberEncoder = climberMotor.getEncoder();
    climberMotor.setIdleMode(IdleMode.kBrake);
    climberMotor.setInverted(false);
    climberMotor.setSmartCurrentLimit(10, 10);

    climberEncoder.setPosition(0);
    
  }
  @Override
  public void periodic() {
    // System.out.println(climberEncoder.getPosition());
   // System.out.println(climberbool);
    // This method will be called once per scheduler run
  }

  public void climberDown() {
    if (climberEncoder.getPosition() < 20) {
      climberMotor.set(0.8);
    } else climberMotor.set(0);
}

  public void climberUp() {
   if (climberEncoder.getPosition() > 0) {
      climberMotor.set(-0.8);
    } else climberMotor.set(0);
  }

  public void climberOff() {
    climberMotor.set(0);
  }

  public void jankClimber() {
    if(climberEncoder.getPosition() < 6.5) {
      climberDown();
      climberbool = false;

    } else climberOff();
      climberbool = true;
  }

  public boolean isClimberdown() {
    if(climberbool == true) {
      return true;
    } else 
      return false;
  }






}
