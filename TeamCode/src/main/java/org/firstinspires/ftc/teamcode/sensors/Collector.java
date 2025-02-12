package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Sleeper;

import java.util.Locale;

/**
 * A class to simplify using the intakeServo on the robot
 * @author josiah
 */
public class Collector {
    private final Servo intakeServo;
    private final double max;
    private final double min;
    Sleeper l;

    /**
     * Creates a new instance of intakeServo logic with the specified {@linkplain LinearOpMode}, {@linkplain Servo}, and min and max values.
     * @param l A {@linkplain LinearOpMode} that is used to call the {@linkplain LinearOpMode#sleep(long) sleep()} and {@linkplain LinearOpMode#idle() idle()}methods. <br>
     *          When used in a TeleOp class, pass as {@code this}.
     * @param intakeServo The Servo that is used for the intakeServo.
     * @param max The maximum value that you want the intakeServo to reach. This is used to stop the servo from resetting.
     * @param min The minimum value that you want the intakeServo to reach. This is used to stop the servo from resetting.
     */
    public Collector(Sleeper l, Servo intakeServo, double max, double min){
        this.intakeServo = intakeServo;
        this.max = max;
        this.min = min;
        this.l =l;
    }

    public void AutoTakeIn() {
        if ((intakeServo.getPosition()%1) < max){
            intakeServo.setPosition(max);
            l.sleepFor(25);
        }
    }

    public void AutoDropOff() {
        if ((intakeServo.getPosition()%1) > min){
            intakeServo.setPosition(min);
            l.sleepFor(25);
        }
    }

    /**
     * Makes the intakeServo rotate inwards as long as it is not going to reach max value
     */
    public void takeIn(){
        if ((intakeServo.getPosition()%1) < max){
            intakeServo.setPosition((intakeServo.getPosition()+.05)% 1.0);
            l.sleepFor(25);
        }
    }

    /**
     * Makes the intakeServo rotate outward as long as it won't reach minimum value
     */
    public void takeOut(){
        if (((intakeServo.getPosition()% 1) > min)){
            intakeServo.setPosition((intakeServo.getPosition()-.05)% 1.0);
            l.sleepFor(25);
        }
    }

    public void update(Gamepad gamepad2){
        if(gamepad2.right_bumper) {
            takeIn();
        }
        if(gamepad2.left_bumper) {
            takeOut();
        }
    }

    public String getPercentage(){
        return String.format(Locale.getDefault(), "%d%%", (int) ((intakeServo.getPosition() - ((min + max)/2) ) * (200/(max - min))));
    }

}
