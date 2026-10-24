package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.TrajectoryGenerator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(group = "new specimen side auto")
public class SuperAwesomeTopBasketAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        // drive object
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //claw control object
        Clawcontrol clawControl = new Clawcontrol(this);

        //slide control object
        LinearSlideControl slideControl = new LinearSlideControl(this);

        clawControl.WristServo.setPosition(0.095);

       Pose2d startpos = new Pose2d(-40,-60,Math.toRadians(180));
        drive.setPoseEstimate(startpos);

       sleep(50);

       Trajectory scorePreLoadSigma = drive.trajectoryBuilder(startpos)
               .lineToConstantHeading (new Vector2d(-54, -54))
               .build();

        waitForStart();

       drive.followTrajectory(scorePreLoadSigma);




    }

}

