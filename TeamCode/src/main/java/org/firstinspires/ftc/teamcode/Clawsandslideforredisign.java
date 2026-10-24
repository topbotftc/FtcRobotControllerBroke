package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

class ClawsAndSlideForRedesign {
    public Servo WristServoOne;
    private Servo clawServoOne;

    private double setPositionOpenOne = 0.0; // fix
    private double setPositionClosedOne = 1; // fix

    public ClawsAndSlideForRedesign(OpMode opMode) {
        clawServoOne = opMode.hardwareMap.get(Servo.class, "CS1");
        WristServoOne = opMode.hardwareMap.get(Servo.class, "WS1");
        clawServoTwo = opMode.hardwareMap.get(Servo.class, "CS2");
        WristServoTwo = opMode.hardwareMap.get(Servo.class, "WS2");
    }

    public void clawWristMovementOne(OpMode opMode) {
        double rotateSpeed = 0.01;
        if (opMode.gamepad2.x) {
            WristServoOne.setPosition(WristServoOne.getPosition() + rotateSpeed);
        }
        if (opMode.gamepad2.y) {
            WristServoOne.setPosition(WristServoOne.getPosition() - rotateSpeed);
        }
    }

    public void clawMovementOne(OpMode opMode) {
        if (opMode.gamepad2.dpad_right) {
            clawServoOne.setPosition(setPositionOpenOne);
        }
        else {
            clawServoOne.setPosition(setPositionClosedOne);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public Servo WristServoTwo;
    private Servo clawServoTwo;

    private double setPositionOpenTwo = 1.0; // fix
    private double setPositionClosedTwo = 0.0; // fix

    public void clawWristMovementTwo(OpMode opMode) {

        if (opMode.gamepad2.b) {
            WristServoTwo.setPosition(0.75);
        } else {
            WristServoTwo.setPosition(0);
        }
    }

    public void clawMovementTwo(OpMode opMode) {
        if (opMode.gamepad2.square) {
            clawServoTwo.setPosition(setPositionOpenTwo);
        }
        if (opMode.gamepad2.triangle) {
            clawServoTwo.setPosition(setPositionClosedTwo);
        }
    }
}
