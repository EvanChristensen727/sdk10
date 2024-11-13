package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class PracticeAuton extends LinearOpMode {

    protected DcMotor frm;
    protected DcMotor flm;
    protected DcMotor blm;
    protected DcMotor brm;
    @Override
    public void runOpMode() throws InterruptedException {
        flm = hardwareMap.get(DcMotor.class, "front-left-motor");
        frm = hardwareMap.get(DcMotor.class, "front-right-motor");
        brm = hardwareMap.get(DcMotor.class, "back-right-motor");
        blm = hardwareMap.get(DcMotor.class, "back-left-motor");

        flm.setDirection(DcMotorSimple.Direction.REVERSE);
        blm.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        flm.setPower(1);
        frm.setPower(1);
        blm.setPower(1);
        brm.setPower(1);
        sleep(1000);
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        sleep(1000);
        flm.setPower(1);
        frm.setPower(1);
    }
}

