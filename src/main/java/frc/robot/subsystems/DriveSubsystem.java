/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

   // Adding in motor controlleres

   public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR_CAN, MotorType.kBrushless);
   public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR_CAN, MotorType.kBrushless);

   //Since there are two motors per side, we need to group them in our program

   public SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeft, backLeft);
   public SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRight, backRight);

   // Put these groups into a differential drive

   public DifferentialDrive differentialRocketLeagueDrive = new DifferentialDrive(leftGroup, rightGroup);

   // Drive method we created from scratch to drive the robot

   public void RocketLeagueDrive(double moving, double turning){

    double turn = 0.0;
    double speed = 0.0;
    double speedConstant = 0.80;
    double rotationConstant = 0.60;
    double pivotConstant = 0.80;

    if(moving >= 0.10 || moving <= -0.10){

      speed = speedConstant * moving;

      if(Math.abs(turning) > 0.10){

        turn = rotationConstant * turning;
      }
    }else if(Math.abs(turning) > 0.10){
      turn = pivotConstant * Math.pow(turning, 3);
    }

    differentialRocketLeagueDrive.arcadeDrive(speed, turn);
   }

  public DriveSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
