package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;

public class Arm extends Thread{
    OpMode opMode;
    Hardware r;
    int pos;
    int low = 0;
    int middle = 250;
    int high = 580;
    public String height="";
    public boolean run;
    public Arm(OpMode opMode, Hardware r){
        this.opMode = opMode;
        this.r = r;
    }

    public void moveTo( String height){
        run = true;
        switch (height){
            case "low":
                pos = low;
                break;
            case "middle":
                pos = middle;
                break;
            case "high":
                pos = high;
                break;
        }
        this.height=height;
        this.start();
    }

    public void run(){
        r.arm.setTargetPosition(pos);
        r.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (!r.getTolerance(r.arm.getCurrentPosition(),r.arm.getTargetPosition(),10) && run){
            r.arm.setPower(0.5);
        }
        r.arm.setPower(0);
        height ="";
    }
}
