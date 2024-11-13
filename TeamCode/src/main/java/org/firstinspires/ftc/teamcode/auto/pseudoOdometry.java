package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.TimeTurn;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous
public class pseudoOdometry extends LinearOpMode{
     MecanumDrive MecanumDriveObj = new MecanumDrive();
//get position of motors and get angle degrees, also send it to the driverhub.
public void getpos() {
    telemetry.addData("positions", MecanumDriveObj.brm.getCurrentPosition());
    telemetry.addData("position", MecanumDriveObj.blm.getCurrentPosition());
    telemetry.addData("position", MecanumDriveObj.frm.getCurrentPosition());
    telemetry.addData("position", MecanumDriveObj.flm.getCurrentPosition());
    telemetry.addData("Our Heading", MecanumDriveObj.getHeading(AngleUnit.DEGREES));
    MecanumDriveObj.flm.getCurrentPosition();
    MecanumDriveObj.frm.getCurrentPosition();
    MecanumDriveObj.blm.getCurrentPosition();
    MecanumDriveObj.brm.getCurrentPosition();
}
public void runOpMode() {
    MecanumDriveObj.init(hardwareMap);
    waitForStart();
    getpos();
    // set power and distance to drive forward
    MecanumDriveObj.DriveForwardEncoder(0.6,4);
    getpos();
    MecanumDriveObj.StrafeEncoder(0.6,32, "left");
    if (MecanumDriveObj.getHeading(AngleUnit.DEGREES)>= 90 && MecanumDriveObj.getHeading(AngleUnit.DEGREES) <= 270){
        MecanumDriveObj.TurnRight();
        getpos();
        if( MecanumDriveObj.getHeading(AngleUnit.DEGREES)<45  && MecanumDriveObj.getHeading(AngleUnit.DEGREES) >=315){
            MecanumDriveObj.Stopper();
        }
    MecanumDriveObj.DriveForwardEncoder(0.6, 385-MecanumDriveObj.flm.getCurrentPosition());
        }
}
}

