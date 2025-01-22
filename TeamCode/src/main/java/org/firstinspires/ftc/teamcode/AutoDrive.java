package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@SuppressWarnings("unused")
public class AutoDrive {
    public final DcMotor left,right;

    public AutoDrive(DcMotor left, DcMotor right) {
        this.left = left;
        this.right = right;
    }

    public void setPower(double left, double right){
        this.left.setPower(-left);
        this.right.setPower(right);
    }
    public void setPower(PowerLevels pl){
        this.left.setPower(-pl.getLeftPower());
        this.right.setPower(pl.getRightPower());
    }

    public void stop(){
        this.left.setPower(0);
        this.right.setPower(0);
    }
}
