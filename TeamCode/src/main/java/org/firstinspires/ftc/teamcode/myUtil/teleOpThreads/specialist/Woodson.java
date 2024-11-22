package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.specialist;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.keyRing;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch.Arm;

@Config
public class Woodson extends Specialist {
    @Override
    public void run(){
        while (!keyRing.start);
        while (true) {


            //Reads time since last runthrough
            deltaTime.reset();

            //Sets deflator with controllable speeds
            deflator = opMode.gamepad2.left_trigger > 0.5 ? 1 : opMode.gamepad2.left_bumper ? 0.3 : 0.7;

            //Arm Positions
            if (manual) {
                r.arm.setPower(opMode.gamepad2.right_stick_y * deflator);
            } else if (opMode.gamepad2.a && !arm.height.equals("low")) {
                opMode.gamepad2.rumble(10);
                arm.moveTo("low");
            } else if (opMode.gamepad2.x && !arm.height.equals("middle")) {
                arm.moveTo("middle");
                opMode.gamepad2.rumble(10);
            } else if (opMode.gamepad2.y && !arm.height.equals("high")) {
                arm.moveTo("high");
                opMode.gamepad2.rumble(10);
            }
            if (opMode.gamepad2.b) {
                unpressB = true;
            } else if (unpressB) {
                opMode.gamepad2.rumble(10);
                if (!manual) {
                    r.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    manual = true;
                    arm.run = false;
                } else {
                    manual = false;
                }
                unpressB = false;
            }

            //Claw Grip Code
            if (opMode.gamepad2.right_bumper) {
                unpressR1 = true;
            } else if (unpressR1) {
                opMode.gamepad2.rumble(10);
                gripHold = !gripHold;
                unpressR1 = false;
            }
            if (gripHold) {
                if (opMode.gamepad2.right_trigger > 0.5) {
                    unpressGrip = true;
                } else if (unpressGrip) {
                    if (pos == 1){pos=0;}
                    else {pos=1;}
                    r.claw.setPosition(pos);
                    unpressGrip = false;
                }
            } else {
                r.claw.setPosition(1-opMode.gamepad2.right_trigger);
            }

            //Controls the slay
//            opMode.telemetry.addData("Delta Time", deltaTime.seconds());
            posx -= opMode.gamepad2.dpad_up ? deltaTime.seconds()*20 : opMode.gamepad2.dpad_down ? -deltaTime.seconds()*20 : 0;
            if (posx > 1){
                posx =1;
            }
            if (posx < 0){
                posx = 0;
            }
            r.slay.setPosition(posx);

            pos2 += deltaTime.seconds()*-opMode.gamepad2.left_stick_y*deflator*20;
            if (pos2 > 1){
                pos2 =1;
            }
            if (pos2 < 0){
                pos2 = 0;
            }
            r.lever.setPosition(pos2);


            //r.cam.setPower(opMode.gamepad2.dpad_down ? -deflator : opMode.gamepad2.dpad_up ? deflator :0);


        }
    }
    double posx = 0;
    double pos2=1;
    int pos = 1;
    ElapsedTime deltaTime = new ElapsedTime();
    OpMode opMode;
    Hardware r;
    double deflator;

    Arm arm;
    boolean gripHold = false;

    boolean unpressB=false;
    boolean unpressR1=false;
    boolean unpressGrip=false;
    boolean manual=true;
    boolean unpressSlay =false;
    int speed=1;
    boolean started;
    public Woodson(OpMode opMode, Hardware r,boolean started){
        this.opMode = opMode;
        this.r=r;
        arm = new Arm(this.opMode,this.r);
        this.started = started;
    }

}
