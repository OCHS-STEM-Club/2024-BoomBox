// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new climberSubsystem. */
  private CANSparkMax climberMotor;

  public ClimberSubsystem() {
    climberMotor = new CANSparkMax(Constants.ClimberConstants.kClimberMotorID, MotorType.kBrushless);
    climberMotor.setIdleMode(IdleMode.kBrake);
    climberMotor.setInverted(false);
    climberMotor.setSmartCurrentLimit(10, 5);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climberMotorUp() {
    climberMotor.set(0.2);
  }

  public void climberMotorDown() {
    climberMotor.set(-0.2);
  }
}
