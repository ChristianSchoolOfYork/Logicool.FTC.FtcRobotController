package org.firstinspires.ftc.teamcode;


class PowerLevels{
    private float leftPower;
    private float rightPower;

    public PowerLevels(float leftPower, float rightPower){
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    public float getLeftPower(){
        return leftPower;
    }

    public float getRightPower(){
        return rightPower;
    }
}
public class Trim {
    private float leftTrim = 1;
    private float rightTrim = 1;
    private final float TRIMAMOUNT = 0.01f;

    public float getLeftTrim(){
        return leftTrim;
    }

    public float getRightTrim(){
        return rightTrim;
    }

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

    public PowerLevels getPowerLevel(float left, float right){
        return new PowerLevels(left*leftTrim,right*leftTrim);
    }

}
