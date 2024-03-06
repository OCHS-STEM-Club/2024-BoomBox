// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.PIDController;
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
  // private static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;
  // private double m_setpoint = 0;
  // private double pidReference = 0;
  // private double armPValue = 0;
  // private double armDValue = 0;

  //private PIDController pid = new PIDController(0.5, 0, 0);

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

    //lowerHardStop = new DigitalInput(2);
    //upperHardStop = new DigitalInput(1);

    armPIDController = armMotorRight.getPIDController();

    armPIDController.setP(0.85);
    armPIDController.setD(20);
    armPIDController.setOutputRange(-1, 1);

    armMotorRight.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 100);
    armMotorRight.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 100);
    armMotorRight.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 100);
    armMotorRight.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 100);

    armMotorLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 100);
    armMotorLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 100);
    armMotorLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 100);
    armMotorLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 100);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
    //  System.out.println(thru.getAbsolutePosition());
    //SmartDashboard.putNumber("PID shooter setpoint value", pid.calculate(thru.getAbsolutePosition()));

    SmartDashboard.putNumber("Arm Encoder", thru.getDistance());

    SmartDashboard.putNumber("Built-In Right Encoder", armMotorRight.getEncoder().getPosition());

    // if (lowerHardStop.get() == false) {
    //   thru.reset();
    //   armEncoderLeft.setPosition(0);
    //   armEncoderRight.setPosition(0);
    //   armPIDController.setReference(0, ControlType.kPosition);
    // }


  }

  // public boolean lowerHardStop() {
  //   return lowerHardStop.get();
  // }

  // public boolean upperHardStop() {
  //   return upperHardStop.get();
  // }

  public void armMotorUp() {
    armMotorRight.set(0.2);
}






  public void armMotorDown() {
    armMotorRight.set(-0.2);
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
    armPIDController.setReference(-3.75, ControlType.kPosition);
    //armMotorRight.set(pid.calculate(thru.getAbsolutePosition(), -0.3));
  }

  public void shooterSetpoint() {
    armPIDController.setReference(-3, ControlType.kPosition);
   // armMotorRight.set(pid.calculate(thru.getAbsolutePosition(),-0.36));
  }

  public void ampSetpoint() {
     armPIDController.setReference(0.32, ControlType.kPosition);
   // armMotorRight.set(pid.calculate(thru.getAbsolutePosition(),0));
  }

    public void trapSetpoint() {
     armPIDController.setReference(-2.5, ControlType.kPosition);
   // armMotorRight.set(pid.calculate(thru.getAbsolutePosition(),0));
  }

  public void setReference(double pidReference) {
    armPIDController.setReference(pidReference,ControlType.kPosition);
  }

  public double getAbsolutePosition() {
    return thru.getAbsolutePosition();
  }
  
  // public void resetEverything() {
  //    if (lowerHardStop.get() == false) {
  //     thru.reset();
  //     armEncoderLeft.setPosition(0);
  //     armEncoderRight.setPosition(0);
  //     armPIDController.setReference(0, ControlType.kPosition);
  //   }
  // }


}

