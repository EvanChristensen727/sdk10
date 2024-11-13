package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class HelloWorld extends OpMode {
    @Override
    public void init(){
        telemetry.addData("This is initialization.","Value");
    }
    @Override
    public void loop(){
        telemetry.addData("This is loop.","Value");
    }
}
