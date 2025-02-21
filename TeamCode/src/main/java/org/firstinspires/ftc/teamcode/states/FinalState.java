package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ArmWrist;

public class FinalState extends AutoModeState
{
    private final ArmWrist armWrist;

    public FinalState(ArmWrist armWrist, Telemetry telemetry) {
        super(telemetry);

        this.armWrist = armWrist;
    }

    public String GetStateName() {
        return "Final State";
    }

    @Override
    public void InternalExecute() {
        armWrist.SetPosition(0, 0);
    }
}
