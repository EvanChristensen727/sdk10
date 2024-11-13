package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.HardwareMap;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous
public class NewLinear extends OpMode {
   MecanumDrive MecanumDriveObj = new MecanumDrive();
   /* public DcMotor frm;
    public DcMotor flm;
    public DcMotor blm;
    public DcMotor brm;
    */

    @Override
    public void init() {
     MecanumDriveObj.init(hardwareMap);

    }

 @Override
 public void start() {
  resetRuntime();
 }

 public void loop() {
    while (getRuntime() <=2) {
     MecanumDriveObj.Straight();
    }
    MecanumDriveObj.Stopper();
    while (getRuntime() <=4) {
     MecanumDriveObj.StraightRight();
    }
    MecanumDriveObj.Stopper();
    while (getRuntime() <=8) {
     MecanumDriveObj.Straight();
    }
     MecanumDriveObj.Stopper();
    while (getRuntime() <=10) {
     MecanumDriveObj.TurnRight();
    }
     MecanumDriveObj.Stopper();
    }

    }
