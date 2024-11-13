package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.util.AutonomousHardwareMap;


    @Autonomous(name="A2_Direct", group="Autonomous")
    public class A2_Direct extends LinearOpMode {

        // this program starts assuming the robot is facing towards the backdrops.

        AutonomousHardwareMap hmap = new AutonomousHardwareMap();

        double strafePower = 0.2;
        double forwardPower = 0.1;
        int strafeTime = 425;
        int forwardTime = 2150;

        @Override
        public void runOpMode() throws InterruptedException {

            hmap.runOpmode(this);
            //hmap.resetEncoders();
            waitForStart();

            hmap.strafe("right", strafePower);

            hmap.wait(strafeTime);
            hmap.setDrivePower(0.0);
            hmap.wait(500);
            hmap.turn("right", 0.75);
            hmap.wait(67);
            hmap.setDrivePower(0.0);

            hmap.wait(500);

            hmap.setDrivePower(forwardPower);

            hmap.wait(forwardTime);

            hmap.setDrivePower(0.0);

        }



    }
