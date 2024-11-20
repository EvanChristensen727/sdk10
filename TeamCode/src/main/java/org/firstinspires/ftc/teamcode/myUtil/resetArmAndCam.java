package org.firstinspires.ftc.teamcode.myUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Pre-Match")
public class resetArmAndCam extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        r.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.cam.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r.cam.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        r.claw.setPosition(0);
//        r.waiter(1000);
    }
}
