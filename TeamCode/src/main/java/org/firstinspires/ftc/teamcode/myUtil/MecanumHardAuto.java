package org.firstinspires.ftc.teamcode.myUtil;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class MecanumHardAuto extends Hardware {
    final int TICKS_PER_INCH = 38;
    double mech = (49.0/24.0);
    final double TPD = 13.1199818605599;
    final double mecanumMulti = 1/0.9;
    int pause = 100;
    LinearOpMode opMode;


    public void initRobot(LinearOpMode opMode) {
        super.initRobot(opMode);
        this.opMode = opMode;
        flm.setDirection(DcMotorSimple.Direction.REVERSE);
        brm.setDirection(DcMotorSimple.Direction.FORWARD);
        frm.setDirection(DcMotorSimple.Direction.FORWARD);
        blm.setDirection(DcMotorSimple.Direction.REVERSE);
    }

        /*
    public void moveInches(double power, double inches) {

        for (DcMotor i : drive) {
            i.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            i.setTargetPosition((int) inches * TICKS_PER_INCH);
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        frm.setPower(power);
        flm.setPower(power);
        blm.setPower(power);
        brm.setPower(power);
        while (!getTolerance(frm.getCurrentPosition(), frm.getTargetPosition(), 10)) {
            frm.setPower(power);
            flm.setPower(power);
            blm.setPower(power);
            brm.setPower(power);
        }

        frm.setPower(0);

        flm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
    }

    public void moveInches(double power, double inches, directions direction) {
        inches *= mecanumMulti * TICKS_PER_INCH;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        switch (direction) {
            case LEFT:
                frm.setTargetPosition((int) -inches);
                flm.setTargetPosition((int) inches);
                blm.setTargetPosition((int) -inches);
                brm.setTargetPosition((int) inches);
                break;
            case RIGHT:
                frm.setTargetPosition((int) inches);
                flm.setTargetPosition((int) -inches);
                blm.setTargetPosition((int) inches);
                brm.setTargetPosition((int) -inches);
                break;
        }
        for (DcMotor i : drive) {
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        while (!getTolerance(frm.getCurrentPosition(), frm.getTargetPosition(), 10)) {
            frm.setPower(power);
            flm.setPower(power);
            blm.setPower(power);
            brm.setPower(power);
        }
        frm.setPower(0);
        flm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
    }

         */

    public void superRotate(double power, double degrees, directions dir){
        degrees *= TPD;

        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        switch (dir){
            case LEFT:
                frm.setTargetPosition((int) degrees);
                flm.setTargetPosition((int) -degrees);
                blm.setTargetPosition((int) -degrees);
                brm.setTargetPosition((int) degrees);
                flm.setPower(-power);
                frm.setPower(power);
                blm.setPower(-power);
                brm.setPower(power);
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),20) && !opMode.isStopRequested()) {

                    opMode.telemetry.addLine("Done");
                    opMode.telemetry.update();
                }
                opMode.telemetry.addLine("3 Done");
                opMode.telemetry.update();
                break;
            case RIGHT:
                frm.setTargetPosition((int) -degrees);
                flm.setTargetPosition((int) degrees);
                blm.setTargetPosition((int) degrees);
                brm.setTargetPosition((int) -degrees);
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),20) && !opMode.isStopRequested()) {
                    flm.setPower(power);
                    frm.setPower(-power);
                    blm.setPower(power);
                    brm.setPower(-power);
                }
                break;
        }

        opMode.telemetry.addLine("2 Done");
        opMode.telemetry.update();
        frm.setPower(0);
        flm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);
    }

    public void rotate(double power, double degrees, directions dir){
        degrees *= TPD;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        switch (dir){
            case LEFT:
                frm.setTargetPosition((int) degrees);
                flm.setTargetPosition((int) -degrees);
                blm.setTargetPosition((int) -degrees);
                brm.setTargetPosition((int) degrees);
                break;
            case RIGHT:
                frm.setTargetPosition((int) -degrees);
                flm.setTargetPosition((int) degrees);
                blm.setTargetPosition((int) degrees);
                brm.setTargetPosition((int) -degrees);
                break;
        }
        for (DcMotor i : drive) {
            i.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (flm.getCurrentPosition() >= flm.getTargetPosition()){
            while (flm.getCurrentPosition() >= flm.getTargetPosition()){
                flm.setPower(-power);
                frm.setPower(power);
                blm.setPower(-power);
                brm.setPower(power);
            }
        }else {
            while (flm.getCurrentPosition() < flm.getTargetPosition()) {
                flm.setPower(power);
                frm.setPower(-power);
                blm.setPower(power);
                brm.setPower(-power);
            }
        }
        frm.setPower(0);
        flm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);
//        waiter(5000);
    }

    public void sensorRotate(double power, double degrees){
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        imu.initialize(parameters);
        degrees = -(degrees);
        YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
//        degrees = (imu.getAngularOrientation().firstAngle % (Math.PI)) + degrees;
        if (degrees < angles.getYaw(AngleUnit.RADIANS)){
            while (degrees < -angles.getYaw(AngleUnit.RADIANS)){
                angles = imu.getRobotYawPitchRollAngles();
                flm.setPower(-power);
                frm.setPower(power);
                blm.setPower(-power);
                brm.setPower(power);
//                opMode.telemetry.addData("Target", degrees);
//                opMode.telemetry.addData("Current", imu.getAngularOrientation().firstAngle % (Math.PI));
//                opMode.telemetry.update();
            }
        }else{
            while (degrees > -angles.getYaw(AngleUnit.RADIANS)){
                flm.setPower(power);
                frm.setPower(-power);
                blm.setPower(power);
                brm.setPower(-power);
                 angles = imu.getRobotYawPitchRollAngles();
//                opMode.telemetry.addData("Target", degrees);
//                opMode.telemetry.addData("Current", imu.getAngularOrientation().firstAngle % (Math.PI));
//                opMode.telemetry.update();
            }
        }
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        opMode.telemetry.addData("Ticks",flm.getCurrentPosition());
        opMode.telemetry.update();
        waiter(pause);
    }
/*
    public void moveSensored(double power, double y, double x){
        imu.initialize(parameters);
//        Position position = imu.getPosition();
//        position.unit = DistanceUnit.INCH;
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

        if (y > 0 && x == 0) {
            angle = Math.PI / 2;
        }
        if (y == 0 && x < 0) {
            angle = Math.PI;
        }
        if (y < 0 && x == 0) {
            angle = 3 * Math.PI / 2;
        }
        //equations taking the polar coordinates and turing them into motor powers
        double v1 = power * Math.cos(angle + (Math.PI / 4));
        double v2 = power * Math.sin(angle + (Math.PI / 4));

        while (Math.abs(position.x) < Math.abs(x)){
            blm.setPower(v1);
            frm.setPower(v1);
            flm.setPower(v2);
            brm.setPower(v2);
        }
        blm.setPower(0);
        frm.setPower(0);
        flm.setPower(0);
        brm.setPower(0);



    }
*/


    public void moveAngled(double power, double degrees, double inches){
        degrees += 45;
//        degrees = 360-degrees;
        degrees %= 360;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        inches *= TICKS_PER_INCH;
        double scalar = degrees % 180;
        scalar = 1;//0.9 *((Math.abs(scalar-90)));
        inches *= scalar;
        int ticks = (int) inches;
        degrees = Math.toRadians(degrees);
        double v1 = -power * Math.cos(degrees);
        double v2 = power * Math.sin(degrees);
        double neg1 = (v1/Math.abs(v1));
        double neg2 = (v2/Math.abs(v2));
        flm.setTargetPosition((int)(ticks*neg2));
        brm.setTargetPosition((int)(ticks*neg2));
        blm.setTargetPosition((int)(ticks*neg1));
        frm.setTargetPosition((int)(ticks*neg1));
        opMode.telemetry.addData("V1", v1);
        opMode.telemetry.addData("V2", v2);
        opMode.telemetry.addData("Inches",inches);
//        opMode.telemetry.update();


//        waiter(2000);
//        flm.setPower(1);
//        waiter(5000);
        if (Math.abs(v1) < Math.abs(v2)){
            if (v2 < 0){
                while(flm.getCurrentPosition() >flm.getTargetPosition()){
                    frm.setPower(v1);
                    flm.setPower(v2);
                    blm.setPower(v1);
                    brm.setPower(v2);
                    opMode.telemetry.addData("Cur", flm.getCurrentPosition());
                    opMode.telemetry.addData("Tar",flm.getTargetPosition());
                    opMode.telemetry.update();
                }
            }else{
                while(flm.getCurrentPosition() <flm.getTargetPosition()){
                    frm.setPower(v1);
                    flm.setPower(v2);
                    blm.setPower(v1);
                    brm.setPower(v2);
                    opMode.telemetry.addData("Cur", flm.getCurrentPosition());
                    opMode.telemetry.addData("Tar",flm.getTargetPosition());
                    opMode.telemetry.update();
                }
            }
        }else{
            if (v1 < 0){
                while(frm.getCurrentPosition() >frm.getTargetPosition()){
                    frm.setPower(v1);
                    flm.setPower(v2);
                    blm.setPower(v1);
                    brm.setPower(v2);
                    opMode.telemetry.addData("Cur", flm.getCurrentPosition());
                    opMode.telemetry.addData("Tar",flm.getTargetPosition());
                    opMode.telemetry.update();
                }
            }else{
                while(frm.getCurrentPosition() <frm.getTargetPosition()){
                    frm.setPower(v1);
                    flm.setPower(v2);
                    blm.setPower(v1);
                    brm.setPower(v2);
                    opMode.telemetry.addData("Cur", flm.getCurrentPosition());
                    opMode.telemetry.addData("Tar",flm.getTargetPosition());
                    opMode.telemetry.update();
                }
            }
        }

        opMode.telemetry.addLine("Done");
        opMode.telemetry.update();
        frm.setPower(0);
        flm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);

    }
    //    /*
    public void moveInches(double power, double inches){

        inches *= TICKS_PER_INCH;

        for (DcMotor i : drive){
            i.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            i.setTargetPosition((int)Math.round(inches));
            i.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }



        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10)) {
            flm.setPower(power);
            frm.setPower(power);
            blm.setPower(power);
            brm.setPower(power);
        }
//        waiter(5000);
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);
    }
    public void moveInches(double power, double inches, directions dir){
        inches *= TICKS_PER_INCH*mecanumMulti;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        switch (dir){
            case RIGHT:
                flm.setTargetPosition((int)inches);
                frm.setTargetPosition((int)-inches);
                blm.setTargetPosition((int)-inches);
                brm.setTargetPosition((int)inches);
                break;
            case LEFT:
                flm.setTargetPosition((int)-inches);
                frm.setTargetPosition((int)inches);
                blm.setTargetPosition((int)inches);
                brm.setTargetPosition((int)-inches);
                break;
        }
        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10)) {
            flm.setPower(power);
            frm.setPower(power);
            blm.setPower(power);
            brm.setPower(power);
        }
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);
    }
    public void waiter(int time) {
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while (!opMode.isStopRequested()) {
            if (Time.milliseconds() > time) {
                break;
            }
        }
    }

    public void fixedMove(double power, double inches, directions dir){
        inches *= TICKS_PER_INCH*mecanumMulti;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        switch (dir){
            case LEFT:

                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(inches*mecanumMulti),10) && !opMode.isStopRequested()) {
                    flm.setPower(-power);
                    frm.setPower(power);
                    blm.setPower(power);
                    brm.setPower(-power);
                }
                break;

            case RIGHT:
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(inches*mecanumMulti),10) && !opMode.isStopRequested()) {
                    flm.setPower(power);
                    frm.setPower(-power);
                    blm.setPower(-power);
                    brm.setPower(power);
                    opMode.telemetry.addData("BRM",brm.getCurrentPosition());
                    opMode.telemetry.addData("BLM",blm.getCurrentPosition());
                    opMode.telemetry.addData("FRM",frm.getCurrentPosition());
                    opMode.telemetry.addData("FLM",flm.getCurrentPosition());
                    opMode.telemetry.update();
                }
                break;
            case BACKWARD:
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(inches*mecanumMulti),10) && !opMode.isStopRequested()) {
                    flm.setPower(-power);
                    frm.setPower(-power);
                    blm.setPower(-power);
                    brm.setPower(-power);
                }
                break;
            default:
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(inches*mecanumMulti),10) && !opMode.isStopRequested()) {
                    flm.setPower(power);
                    frm.setPower(power);
                    blm.setPower(power);
                    brm.setPower(power);
                }
                break;

        }
//        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);

    }
    public void simpleMove(double power, double inches, directions dir){
        inches *= TICKS_PER_INCH*mecanumMulti;
        setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        switch (dir){
            case LEFT:
                flm.setTargetPosition((int)(inches*mech));
                frm.setTargetPosition((int)(-inches*mech));
                blm.setTargetPosition((int)-(inches*mech));
                brm.setTargetPosition((int)(inches*mech));
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10) && !opMode.isStopRequested()) {
                    flm.setPower(-power);
                    frm.setPower(power);
                    blm.setPower(power);
                    brm.setPower(-power);
                }
                break;

            case RIGHT:
                flm.setTargetPosition((int)-(inches*mech));
                frm.setTargetPosition((int)(inches*mech));
                blm.setTargetPosition((int)(inches*mech));
                brm.setTargetPosition((int)-(inches*mech));
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10) && !opMode.isStopRequested()) {
                    flm.setPower(power);
                    frm.setPower(-power);
                    blm.setPower(-power);
                    brm.setPower(power);
                    opMode.telemetry.addData("BRM",brm.getCurrentPosition());
                    opMode.telemetry.addData("BLM",blm.getCurrentPosition());
                    opMode.telemetry.addData("FRM",frm.getCurrentPosition());
                    opMode.telemetry.addData("FLM",flm.getCurrentPosition());
                    opMode.telemetry.update();
                }
                break;
            case BACKWARD:
                flm.setTargetPosition((int)-inches);
                frm.setTargetPosition((int)-inches);
                blm.setTargetPosition((int)-inches);
                brm.setTargetPosition((int)-inches);
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10) && !opMode.isStopRequested()) {
                    flm.setPower(-power);
                    frm.setPower(-power);
                    blm.setPower(-power);
                    brm.setPower(-power);
                }
                break;
            default:
                flm.setTargetPosition((int)-inches);
                frm.setTargetPosition((int)-inches);
                blm.setTargetPosition((int)-inches);
                brm.setTargetPosition((int)-inches);
                while (!getTolerance(Math.abs(flm.getCurrentPosition()),  Math.abs(flm.getTargetPosition()),10) && !opMode.isStopRequested()) {
                    flm.setPower(power);
                    frm.setPower(power);
                    blm.setPower(power);
                    brm.setPower(power);
                }
                break;

        }
//        setDriveMode(DcMotor.RunMode.RUN_TO_POSITION);

        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);
        waiter(pause);

    }

    public void homeArmAndCam(){
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cam.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cam.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stableMove(double degrees, double power, double inches){

        double power1 = 0;
        double power2 = 0;
        double power3 = 0;
        double power4 = 0;degrees-=180;
        inches *= TICKS_PER_INCH*mecanumMulti;
        for (DcMotor motor: drive){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition((int) Math.round(inches));
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        imu.initialize(parameters);
        if (degrees<90){
            power=-power;
        }

        /*
            fl______fr
            |    +   |
            bl______br
         */

        //this first section creates the variables that will be used later
        //This is the mecanum drive code, it is weird
        //first we must translate the rectangular values of the joystick into polar coordinates;
        double angle2;
        while (!getTolerance(Math.abs(flm.getCurrentPosition()), Math.abs(flm.getTargetPosition()),30)) {

            YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
            try {
                angle2 = angles.getYaw(AngleUnit.RADIANS);
            } catch (Exception e) {
                angle2 = 0;
            }

            double angle = Math.toRadians(degrees);

            //equations taking the polar coordinates and turing them into motor powers
            opMode.telemetry.addData("Angle",angle);
            opMode.telemetry.addData("Pos", flm.getCurrentPosition());
            opMode.telemetry.addData("Target", flm.getTargetPosition());
            double v1 = power * Math.cos(angle + (Math.PI / 4) - angle2);
            double v2 = power * Math.sin(angle + (Math.PI / 4) - angle2);

            power1 = v1;
            power2 = v2;
            power3 = v2;
            power4 = v1;
            flm.setPower(power3);
            frm.setPower(power4);
            blm.setPower(power1);
            brm.setPower(power2);
            opMode.telemetry.update();
        }
        flm.setPower(0);
        frm.setPower(0);
        blm.setPower(0);
        brm.setPower(0);

    }
//    */





}
