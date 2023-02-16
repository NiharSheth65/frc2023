// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

import com.revrobotics.RelativeEncoder;

public class AutonomousMoveForward extends CommandBase {
  /** Creates a new AutonomousMoveForward. */

  private DriveSubsystem DRIVE_SUBSYSTEM;

  private AHRS navx = new AHRS(SPI.Port.kMXP);
  private double gyroPosition;
  private double gyroPositionInitial; 
  double initialGyroPosition; 

  public AutonomousMoveForward(DriveSubsystem drive, double driveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.DRIVE_SUBSYSTEM = drive;
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    navx.reset();
    navx.zeroYaw(); 

    gyroPositionInitial = navx.getYaw(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    gyroPosition = navx.getYaw(); 

    double angleTotal = 0; 
    double timesRan = 0; 
    double leftSpeed = 0; 
    double rightSpeed = 0; 

    
    
    double gyroCounter = 0; 
    while(gyroCounter == 0){
      initialGyroPosition = navx.getYaw(); 
      gyroCounter++; 
      SmartDashboard.putNumber("Gyro Position Inital 2", initialGyroPosition); 
    }

    SmartDashboard.putNumber("Gyro Position Inital", gyroPositionInitial); 
    SmartDashboard.putNumber("Gyro counter", gyroCounter); 

    if(navx.getYaw() >= -0.5 && navx.getYaw() <= 0.5){
      rightSpeed = 0.125; 
      leftSpeed = 0.125; 
    }

    else if(navx.getYaw() > 0.5){
      rightSpeed = 0.22; 
      leftSpeed = 0.125; 
    }
    
    else if(navx.getYaw() < -0.5){
      rightSpeed = 0.125; 
      leftSpeed = 0.175; 
    }

    else{
      rightSpeed = 0; 
      leftSpeed = 0; 
    }

    timesRan++;
    angleTotal += navx.getYaw(); 

    double medianAngle = angleTotal/timesRan; 
    SmartDashboard.putNumber("median number", medianAngle); 
    DRIVE_SUBSYSTEM.setAutonomousForward(leftSpeed, rightSpeed);
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
