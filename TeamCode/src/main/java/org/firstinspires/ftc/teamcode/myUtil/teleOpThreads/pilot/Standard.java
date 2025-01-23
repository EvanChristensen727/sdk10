package org.firstinspires.ftc.teamcode.myUtil.teleOpThreads.pilot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.myUtil.Hardware;

public class Standard extends Pilot{
    //    double power1,power2,power3,power4;

    //Initial variables
    double deflator;
    boolean unpressL = true;

    boolean unpressR = true;
    boolean unpressB = false;
    boolean unpressY = false;

    boolean angled = true;

    double twistMulti = 2;
    OpMode opMode;
    Hardware r;


    public Standard(OpMode opMode, Hardware r,boolean started) {
        this.opMode = opMode;
        this.r = r;
        angles = r.imu.getRobotYawPitchRollAngles();
        this.started = started;
    }
    public boolean started;


    public void run() {

        while (true) {

            angles = r.imu.getRobotYawPitchRollAngles();
            double power1 = 0;
            double power2 = 0;
            double power3 = 0;
            double power4 = 0;

            //Allows the user to shift the "forward" of the robot left or right
            if (opMode.gamepad1.dpad_left) {
                unpressL = true;
            } else if (unpressL) {
                twist += -Math.toRadians(twistMulti);
                unpressL = false;
                opMode.gamepad1.rumble(10);
            }
            if (opMode.gamepad1.dpad_right) {
                unpressR = true;
            } else if (unpressR) {
                twist += Math.toRadians(twistMulti);
                unpressR = false;
                opMode.gamepad1.rumble(10);
            }


            //!lineUp.isAlive()) {

            //Determines how much the robot has been turned since the beginning of the opMode
            double angle2;

            try {
                angle2 = angles.getYaw(AngleUnit.RADIANS);
            } catch (Exception e) {
                angle2 = 0;
            }

            angle2 %= 2 * Math.PI;


        /*
            fl______fr
            |    +   |
            bl______br
         */


            deflator = opMode.gamepad1.left_bumper && opMode.gamepad1.right_bumper ? 1.0 : opMode.gamepad1.right_bumper ? 0.3 : 0.7;
            //this first section creates the variables that will be used later
            //This is the mecanum drive code, it is weird
            //first we must translate the rectangular values of the joystick into polar coordinates;

            double y = -opMode.gamepad1.left_stick_y;
            double x = opMode.gamepad1.left_stick_x;
            if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) < 0.2) {
                x = 0;
                y = 0;
            }
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

            double velocity = Math.sqrt(Math.pow(opMode.gamepad1.left_stick_y, 2) + Math.pow(opMode.gamepad1.left_stick_x, 2));
            double rotation = opMode.gamepad1.right_stick_x;

            //equations taking the polar coordinates and turing them into motor powers
            double v1 = 0;
            double v2 = 0;
            if (angled) {
                v1 = velocity * Math.cos(angle + (Math.PI / 4) - angle2 - twist);
                v2 = velocity * Math.sin(angle + (Math.PI / 4) - angle2 - twist);
            } else {
                v1 = velocity * Math.cos(angle + (Math.PI / 4));
                v2 = velocity * Math.sin(angle + (Math.PI / 4));
            }


            power1 = v1 - rotation;
            power2 = v2 - rotation;
            power3 = v2 + rotation;
            power4 = v1 + rotation;

            r.flm.setPower(power3 * deflator);
            r.frm.setPower(power4 * deflator);
            r.blm.setPower(power1 * deflator);
            r.brm.setPower(power2 * deflator);


            //Allows the user to disable the turning of the robot relative to the driver
            if (opMode.gamepad1.b) {
                unpressB = true;
            } else if (unpressB) {
                opMode.gamepad1.rumble(10);
                angled = !angled;
                unpressB = false;
            }

//        Resets the robot "forward" position
            if (opMode.gamepad1.y) {
                unpressY = true;
            } else if (unpressY) {
                opMode.gamepad1.rumble(10);
                r.imu.resetYaw();
                unpressY = false;
            }


        }

    }
}
