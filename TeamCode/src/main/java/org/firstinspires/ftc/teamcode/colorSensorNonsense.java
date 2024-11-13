package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@TeleOp()
public class colorSensorNonsense extends OpMode{
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    private double sensitivity = 2;
    int length = 10;
    @Override
    public void init(){MecanumDriveObj.init(hardwareMap);}

    @Override
    public void loop() {
        //telemetry.addData("Amount red", MecanumDriveObj.getAmountRed());
        //telemetry.addData("Distance(CM)", MecanumDriveObj.getDistance(DistanceUnit.CM));
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        MecanumDriveObj.drive(forward,right,rotate,sensitivity);
        
    }

    //telemetry.addData("Our Heading",MecanumDriveObj.getHeading(AngleUnit.DEGREES));
}
//}



