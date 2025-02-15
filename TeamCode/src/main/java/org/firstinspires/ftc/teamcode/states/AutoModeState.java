package org.firstinspires.ftc.teamcode.states;

import org.firstinspires.ftc.robotcontroller.external.samples.UtilityOctoQuadConfigMenu;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class AutoModeState {

    protected final Telemetry telemetry;

    public AutoModeState(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public abstract String GetStateName();

    public void Execute() {
        telemetry.addData("State",GetStateName() + " Running");
        telemetry.update();

        InternalExecute();

        telemetry.addData("State",GetStateName() + " Finished");
        telemetry.update();
    }

    protected abstract void InternalExecute();

}
