package opmodes.Regionals;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import TestRig.SelectModes;
import team25core.GamepadTask;
import team25core.Robot;
import team25core.RobotEvent;
import team25core.StandardFourMotorRobot;

@TeleOp(name = "Test Rig Demo")
//@Disabled

public class TestRigDemo extends Robot {
    private GamepadTask gamepad1Task;
    private SelectModes modes;

    // this init method is called when the init button is pressed on the driver station
    @Override
    public void init() {
        // here we instantiate the SelectModes - set aside memory for that class
        modes = new SelectModes(this);
        // here we instantiate the GamepadTask - set aside memory for that class
        gamepad1Task = new GamepadTask(this, GamepadTask.GamepadNumber.GAMEPAD_1);
        // begins monitoring the gamepad controls and generates gamepad events when the controls are pressed
        addTask(gamepad1Task);

    }

    // the handleEvent method is called after the task is added and a control is pressed
    @Override
    public void handleEvent(RobotEvent e) {
        GamepadTask.GamepadEvent event = (GamepadTask.GamepadEvent) e;

        switch (event.kind) {
            case BUTTON_X_DOWN:
                // do whatever when button is pressed
                break;
            case RIGHT_TRIGGER_DOWN:
                // do whatever when trigger is pressed
                break;
        }
    }

    @Override
    public void start() {

    }
}

