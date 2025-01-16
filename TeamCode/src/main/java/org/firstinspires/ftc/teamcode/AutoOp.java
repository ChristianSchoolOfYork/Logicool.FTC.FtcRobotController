package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoOp extends LinearOpMode  implements Sleeper{
    @Override
    public void runOpMode() {

        Servo grab = hardwareMap.get(Servo.class, "Gripper");

        Gripper gripper = new Gripper(grab,0.5,0.3);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();



        }
    }
    @Override
    public void sleepFor(long milliseconds) {
        this.sleep(milliseconds);
        this.idle();
    }
}
