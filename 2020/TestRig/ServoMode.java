package TestRig;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team25core.GamepadTask;
import team25core.Robot;

class ServoMode {
    public enum ServoPosition {
        SERVO_LOW,
        SERVO_MID,
        SERVO_HIGH,
    }

    List<Servo> servoList;
    Robot myRobot;
    int numServos;
    private Map<Servo, Telemetry.Item> servoTlmMap;

    //method getServoName is given a servo and returns the name of the servo
    String getServoName(Servo servo){
        //getNamesOf() is method that returns all the names by which the device is known
        Set<String> names = myRobot.hardwareMap.getNamesOf(servo);
        //if device has more than one name use first name
        for (String name: names) {
            return name;
        }
        return "No Servo Name Found";
    }
    //populateServoTlmMap is a method that is given a list a servos and returns a map.
    // given a servo, the mao returns the corresponding Telemetry.Item.
    // the telemetry item is used to print the servo name to the driver station phone.
    private Map<Servo, Telemetry.Item> populateServoTlmMap(List<Servo> servoList) {
        Map<Servo, Telemetry.Item> tmpServoTlmMap = null;
        //each loop of for loop wll give next servo on servo list
        for (Servo servo: Collections.emptyList(servoList)){
            String servoName = getServoName(servo);
            Telemetry.Item servoTlm = myRobot.telemetry.addData(servoName," ");
            tmpServoTlmMap.put(servo, servoTlm);
        }
        return(tmpServoTlmMap);
    }

    public ServoMode (Robot robot) {
        myRobot =  robot;
        //get all servos in phone configuration
        servoList = myRobot.hardwareMap.getAll(Servo.class);
        //get number of servos
        numServos = servoList.size();
        if (numServos == 0) {
            //printing out to driver station phone there are no servos
            Telemetry.Item none = myRobot.telemetry.addData("No Servos", " ");
        }
        //populate servo to Telemetry.Item map
        servoTlmMap = populateServoTlmMap(servoList);
    }

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
