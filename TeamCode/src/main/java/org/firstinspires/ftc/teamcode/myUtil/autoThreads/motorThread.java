package org.firstinspires.ftc.teamcode.myUtil.autoThreads;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;

public class motorThread extends Thread{
    public void run(){
        FtcDashboard dash = FtcDashboard.getInstance();
        TelemetryPacket telemetry;
        motor.setTargetPosition(end);
        //motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        double direction;
        while (!r.getTolerance(Math.abs(motor.getCurrentPosition()),Math.abs(end),tol) && !opMode.isStopRequested()){
            direction = (motor.getTargetPosition()-motor.getCurrentPosition());
            motor.setPower(power*direction);
            telemetry=new TelemetryPacket();
            telemetry.put("Current Pos", motor.getCurrentPosition());
            telemetry.put("Target Pos",motor.getTargetPosition());
            dash.sendTelemetryPacket(telemetry);
        }
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    LinearOpMode opMode;
    Hardware r;
    int end;
    DcMotor motor;
    double power;
    int tol;
    public void begin(DcMotor motor, int end, int tol, double power){
        this.motor = motor;
        this.end = end;
        this.tol = tol;
        this.power = power;
        this.start();
    }
    public motorThread(LinearOpMode opMode, Hardware r){
        this.opMode=opMode;
        this.r=r;
    }
}
