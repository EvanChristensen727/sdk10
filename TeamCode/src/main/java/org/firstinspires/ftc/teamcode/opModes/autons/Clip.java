package org.firstinspires.ftc.teamcode.opModes.autons;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;
import org.firstinspires.ftc.teamcode.myUtil.autoThreads.motorThread;
@Config
@Autonomous(name="Clip")
public class Clip extends LinearOpMode {
    public static int camEnd = -1125;
    //You are one in a krillion

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        r.homeArmAndCam();
        motorThread a = new motorThread(this,r);
        r.claw.setPosition(0);
        motorThread m = new motorThread(this, r);
        waitForStart();

        //Raise Cam and move Forward
        m.begin(r.cam,-1259, 50,0.5);
        r.waiter(1000);
        r.fixedMove(0.5,16, Hardware.directions.FORWARD);
        while (m.isAlive());

        //Extend arm and lower cam
        m.begin(r.arm, 450, 10,0.5);
        while (m.isAlive());
        r.waiter(500);
        m.begin(r.cam, camEnd,20,0.5);
        while(m.isAlive());
        r.waiter(500);

        //Hook arm back, release, and shake off bar
        m.begin(r.arm,250, 10,0.5);
        r.waiter(1000);
        r.claw.setPosition(1);
        r.waiter(500);
        a.begin(r.cam, -1259, 50, 0.5);
        r.waiter(500);

        //Lower the arm and cam
        m.begin(r.arm,0,10,0.5);
        while(m.isAlive());
        m.begin(r.cam,-10,50,0.5);

        //Move to the park zone
        r.fixedMove(0.5,47, Hardware.directions.RIGHT);
        r.fixedMove(0.5,12.5, Hardware.directions.BACKWARD);
        while(m.isAlive());
    }
}
