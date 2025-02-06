package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ArmWrist;
import org.firstinspires.ftc.teamcode.Gripper;
import org.firstinspires.ftc.teamcode.MainDrive;

public class GrabSpecimen extends AutoModeState{
    MainDrive mainDrive;
    ArmWrist armWrist;
    Gripper gripper;

    /**
     * Upon execution, moves back 10 inches, moves arm to a forward position and opens the gripper
     */
    public GrabSpecimen(MainDrive mainDrive, ArmWrist armWrist, Gripper gripper, Telemetry telemetry) {
        super(telemetry);

        this.mainDrive = mainDrive;
        this.armWrist = armWrist;
        this.gripper = gripper;
    }

    @Override
    public String GetStateName() {
        return "Grab specimen";
    }

    @Override
    protected void InternalExecute() {
        mainDrive.MoveForward(-10);
        //armWrist.SetPosition(0, );
        gripper.open();
    }
}
