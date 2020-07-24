package TestRig;

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
}
