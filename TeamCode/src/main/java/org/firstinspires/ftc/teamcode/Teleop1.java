package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@TeleOp
public class Teleop1 extends OpMode {
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    @Override
    public void  init(){
        MecanumDriveObj.init(hardwareMap);
    }
    double deflator = 0.5;
    @Override
    public void loop(){
        deflator = gamepad1.left_bumper && gamepad1.right_bumper ? 0.9 : gamepad1.left_bumper ? 0.4 : 0.7;
        double angle2;

        try {
            angle2 = MecanumDriveObj.imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
        } catch (Exception e) {
            angle2 = 0;
        }

        angle2 %= 2 * Math.PI;

            /*Message to future Evan: Don't mess with this
            Everything down here you will not understand
            Let Ben mess with this and get to learning how to use java
            stop scrolling down here
            I said stop!
            No Evan...
            Don't
            This is painful down here
            Stop
            Do you like black holes?
            You will somehow create one if you
            mess with this
            code that runs our mecanum drive wheels
            */
        //this first section creates the variables that will be used later

        //first we must translate the rectangular values of the joystick into polar coordinates;
        double y = -gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x;
        double angle = 0;

        if (y > 0 && x > 0)//quadrant 1
            angle = Math.atan(y / x);
        else {
            double angle1 = Math.toRadians(180) + Math.atan(y / x);
            if (y > 0 && x < 0)//quadrant 2
                angle = angle1;
            else if (y < 0 && x < 0)//quadrant 3
                angle = angle1;
            else if (y < 0 && x > 0)//quadrant 4
                angle = Math.toRadians(360) + Math.atan(y / x);
        }

        if (y == 0 && x > 1) {
            angle = 0;
        }
        if (y > 0 && x == 0) {
            angle = Math.PI / 2;
        }
        if (y == 0 && x < 0) {
            angle = Math.PI;
        }
        if (y < 0 && x == 0) {
            angle = 3 * Math.PI / 2;
        }

        double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        double rotation = gamepad1.right_stick_x;

        //equations taking the polar coordinates and turing them into motor powers
        double v1 = -velocity * Math.cos(angle + (Math.PI / 4)+angle2);
        double v2 = velocity * Math.sin(angle + (Math.PI / 4)+angle2);
        double power1 = v1 + rotation;
        double power2 = v2 - rotation;
        double power3 = v2 + rotation;
        double power4 = v1 - rotation;

        MecanumDriveObj.flm.setPower(power1 * deflator);
        MecanumDriveObj.frm.setPower(power2 * deflator);
        MecanumDriveObj.blm.setPower(power3 * deflator);
        MecanumDriveObj.brm.setPower(power4 * deflator);
    }

}

