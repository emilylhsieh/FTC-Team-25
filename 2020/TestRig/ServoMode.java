package TestRig;

import com.qualcomm.robotcore.hardware.Servo;

import team25core.GamepadTask;

class ServoMode {
    public enum ServoPosition {
        SERVO_LOW,
        SERVO_MID,
        SERVO_HIGH,
    }

    List<Servo> servoList;

    public void init() {
        // servo1 = hardwareMap.servo.get("servo1");
        // servo2 = hardwareMap.servo.get("servo2");
        // servo3 = hardwareMap.servo.get("servo3");
        // servo4 = hardwareMap.servo.get("servo4");

    }

    protected void setUpGamepad1ForServoControl(GamepadTask.GamepadEvent event) {

        // casting instance of myRobot(generic robot type) into TestRigDemo type
        TestRigDemo demoInstance = (TestRigDemo) myRobot;

        switch (event.kind) {
            case RIGHT_TRIGGER_DOWN:
                // drive servo
                break;
            case DPAD_UP_DOWN:
                // select servo 1
                break;
            case DPAD_RIGHT_DOWN:
                // select servo 2
                break;
            case DPAD_DOWN_DOWN:
                // select servo 3
                break;
            case DPAD_LEFT_DOWN:
                // select servo 4
                break;
            default:
                // msgTelem.setValue("Use right trigger to select mode, use button X to exit mode selection");
        }
        return;
    }

}
