package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
/*
Controller 1
-left and right thumbsticks are tank drive
Controller 2
-left thumbstick: arm movement
-right thumbstick: wrist movement
-left and right bumpers: intake
-a: open grabber
-b: close grabber
 */
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

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Gripper gripper = new Gripper(grab,0.5,0.3);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
        MainDrive drive = new MainDrive(gamepad1,leftMotor,rightMotor);
        ArmWrist armWrist = new ArmWrist(arm, wrist);
        Trim t = new Trim();
        PowerLevels pl;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        intake.setDirection(Servo.Direction.FORWARD);
        intake.getController().pwmEnable();
        while (opModeIsActive()) {
            pl=t.getPowerLevel(1,1);
            float leftThumbstickValue = gamepad1.left_stick_y;
            float rightThumbstickValue = gamepad1.right_stick_y;

            telemetry.addData("Left Thumbstick Value", leftThumbstickValue);
            telemetry.addData("Right Thumbstick Value", rightThumbstickValue);
            telemetry.addData("Status", "Running");
            telemetry.addData("left Trim", t.getLeftTrim());
            telemetry.addData("right Trim", t.getRightTrim());
            telemetry.addData("Intake", intake.getPosition());
            telemetry.addData("arm", arm.getCurrentPosition());
            telemetry.addData("wrist",wrist.getCurrentPosition());
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
