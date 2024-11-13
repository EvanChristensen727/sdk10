

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

    @Autonomous
    public class testarm extends OpMode {
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
        public void loop() {
            while (getRuntime() <=2) {
                MecanumDriveObj.Straight();
            }


        }

    }


