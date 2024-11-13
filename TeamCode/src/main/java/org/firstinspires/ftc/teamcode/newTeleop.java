package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.MecanumDrive;
@TeleOp
public class newTeleop extends OpMode {
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    private double sensitivity =2;
    @Override


    public void init() {
       MecanumDriveObj.init(hardwareMap);
    }

    @Override
    public void loop() {
    if (gamepad1.a){
        MecanumDriveObj.arm.setPosition(.5);
    }
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;
        MecanumDriveObj.drive(forward,right,rotate,sensitivity);
        if (gamepad2.b){MecanumDriveObj.arm.setPosition(0);}
        if (gamepad2.right_bumper){MecanumDriveObj.claw.setPosition(3);

    }
}
}

