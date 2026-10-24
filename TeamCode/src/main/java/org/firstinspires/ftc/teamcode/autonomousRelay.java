package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class autonomousRelay {
    private static LinearOpMode imu;

    // Circumference = 1.890
    // Ticks per revolution = 2000
    // Distance travelled = t/2000.0 * 1.89

    public static void goForwardIn(double inchesForward) {
        double ticks = 0;
        Robot.backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (distanceTravelled(ticks) < inchesForward) {
            ticks = Robot.backleft.getCurrentPosition();
            double speed = Math.pow((inchesForward - distanceTravelled(ticks)) / 16.0, (0.27352297593));
            Robot.drive(speed, speed, speed, speed);
        }

        Robot.drive(0, 0, 0, 0);
    }

    public static void goRightIn(double inchesToTheRight) {
        double ticks = 0;
        Robot.backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (distanceTravelled(ticks) < inchesToTheRight) {
            ticks = Robot.backleft.getCurrentPosition();
            double speed = Math.pow((inchesToTheRight - distanceTravelled(ticks)) / 16.0, (0.27352297593));
            Robot.drive(speed, speed, speed, speed);
        }

        Robot.drive(0, 0, 0, 0);
    }
    public void turnDegrees(LinearOpMode opMode, double degrees, IMUControl imu){
        double currentHeading = imu.getHeading();
        double startHeading = currentHeading;

        while (opMode.opModeIsActive() && Math.abs(startHeading - currentHeading) < degrees) {
            currentHeading = imu.getHeading();
            double power = 0.5; // Set power for turning

            Robot.drive(power, power, -power, -power);


        }
    }

    public static double distanceTravelled(double ticks) {

        return ticks / 2000.0 * 1.89;
    }

    @Autonomous(name="Autonomous Relay", group="Autonomous")
    public class AutonomousRelayOpMode extends LinearOpMode {
        @Override
        public void runOpMode() {
            IMUControl imu = new IMUControl(hardwareMap); // Initialize IMU
            autonomousRelay relay = new autonomousRelay();

            waitForStart();

            if (opModeIsActive()) {
                relay.goForwardIn(9);
                relay.goRightIn(9);
                relay.goForwardIn(-9);
                relay.goRightIn(-9);
                relay.turnDegrees(this, 90, imu);
            }
        }
    }



}
