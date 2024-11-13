package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous
public class TryAtEncoders extends LinearOpMode {

    MecanumDrive MecanumDriveObj = new MecanumDrive();
    public int TicksPerRev = 385;
    public void getpos(){
        telemetry.addData("positions", MecanumDriveObj.brm.getCurrentPosition());
        telemetry.addData("position", MecanumDriveObj.blm.getCurrentPosition());
        telemetry.addData("position", MecanumDriveObj.frm.getCurrentPosition());
        telemetry.addData("position", MecanumDriveObj.flm.getCurrentPosition());
    }

    public void runOpMode() {
        MecanumDriveObj.init(hardwareMap);
        waitForStart();
        MecanumDriveObj.flm.getCurrentPosition();
        MecanumDriveObj.frm.getCurrentPosition();
        MecanumDriveObj.blm.getCurrentPosition();
        MecanumDriveObj.brm.getCurrentPosition();

        MecanumDriveObj.DriveForwardEncoder(0.6, 385);
        getpos();
        MecanumDriveObj.StrafeLeftEncoder(0.6,32);

    }
}
