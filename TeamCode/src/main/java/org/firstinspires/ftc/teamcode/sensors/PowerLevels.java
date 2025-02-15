package org.firstinspires.ftc.teamcode.sensors;

/**
 * A class that is called to feed the current trimmed motor values out of the {@linkplain Trim} class.
 * @author josiah
 */
public class PowerLevels{
    private final float leftPower;
    private final float rightPower;

    /**
     * A constructor only meant to be used by {@linkplain Trim} class.
     * @param leftPower The left power value
     * @param rightPower The right power value
     */
    public PowerLevels(float leftPower, float rightPower){
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    /**
     * Gives you the left power value,
     * in the case of using this in conjunction with
     * {@link Trim#getPowerLevel(float, float) Trim.getPowerLevel()},
     * gives you the left motor value after trimming.
     * @return left power
     */
    public float getLeftPower(){
        return leftPower;
    }
    /**
     * Gives you the right power value,
     * in the case of using this in conjunction with
     * {@link Trim#getPowerLevel(float, float) Trim.getPowerLevel()},
     * gives you the right motor value after trimming.
     * @return right power
     */
    public float getRightPower(){
        return rightPower;
    }
}
