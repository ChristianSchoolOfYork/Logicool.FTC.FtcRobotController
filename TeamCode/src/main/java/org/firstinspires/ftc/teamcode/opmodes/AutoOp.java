package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.sensors.ArmWrist;
import org.firstinspires.ftc.teamcode.sensors.Collector;
import org.firstinspires.ftc.teamcode.sensors.DistanceCheck;
import org.firstinspires.ftc.teamcode.sensors.Gripper;
import org.firstinspires.ftc.teamcode.sensors.GyroSensor;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;
import org.firstinspires.ftc.teamcode.Sleeper;
import org.firstinspires.ftc.teamcode.states.FinalState;
import org.firstinspires.ftc.teamcode.states.InitialState;
import org.firstinspires.ftc.teamcode.states.InitialStateLiftSpecimen;
import org.firstinspires.ftc.teamcode.states.MoveHome;
import org.firstinspires.ftc.teamcode.states.MoveToSample;
import org.firstinspires.ftc.teamcode.states.MoveToSubmersible;
import org.firstinspires.ftc.teamcode.states.PickUpSample;
import org.firstinspires.ftc.teamcode.states.PickUpSpecimen;

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
        DistanceSensor distanceLeft = hardwareMap.get(DistanceSensor.class, "left-range-sensor");
        DistanceSensor distanceRight = hardwareMap.get(DistanceSensor.class, "right-range-sensor");

        IMU imu = hardwareMap.get(IMU.class, "imu");
        GyroSensor gyro = new GyroSensor(imu, new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

        ArmWrist armWrist = new ArmWrist(armMotor, wristMotor, telemetry);
        MainDrive mainDrive = new MainDrive(gamepad1, leftMotor, rightMotor, gyro, this, telemetry);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
        Gripper gripper =  new Gripper(gripperServo,0.1,0.3);
        DistanceCheck distanceCheck = new DistanceCheck(distanceLeft, distanceRight);

        InitialState initialState = new InitialState(armWrist, gripper, telemetry);
        MoveToSample moveToSample = new MoveToSample(mainDrive, telemetry);
        FinalState finalState = new FinalState(armWrist, telemetry);
        PickUpSample pickUpSample = new PickUpSample(gripper, armWrist, telemetry);
        MoveHome moveHome = new MoveHome(mainDrive, telemetry);
        PickUpSpecimen pickUpSpecimen = new PickUpSpecimen(gripper, armWrist, mainDrive, distanceCheck, this ,telemetry);
        MoveToSubmersible moveToSubmersible = new MoveToSubmersible(gripper, armWrist, mainDrive, telemetry);
        InitialStateLiftSpecimen initialStateLiftSpecimen = new InitialStateLiftSpecimen(gripper, armWrist, mainDrive,distanceCheck, this, telemetry);

        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gripper.close();

        telemetry.addData("Current arm position", armMotor.getCurrentPosition());
        telemetry.addData("Current wrist position", wristMotor.getCurrentPosition());
        telemetry.addData("Gripper state", gripper.getState());
        telemetry.addData("Yaw Orientation", gyro.getYaw());
        telemetry.update();

        waitForStart();
        gyro.resetYaw();
        if (opModeIsActive()) {
//            initialState.Execute();
//            moveToSample.Execute();
//            pickUpSample.Execute();
//            moveHome.Execute();
//            pickUpSpecimen.Execute();
//            moveToSubmersible.Execute();
              initialStateLiftSpecimen.Execute();

            //finalState.Execute();
        }

        telemetry.addData("Yaw Orientation", gyro.getYaw());
        telemetry.update();

//        while(opModeIsActive())
//        {
//            moveHome.GetRotationData().UpdateTelemetry(telemetry);
//            telemetry.addData("Yaw Orientation", gyro.getYaw());
//            telemetry.addData("Distance From Wall", distanceLeft.getDistance(DistanceUnit.INCH));
//            telemetry.update();
//        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }

    @Override
    public void Idle() {
        this.idle();
    }
}
