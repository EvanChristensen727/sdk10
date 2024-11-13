package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@TeleOp
public class TestTeleop extends OpMode {
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    private double sensitivity = 2;
    double deflator = 0.5;
    @Override
    public void init(){
        MecanumDriveObj.init(hardwareMap);
    }
    @Override
    public void loop(){
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        //if (sensitivity > 2) sensitivity -= gamepad1.left_trigger/10;
        //sensitivity += gamepad1.right_trigger/10;
        telemetry.addData("Sensitivity",sensitivity);
        MecanumDriveObj.drive(forward,right,rotate,sensitivity);
        telemetry.addData("Our Heading",MecanumDriveObj.getHeading(AngleUnit.DEGREES));
        telemetry.addData("our Heading", MecanumDriveObj.getHeading(AngleUnit.RADIANS));

        }


    }
//}
