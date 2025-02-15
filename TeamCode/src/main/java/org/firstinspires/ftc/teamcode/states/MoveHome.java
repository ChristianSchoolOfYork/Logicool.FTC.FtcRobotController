package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Rotation;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;
import org.firstinspires.ftc.teamcode.sensors.RotationData;

public class MoveHome extends AutoModeState {
    private final MainDrive mainDrive;
    private RotationData rotationData;

    public MoveHome(MainDrive mainDrive, Telemetry telemetry) {
        super(telemetry);

        this.mainDrive = mainDrive;
    }

    public String GetStateName() {
        return "Move to sample";
    }
    public RotationData GetRotationData() { return this.rotationData; }

    @Override
    protected void InternalExecute() {
        mainDrive.MoveForward(-15.0f);
        rotationData = mainDrive.RotateLeftTo(180);
        mainDrive.MoveForward(10.0f);
    }
}
