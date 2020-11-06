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

    public enum ServoDirection {
        STOP,
        CLOCKWISE,
        COUNTER_CLOCKWISE;

        public ServoMode.ServoDirection nextDirection() {
            ServoMode.ServoDirection currentDirection = values()[ordinal()];

            if (currentDirection != COUNTER_CLOCKWISE) {
                return values()[ordinal() +1];
            } else {
                return values()[0];
            }
        }
    }

    List<Servo> servoList;
    List<ServoDirection> servoDirectionList;
    Robot myRobot;
    int numServos;
    private Map<Servo, Telemetry.Item> servoTlmMap;
    private Servo activeServo;
    private ServoDirection servo1Direction;
    private ServoDirection servo2Direction;
    private ServoDirection servo3Direction;
    private ServoDirection servo4Direction;
    private Telemetry.Item servo1DirectionTlm;
    private Telemetry.Item servo2DirectionTlm;
    private Telemetry.Item servo3DirectionTlm;
    private Telemetry.Item servo4DirectionTlm;
    private ServoDirection currentDirection;
    List<Integer> servoPositions;

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
    //taking in list of servos and populating a list of Telemetry.Items, populating ServoTlmMap.
    //modifier is private
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

    private Servo getFirstServoInMap(Map<Servo, Telemetry.Item> servoMap) {
        //declaring local variable called firstServo type Servo
        //first servo is initialized  with first element of servo map
        //keySet is method of Map class that creates a set of the keys of the map
        //key- key used to get the value
        //toArray- takes set of keys and creates an array (a box/table)
        //0- indexing into the first element of the array
        Servo firstServo = ((Servo)servoMap.keySet().toArray()[0]);

        return(firstServo);
    }

    private Servo getNthServoInMap(Map<Servo, Telemetry.Item> servoMap, int n) {

        Servo nthServo = ((Servo)servoMap.keySet().toArray()[n - 1]);

        return(nthServo);
    }

    private void driveServos(){
        //calculate servo positions
        //going through directions in list and setting to 0 or 1
        for(ServoDirection direction :servoDirectionList) {
            if (direction == ServoDirection.CLOCKWISE) {
                servoPositions.add(1);
            } else if (direction == ServoDirection.COUNTER_CLOCKWISE) {
                servoPositions.add(0);
            } else {
                //handle case when servo is stopped
            }
        }
        //drive the servos
        //getting each servo and setting them to calculated position
        for(int i = 1; i <= 4; i = i + 1) {
            getNthServoInMap(servoTlmMap,i).setPosition((float)servoPositions.get(i - 1)/(float)256.0 );
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
        //set initiation servo directions
        servo1Direction = ServoDirection.STOP;
        servo2Direction = ServoDirection.STOP;
        servo3Direction = ServoDirection.STOP;
        servo4Direction = ServoDirection.STOP;
        servo1DirectionTlm = myRobot.telemetry.addData("servo1 Direction:", ServoDirection.STOP);
        servo2DirectionTlm = myRobot.telemetry.addData("servo2 Direction:", ServoDirection.STOP);
        servo3DirectionTlm = myRobot.telemetry.addData("servo3 Direction:", ServoDirection.STOP);
        servo4DirectionTlm = myRobot.telemetry.addData("servo4 Direction:", ServoDirection.STOP);
        if (numServos == 0) {
            //printing out to driver station phone there are no servos
            Telemetry.Item none = myRobot.telemetry.addData("No Servos", " ");
        }
        //populate servo to Telemetry.Item map
        servoTlmMap = populateServoTlmMap(servoList);

        //get the first servo in the map
        activeServo = getFirstServoInMap(servoTlmMap);
    }


    //sets up Gamepad1 for servo controls, type void so no return
    //modifier is protected
    protected void setUpGamepad1ForServoControl(GamepadTask.GamepadEvent event) {

        // casting instance of myRobot(generic robot type) into TestRigDemo type
        TestRigDemo demoInstance = (TestRigDemo) myRobot;

        switch (event.kind) {
            case RIGHT_TRIGGER_DOWN:
                // drive servo
                driveServos();
                break;
            case DPAD_UP_DOWN:
                // select servo 1
                getFirstServoInMap(servoTlmMap);
                servo1Direction = servo1Direction.nextDirection();
                servo1DirectionTlm.setValue(servo1Direction);
                break;
            case DPAD_RIGHT_DOWN:
                // select servo 2
                getNthServoInMap(servoTlmMap,2);
                servo2Direction = servo2Direction.nextDirection();
                servo2DirectionTlm.setValue(servo2Direction);
                break;
            case DPAD_DOWN_DOWN:
                // select servo 3
                getNthServoInMap(servoTlmMap,3);
                servo3Direction = servo3Direction.nextDirection();
                servo3DirectionTlm.setValue(servo3Direction);
                break;
            case DPAD_LEFT_DOWN:
                // select servo 4
                getNthServoInMap(servoTlmMap,4);
                servo4Direction = servo4Direction.nextDirection();
                servo4DirectionTlm.setValue(servo4Direction);
                break;
            default:
                // msgTelem.setValue("Use right trigger to select mode, use button X to exit mode selection");
        }
        return;
    }

}
