package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Robot.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "FirstAuto", preselectTeleOp="Telesnop")
public class FirstAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        double speed = 0.4;
        initMotors(this);
        waitForStart();
        double startTime = this.time;
        while (opModeIsActive()){
            while(this.time - startTime <= .9){
                drive(-speed,-speed,-speed,-speed);
            }
            drive(0,0,0,0);
            this.requestOpModeStop();
        }
    }

}