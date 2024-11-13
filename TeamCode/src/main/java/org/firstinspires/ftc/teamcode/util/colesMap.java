package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class colesMap {
    public DcMotor frm;
    public DcMotor flm;
    public DcMotor brm;
    public DcMotor blm;


    public void init(HardwareMap hardwareMap) {
        frm = hardwareMap.get(DcMotor.class, "frm");
    }

}
