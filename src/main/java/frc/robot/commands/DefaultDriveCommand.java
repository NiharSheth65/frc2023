// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDriveCommand extends CommandBase {
  /** Creates a new DefaultDriveCommand. */

  private DriveSubsystem DRIVE_SUBSYSTEM; 
  private Joystick joystick; 

  double left; 
  double right; 

  SlewRateLimiter y_RateLimiter = new SlewRateLimiter(2); 
  SlewRateLimiter x_RateLimiter = new SlewRateLimiter(2); 

  

  public DefaultDriveCommand(DriveSubsystem drive, Joystick joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM = drive; 
    this.joystick = joy; 
    
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    left = 0;  
    right = 0; 

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    left = joystick.getRawAxis(1); 
    right = joystick.getRawAxis(4); 

    DRIVE_SUBSYSTEM.setTeleOp(-y_RateLimiter.calculate(left), -
    x_RateLimiter.calculate(right));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
