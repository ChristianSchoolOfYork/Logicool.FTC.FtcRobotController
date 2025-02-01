package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Locale;

/*
Controller 1
-left and right thumbsticks are tank drive
Controller 2
-left thumbstick: arm movement
-right thumbstick: wrist movement
-left and right bumpers: intake
-a: close grabber
-b: open grabber
 */
@SuppressWarnings({"unused"})
@TeleOp
public class MainOp extends LinearOpMode implements Sleeper{


    @Override
    public void runOpMode() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "Left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "Right");
        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");
        DcMotor wrist = hardwareMap.get(DcMotor.class, "Wrist");
        Servo intake = hardwareMap.get(Servo.class, "Intake");
        Servo grab = hardwareMap.get(Servo.class, "Gripper");
        DistanceSensor leftDistance = hardwareMap.get(DistanceSensor.class, "left-range-sensor");
        DistanceSensor rightDistance = hardwareMap.get(DistanceSensor.class, "right-range-sensor");
        IMU imu = hardwareMap.get(IMU.class, "imu");
        RevTouchSensor leftBack = hardwareMap.get(RevTouchSensor.class, "back left touch");
        RevTouchSensor rightBack = hardwareMap.get(RevTouchSensor.class, "back right touch");

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Gripper gripper = new Gripper(grab,0.1,0.3);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
        MainDrive drive = new MainDrive(gamepad1,leftMotor,rightMotor, telemetry);
        ArmWrist armWrist = new ArmWrist(arm, wrist, telemetry);
        Trim t = new Trim();
        FrontCollisionAvoidance distance = new FrontCollisionAvoidance(leftDistance, rightDistance);
        GyroSensor gyro = new GyroSensor(imu, new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        PowerLevels pl;
        TouchSensors touchSensors = new TouchSensors(leftBack, rightBack);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        wrist.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        intake.setDirection(Servo.Direction.FORWARD);
        intake.getController().pwmEnable();
        while (opModeIsActive()) {
            pl=t.getPowerLevel(1,1);
            telemetry.addData("Status", "Running");
            telemetry.addData("Intake", collector.getPercentage());
            telemetry.addData("gripper",gripper.getState()?"open":"closed");
            telemetry.addData("gripper value", grab.getPosition());
            telemetry.addData("left distance", distance.getLeftValue());
            telemetry.addData("right distance", distance.getRightValue());
            telemetry.addData("Yaw, Pitch, And Roll", "%.2f, %.2f, %.2f", gyro.getYaw(),gyro.getPitch(), gyro.getRoll());
            telemetry.addData("Left Back Touch Sensor", touchSensors.getLeft()? "pressed":"not pressed");
            telemetry.addData("Right Back Touch Sensor", touchSensors.getRight()? "pressed":"not pressed");
            telemetry.addData("Gamepad B left stick position", gamepad2.left_stick_y);
            telemetry.addData("Gamepad B right stick position", gamepad2.right_stick_y);
            telemetry.addData("Wrist Motor position", wrist.getCurrentPosition());
            telemetry.addData("Arm Motor position", arm.getCurrentPosition());
            telemetry.update();

            t.update(gamepad1);
            drive.update(pl);
            armWrist.update(gamepad2);
            collector.update(gamepad2);
            gripper.update(gamepad2);

        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }

}
