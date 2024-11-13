package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;



    public class HardwareMap {

  /*
   * ENSURE YOUR HARDWARE IS ALWAYS UP-TO-DATE! *

    NOTE FOR ENCODERS
    The ticks per revolution will always be 28 for HEX HD motors.
    However, if you decide to change the wheels (or the motors,) then you
    must update this data in DriveConstants.java!

    To calculate the ticks per inch, divide the circumference of your
    wheel by the number of ticks per revolution on your motor (the latter
    value is usually listed online where you can purchase one.)
    Currently, this is 12/28 inches per tick.
  */

        /////////////////////
        // CLASS VARIABLES //
        /////////////////////

        OpMode opMode;
        public DcMotorEx frontLeft, frontRight, backLeft, backRight, armBase;
        public DcMotor armTopLeft, armTopRight, droneLauncher;
        public Servo clawServo;
        public CRServo droneFeeder;
        public VoltageSensor VoltageReader;
        public DcMotorEx[] drive;

        // Magnitudinally, the maximum power that can be delivered to the motors.
        public final double MAX_POWER = 0.8;
        public final double TOP_ARM_POWER = 0.8;
        public final double BASE_ARM_POWER = 0.6;

        public enum Direction {
            LEFT,
            RIGHT
        }

        ////////////////////
        // INITIALIZATION //
        ////////////////////

        public void runOpmode(OpMode opMode) {
            this.opMode = opMode;
            this.initialize();
        };

        private void initialize() {
            try {

                // try getting all the motors
                // make sure the motors are correctly named under the driver station.

                /////////////////
                // CONTROL HUB //
                /////////////////
                frontRight = opMode.hardwareMap.get(DcMotorEx.class, "front-right-motor"); // Port 0, motor faulty @ lower power >:C
                backRight = opMode.hardwareMap.get(DcMotorEx.class, "back-right-motor"); // Port 1
                backLeft = opMode.hardwareMap.get(DcMotorEx.class, "back-left-motor");  // Port 2
                frontLeft = opMode.hardwareMap.get(DcMotorEx.class, "front-left-motor"); // Port 3

                ///////////////////
                // EXPANSION HUB //
                ///////////////////
                armBase = opMode.hardwareMap.get(DcMotorEx.class, "arm-base"); // Port 0 of expansion hub
                armTopLeft = opMode.hardwareMap.get(DcMotor.class, "arm-top-left"); // TODO organize these by index for neatness :>
                armTopRight = opMode.hardwareMap.get(DcMotor.class, "arm-top-right");
                droneLauncher = opMode.hardwareMap.get(DcMotor.class, "drone-launcher");

                // Servos under control hub
                clawServo = opMode.hardwareMap.get(Servo.class, "drone-servo"); // Port 0 TODO rename this in the driver hub.
                droneFeeder = opMode.hardwareMap.get(CRServo.class, "drone-feeder"); // Port 1

                // Really neat built in voltage monitoring unit
                VoltageReader = opMode.hardwareMap.get(VoltageSensor.class, "Control Hub");

                ////////////////////////////////
                // MOTOR/SERVO CONFIGURATIONS //
                ////////////////////////////////
                drive = new DcMotorEx[] {frontLeft, frontRight, backLeft, backRight};

                for (int i = 0; i < drive.length; i++) {
                    drive[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }

                frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                backRight.setDirection(DcMotorSimple.Direction.FORWARD);

                armTopLeft.setDirection(DcMotorSimple.Direction.REVERSE);

                armBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                armTopLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                print("Initialization success!");
            } catch (Exception e) {
                print("Initialization failed; DO NOT RUN!\nException: " + e);
            }
        }

        ///////////////////////////
        // MISCELLANEOUS METHODS //
        ///////////////////////////

        // below method waits in milliseconds.
        public void wait(int msTime) {
            ElapsedTime et = new ElapsedTime();
            et.reset();
            // do nothing lol
            while (et.milliseconds() < msTime);
        }

        // note that this will overwrite the existing message put into the output
        public void print(String message) {
            opMode.telemetry.addLine(
                    "[" + opMode.getClass().getSimpleName() + "]: " + message
            );
            opMode.telemetry.update();
        }

        public void resetEncoder(DcMotorEx motor) {
            motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }

        public void resetEncoder(DcMotor motor) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public void resetEncoders() {
            // use normal for-loop since advanced would require a type and thus need a separate method for different types of motors
            for (int i = 0; i < drive.length; i++) {
                resetEncoder(drive[i]);
            }
        }

        // below method applies a quadratic curve to the motor movements and limits power so that they don't obliterate the voltage
        public double limitPower(double power) {
            return (power >= 0)
                    ? Math.min(Math.pow(power, 2.0), MAX_POWER)
                    : Math.max(-1.0 * Math.pow(power, 2.0), -MAX_POWER);
        }

    }

