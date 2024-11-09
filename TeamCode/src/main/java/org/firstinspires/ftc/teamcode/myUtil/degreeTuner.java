package org.firstinspires.ftc.teamcode.myUtil;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@Config
@Autonomous(name="Waltz")
public class degreeTuner extends LinearOpMode {
    public static double power = 0.3;
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumHardAuto r = new MecanumHardAuto();
        r.initRobot(this);
        FtcDashboard dash = FtcDashboard.getInstance();
        TelemetryPacket telemetryPacket = new TelemetryPacket();
        waitForStart();
        r.setDriveMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setDriveMode(DcMotor.RunMode.RUN_USING_ENCODER);
        YawPitchRollAngles angles = r.imu.getRobotYawPitchRollAngles();
        while (angles.getYaw(AngleUnit.DEGREES)<90 && !isStopRequested()){
            r.flm.setPower(-power);
            r.brm.setPower(power);
            r.blm.setPower(-power);
            r.frm.setPower(power);
            angles = r.imu.getRobotYawPitchRollAngles();
            telemetryPacket.clearLines();
            telemetryPacket.put("Degrees",angles.getYaw(AngleUnit.DEGREES));
            dash.sendTelemetryPacket(telemetryPacket);
        }
        r.flm.setPower(0);
        r.frm.setPower(0);
        r.blm.setPower(0);
        r.brm.setPower(0);
        angles = r.imu.getRobotYawPitchRollAngles();
        telemetryPacket.put("Angle",angles.getYaw());
        telemetryPacket.put("Ticks",r.flm.getCurrentPosition());
        telemetryPacket.put("Ticks Per Degree", ((double)(r.flm.getCurrentPosition()))/angles.getYaw());
        dash.sendTelemetryPacket(telemetryPacket);
        while(!isStopRequested());

    }
}
