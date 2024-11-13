package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.MecanumDrive;
@Autonomous
public class test_strafe extends OpMode{
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    @Override
    public void init() {
        MecanumDriveObj.init(hardwareMap);
    }

    @Override
    public void loop() {
    MecanumDriveObj.Straight();

    }
}
