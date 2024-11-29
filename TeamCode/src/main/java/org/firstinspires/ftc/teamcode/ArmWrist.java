package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ArmWrist {
    private final DcMotor arm, wrist;
    public ArmWrist(DcMotor arm, DcMotor wrist){
        this.arm = arm;
        this.wrist = wrist;
    }

    public void update(Gamepad gamepad2){
        arm.setPower(gamepad2.left_stick_y*.5);
        wrist.setPower(gamepad2.right_stick_y*-.5);
    }
}
