package org.firstinspires.ftc.teamcode.opModes.teleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
//Do you wnat the linear slide slower
//yeah sure but i am currently non verbal
@TeleOp(name="Vibrator")
public class vibrator extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (gamepad1.a){
            gamepad1.rumble(50);
        }
    }
}
