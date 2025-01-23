package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.specialist;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch.Arm;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch.Claw;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.armsAndSuch.holdArm;

@Config
public class christopher extends Specialist {
    @Override
    public void run(){
        Claw claw = new Claw(opMode,r);
        r.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (true) {


            //Reads time since last runthrough
            deltaTime.reset();

            //Sets deflator with controllable speeds
            deflator = opMode.gamepad2.left_trigger > 0.5 ? 1 : opMode.gamepad2.left_bumper ? 0.3 : 0.7;

            //Arm Positions

//            target += ((opMode.gamepad2.right_stick_y*6*deflator));



            //Does preset positions when not in manual, and sets power when manual is true
            if (!manual){
                target-=((opMode.gamepad2.right_stick_y*deltaTime.milliseconds())*1000);
                if (target>2700){
                    target=2700;
                } if (target <0){
                    target = 0;
                }
                //high is 374
                if (opMode.gamepad2.y){
                    target=1188;
                }else if (opMode.gamepad2.a){
                    target=0;
                }
                r.arm.setTargetPosition((int)target);
                r.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                r.arm.setPower(0.8);

            }else{
                r.arm.setPower(-opMode.gamepad2.right_stick_y*deflator<0 &&r.arm.getCurrentPosition()<0? 0: -opMode.gamepad2.right_stick_y*deflator>0 &&r.arm.getCurrentPosition()>2700 ? 0 : -opMode.gamepad2.right_stick_y*deflator);


                r.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                r.arm.setPower(-opMode.gamepad2.right_stick_y*deflator);
            }
//            opMode.telemetry.addData("Power",r.arm.getPower());
//            opMode.telemetry.addData("Target Power",-opMode.gamepad2.right_stick_y*deflator);
//            opMode.telemetry.addData("Manual",manual);
//            opMode.telemetry.update();

//            r.arm.setPower(opMode.gamepad2.right_stick_y);

            //switches the code from arm holding to arm floating
            if (opMode.gamepad2.b) {
                unpressB = true;
            } else if (unpressB) {
//                opMode.gamepad2.rumble(10);
                if (!manual) {
                    manual = true;
                    target = 0;



                } else {
                    manual = false;
                    r.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    arm.run = false;


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
                if (opMode.gamepad2.right_trigger > 0.2) {
                    unpressGrip = true;
                } else if (unpressGrip) {
                    if (pos == 1){pos=0;}
                    else {pos=1;}
                    claw.setPosition(pos);
                    unpressGrip = false;
                }
            } else {
                claw.setPosition(opMode.gamepad2.right_trigger);
            }

            //Controls the slay
//            opMode.telemetry.addData("Pos", target);
//            opMode.telemetry.addData("Milliseconds",deltaTime.milliseconds());
            //This is for the wrist (slay) and the lever arm, the calculation is essentially multiplying the stick by
            //the delta time between frames, which locks in the max rate of change regardless of fps
            posx -= opMode.gamepad2.dpad_left ? (deltaTime.milliseconds())/1000 : opMode.gamepad2.dpad_right ? -(deltaTime.milliseconds())/1000 : 0;
            if (posx > 1){
                posx =1;
            }
            if (posx < 0){
                posx = 0;
            }
            r.slay.setPosition(posx);

            pos2 += ((deltaTime.milliseconds()*-opMode.gamepad2.left_stick_y*deflator)/1000);
            if (pos2 > 1){
                pos2 =1;
            }
            if (pos2 < 0){
                pos2 = 0;
            }
            r.lever.setPosition(pos2);


            //This is up and down presets for the lever/wrist servos
            if (opMode.gamepad2.dpad_up){
                unpressUpPreset=true;
            }else if (unpressUpPreset){
                posx=0.3;
                pos2=1;
                unpressUpPreset=false;
            }
            if (opMode.gamepad2.dpad_down){
                unpressDownPreset=true;
            }else if (unpressDownPreset){
                posx=0.3;
                pos2=0;
                unpressDownPreset=false;
            }


            //r.cam.setPower(opMode.gamepad2.dpad_down ? -deflator : opMode.gamepad2.dpad_up ? deflator :0);


        }
    }
    double posx = 1;
    double pos2=1;
    double target;
    double pos = 1;
    int armTarget = 0;
    ElapsedTime deltaTime = new ElapsedTime();
    OpMode opMode;
    Hardware r;
    double deflator;

    Arm arm;
    boolean unpressDownPreset = false;
    boolean unpressUpPreset=false;
    boolean gripHold = false;

    boolean unpressB=false;
    boolean unpressR1=false;
    boolean unpressGrip=false;
    boolean manual=true;
    boolean unpressSlay =false;
    int speed=1;
    boolean started;
    holdArm holdArm;
    public christopher(OpMode opMode, Hardware r,boolean started){
        this.opMode = opMode;
        this.r=r;
        arm = new Arm(this.opMode,this.r);
        holdArm = new holdArm(this.r, this.opMode);
        this.started = started;
    }

}
