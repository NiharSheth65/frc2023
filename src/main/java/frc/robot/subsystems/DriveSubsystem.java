// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  // LEFT SIDE MOTORS
  private CANSparkMax leftMotorFront = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax leftMotorBack = new CANSparkMax(1, MotorType.kBrushless);

  // RIGHT SIDE MOTORS
  private CANSparkMax rightMotorFront = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax rightMotorBack = new CANSparkMax(3, MotorType.kBrushless);



  // inital set points
  // private double LeftMotor_Setpoint = 0;
  // private double RightMotor_Setpoint = 0;

  public DriveSubsystem() {

    // RESTORE ALL SETTING TO FACTORY DEFAULT
    leftMotorFront.restoreFactoryDefaults();
    leftMotorBack.restoreFactoryDefaults();

    rightMotorFront.restoreFactoryDefaults();
    rightMotorBack.restoreFactoryDefaults();

    // INVERT LEFT SIDE MOTORS
    leftMotorFront.setInverted(true); 
    leftMotorBack.setInverted(true); 

    // GETTING BACK MOTORS TO FOLLOW FRONT LEAD MOTORS
    leftMotorBack.follow(leftMotorFront);
    rightMotorBack.follow(rightMotorFront);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  //  THE DRIVE TRAIN SHOULD WORK WHEN FUNCTION IS CALLED 

  public void setTeleOp(double y_axis_joystick, double x_axis_joystick){
    // double safetyFactor = 0.5;
    // double joyStick_left_Y = y_axis_joystick * safetyFactor;
    // double joyStick_right_X = x_axis_joystick * safetyFactor;

    // // Acceleration Control _ Needs a time factor included

    // double turnRate = 0.25;

    // double LeftMotor_TargetPoint = joyStick_left_Y - (joyStick_right_X * turnRate);
    // double RightMotor_TargetPoint = joyStick_left_Y + (joyStick_right_X * turnRate);
    
    
    // LeftMotor_Setpoint = Acceleration_contol(LeftMotor_TargetPoint, LeftMotor_Setpoint);
    // RightMotor_Setpoint = Acceleration_contol(RightMotor_TargetPoint, RightMotor_Setpoint);

    rightMotorFront.set(y_axis_joystick + x_axis_joystick);
    leftMotorFront.set(y_axis_joystick - x_axis_joystick);
  }

  public void setAutonomousForward(double leftSpeed, double rightSpeed){
    rightMotorFront.set(rightSpeed); 
    leftMotorFront.set(leftSpeed);
  }

  // ACCELERATION CONTROL CODE 

  public double Acceleration_contol(double TargetPoint, double Setpoint) {

    double agressionFactor = 5;

    // return (Setpoint + (TargetPoint - Setpoint)/agressionFactor);

    if (((TargetPoint > 0) && (Setpoint < 0) || (TargetPoint < 0) && (Setpoint > 0))) {

      agressionFactor = 10;
    }

    return (Setpoint + (TargetPoint - Setpoint) / agressionFactor);

  }

  // STOP FUNCTION 

  public void stop(){
    rightMotorFront.set(0);
    leftMotorFront.set(0);
  }
}
