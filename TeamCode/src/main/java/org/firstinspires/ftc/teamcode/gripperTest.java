package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class gripperTest extends LinearOpMode{

    @Override
    public void runOpMode(){
        Servo g = hardwareMap.get(Servo.class, "Gripper");
        Gripper gripper = new Gripper(g,0.5,0.3);
        boolean isGripperOpen = false;
        telemetry.addData("Status", "initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Status", "running");
            if (gamepad2.a){
                gripper.close();
            }
            if (gamepad2.b){
                gripper.open();
            }
        }
    }

}
