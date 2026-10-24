package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.apache.commons.math3.geometry.euclidean.twod.Line;

public class LinearSlideControl {
    DcMotor extendo;
    DcMotor Winch;

    int topBasket = 0;
    int groundLevel = 0;
    int specimen = 0;
    int targetLevel = groundLevel;
    int targetPosition = 1000;

    public LinearSlideControl(OpMode opMode) {
        Winch = opMode.hardwareMap.get(DcMotor.class, "pivotupSigma");
        extendo = opMode.hardwareMap.get(DcMotor.class, "slideUp");

        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Winch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Winch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //slide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setSlidePow(double power) {
        extendo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendo.setPower(power);
    }

    public void pivot2(double power) { // Winch control
        if (power != 0) {
            Winch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Winch.setPower(power * 0.5);// Stop the motor
        } else {
            if(Winch.getCurrentPosition() > 300){
                this.winchGoTo(Winch.getCurrentPosition(), .80);
            } else {
                Winch.setPower(0);
            }
        }
    }

    public void pivotNoo2(double i) {
        pivot2(-i);
    }

    public void pivotYay2(double i) {
        pivot2(i);
    }

    public void slideGoTo(int target){
        slideGoTo(target, .80);
    }
    public void slideGoTo(int target, double power) {
        extendo.setTargetPosition(target);
        extendo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendo.setPower(power);
    }

    public void winchGoTo(int target, double power){
        Winch.setTargetPosition(target);
        Winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Winch.setPower(power);
    }

    // Example Teleop Usage
    public void teleopControl(boolean leftBumper, boolean rightBumper) {
        if (leftBumper && rightBumper) {
            pivot2(0);
        }else if (rightBumper) {
            pivotNoo2(1.0); // Move in the opposite direction
        } else if (leftBumper ) {
            pivotYay2(1.0); // Move in one direction
        } else {
            pivot2(0); // Stop the motor

        }
    }
}

