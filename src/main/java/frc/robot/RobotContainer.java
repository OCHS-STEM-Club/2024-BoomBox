// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
// import frc.robot.autos.exampleAuto;
import frc.robot.commands.AlignToTagCmd;
import frc.robot.commands.ArmDownCommand;
import frc.robot.commands.ArmUpCommand;
import frc.robot.commands.ClimberDownCommand;
import frc.robot.commands.ClimberUpCommand;
import frc.robot.commands.ClimberUpOverrideCmd;
import frc.robot.commands.DriveTeleopCmd;
import frc.robot.commands.IntakeInCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.commands.IntakeOverrideCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.setpoints.AmpSetpoint;
import frc.robot.commands.setpoints.IntakeSetpoint;
import frc.robot.commands.setpoints.ShooterSetpoint;
import frc.robot.commands.setpoints.TrapSetpoint;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  SwerveSubsystem m_swerveSubsystem = new SwerveSubsystem();
  ArmSubsystem m_armSubsystem = new ArmSubsystem();
  IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  
  // Controllers
  public CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverdriveControllerPort);
  public CommandXboxController m_buttonBox = new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  public CommandJoystick m_driveJoystick = new CommandJoystick(Constants.OperatorConstants.kDriverdriveControllerPort);
  public CommandJoystick m_rotJoystick = new CommandJoystick(OperatorConstants.kDriverrotControllerPort);

  // Commands
  DriveTeleopCmd m_driveTeleopCmd = new DriveTeleopCmd(m_swerveSubsystem, m_driverController, m_driveJoystick, m_rotJoystick);
  ArmDownCommand m_armDownCommand = new ArmDownCommand(m_armSubsystem);
  ArmUpCommand m_armUpCommand = new ArmUpCommand(m_armSubsystem);
  IntakeInCommand m_intakeCommand = new IntakeInCommand(m_intakeSubsystem);
  IntakeOutCommand m_intakeOutCommand = new IntakeOutCommand(m_intakeSubsystem);
  IntakeOverrideCommand m_IntakeOverrideCommand = new IntakeOverrideCommand(m_intakeSubsystem);
  ShooterCommand m_shooterCommand = new ShooterCommand(m_shooterSubsystem);
  AlignToTagCmd m_alignToTagCmd = new AlignToTagCmd(m_swerveSubsystem);
  ClimberDownCommand m_climberDownCommand = new ClimberDownCommand(m_climberSubsystem);
  ClimberUpCommand m_climberUpCommand = new ClimberUpCommand(m_climberSubsystem);
  IntakeSetpoint m_intakeSetpoint = new IntakeSetpoint(m_armSubsystem);
  ShooterSetpoint m_shooterSetpoint = new ShooterSetpoint(m_armSubsystem);
  AmpSetpoint m_ampSetpoint = new AmpSetpoint(m_armSubsystem);
  TrapSetpoint m_trapSetpoint = new TrapSetpoint(m_armSubsystem);
  ClimberUpOverrideCmd m_ClimberUpOverrideCmd = new ClimberUpOverrideCmd(m_climberSubsystem);



  // Autos
  //exampleAuto m_exampleAuto = new exampleAuto(m_swerveSubsystem);

  private final SendableChooser<Command> autoChooser;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // All of the Names Commands
    NamedCommands.registerCommand("Intake In", Commands.runOnce(() -> m_intakeSubsystem.intakeOn()));
    NamedCommands.registerCommand("Intake Off", Commands.runOnce(() -> m_intakeSubsystem.intakeOff()));
    NamedCommands.registerCommand("Shooter On", Commands.runOnce(() -> m_shooterSubsystem.shooterOn(0.4)));
    NamedCommands.registerCommand("Shooter On (IamSPED)", Commands.runOnce(() -> m_shooterSubsystem.shooterOn(0.5)));
    NamedCommands.registerCommand("Shooter Off", Commands.runOnce(() -> m_shooterSubsystem.shooterOff()));
    NamedCommands.registerCommand("Climber down", Commands.runOnce(() -> m_climberSubsystem.jankClimber()));
    NamedCommands.registerCommand("Turn 90", new TurnToAngle(m_swerveSubsystem, 90, false));
    NamedCommands.registerCommand("Arm to Intake", new IntakeSetpoint(m_armSubsystem).withTimeout(0.5));
    NamedCommands.registerCommand("Arm to Shooter", new ShooterSetpoint(m_armSubsystem).withTimeout(1.5));
    NamedCommands.registerCommand("Intake in BB", new IntakeInCommand(m_intakeSubsystem).withTimeout(3));
    NamedCommands.registerCommand("Arm to Trap", new TrapSetpoint(m_armSubsystem).withTimeout(0.5));
  



    m_swerveSubsystem.setDefaultCommand(m_driveTeleopCmd);

    autoChooser = AutoBuilder.buildAutoChooser();
    autoChooser.addOption("Turn 90", new TurnToAngle(m_swerveSubsystem, 90, false));

    SmartDashboard.putData("Autos", autoChooser);
    

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_rotJoystick.button(11).onTrue(
      new InstantCommand(m_swerveSubsystem::resetHeading, m_swerveSubsystem)
      );

    // m_driverController.y().onTrue(
    //   new InstantCommand(m_swerveSubsystem::resetPose, m_swerveSubsystem)
    //   );

    // Button Box controls
    m_buttonBox.pov(0).whileTrue(
      m_armUpCommand
    );

    m_buttonBox.pov(180).whileTrue(
      m_armDownCommand
    );

    m_buttonBox.button(1).whileTrue(
      m_climberDownCommand
    );

    m_buttonBox.button(3).whileTrue(
      m_climberUpCommand
    );

    m_buttonBox.button(4).whileTrue(
      m_intakeSetpoint
    );

    m_buttonBox.button(6).whileTrue(
      m_shooterSetpoint
    );

    m_buttonBox.button(5).whileTrue(
      m_ampSetpoint
    );

    m_buttonBox.button(10).whileTrue(
      m_ClimberUpOverrideCmd
    );
   
    m_buttonBox.leftTrigger().whileTrue(
      m_trapSetpoint
    );
    

    // Driver controls
    m_rotJoystick.button(1).whileTrue(
      m_shooterCommand
    );

    m_driverController.b().whileTrue(
      m_alignToTagCmd
    );

    m_driveJoystick.button(1).whileTrue(
      m_intakeCommand
    );

    m_driveJoystick.button(3).whileTrue(
      m_intakeOutCommand
    );

    m_rotJoystick.button(3).whileTrue(
      m_IntakeOverrideCommand
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   * 
   */
  

  public Command getAutonomousCommand() {
    // return new PathPlannerAuto("Auto1");
    //autoChooser = AutoBuilder.buildAutoChooser();
    // Load the path you want to follow using its name in the GUI


    m_swerveSubsystem.resetPose();
    // // Create a path following command using AutoBuilder. This will also trigger event markers.
    // return AutoBuilder.followPath(path);
    //return autoChooser.getSelected();
    return autoChooser.getSelected();
   
  }

  public void resetGyro() {
    m_swerveSubsystem.resetHeading();
  }

  public Command resetPose() {
    return new InstantCommand(m_swerveSubsystem::resetPose, m_swerveSubsystem);
  }

  public void armBrakeMode() {
    m_armSubsystem.armBrakeMode();
  }

  public void armCoastMode() {
    m_armSubsystem.armCoastMode();
  }

  public void dumdumClimber() {
    m_climberSubsystem.jankClimber();
  }

  // public boolean isClimberdown()  {
  //   return m_climberSubsystem.isClimberdown();
  // }

  // public void ArmAutoMove() {
  //   m_armSubsystem.ArmMove();
  // }

  public void armDownAuto() {
    m_climberSubsystem.jankClimber();
    if (m_climberSubsystem.isClimberdown() == true) {
        m_armSubsystem.ArmMove();
    } else m_armSubsystem.armOff();
  }

  public void resetEverything() {
    m_armSubsystem.resetEverything();
  }



}

  

