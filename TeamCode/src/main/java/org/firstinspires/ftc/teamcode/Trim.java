package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * A class that is called to feed the current trimmed motor values out of the {@linkplain Trim} class.
 * @author josiah
 */
class PowerLevels{
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

/** A class to apply a trim to the motors on the robot
 * @author josiah, Ethan
 */
public class Trim {
    private float leftTrim = 1;
    private float rightTrim = 1;
    private final float TRIMAMOUNT = 0.01f;
    boolean isDpadLeft =false,isDpadRight=false;
    /**
     * Returns the left trim value
     * @return the left trim as a value between 0 and 1
     */
    public float getLeftTrim(){
        return leftTrim;
    }

    /**
     * Returns the right trim value
     * @return the right trim as a value between 0 and 1
     */
    public float getRightTrim(){
        return rightTrim;
    }

    /**
     * Trims to the left by the value of {@linkplain Trim#TRIMAMOUNT}
     */
    public void addLeft(){

        if(rightTrim == 1)
        {
            leftTrim = Math.max(0,leftTrim-TRIMAMOUNT);
        }
        else
        {
            rightTrim = Math.min(rightTrim + TRIMAMOUNT, 1);
        }
    }

    /**
     * Trims to the right by the value of {@linkplain Trim#TRIMAMOUNT}
     */
    public void addRight(){

        if(leftTrim == 1)
        {
            rightTrim = Math.max(0,rightTrim-TRIMAMOUNT);
        }
        else
        {
            leftTrim = Math.min(leftTrim + TRIMAMOUNT, 1);
        }
    }

    public void setLeft(float trim){
        leftTrim = trim;
        rightTrim = 1;
    }

    public void setRight(float trim){
        leftTrim = 1;
        if (trim < 0){
            rightTrim = 0;
        } else if (trim > 1){
            rightTrim = 1;
        } else {
            rightTrim = trim;
        }
    }
    /**
     * Returns what the motors should be set to by applying the trim to the driving values
     * @param left The untrimmed value of the left motor
     * @param right The untrimmed value of the right motor
     * @return A {@link PowerLevels} containing the trimmed motor values
     */
    public PowerLevels getPowerLevel(float left, float right){
        return new PowerLevels(left*leftTrim,right*leftTrim);
    }

    public void update(Gamepad gamepad1){
        if (gamepad1.dpad_left && !isDpadLeft){
            addLeft();
            isDpadLeft = true;
        }

        if (!gamepad1.dpad_left){
            isDpadLeft = false;
        }


        if (gamepad1.dpad_right && !isDpadRight){
            addRight();
            isDpadRight = true;
        }

        if (!gamepad1.dpad_right) {
            isDpadRight = false;
        }
    }
}
