package TestRig;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import team25core.Robot;

public class IndepMotorMode {
    public enum MotorDirection {
        STOP,
        CLOCKWISE,
        COUNTER_CLOCKWISE;

        public IndepMotorMode.MotorDirection nextDirection() {
            IndepMotorMode.MotorDirection currentDirection = values()[ordinal()];

            if(currentDirection != COUNTER_CLOCKWISE) {
                return values()[ordinal() +1];
            } else {
                return values()[0];
            }
        }
    }

    Robot myRobot;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;

    private Telemetry.Item motor1DirectionTlm;
    private Telemetry.Item motor2DirectionTlm;
    private Telemetry.Item motor3DirectionTlm;
    private Telemetry.Item motor4DirectionTlm;

    private MotorDirection motor1Direction;
    private MotorDirection motor2Direction;
    private MotorDirection motor3Direction;
    private MotorDirection motor4Direction;


    //constructor to instantiate independent motor object
    public IndepMotorMode (Robot robot) {
        myRobot = robot;

        motor1 = myRobot.hardwareMap.get(DcMotor.class, "motor1");
        motor2 = myRobot.hardwareMap.get(DcMotor.class, "motor2");
        motor3 = myRobot.hardwareMap.get(DcMotor.class, "motor3");
        motor4 = myRobot.hardwareMap.get(DcMotor.class, "motor4");

        motor1Direction = MotorDirection.STOP;
        motor2Direction = MotorDirection.STOP;
        motor3Direction = MotorDirection.STOP;
        motor4Direction = MotorDirection.STOP;

        motor1DirectionTlm = myRobot.telemetry.addData("motor1 Direction", motor1Direction);
        motor2DirectionTlm = myRobot.telemetry.addData("motor2 Direction", motor2Direction);
        motor3DirectionTlm = myRobot.telemetry.addData("motor3 Direction", motor3Direction);
        motor4DirectionTlm = myRobot.telemetry.addData("motor4 Direction", motor4Direction);

    }

}
