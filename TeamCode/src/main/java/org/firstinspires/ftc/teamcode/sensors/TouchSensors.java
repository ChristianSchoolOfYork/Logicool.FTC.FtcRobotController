package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.rev.RevTouchSensor;
@SuppressWarnings("unused")
public class TouchSensors {
    private final RevTouchSensor left, right;
    public TouchSensors(RevTouchSensor touchSensorLeft, RevTouchSensor right){
        this.left = touchSensorLeft;
        this.right = right;
    }

    public boolean getLeft() {
        return left.isPressed();
    }

    public boolean getRight() {
        return right.isPressed();
    }
}
