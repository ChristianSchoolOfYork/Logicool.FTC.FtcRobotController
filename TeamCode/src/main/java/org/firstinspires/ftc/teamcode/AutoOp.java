package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.states.AutoModeState;
import org.firstinspires.ftc.teamcode.states.FinalState;
import org.firstinspires.ftc.teamcode.states.InitialState;
import org.firstinspires.ftc.teamcode.states.MoveHome;
import org.firstinspires.ftc.teamcode.states.MoveToSample;
import org.firstinspires.ftc.teamcode.states.PickUpSample;

/** @noinspection ALL*/
@SuppressWarnings("unused")
@Autonomous
public class AutoOp extends LinearOpMode implements Sleeper {



    @Override
    public void runOpMode() {
        DcMotor armMotor = hardwareMap.get(DcMotor.class, "Arm");
        DcMotor wristMotor = hardwareMap.get(DcMotor.class, "Wrist");
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "Left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "Right");
        Servo intake = hardwareMap.get(Servo.class, "Intake");
        Servo gripperServo = hardwareMap.get(Servo.class, "Gripper");

        IMU imu = hardwareMap.get(IMU.class, "imu");
        GyroSensor gyro = new GyroSensor(imu, new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

        ArmWrist armWrist = new ArmWrist(armMotor, wristMotor, telemetry);
        MainDrive maindrive = new MainDrive(gamepad1, leftMotor, rightMotor, gyro, telemetry);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
        Gripper gripper =  new Gripper(gripperServo,0.1,0.3);

        InitialState initialState = new InitialState(armWrist, gripper, telemetry);
        MoveToSample moveToSample = new MoveToSample(maindrive, telemetry);
        FinalState finalState = new FinalState(armWrist, telemetry);
        PickUpSample pickUpSample = new PickUpSample(gripper, armWrist, telemetry);
        MoveHome moveHome = new MoveHome(maindrive, telemetry);

        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gyro.resetYaw();

        telemetry.addData("Current arm position", armMotor.getCurrentPosition());
        telemetry.addData("Current wrist position", wristMotor.getCurrentPosition());
        telemetry.addData("Gripper state", gripper.getState());
        telemetry.addData("Yaw Orientation", gyro.getYaw());
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            initialState.Execute();
            moveToSample.Execute();
            pickUpSample.Execute();
            moveHome.Execute();
            finalState.Execute();
        }

        telemetry.addData("Yaw Orientation", gyro.getYaw());
        telemetry.update();

        while(opModeIsActive())
        {
            telemetry.addData("Yaw Orientation", gyro.getYaw());
            telemetry.update();
        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }
}
