package opmodes.Regionals;

import com.qualcomm.robotcore.hardware.Servo;

class ServoMode {
    public enum ServoPosition {
        SERVO_LOW,
        SERVO_MID,
        SERVO_HIGH,
    }

    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    private Servo servo4;

    public void init() {
        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");
        servo3 = hardwareMap.servo.get("servo3");
        servo4 = hardwareMap.servo.get("servo4");
    }
}
