// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutonomousMoveForward;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.GearBoxShiftLowCommand;
import frc.robot.commands.JawTeleOpCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GearboxSubsystem;
import frc.robot.subsystems.JawSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem(); 
  private GearboxSubsystem GEARBOX_SUBSYSTEM = new GearboxSubsystem(); 
  private JawSubsystem JAW_SUBSYSTEM = new JawSubsystem(); 

  private Joystick joystick = new Joystick(0); 
  // public XboxController controller = new XboxController(0); 
  
  // private JoystickButton buttonRB = new JoystickButton(joystick, 5); 
  // private JoystickButton buttonLB = new JoystickButton(joystick, 6); 
  // private JoystickButton buttonA = new JoystickButton(joystick, 0); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    // configureBindings();
    configureButtonBindings();
    defaultCommands();
  }

 
  private void configureBindings(){


  }
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
  private void configureButtonBindings(){
    Trigger BUTTON_A = new JoystickButton(joystick, jotstick.getRawButton(0));

    // BUTTON_A.onTrue(JAW_SUBSYSTEM.JawTeleOpCommand(10));  

  
    // controller.a().onTrue(m_robotArm.setArmGoalCommand(2));
  
    BUTTON_A.onTrue(JawTeleOpCommand(JAW_SUBSYSTEM, 30)); 
  }



  private void defaultCommands(){
    DRIVE_SUBSYSTEM.setDefaultCommand(new DefaultDriveCommand(DRIVE_SUBSYSTEM, joystick));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new AutonomousMoveForward(DRIVE_SUBSYSTEM, 10); 
  }
}
