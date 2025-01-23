package org.firstinspires.ftc.teamcode.opModes.tunes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
//@Disabled
@TeleOp(name="Marionette")
public class armTuner extends OpMode {
    Hardware r = new Hardware();
    @Override
    public void init() {
        r.initRobot(this);
        r.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        r.cam.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        r.cam.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        r.lever.setPosition(1);
    }
}
