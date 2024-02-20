// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private CANSparkMax armMotorLeft;
  private CANSparkMax armMotorRight;
  private RelativeEncoder armEncoderLeft;
  private RelativeEncoder armEncoderRight;
  private DigitalInput hardStop;
  private boolean atshootervalue;

  private DutyCycleEncoder thru;

  private double speed;

  public ArmSubsystem() {
    armMotorLeft = new CANSparkMax(Constants.ArmConstants.kArmMotorLeftID, MotorType.kBrushless);
    armMotorRight = new CANSparkMax(Constants.ArmConstants.kArmMotorRightID, MotorType.kBrushless);
    thru = new DutyCycleEncoder(3);
    armEncoderLeft = armMotorLeft.getEncoder();
    armEncoderRight = armMotorRight.getEncoder();
    armMotorLeft.setIdleMode(IdleMode.kBrake);
    armMotorRight.setIdleMode(IdleMode.kBrake);
    armMotorLeft.setInverted(false);
    armMotorRight.setInverted(false);
    // armMotorRight.setSmartCurrentLimit(30, 15);
    // armMotorLeft.setSmartCurrentLimit(30, 15);

    armMotorRight.setSmartCurrentLimit(40, 40);
    armMotorLeft.setSmartCurrentLimit(40, 40);
    
    armMotorLeft.follow(armMotorRight);

    hardStop = new DigitalInput(1);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
     System.out.println(thru.getAbsolutePosition());
    // System.out.println(armEncoderLeft.getPosition());
    //System.out.println(valueBoolean());
  }

  public void armMotorUp() {
    armMotorLeft.set(0.3);
    armMotorRight.set(0.3);
  }

  public void armMotorDown() {
    armMotorLeft.set(-0.3);
    armMotorRight.set(-0.3);
  }

  public void armOff() {
    armMotorLeft.set(0);
    armMotorRight.set(0);
  }

  public void armBrakeMode() {
    armMotorLeft.setIdleMode(IdleMode.kBrake);
    armMotorRight.setIdleMode(IdleMode.kBrake);
  }

  public void armCoastMode() {
    armMotorLeft.setIdleMode(IdleMode.kCoast);
    armMotorRight.setIdleMode(IdleMode.kCoast);
  }

  public void ArmMove() {
  if (armEncoderLeft.getPosition() > 0.2) {
      armMotorDown();
    } else 
      armOff();
  }

  public boolean valueBoolean() {
    if (thru.getAbsolutePosition() < 0.2) {
      return false;
    } else return true;
  }

  public boolean ampValueBoolean() {
    if (armEncoderLeft.getPosition() < 0.41) {
      return false;
    } else return true;
  }

  public void set(double speed) {
      armMotorLeft.set(speed);
      armMotorRight.set(speed);
  }
}

