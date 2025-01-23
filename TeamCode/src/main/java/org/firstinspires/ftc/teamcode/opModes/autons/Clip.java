package org.firstinspires.ftc.teamcode.opModes.autons;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch.Claw;

@Config
@Autonomous(name="Clip")
public class Clip extends LinearOpMode {
    public static int camEnd = -1125;
    //You are one in a krillion

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();

        r.initRobot(this);
        Claw claw = new Claw(this, r);
        r.pause = 500;
        claw.setPosition(1);
        r.slay.setPosition(0.4);
        r.lever.setPosition(1);
        waitForStart();
        r.waiter(10000);
        r.simpleMove(0.5,6, Hardware.directions.FORWARD);
        r.simpleMove(0.5, 4, Hardware.directions.LEFT);
        r.simpleMove(0.5, 14, Hardware.directions.FORWARD);
        r.arm.setTargetPosition(1200);
        r.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm.setPower(0.8);
        while (!r.getTolerance(1200,r.arm.getCurrentPosition(),5));
        r.simpleMove(0.6,4, Hardware.directions.FORWARD);
//        r.simpleMove(0.5,4, Hardware.directions.FORWARD);
        r.arm.setPower(0.3);
        r.arm.setTargetPosition(600);
        r.waiter(2000);
//        r.simpleMove(0.4,1, Hardware.directions.FORWARD);
//        r.arm.setTargetPosition(400);
//        r.waiter(3000);
        claw.setPosition(0);
        r.waiter(1000);
        r.arm.setPower(0.8);
        r.arm.setTargetPosition(0);
        r.simpleMove(0.5,6, Hardware.directions.BACKWARD);
        r.simpleMove(0.5,27, Hardware.directions.RIGHT);
        r.simpleMove(0.5,15, Hardware.directions.BACKWARD);
    }
}
