package org.firstinspires.ftc.teamcode.myUtil;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware {
    OpMode opMode;
    public DcMotor frm, flm, blm, brm,arm,cam;
    public String[] splashes = new String[]{
            "Evan is having a mental breakdown",
            "Give Evan $20",
            "Evan isn't angry, they're just non verbal",
            "Evan isn't non verbal, they're just angry",
            "Give Evan headpats",
            "Evan has just mauled someone for making eye contact"
    };

    public Servo claw;
    public IMU imu;


    public IMU.Parameters parameters;


    public DcMotor[] drive;

    public void initRobot(OpMode opMode){
        this.opMode = opMode;
        initHardware();
    }
    public void initHardware(){
        try{
            frm = opMode.hardwareMap.dcMotor.get("frm");
            flm = opMode.hardwareMap.dcMotor.get("flm");
            brm = opMode.hardwareMap.dcMotor.get("brm");
            blm = opMode.hardwareMap.dcMotor.get("blm");
            drive = new DcMotor[]{frm, flm, brm, blm};
            for (DcMotor motor: drive){
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
//            frm.setDirection(DcMotorSimple.Direction.REVERSE);
//            flm.setDirection(DcMotorSimple.Direction.REVERSE);
            frm.setDirection(DcMotorSimple.Direction.REVERSE);
            flm.setDirection(DcMotorSimple.Direction.REVERSE);
            blm.setDirection(DcMotorSimple.Direction.FORWARD);

//            brm.setDirection(DcMotorSimple.Direction.REVERSE);

        }catch (Exception e) {
            opMode.telemetry.addLine("Drive motors are uninitiated, the robot will not drive forward or backwards");
        }

        try {
            parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));

            imu = opMode.hardwareMap.get(IMU.class, "imu");
            imu.initialize(parameters);
        }catch (Exception e) {
            opMode.telemetry.addLine("IMU not initialized");
        }

        try{
            arm = opMode.hardwareMap.dcMotor.get("arm");
            cam = opMode.hardwareMap.dcMotor.get("cam");
            claw = opMode.hardwareMap.servo.get("claw");
            //want me to reset it first
            //i havent uploaded
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            cam.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setDirection(DcMotorSimple.Direction.REVERSE);
        }catch(Exception e){
            opMode.telemetry.addLine("Arm uninitialized");
        }

        opMode.telemetry.update();
//        crossbow.setPosition(0);
    }



    public void setMotorPowers(double power){
        frm.setPower(power);
        flm.setPower(power);
        blm.setPower(power);
        brm.setPower(power);
    }

    /**
     *
     * @param power1 Front Right
     * @param power2 Front Left
     * @param power3 Back Left
     * @param power4 Back Right
     */
    public void setMotorPowers(double power1, double power2, double power3, double power4){
        frm.setPower(power1);
        flm.setPower(power2);
        blm.setPower(power3);
        brm.setPower(power4);
    }

    public void setMotorTicks(int ticks){
        frm.setTargetPosition(ticks);
        flm.setTargetPosition(ticks);
        blm.setTargetPosition(ticks);
        brm.setTargetPosition(ticks);
    }
    public void setDriveMode(DcMotor.RunMode mode){
        frm.setMode(mode);
        flm.setMode(mode);
        blm.setMode(mode);
        brm.setMode(mode);
    }

    public boolean getTolerance(double val1, double val2, double tolerance){

        return (val1 - tolerance < val2) && (val1 + tolerance >val2);
    }

    public enum directions{
        LEFT,
        RIGHT,
        FORWARD,
        NULL,
        BACKWARD
    }

}
