package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.AutoDrive;

@SuppressWarnings("unused")
public class GyroSensor {
    private final IMU imu;
    AutoDrive drive;
    public GyroSensor(IMU imu, RevHubOrientationOnRobot orientation, AutoDrive drive){
        this.imu = imu;
        this.imu.initialize(new IMU.Parameters(orientation));
        imu.resetYaw();
        this.drive = drive;
    }

    public GyroSensor(IMU imu, RevHubOrientationOnRobot orientation){
        this.imu = imu;
        this.imu.initialize(new IMU.Parameters(orientation));
        imu.resetYaw();
    }

    public double getYaw(){
        return imu.getRobotYawPitchRollAngles().getYaw();
    }

    public double getPitch(){
        return imu.getRobotYawPitchRollAngles().getPitch();
    }

    public double getRoll(){
        return imu.getRobotYawPitchRollAngles().getRoll();
    }

    public void resetYaw(){
        imu.resetYaw();
    }

}
