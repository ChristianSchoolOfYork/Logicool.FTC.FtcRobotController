package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class LengthCheck extends LinearOpMode implements Sleeper{
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");
        DcMotor wrist = hardwareMap.get(DcMotor.class, "Wrist");



        Servo intake = hardwareMap.get(Servo.class, "Intake");
        Servo grab = hardwareMap.get(Servo.class, "Gripper");

        Gripper Gripper = new Gripper(grab,0.5,0.3);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
        boolean out = false;
        boolean in = true;
        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("out",out);
            telemetry.update();
            if (gamepad1.left_bumper && gamepad1.right_bumper && !out) {
                arm.setPower(0.5);
                wrist.setPower(0.5);
                arm.setTargetPosition(-1415);
                wrist.setTargetPosition(310);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(2000);
                out = true;



            } else if (gamepad1.dpad_down && gamepad1.a && out) {
                wrist.setTargetPosition(0);
                sleep(2000);
                arm.setTargetPosition(0);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sleep(2000);
                out = false;
                arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        sleep(milliseconds);
        idle();
    }
}
