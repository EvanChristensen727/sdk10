package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class hardwaremap  {
    public OpMode opMode;
    public DcMotor motor,flm, frm,blm,brm;
    public DcMotor[] drive;

    public void initrobot(OpMode opMode){
        this.opMode = opMode;
        inithardware();
    }
    private void inithardware(){
        try{
            motor= opMode.hardwareMap.dcMotor.get("motor");
            flm =  opMode.hardwareMap.dcMotor.get("flm");
            frm =  opMode.hardwareMap.dcMotor.get("frm");
            blm=  opMode.hardwareMap.dcMotor.get("blm");
            brm =  opMode.hardwareMap.dcMotor.get("brm");
            drive = new DcMotor[]{flm, frm, blm, brm};
        }catch (Exception e){

        }
    }
}
