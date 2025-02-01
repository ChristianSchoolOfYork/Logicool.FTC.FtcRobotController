package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.states.AutoModeState;
import org.firstinspires.ftc.teamcode.states.FinalState;
import org.firstinspires.ftc.teamcode.states.InitialState;
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

        ArmWrist armWrist = new ArmWrist(armMotor, wristMotor, telemetry);
        MainDrive maindrive = new MainDrive(gamepad1, leftMotor, rightMotor, telemetry);
        Collector collector = new Collector(this, intake, 0.94, 0.06);

        InitialState initialState = new InitialState(armWrist, telemetry);
        MoveToSample moveToSample = new MoveToSample(maindrive, telemetry);
        FinalState finalState = new FinalState(armWrist, telemetry);
        PickUpSample pickUpSample = new PickUpSample(collector, telemetry);

        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Current arm position", armMotor.getCurrentPosition());
        telemetry.addData("Current wrist position", wristMotor.getCurrentPosition());
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            initialState.Execute();
            moveToSample.Execute();
            pickUpSample.Execute();
            finalState.InternalExecute();
        }
    }

    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }
}
//        leftDrive = hardwareMap.get(DcMotor.class, "Left");
//        rightDrive = hardwareMap.get(DcMotor.class, "Right");
//        DcMotor wrist = hardwareMap.get(DcMotor.class, "Wrist");
//        IMU imu = hardwareMap.get(IMU.class, "imu");
//        final float LEFTTRIMCONSTANT = 0.96f;
//        final float RIGHTTRIMCONSTANT = 1;
//
//        Trim t = new Trim();
//        t.setLeft(LEFTTRIMCONSTANT);
//        GyroSensor gyro = new GyroSensor(imu, new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
//        PowerLevels pl;
//        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        waitForStart();
//        gyro.resetYaw();
//
//        encoderDrive(0.5, 25, 25, 5);
//
//    }
//
//    public void encoderDrive(double speed,
//                             double leftInches, double rightInches,
//                             double timeoutS) {
//        int newLeftTarget;
//        int newRightTarget;
//
//        // Ensure that the OpMode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            newLeftTarget = leftDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
//            newRightTarget = rightDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
//            leftDrive.setTargetPosition(-newLeftTarget);
//            rightDrive.setTargetPosition(newRightTarget);
//
//            // Turn On RUN_TO_POSITION
//            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // reset the timeout time and start motion.
//            runtime.reset();
//            leftDrive.setPower(Math.abs(speed));
//            rightDrive.setPower(Math.abs(speed));
//
//            // keep looping while we are still active, and there is time left, and both motors are running.
//            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
//            // its target position, the motion will stop.  This is "safer" in the event that the robot will
//            // always end the motion as soon as possible.
//            // However, if you require that BOTH motors have finished their moves before the robot continues
//            // onto the next step, use (isBusy() || isBusy()) in the loop test.
//            while (opModeIsActive() &&
//                    (runtime.seconds() < timeoutS) &&
//                    (leftDrive.isBusy() && rightDrive.isBusy())) {
//
//                // Display it for the driver.
//                telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
//                telemetry.addData("Currently at", " at %7d :%7d",
//                        leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
//                telemetry.update();
//            }
//
//            // Stop all motion;
//            leftDrive.setPower(0);
//            rightDrive.setPower(0);
//
//            // Turn off RUN_TO_POSITION
//            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            sleep(250);   // optional pause after each move.
//        }

//    }
//}