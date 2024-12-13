package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
@Config
public class Claw extends Hardware {
    OpMode opMode;
    Hardware r;
    public static double claw1Min=0.6;
    public static double claw1Max=0.2;
    public static double claw2Max=0.7;
    public static double claw2Min=0;
    public Claw(OpMode opMode, Hardware r){
        this.opMode = opMode;
        this.r = r;
    }
    public void setPosition(double pos){
        r.claw.setPosition(claw1Min-(pos*(claw1Min-claw1Max)));
        r.claw2.setPosition(claw2Min+(pos*(Math.abs(claw2Min-claw2Max))));
    }

}
