// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.JawSubsystem;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

public class JawTeleOpCommand extends CommandBase {
  
  private JawSubsystem JAW_SUBSYSTEM; 
  private PIDController pidController; 

  // private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;
  

  public double kP; 
  public double error; 
  public double command; 
  public double measurement; 

  // public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  /** Creates a new JawTeleOpCommand. */
  public JawTeleOpCommand(JawSubsystem jaw, double setPoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.JAW_SUBSYSTEM = jaw; 
    this.pidController = new PIDController(3, 0, 0.8); 
    pidController.setSetpoint(setPoint);
    addRequirements(JAW_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.reset();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = pidController.calculate(JAW_SUBSYSTEM.getEncoderDegrees()); 
    JAW_SUBSYSTEM.setJaw(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    JAW_SUBSYSTEM.setJaw(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  } 
}
