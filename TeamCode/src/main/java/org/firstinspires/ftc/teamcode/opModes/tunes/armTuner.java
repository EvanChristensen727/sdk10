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
        r.cam.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.cam.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        r.arm.setPower(-gamepad1.right_stick_y*0.3);
        r.cam.setPower(gamepad1.dpad_up ? -0.3 : gamepad1.dpad_down ? 0.3 : 0);
        telemetry.addData("Arm Position", r.arm.getCurrentPosition());
        telemetry.addData("Arm Position", r.cam.getCurrentPosition());
        telemetry.update();
    }
}
