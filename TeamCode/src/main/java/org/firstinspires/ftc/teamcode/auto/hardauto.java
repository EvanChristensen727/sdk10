package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardwaremap;


public class hardauto extends hardwaremap {
    public final double ticksperinch=51.2;
    public void initrobot(LinearOpMode opMode){
        super.initrobot(opMode);
    }
    public void moveticks(int inches, double power) {
        for(DcMotor motor: drive) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition((int)(inches*ticksperinch));
            motor.setPower(power*(inches/Math.abs(inches)));
        }while (Math.abs(flm.getCurrentPosition())< Math.abs(frm.getTargetPosition()));
        for(DcMotor motor: drive){motor.setPower(0);}


    }
}
