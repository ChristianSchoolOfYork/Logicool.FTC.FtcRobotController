package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A class to simplify using the gripper on the robot
 * @author josiah
 */
public class Gripper {
    private final Servo gripperServo;
    private boolean isGripperOpen;
    private final double max;
    private final double min;
    private boolean isMoving = false;
    /**
     * Creates a new instance of Gripper which controls the little claw on the robot. Requires a {@linkplain Servo}, and two doubles: max and min.
     * @param gripperServo The Servo that the gripper is on.
     * @param min The closed position of the gripper.
     * @param max The open position of the gripper.
     */
    Gripper(Servo gripperServo, double min, double max){
        this.gripperServo = gripperServo;
        this.max = max;
        this.min = min;
        this.gripperServo.setPosition(min);
        this.isGripperOpen = false;
    }

    /**
     * Sets the gripper to the open position if and only if it is closed
     */
    public void open(){
        if (!isGripperOpen && !isMoving){
            isMoving = true;
            gripperServo.setPosition(max);
            isGripperOpen = true;
            isMoving = false;
        }
    }
    /**
     * Sets the gripper to the closed position if and only if it is open
     */
    public void close(){
        if (isGripperOpen && ! isMoving){
            isMoving = true;
            gripperServo.setPosition(min);
            isGripperOpen = false;
            isMoving = false;
        }
    }

    public void update(Gamepad gamepad2){
        if (gamepad2.a){
            close();
        }
        if (gamepad2.b){
            open();
        }
    }
    public boolean getState(){
        return isGripperOpen;
    }
}
