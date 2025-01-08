package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")
public class FrontCollisionAvoidance {
    private final DistanceSensor leftSensor, rightSensor;
    public FrontCollisionAvoidance(DistanceSensor left, DistanceSensor right) {
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
