package org.firstinspires.ftc.teamcode.opModes.tunes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
@Disabled
@TeleOp(name="Knots")
public class encoderTuner extends OpMode {
    Hardware r = new Hardware();
    double y;
    @Override
    public void init() {
        r.initRobot(this);
        r.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        y=-gamepad1.left_stick_y*0.7;
        r.blm.setPower(y);
        r.flm.setPower(y);
        r.frm.setPower(y);
        r.brm.setPower(y);
        //
        telemetry.addData("Encoders: ", r.flm.getCurrentPosition());
        telemetry.update();
    }
}
