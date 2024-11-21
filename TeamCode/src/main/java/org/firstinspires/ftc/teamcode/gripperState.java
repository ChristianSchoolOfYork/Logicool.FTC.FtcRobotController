package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class gripperState {
    Servo gripper;
    private boolean isGripperOpen;
    private final double max;
    private final double min;
    gripperState(Servo gripper,double min,double max){
        this.gripper = gripper;
        this.max = max;
        this.min = min;
        this.gripper.setPosition(max);
        this.isGripperOpen = true;
    }

    public void open(){
        if (!isGripperOpen){
            gripper.setPosition(max);
            isGripperOpen = true;
        }
    }

    public void close(){
        if (isGripperOpen){
            gripper.setPosition(min);
            isGripperOpen = false;
        }
    }
}
