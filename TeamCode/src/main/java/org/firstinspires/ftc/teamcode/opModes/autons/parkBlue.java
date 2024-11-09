package org.firstinspires.ftc.teamcode.opModes.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;
import org.firstinspires.ftc.teamcode.myUtil.autoThreads.motorThread;

@Autonomous(name="Theater")
public class parkBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        motorThread m = new motorThread(this,r);
        waitForStart();
        r.superRotate(0.3,90, Hardware.directions.LEFT);
        r.waiter(1000);
        r.superRotate(0.3,180, Hardware.directions.RIGHT);
//        while(!isStopRequested());
//        r.stableMove(0,0.5,18);
    }
}
