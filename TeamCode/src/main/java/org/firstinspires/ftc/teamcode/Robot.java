package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Robot{
    static DcMotor frontright;
    static DcMotor frontleft;
    static DcMotor backright;
    static DcMotor backleft;


    static LinearSlideControl linearSlideControl;
    static Clawcontrol claw;
    static LinearSlideControl Slide;


    public static void initMotors(OpMode opMode){
        frontright = opMode.hardwareMap.get(DcMotor.class, "frontRight");
        frontleft = opMode.hardwareMap.get(DcMotor.class, "frontLeft");
        backright = opMode.hardwareMap.get(DcMotor.class, "backRight");
        backleft = opMode.hardwareMap.get(DcMotor.class, "backLeft");


//        lspin = opMode.hardwareMap.get(DcMotor.class,"Lspin");
//        rspin = opMode.hardwareMap.get(DcMotor.class,"Rspin");
 //       DcMotor upDownSlide = opMode.hardwareMap.get(DcMotor.class, "Slideup");



        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);

        linearSlideControl = new LinearSlideControl(opMode);
        claw = new Clawcontrol(opMode);

//
//        frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        lspin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rspin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        upDownSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public static void drive(double fl, double fr, double bl, double br){

        frontleft.setPower(fl);
        frontright.setPower(fr);
        backleft.setPower(bl);
        backright.setPower(br);
    }



    }
