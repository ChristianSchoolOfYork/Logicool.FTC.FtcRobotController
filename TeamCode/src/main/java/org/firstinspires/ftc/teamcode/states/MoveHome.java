package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;

public class MoveHome extends AutoModeState {
    private final MainDrive mainDrive;

    public MoveHome(MainDrive mainDrive, Telemetry telemetry) {
        super(telemetry);

        this.mainDrive = mainDrive;
    }

    public String GetStateName() {
        return "Move to sample";
    }

    @Override
    protected void InternalExecute() {
        mainDrive.MoveForward(-15.0f);
        mainDrive.RotateLeftTo(180);
        mainDrive.MoveForward(10.0f);
    }
}
