package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")
public class DistanceCheck
{
    private final DistanceSensor leftSensor, rightSensor;
    public DistanceCheck(DistanceSensor left, DistanceSensor right) {
        this.leftSensor = left;
        this.rightSensor = right;
    }

    public double getLeftValue() {
        return leftSensor.getDistance(DistanceUnit.CM);
    }

    public double getRightValue() {
        return rightSensor.getDistance(DistanceUnit.CM);
    }
}
