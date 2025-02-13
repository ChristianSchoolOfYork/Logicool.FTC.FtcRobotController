package org.firstinspires.ftc.teamcode.sensors;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RotationData
{
    private final double degreesToTarget;
    private final double targetDegrees;
    private final double yaw;

    public RotationData(double degreesToTarget, double targetDegrees, double yaw) {

        this.degreesToTarget = degreesToTarget;
        this.targetDegrees = targetDegrees;
        this.yaw = yaw;
    }
    
    public void UpdateTelemetry(Telemetry telemetry)
    {
        telemetry.addData("Degrees to target 1", degreesToTarget);
        telemetry.addData("Target degrees", targetDegrees);
        telemetry.addData("Yaw when loop exited", yaw);
    }
}
