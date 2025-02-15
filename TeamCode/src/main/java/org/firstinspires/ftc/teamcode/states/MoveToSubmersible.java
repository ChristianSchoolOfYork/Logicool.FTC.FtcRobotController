package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ArmWrist;
import org.firstinspires.ftc.teamcode.sensors.Gripper;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;

public class MoveToSubmersible extends AutoModeState {

    private final ArmWrist armWrist;
    private final Gripper gripper;
    private final MainDrive drive;

    public MoveToSubmersible(Gripper gripper, ArmWrist armWrist, MainDrive drive, Telemetry telemetry) {
        super(telemetry);
        this.armWrist = armWrist;
        this.gripper = gripper;
        this.drive = drive;
    }

    @Override
    public String GetStateName() {
        return "Move To Submersible";
    }

    @Override
    protected void InternalExecute() {
        drive.RotateRightTo(90);
        drive.MoveForward(60);
        drive.RotateRightTo(0);
    }
}
