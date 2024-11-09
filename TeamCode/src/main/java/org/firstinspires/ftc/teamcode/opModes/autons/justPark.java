package org.firstinspires.ftc.teamcode.opModes.autons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;

public class justPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        waitForStart();
        r.simpleMove(0.5, 6, Hardware.directions.FORWARD);
        r.simpleMove(0.5, 48, Hardware.directions.RIGHT);
        r.simpleMove(0.5, 3, Hardware.directions.BACKWARD);
    }
}
