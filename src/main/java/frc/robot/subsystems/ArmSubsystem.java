// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private CANSparkMax armMotorLeft;
  private CANSparkMax armMotorRight;
  private RelativeEncoder armEncoderLeft;
  private RelativeEncoder armEncoderRight;
  private DigitalInput lowerHardStop;
  private DigitalInput upperHardStop;
  private boolean atshootervalue;

  private DutyCycleEncoder thru;

  private double speed;

  private SparkPIDController armPIDController;
  private double m_setpoint = 0;
  private double pidReference = 0;
  private double armPValue = 0;
  private double armDValue = 0;

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

    armMotorRight.setSmartCurrentLimit(30, 20);
    armMotorLeft.setSmartCurrentLimit(30, 20);
    
    armMotorLeft.follow(armMotorRight);

    lowerHardStop = new DigitalInput(2);
    upperHardStop = new DigitalInput(1);

    armPIDController = armMotorRight.getPIDController();
    armPIDController.setP(0.85);
    armPIDController.setD(20);
    armPIDController.setOutputRange(-1, 1);
    
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
     System.out.println(thru.getAbsolutePosition());
     //System.out.println(armEncoderLeft.getPosition());
    //System.out.println(valueBoolean());

    //armPValue = SmartDashboard.getNumber("Arm P Value", 0);
    //armDValue = SmartDashboard.getNumber("Arm D Value", 0);

    //armPIDController.setP(armPValue);
    //armPIDController.setD(armDValue);



  }

  public boolean lowerHardStop() {
    return lowerHardStop.get();
  }

  public boolean upperHardStop() {
    return upperHardStop.get();
  }

  public void armMotorUp() {
    armMotorRight.set(0.3);
}

  public void armMotorDown() {
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

  public void set(double speed) {
      armMotorLeft.set(speed);
      armMotorRight.set(speed);
  }

  public void intakeSetpoint() {
    armPIDController.setReference(0, ControlType.kPosition);
  }

  public void shooterSetpoint() {
    armPIDController.setReference(1, ControlType.kPosition);
  }

  public void ampSetpoint() {
    armPIDController.setReference(4.5, ControlType.kPosition);
  }


}

