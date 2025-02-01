package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmWrist {
    private final DcMotor arm;
    private final DcMotor wrist;
    private final Telemetry telemetry;

    public ArmWrist(DcMotor arm, DcMotor wrist, Telemetry telemetry){
        this.arm = arm;
        this.wrist = wrist;
        this.telemetry = telemetry;
    }

    public void SetPosition(int wristPosition, int armPosition) {

        int currentWristPosition = wrist.getCurrentPosition();
        int currentArmPosition = arm.getCurrentPosition();

        wrist.setTargetPosition(wristPosition);
        wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setTargetPosition(armPosition);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double wristPower = wristPosition > currentWristPosition ? -.25 : .25;
        double armPower = armPosition > currentArmPosition ? -1.0 : 1.0;

        wrist.setPower(wristPower);
        arm.setPower(armPower);

        while(wrist.isBusy() || arm.isBusy()) {
            telemetry.addData("Running wrist to", wristPosition);
            telemetry.addData("Wrist Currently at", wrist.getCurrentPosition());
            telemetry.addData("Running arm to", armPosition);
            telemetry.addData("Arm Currently at", arm.getCurrentPosition());
            telemetry.update();

            if(!wrist.isBusy()) {
                wrist.setPower(0);
            }

            if(!arm.isBusy()) {
                arm.setPower(0);
            }
        }

        arm.setPower(0);
        wrist.setPower(0);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void update(Gamepad gamepad2){
        arm.setPower(gamepad2.left_stick_y*.5);
        wrist.setPower(gamepad2.right_stick_y*-.5);
    }
}
