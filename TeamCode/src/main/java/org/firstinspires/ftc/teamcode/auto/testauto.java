package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name= "autonomous")
public class testauto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        hardauto r =new hardauto();
        r.initrobot(this);
        waitForStart();

    }
}
