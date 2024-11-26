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
public class mainOp extends LinearOpMode implements Sleeper{

    @Override
    public void runOpMode() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "Left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "Right");
        DcMotor arm = hardwareMap.get(DcMotor.class, "Arm");
        DcMotor wrist = hardwareMap.get(DcMotor.class, "Wrist");
        Servo intake = hardwareMap.get(Servo.class, "Intake");
        Servo grab = hardwareMap.get(Servo.class, "Gripper");
        boolean isDpadLeft =false,isDpadRight=false;
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Gripper gripper = new Gripper(grab,0.5,0.3);
        Collector collector = new Collector(this, intake, 0.94, 0.06);
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

            if (gamepad1.dpad_left && !isDpadLeft){
                t.addLeft();
                isDpadLeft = true;
            }

            if (!gamepad1.dpad_left){
                isDpadLeft = false;
            }

            
            if (gamepad1.dpad_right && !isDpadRight){
                t.addRight();
                isDpadRight = true;
            }

            if (!gamepad1.dpad_right) {
                isDpadRight = false;
            }

            /*if (gamepad1.a) {
                leftMotor.setPower(-pl.getLeftPower());
                //0.95 was the value that the Trim was set to before I added my code
                rightMotor.setPower(pl.getRightPower());
            } else if (gamepad1.b) {
                leftMotor.setPower(pl.getLeftPower());
                rightMotor.setPower(-pl.getRightPower());
            } else {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }*/
            leftMotor.setPower(leftThumbstickValue * pl.getLeftPower());
            rightMotor.setPower(-rightThumbstickValue * pl.getRightPower());
            arm.setPower(gamepad2.left_stick_y*.5);
            wrist.setPower(gamepad2.right_stick_y*-.5);

            if(gamepad2.right_bumper) {
                collector.takeIn();
            }
            if(gamepad2.left_bumper) {
                collector.takeOut();
            }

            if (gamepad2.a){
                gripper.close();
            }
            if (gamepad2.b){
                gripper.open();
            }

        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }

}