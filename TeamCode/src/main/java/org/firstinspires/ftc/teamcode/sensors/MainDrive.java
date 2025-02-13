package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Sleeper;

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

    public void MoveForward(double distanceToMove) {
        encoderDrive(.5, distanceToMove, distanceToMove);
    }

    public RotationData RotateRightTo(double degrees) {

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentYaw = gyro.getYaw();
        double initialDegreesToTarget = ((currentYaw + 360) % 360) - degrees;
        double degreesToTarget = initialDegreesToTarget;

        left.setPower(-.25);
        right.setPower(-.25);

        while (degreesToTarget >= 0) {
            currentYaw = gyro.getYaw();
            degreesToTarget = ((currentYaw + 360) % 360) - degrees;

            if(degreesToTarget > initialDegreesToTarget)
            {
                degreesToTarget = degrees - (currentYaw + 360);
            }

            telemetry.addData("Current Yaw", currentYaw);
            telemetry.addData("Degreees to target", degreesToTarget);
            telemetry.addData("Target Degrees", degrees);
            telemetry.update();
            sleeper.Idle();
        }
        RotationData rotationData = new RotationData(degreesToTarget, degrees, gyro.getYaw());

        left.setPower(0);
        right.setPower(0);

        return rotationData;
    }

    public RotationData RotateLeftTo(double degrees) {

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentYaw = gyro.getYaw();
        double initialDegreesToTarget = degrees - currentYaw;
        double degreesToTarget = initialDegreesToTarget;

        left.setPower(.25);
        right.setPower(.25);

        while (degreesToTarget >= 0) {
            currentYaw = gyro.getYaw();
            degreesToTarget = degrees - currentYaw;

            if(degreesToTarget > initialDegreesToTarget)
            {
                degreesToTarget = degrees - (currentYaw + 360);
            }

            // degreesTraveled = getNormalizedYaw() - startingYaw;
            telemetry.addData("Current Yaw", currentYaw);
            telemetry.addData("Degreees to target", degreesToTarget);
            telemetry.addData("Target Degrees", degrees);
            telemetry.update();
            sleeper.Idle();
        }
        RotationData rotationData = new RotationData(degreesToTarget, degrees, gyro.getYaw());

        left.setPower(0);
        right.setPower(0);

        return rotationData;
    }

    private double getNormalizedYaw(){
        double currentYaw = gyro.getYaw();

        if (currentYaw < 0){
            currentYaw = Math.abs(currentYaw);
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

