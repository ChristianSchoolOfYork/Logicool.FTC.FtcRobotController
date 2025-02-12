package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;

public class MoveToSample extends AutoModeState {
    private final MainDrive mainDrive;

    public MoveToSample(MainDrive mainDrive, Telemetry telemetry) {
        super(telemetry);

        this.mainDrive = mainDrive;
    }

    public String GetStateName() {
        return "Move to sample";
    }

    @Override
    protected void InternalExecute() {
        mainDrive.MoveForward(25.0f);
    }
}
