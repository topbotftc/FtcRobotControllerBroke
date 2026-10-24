package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


public class Clawcontrol {

    public Servo WristServo;
    Servo clawServo;
    Servo specimenClaw;




    public Clawcontrol(OpMode opMode) {
        clawServo = opMode.hardwareMap.get(Servo.class, "SERVO");
        WristServo = opMode.hardwareMap.get(Servo.class, "WristServo");
        specimenClaw = opMode.hardwareMap.get(Servo.class ,"specimen_claw");
    }

    public void clawwristMovement(OpMode opMode) {
//        if (opMode.gamepad2.x) {
//            WristServo.setPosition(pickup);
//        } if (opMode.gamepad2.y) {
//            WristServo.setPosition(close);
//        } if (opMode.gamepad2.b){
//            WristServo.setPosition(dropOff);
//        }
        double rotatesped = 0.01;
        if (opMode.gamepad2.x) {
            WristServo.setPosition(WristServo.getPosition() + rotatesped);
        }
        if (opMode.gamepad2.y) {
                WristServo.setPosition(WristServo.getPosition() - rotatesped);
        }

            //WristServo.setPosition(WristServo.getPosition());
    }

    double pinchNoo = 0.52;//.13;0.52
    double pinchYay = 0.65;//0.03;0.65

    // public void clawMovement(OpMode opMode) {
    //    if (opMode.gamepad2.dpad_right) {
     //       clawServo.setPosition(pinchNoo);

     //   } else if(opMode.gamepad2.dpad_left) {
      //      clawServo.setPosition(pinchYay);
      //  }
   // }
double egg = 0.8;;
    public void clawMovement(OpMode opMode) {
        if (opMode.gamepad2.dpad_right) {
            //clawServo.setPosition(clawServo.getPosition() + egg );
            clawServo.setPosition(1);

        }else if(opMode.gamepad2.dpad_left) {
            //clawServo.setPosition(clawServo.getPosition() - egg);
            clawServo.setPosition(0);
        }
        else {
            clawServo.setPosition(0.5);
        }
    }


    public void specimenClawMovement(OpMode opMode){

        if(opMode.gamepad2.a){
            specimenClaw.setPosition(0.095);
        } else {
            specimenClaw.setPosition(0.153);
        }


    }
}

