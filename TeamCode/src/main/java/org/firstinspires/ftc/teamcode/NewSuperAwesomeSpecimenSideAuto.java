package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(group = "new specimen side auto")
public class NewSuperAwesomeSpecimenSideAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        // drive objectlinre
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //claw control object
        Clawcontrol clawControl = new Clawcontrol(this);

        //slide control object
        LinearSlideControl slideControl = new LinearSlideControl(this);

        Pose2d startPos = new Pose2d(22, -60, 90);
        drive.setPoseEstimate(startPos);

        Trajectory getFirstSample = drive.trajectoryBuilder(startPos)
                .lineToConstantHeading(new Vector2d(10.5, -2))
                .splineToConstantHeading(new Vector2d(54, -46), Math.toRadians(0))
                .build();

        Trajectory getSecondSample = drive.trajectoryBuilder(getFirstSample.end())
                .lineToConstantHeading(new Vector2d(15, 3))
                .splineToConstantHeading(new Vector2d(58, -20), Math.toRadians(90))
                .build();

        Trajectory spin = drive.trajectoryBuilder(getSecondSample.end())
                .lineToLinearHeading(new Pose2d(54, -26, 160))
                .build();

        Trajectory getFirstSpecimen = drive.trajectoryBuilder(spin.end())
                .strafeLeft(30)
                .build();

        Trajectory scoreFirstSpecimen = drive.trajectoryBuilder(getFirstSpecimen.end())
                .lineToLinearHeading(new Pose2d(5, -9, Math.toRadians(0)))
                .build();

        Trajectory getSecondSpecimen = drive.trajectoryBuilder(scoreFirstSpecimen.end())
                .lineToLinearHeading(new Pose2d(44, -38, Math.toRadians(160)))
                .build();

        Trajectory getSecondSpecimenStrafe = drive.trajectoryBuilder(spin.end())
                .strafeLeft(18)
                .build();

        Trajectory scoreSecondSpecimen = drive.trajectoryBuilder(getSecondSpecimenStrafe.end())
                .lineToLinearHeading(new Pose2d(1, -5, Math.toRadians(10)))
                .build();

        Trajectory goToPark = drive.trajectoryBuilder(scoreSecondSpecimen.end())
                .lineToLinearHeading(new Pose2d(120, -40, Math.toRadians(10)))
                .build();

        Trajectory get3rdSample = drive.trajectoryBuilder(scoreSecondSpecimen.end())
                .lineToLinearHeading(new Pose2d(95, -36, Math.toRadians(90)))
                .strafeLeft(10)
                .build();

        slideControl.winchGoTo(slideControl.Winch.getCurrentPosition(),1);

        clawControl.specimenClaw.setPosition(0.095); // open

        waitForStart();

        drive.followTrajectory(getFirstSample);

//        drive.followTrajectory(getSecondSample);

        drive.followTrajectory(spin);

        drive.followTrajectory(getFirstSpecimen);

        clawControl.specimenClaw.setPosition(0.158); // close

        sleep(250);

        slideControl.slideGoTo(-1900); // high chamber position

        drive.followTrajectory(scoreFirstSpecimen);

        slideControl.slideGoTo(-900);

        sleep(250);

        clawControl.specimenClaw.setPosition(.095); // let go completely

        slideControl.slideGoTo(0);

        drive.followTrajectory(getSecondSpecimen);

        drive.followTrajectory(getSecondSpecimenStrafe);

        clawControl.specimenClaw.setPosition(0.155); // close

        sleep(400);

        slideControl.slideGoTo(-1900); // high chamber position

        drive.followTrajectory(scoreSecondSpecimen);

        slideControl.slideGoTo(-900);

        sleep(250);

        clawControl.specimenClaw.setPosition(.095); // let go completely

        drive.followTrajectory(get3rdSample);











    }

}
