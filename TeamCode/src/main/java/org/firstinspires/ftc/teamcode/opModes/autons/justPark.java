package org.firstinspires.ftc.teamcode.opModes.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.myUtil.Hardware;
import org.firstinspires.ftc.teamcode.myUtil.MecanumHardAuto;
@Autonomous(name="Just Park")
public class justPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        waitForStart();
        r.fixedMove(0.5,12, Hardware.directions.FORWARD);
        r.fixedMove(0.5, 36, Hardware.directions.RIGHT);
        r.fixedMove(0.5,9, Hardware.directions.BACKWARD);

    }
}
