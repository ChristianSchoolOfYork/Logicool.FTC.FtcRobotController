package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * A class to simplify using the gripper on the robot
 * @author josiah
 */
public class gripperState {
    private final Servo gripper;
    private boolean isGripperOpen;
    private final double max;
    private final double min;
    private boolean isMoving = false;
    /**
     * Creates a new instance of gripperState which controls the little claw on the robot. Requires a {@linkplain Servo}, and two doubles: max and min.
     * @param gripper The Servo that the gripper is on.
     * @param min The closed position of the gripper.
     * @param max The open position of the gripper.
     */
    gripperState(Servo gripper,double min,double max){
        this.gripper = gripper;
        this.max = max;
        this.min = min;
        this.gripper.setPosition(max);
        this.isGripperOpen = true;
    }

    /**
     * Sets the gripper to the open position if and only if it is closed
     */
    public void open(){
        if (!isGripperOpen && !isMoving){
            isMoving = true;
            gripper.setPosition(max);
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
            gripper.setPosition(min);
            isGripperOpen = false;
            isMoving = false;
        }
    }
}
