package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class MainDrive {
    Gamepad gamepad1;
    DcMotor left, right;
    double accelerationSpeed = 0.5f

    public MainDrive(Gamepad gamepad1, DcMotor left, DcMotor right){
        this.gamepad1 = gamepad1;
        this.left = left;
        this.right = right;
    }
    public void update(PowerLevels pl, float deltaTime){
        //left.setPower(gamepad1.left_stick_y * pl.getLeftPower());
        //right.setPower(-gamepad1.right_stick_y * pl.getRightPower());
        
        if(left.getPower()==gamepad1.left_stick_y * pl.getLeftPower()){
            left.setPower(gamepad1.left_stick_y * pl.getLeftPower());
        }else {
            left.setPower(left.getPower() + (accelerationSpeed * deltaTime);
        }
    }

}
