package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ArmWrist;
import org.firstinspires.ftc.teamcode.sensors.DistanceCheck;
import org.firstinspires.ftc.teamcode.sensors.Gripper;
import org.firstinspires.ftc.teamcode.sensors.MainDrive;
@SuppressWarnings("unused")
public class PickUpSpecimen extends AutoModeState {
    private static final double TARGET_DISTANCE = 24;
    private static final int PICKUP_DISTANCE = -1100;
    private final ArmWrist armWrist;
    private final Gripper gripper;
    private final MainDrive mainDrive;
    private final DistanceCheck distanceCheck;

    public PickUpSpecimen(Gripper gripper, ArmWrist armWrist, MainDrive mainDrive, DistanceCheck distanceCheck, Telemetry telemetry) {
        super(telemetry);
        this.armWrist = armWrist;
        this.gripper = gripper;
        this.mainDrive = mainDrive;
        this.distanceCheck = distanceCheck;
    }
    @Override
    public String GetStateName() {
        return "Pick up specimen";
    }

    @Override
    protected void InternalExecute() {
        double distanceFromWall = distanceCheck.getLeftValue();
        double distanceToTarget = distanceFromWall - TARGET_DISTANCE;
        gripper.open();
        mainDrive.MoveForward(distanceToTarget);
        armWrist.SetPosition(0,PICKUP_DISTANCE);
        gripper.close();
    }
}
