package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@SuppressWarnings("unused")
@Autonomous
public class SquareDrive extends LinearOpMode implements Sleeper {

    @Override
    public void runOpMode(){
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "Left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "Right");
        IMU imu = hardwareMap.get(IMU.class, "imu");
        final float LEFTTRIMCONSTANT = 0.96f;
        final float RIGHTTRIMCONSTANT = 1;
        double currentZeroGyro;

        Trim t = new Trim();
        t.setLeft(LEFTTRIMCONSTANT);
        AutoDrive drive = new AutoDrive(leftMotor, rightMotor);
        GyroSensor gyro = new GyroSensor(imu, new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        PowerLevels pl;
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        gyro.resetYaw();

        drive.setPower(t.getPowerLevel(0.5f,0.5f));
        sleepFor(2000);
        drive.stop();
        drive.setPower(t.getPowerLevel(0.25f,-0.25f));

        currentZeroGyro = gyro.getYaw();
        while (!(gyro.getYaw() < currentZeroGyro-90)){
            this.idle();
            telemetry.addData("Yaw",gyro.getYaw());
            telemetry.update();
        }
        drive.stop();


    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }
}
