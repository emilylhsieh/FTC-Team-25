package TestRig;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import team25core.Robot;

public class SelectModes {
    public enum TestRigMode {
        INDEP_MOTOR,
        SERVO,
        DRIVE,
        SERVO_N_DRIVE;

        public TestRigMode nextMode() {
            TestRigMode currentMode = values()[ordinal()];

            if (currentMode != SERVO_N_DRIVE) {
                return values()[ordinal() +1];
            } else {
                return values()[0];
            }
        }
    }

    private Robot myRobot;
    private TestRigMode mode;
    private Telemetry.Item msgTelem;
    private Telemetry.Item modeTelem;

    SelectModes(Robot theirRobot) {
        myRobot = theirRobot;
        mode = TestRigMode.INDEP_MOTOR;
        msgTelem = myRobot.telemetry.addData("Use right trigger to toggle through modes");
        modeTelem = myRobot.telemetry.addData("Current mode: " , mode);
    }
}
