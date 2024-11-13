package org.firstinspires.ftc.teamcode.auto;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class PracticeAutonOpmode extends OpMode {
    protected DcMotor frm;
    protected DcMotor flm;
    protected DcMotor blm;
    protected DcMotor brm;
    public void Straight() {
        flm.setPower(-0.5);
        frm.setPower(0.5); //back left
        blm.setPower(0.5);
        brm.setPower(-0.5); //back right
    }
    public void StraightRight() {
        flm.setPower(0);
        frm.setPower(0.5); //back left
        blm.setPower(0.5);
        brm.setPower(0); //back right//
    }
    public void TurnRight(){
        flm.setPower(0.5);
        frm.setPower(0.5);
        blm.setPower(-0.5);
        brm.setPower(0.5);
    }
    @Override
    public void init(){

        flm = hardwareMap.get(DcMotor.class, "front-left-motor");
        frm = hardwareMap.get(DcMotor.class, "front-right-motor");
        brm = hardwareMap.get(DcMotor.class, "back-right-motor");
        blm = hardwareMap.get(DcMotor.class, "back-left-motor");
        flm.setDirection(DcMotorSimple.Direction.REVERSE);
        blm.setDirection(DcMotorSimple.Direction.REVERSE);
        frm.setDirection(DcMotorSimple.Direction.FORWARD);
        brm.setDirection(DcMotorSimple.Direction.FORWARD);

        flm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//back left
        blm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         brm.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //back right



    }
    @Override
    public void loop(){
        telemetry.addData("4WheelDrive","Forward");
        while (getRuntime() <=1) {
        Straight();
        }

        while(getRuntime()<=3) {
            StraightRight();
        }
        while(getRuntime()<=4){
            TurnRight();
        }
        //telemetry.addData("Stop","Stop");
            //flm.setPower(0); a("2WheelDrive","Forward");

        //flm.setPower(1);
            //frm.setPower(1);
        //while (getRuntime()<=10) {

        //}
        stop();
    }

    }
