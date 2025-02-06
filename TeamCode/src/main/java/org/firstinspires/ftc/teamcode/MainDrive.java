package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MainDrive {
    static final double COUNTS_PER_MOTOR_REV = 560;     // factor of 288
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 3.5;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private final Telemetry telemetry;
    private final GyroSensor gyro;
    private final Sleeper sleeper;

    Gamepad gamepad1;
    DcMotor left, right;
    public MainDrive(Gamepad gamepad1, DcMotor left, DcMotor right, GyroSensor gyro, Sleeper sleeper, Telemetry telemetry){
        this.gamepad1 = gamepad1;
        this.left = left;
        this.right = right;
        this.gyro = gyro;
        this.sleeper = sleeper;
        this.telemetry = telemetry;
    }
    public void update(PowerLevels pl){
        left.setPower(gamepad1.left_stick_y * pl.getLeftPower());
        right.setPower(-gamepad1.right_stick_y * pl.getRightPower());
    }

    public void MoveForward(float distanceToMove) {
        encoderDrive(.5, distanceToMove, distanceToMove);
    }

    public void RotateLeftTo(double degrees) {

        double startingYaw = gyro.getYaw();
        double target = degrees - startingYaw;
        double degreesTraveled = 0;

        left.setPower(.25);
        right.setPower(.25);

        
        while (degreesTraveled < target){
            degreesTraveled = getNormalizedYaw() - startingYaw;
            telemetry.addData("Target Degrees Traveled", target);
            telemetry.addData("Degrees Traveled", degreesTraveled);
            telemetry.addData("Target Yaw", degrees);
            telemetry.addData("Current Yaw",gyro.getYaw());
            telemetry.update();
            sleeper.Idle();
        }

        left.setPower(0);
        right.setPower(0);
    }
    private double getNormalizedYaw(){
        double currentYaw = gyro.getYaw();

        if (currentYaw < 0){
            currentYaw += 360;
        }

        return currentYaw;
    }
    private void encoderDrive(double speed,
                             double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active

        // Determine new target position, and pass to motor controller
        newLeftTarget = left.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        newRightTarget = right.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
        left.setTargetPosition(-newLeftTarget);
        right.setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        left.setPower(speed);
        right.setPower(speed);

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (left.isBusy() && right.isBusy()) {

            // Display it for the driver.
            telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
            telemetry.addData("Currently at", " at %7d :%7d",
                    left.getCurrentPosition(), right.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        left.setPower(0);
        right.setPower(0);

        // Turn off RUN_TO_POSITION
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}
