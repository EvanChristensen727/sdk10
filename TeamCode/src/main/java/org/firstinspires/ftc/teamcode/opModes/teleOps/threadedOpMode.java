package org.firstinspires.ftc.teamcode.opModes.teleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.keyRing;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.pilot.Pilot;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.pilot.Standard;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.specialist.Specialist;
import org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.specialist.Woodson;
@TeleOp(name="WOODSON/BEYER OPMODE")
public class threadedOpMode extends OpMode {
    Specialist operator;
    Pilot pilot;
    Hardware r = new Hardware();
    String splash="";
    ElapsedTime times = new ElapsedTime();
    volatile boolean started=false;
    @Override
    public void init() {
        r.initRobot(this);
        //Because all parts of the telop threads are children classes I can individually switch the
        //variables to be the classes for each of the drivers to have personalized controls
        keyRing.start=false;
        operator = new Woodson(this, r,started);
        pilot = new Standard(this,r,started);
        splash=r.splashes[(int)(r.splashes.length*Math.random())];
        operator.start();
        pilot.start();
        times.reset();
    }

    @Override
    public void loop() {
        keyRing.start = true;
        if (times.seconds()>15){
            splash=r.splashes[(int)(r.splashes.length*Math.random())];
            times.reset();
        }
        telemetry.addLine(splash);
        telemetry.addData("Twist", Math.toDegrees(pilot.twist));
        telemetry.addData("Angle", pilot.angles.getYaw(AngleUnit.DEGREES));
        telemetry.addData("Arm Pos",r.arm.getCurrentPosition());
//        telemetry.addData("Cam Position (0-"+ -Woodson.LETSFREAKINGGOIMGOINGTOMAKETHISVARIABLENAMESOINCREDIBLYLONG+")",r.cam.getCurrentPosition());
        telemetry.addLine("To strafe robot, use left joystick");
        telemetry.addLine("To rotate robot, use right joystick");
        telemetry.addLine("Adjust Left/Right Twist: Left/Right");
        telemetry.addLine("Hold both bumpers to full power robot movement (\"FUll POWER TO THRUSTERS\")");
        telemetry.addLine("Hold down the left bumper only to half robot speed");
        telemetry.addLine("To end gyroscopic motion, press ◯");
        telemetry.addLine("To reset gyroscopic heading, press △");
        telemetry.update();
    }
}
