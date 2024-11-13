package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

    public class AutonomousHardwareMap extends HardwareMap {

        public final double PIXEL_POWER = 0.64;
        public final int PIXEL_LONG_MOVE_TIME = 1050;
        public final int PIXEL_SHORT_MOVE_TIME = 500;
        private final double VOLTAGE_THRESHOLD_HIGH = 13.65;
        // private final double VOLTAGE_THRESHOLD_LOW = 12.85;

        public double voltageMultiplier() {
            double v = VoltageReader.getVoltage();
            return v > VOLTAGE_THRESHOLD_HIGH
                    ? VOLTAGE_THRESHOLD_HIGH / (v * 1.2)
                    : 1.0;
        }

        public void setDrivePower(double power) {
            for (int i = 0; i < drive.length; i++) {
                drive[i].setPower(power);
            }
        }

        public void moveDriveToPosition(double power, int ticks) {
            while (Math.abs(frontLeft.getCurrentPosition()) < ticks) {
                setDrivePower(power);
                print("Position: " + frontLeft.getCurrentPosition());
            }
            setDrivePower(0.0);
        }

        public void strafe(String direction, double power) {
            if (direction == "left") {
                frontLeft.setPower(-power);
                backLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(-power);
            } else if (direction == "right") {
                frontLeft.setPower(power);
                backLeft.setPower(-power);
                frontRight.setPower(-power);
                backRight.setPower(power);
            }
        }

        public void strafe(Direction direction, double power) {
            resetEncoders();
            switch (direction) {
                case LEFT:
                    frontLeft.setPower(power);
                    backLeft.setPower(-power);
                    frontRight.setPower(-power);
                    backRight.setPower(power);
                    break;
                case RIGHT:
                    frontLeft.setPower(-power);
                    backLeft.setPower(power);
                    frontRight.setPower(power);
                    backRight.setPower(-power);
                    break;
            }
        }

        public void strafe(Direction direction, double power, int ticks) {
            resetEncoders();
            switch (direction) {
                case LEFT:
                    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    moveDriveToPosition(power, ticks);
                    frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    break;
                case RIGHT:
                    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                    frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    moveDriveToPosition(power, ticks);
                    backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    break;
            }
        }

        public void turn(String direction, double power) {
            if (direction == "left") {
                frontLeft.setPower(-power);
                backLeft.setPower(-power);
                frontRight.setPower(power);
                backRight.setPower(power);
            } else if (direction == "right") {
                frontLeft.setPower(power);
                backLeft.setPower(power);
                frontRight.setPower(-power);
                backRight.setPower(-power);
            }
        }

        public void turn(Direction direction, double power, int ticks) {
            resetEncoders();
            switch (direction) {
                case LEFT:
                    // TODO fit this for left turn, currently right turning
                    frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    moveDriveToPosition(power, ticks);
                    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                    frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    backRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    break;
                case RIGHT:
                    frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    moveDriveToPosition(power, ticks);
                    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    break;
            }
        }

    }