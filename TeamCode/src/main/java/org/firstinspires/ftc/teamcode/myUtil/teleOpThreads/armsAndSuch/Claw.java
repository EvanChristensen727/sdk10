package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;

public class Claw extends Hardware {
    OpMode opMode;
    Hardware r;
    double claw1Min=0.5;
    double claw1Max=0;
    double claw2Max=0.5;
    double claw2Min=0;
    public Claw(OpMode opMode, Hardware r){
        this.opMode = opMode;
        this.r = r;
    }
    public void setPosition(double pos){
        r.claw.setPosition(claw1Min-(pos*(claw1Min-claw1Max)));
        r.claw2.setPosition(claw2Min+(pos*(Math.abs(claw2Min-claw2Max))));
    }

}
