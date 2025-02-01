package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Collector;

public class PickUpSample extends AutoModeState {

    private final Collector collector;

    public PickUpSample(Collector collector, Telemetry telemetry) {
        super(telemetry);
        this.collector = collector;
    }

    @Override
    public String GetStateName() {
        return "Pick up Sample";
    }

    @Override
    protected void InternalExecute() {
        collector.AutoTakeIn();
    }
}
