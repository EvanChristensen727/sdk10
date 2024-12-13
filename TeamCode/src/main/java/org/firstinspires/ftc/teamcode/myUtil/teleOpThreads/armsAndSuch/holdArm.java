package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;

public class holdArm extends Thread{

    public void run(){
        r.arm.setTargetPosition(target);
        r.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.arm.setPower(0.8);
        while (true){

        }
    }
    Hardware r;
    public volatile int target = 0;
    OpMode opMode;
    public holdArm(Hardware r, OpMode opMode){
        this.r = r;
        this.opMode = opMode;
    }
}
