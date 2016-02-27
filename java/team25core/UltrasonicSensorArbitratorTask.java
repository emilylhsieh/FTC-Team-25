package team25core;

/*
 * FTC Team 25: cmacfarl, February 22, 2016
 */

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UltrasonicSensorArbitratorTask extends RobotTask {

    ElapsedTime rateLimit = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    HashSet<SensorCache> sensors;
    Iterator<SensorCache> iterator;
    SensorCache sensor;
    protected SensorState state;

    private class SensorCache {

        Team25UltrasonicSensor sensor;
        double cacheVal;

        public SensorCache(Team25UltrasonicSensor sensor)
        {
            this.sensor = sensor;
            this.cacheVal = 0;
        }
    }

    protected enum SensorState {
        PING,
        PONG,
    };

    public UltrasonicSensorArbitratorTask(Robot robot, Set<Team25UltrasonicSensor> set)
    {
        super(robot);

        sensors = new HashSet<SensorCache>();

        for (Team25UltrasonicSensor s : set) {
            this.sensors.add(new SensorCache(s));
        }

        this.iterator = this.sensors.iterator();
        this.sensor = iterator.next();
        this.state = SensorState.PING;
    }

    public double getUltrasonicLevel(Team25UltrasonicSensor sensor)
    {
        for (SensorCache s : sensors) {
            if (s.sensor == sensor) {
                return s.cacheVal;
            }
        }
        RobotLog.e("Could not find sensor in set");
        return 255;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public boolean timeslice()
    {
        if (rateLimit.time() <= 20) {
            return false;
        }

        rateLimit.reset();

        if (state == SensorState.PING) {
            RobotLog.i("Arbitrator: " + sensor.sensor.getConnectionInfo() + " : Ping");
            sensor.sensor.doPing();
            state = SensorState.PONG;
        } else {
            RobotLog.i("Arbitrator: " + sensor.sensor.getConnectionInfo() + " : Pong");
            double val = sensor.sensor.getUltrasonicLevel();

            if ((val == 0) || (val == 255)) {
                return false;
            }

            // robot.telemetry.addData("Distance " + sensor.sensor.getConnectionInfo(), val);
            sensor.cacheVal = val;

            if (iterator.hasNext()) {
                sensor = iterator.next();
            } else {
                iterator = sensors.iterator();
                sensor = iterator.next();
            }
            state = SensorState.PING;
        }

        /*
         * Never stops
         */
        return false;
    }
}
