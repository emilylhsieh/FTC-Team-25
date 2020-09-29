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


    //taking in list of servos and populating a list of Telemetry.Items, populating ServoTlmMap.
    //modifier is private
    private Map<Servo, Telemetry.Item> populateServoTlmMap(List<Servo> servoList) {
        Map<Servo, Telemetry.Item> tmpServoTlmMap = null;
        for (Servo servo: Collections.emptyList(servoList)){
            String servoName = getServoName(servo);
        }
    }


    //constructor, called to instantiate a ServoMode object and allocate it memory
    //modifier is public, can be called within this class, package, and other packages
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


    //sets up Gamepad1 for servo controls, type void so no return
    //modifier is protected
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
