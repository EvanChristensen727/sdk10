package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous
public class SwitchState extends OpMode {
    enum State{
        Straight,StraightRight,TurnRight,Done
    }
    MecanumDrive MecanumDriveObj = new MecanumDrive();
    State state = State.Straight;
    double lastTime;
    @Override
    public void init() {
        MecanumDriveObj.init(hardwareMap);
    }
    @Override
    public void start(){
        state = State.Straight;
        resetRuntime();
        lastTime= getRuntime();
    }
    @Override
    public void loop(){
        telemetry.addData("State",state);
        telemetry.addData("Runtime",getRuntime());
        telemetry.addData("Time in State", getRuntime() - lastTime);
        telemetry.addData("Our Heading", MecanumDriveObj.getHeading(AngleUnit.DEGREES));
        switch (state){
            case Straight:
                MecanumDriveObj.Straight();
                if (getRuntime() >=lastTime + 2.0){
                    state = State.TurnRight;
                    lastTime=getRuntime();
                }
                break;
            case TurnRight:
                MecanumDriveObj.TurnRight();
                if (MecanumDriveObj.getHeading(AngleUnit.DEGREES)>0 && MecanumDriveObj.getHeading(AngleUnit.DEGREES) == 90){
                    state = State.StraightRight;
                    lastTime=getRuntime();
                }
            case StraightRight:
                MecanumDriveObj.Backwards();
                if (getRuntime()>= lastTime + 2.0){
                    state = State.Done;
                    lastTime = getRuntime();
                }
        }
    }
}
