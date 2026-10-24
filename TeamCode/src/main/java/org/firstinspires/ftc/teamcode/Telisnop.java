package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Telesnop")
public class Telisnop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        initMotors(this);

        boolean slow = true;

        waitForStart();

        double dspeed = 0.5;

        double fastspeed = .7;
        double slowspeed = .3;
        double speed = fastspeed;


        boolean options = gamepad1.options;
        boolean previousOptions = gamepad1.options;

        boolean up = gamepad1.dpad_up;
        boolean down = gamepad1.dpad_down;
        boolean left = gamepad1.dpad_left;
        boolean right = gamepad1.dpad_right;

        double LSX = gamepad1.left_stick_x;
        double LSY = gamepad1.left_stick_y;
        double RSX = gamepad1.right_stick_x;

        boolean x = gamepad2.x;

        boolean ExtendSlide = gamepad2.dpad_up;
        boolean intakeSlide = gamepad2.dpad_down;

        boolean Clawone = gamepad2.right_bumper;
        boolean wristone = gamepad2.left_bumper;



        //defining things
        while (opModeIsActive()) {
            //buttons
            up = gamepad1.dpad_up;
            down = gamepad1.dpad_down;
            left = gamepad1.dpad_left;
            right = gamepad1.dpad_right;

            options = gamepad1.options;

            LSX = gamepad1.left_stick_x;
            LSY = gamepad1.left_stick_y;
            RSX = gamepad1.right_stick_x;

            x = gamepad2.x;

            ExtendSlide = gamepad2.dpad_up;
            intakeSlide = gamepad2.dpad_down;

/*
            if (ExtendSlide){
                linearSlideControl.slideGoTo(-1000);     //button = move yippie
            } else if (intakeSlide){
                linearSlideControl.slideGoTo(0);
            } else {
                linearSlideControl.setSlidePow(0);
            }
*/

            if (ExtendSlide) {
                linearSlideControl.slideGoTo(1);    // button = motor moves yippie
            } else if (intakeSlide && (linearSlideControl.extendo.getCurrentPosition() > -950 || linearSlideControl.Winch.getCurrentPosition() < 300)){
                linearSlideControl.setSlidePow(-1);
            }else {
                linearSlideControl.slideGoTo(linearSlideControl.extendo.getCurrentPosition());
            }
            claw.clawMovement(this);

            claw.clawwristMovement(this);
            claw.specimenClawMovement(this);

            //gearshift for joystick is ds, gearshift for dpad drive is bs
            // this is so you can change the speeds for both Ex: the joystick speed at 0.75 and the dpad speed at 1
            // change gear with options button


            if (options && !previousOptions) {
                if (speed == fastspeed) {
                    speed = slowspeed;
                } else {
                    speed = fastspeed;
                }
            }
            previousOptions = options;


            //joystick drive
            if (Math.abs(LSX) > 0.05 || Math.abs(LSY) > 0.05 || Math.abs(RSX) > 0.05) {


                double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y) * speed;
                double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
                double rightX = gamepad1.right_stick_x * speed;

                double v1 = r * Math.cos(robotAngle) + rightX; //lf // wsa cos
                double v2 = r * Math.sin(robotAngle) - rightX; //rf // was sin
                double v3 = r * Math.sin(robotAngle) + rightX; //lb // was sin
                double v4 = r * Math.cos(robotAngle) - rightX; //rb // was cos
                double v5 = r * Math.cos(robotAngle) - rightX; //rb // was cos

                drive(v1, v2, v3, v4);

            }
            //dpad drive

            else if (up) {
                drive(speed, speed, speed, speed);
            } else if (down) {
                drive(-speed, -speed, -speed, -speed);
            } else if (left) {
                drive(-speed, speed, speed, -speed);
            } else if (right) {
                drive(speed, -speed, -speed, speed);
            } else {
                int i = 0;
                drive(i, i, i, i);

            }

            int pivotPos = linearSlideControl.Winch.getCurrentPosition();


            linearSlideControl.teleopControl(gamepad2.left_bumper, gamepad2.right_bumper);

//            else if(gamepad2.right_trigger > 0.25){
//                linearSlideControl.piviotGoTo2(-80);
//            }
//            else{
//                linearSlideControl.pivotNoo2(0);
//            }

                telemetry.addData("Slide encoder", linearSlideControl.extendo.getCurrentPosition());
//            telemetry.addData("pivotPos Right", linearSlideControl.pivotR.getCurrentPosition());
//            telemetry.addData("pivotPos Left", linearSlideControl.pivotL.getCurrentPosition());
//            telemetry.addData("claw pos", claw.clawServo.getPosition());
//            telemetry.addData("wrist pos", claw.WristServo.getPosition());
                telemetry.addData("speed", speed);
                telemetry.addData("armpos", linearSlideControl.Winch.getCurrentPosition());
//                telemetry.addData("armpow", linearSlideControl.pivotupsigma.getPower());
                telemetry.addData("claw2pos", claw.specimenClaw.getPosition());


                telemetry.update();


            }
        }
    }



