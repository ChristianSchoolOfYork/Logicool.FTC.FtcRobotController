package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A class to simplify using the intake on the robot
 * @author josiah
 */
public class intakeLogic {
    private final Servo intake;
    private final double max;
    private final double min;
    LinearOpMode l;

    /**
     * Creates a new instance of intake logic with the specified {@linkplain LinearOpMode}, {@linkplain Servo}, and min and max values.
     * @param l A {@linkplain LinearOpMode} that is used to call the {@linkplain LinearOpMode#sleep(long) sleep()} and {@linkplain LinearOpMode#idle() idle()}methods. <br>
     *          When used in a TeleOp class, pass as {@code this}.
     * @param intake The Servo that is used for the intake.
     * @param max The maximum value that you want the intake to reach. This is used to stop the servo from resetting.
     * @param min The minimum value that you want the intake to reach. This is used to stop the servo from resetting.
     */
    public intakeLogic(LinearOpMode l, Servo intake, double max, double min){
        this.intake = intake;
        this.max = max;
        this.min = min;
        this.l =l;
    }

    /**
     * Makes the intake rotate inwards as long as it is not going to reach max value
     */
    public void takeIn(){
        if ((intake.getPosition()%1) < max){
            intake.setPosition((intake.getPosition()+.05)% 1.0);
            l.sleep(25);
            l.idle();
        }
    }

    /**
     * Makes the intake rotate outward as long as it won't reach minimum value
     */
    public void takeOut(){
        if (((intake.getPosition()% 1) > min)){
            intake.setPosition((intake.getPosition()-.05)% 1.0);
            l.sleep(25);
            l.idle();
        }
    }
}
