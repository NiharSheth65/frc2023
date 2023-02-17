// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

public class JawSubsystem extends SubsystemBase {
   
  private CANSparkMax jawMotor = new CANSparkMax(5, MotorType.kBrushless); 
  private RelativeEncoder motorEncoder; 

  // add ticks to degrees code here

  /** Creates a new JawSubsystem. */
  
  
  public JawSubsystem() {

    jawMotor.restoreFactoryDefaults(); 
    motorEncoder = jawMotor.getEncoder(); 
    
    // pid coefficients
  }

  public double getEncoderDegrees(){
    return motorEncoder.getPosition();  
  }
  

  @Override
  public  void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder Value", getEncoderDegrees()); 
  }

  public void setJaw(double speed){
    jawMotor.set(speed);
  }

}
