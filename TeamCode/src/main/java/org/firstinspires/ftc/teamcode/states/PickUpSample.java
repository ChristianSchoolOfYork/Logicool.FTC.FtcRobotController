package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ArmWrist;
import org.firstinspires.ftc.teamcode.sensors.Gripper;

public class PickUpSample extends AutoModeState {

    private final ArmWrist armWrist;
    private final Gripper gripper;

    public PickUpSample(Gripper gripper, ArmWrist armWrist, Telemetry telemetry) {
        super(telemetry);
        this.armWrist = armWrist;
        this.gripper = gripper;
    }

    @Override
    public String GetStateName() {
        return "Pick up Sample";
    }

    @Override
    protected void InternalExecute() {
        armWrist.SetPosition(0,0);
        gripper.close();
    }
}
