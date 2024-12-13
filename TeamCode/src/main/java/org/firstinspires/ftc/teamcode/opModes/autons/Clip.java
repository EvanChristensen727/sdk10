package org.firstinspires.ftc.teamcode.opModes.autons;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;

@Config
@Autonomous(name="Clip")
public class Clip extends LinearOpMode {
    public static int camEnd = -1125;
    //You are one in a krillion

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        r.pause = 500;
        r.claw.setPosition(0);
        r.slay.setPosition(0.4);
        r.lever.setPosition(1);
        waitForStart();
        r.simpleMove(0.5,6, Hardware.directions.FORWARD);
        r.simpleMove(0.5, 4, Hardware.directions.LEFT);
        r.simpleMove(0.5, 14, Hardware.directions.FORWARD);
        r.arm.setTargetPosition(395);
        r.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm.setPower(0.8);
        while (!r.getTolerance(395,r.arm.getCurrentPosition(),2));
        r.simpleMove(0.6,3.25, Hardware.directions.FORWARD);
//        r.simpleMove(0.5,4, Hardware.directions.FORWARD);
        r.arm.setTargetPosition(100);
        r.waiter(1000);
        r.claw.setPosition(0.6);
        r.waiter(1000);
        r.arm.setTargetPosition(0);
        r.simpleMove(0.5,6, Hardware.directions.BACKWARD);
        r.simpleMove(0.5,27, Hardware.directions.RIGHT);
        r.simpleMove(0.5,15, Hardware.directions.BACKWARD);
    }
}
