package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.MecanumDrive;
    @Autonomous
    public class Auton1 extends LinearOpMode {
        MecanumDrive MecanumDriveObj = new MecanumDrive();

        @Override
        public void runOpMode() {
            MecanumDriveObj.init(hardwareMap);

                waitForStart();
                MecanumDriveObj.Straight();

                MecanumDriveObj.TurnRight();

                MecanumDriveObj.Straight();

                MecanumDriveObj.ArmOut();

                MecanumDriveObj.ClawHold();


                MecanumDriveObj.ArmBack();


                MecanumDriveObj.TurnRight();

                MecanumDriveObj.Straight();

                MecanumDriveObj.ArmOut();
                MecanumDriveObj.ClawRelease();
            }
        }

