package org.firstinspires.ftc.teamcode.states;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ArmWrist;
import org.firstinspires.ftc.teamcode.Gripper;

public class InitialState extends AutoModeState {

    private final ArmWrist armWrist;
    private final Gripper gripper;

    public InitialState(ArmWrist armWrist, Gripper gripper, Telemetry telemetry) {
        super(telemetry);
        this.armWrist = armWrist;
        this.gripper = gripper;
    }

    public String GetStateName()
    {
        return "Initial State";
    }

    @Override
    protected void InternalExecute() {
        armWrist.SetPosition(0, -665 );
        gripper.open();
    }
}
