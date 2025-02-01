package org.firstinspires.ftc.teamcode.states;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ArmWrist;

public class InitialState extends AutoModeState {

    private final ArmWrist armWrist;

    public InitialState(ArmWrist armWrist, Telemetry telemetry) {
        super(telemetry);
        this.armWrist = armWrist;
    }

    public String GetStateName()
    {
        return "Initial State";
    }

    @Override
    protected void InternalExecute() {
        armWrist.SetPosition(260, -330
        );
    }
}
