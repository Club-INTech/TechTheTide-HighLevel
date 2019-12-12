package lowlevel.order;

import lowlevel.actuators.Servo;
import lowlevel.actuators.ServoGroup;

/**
 * Instance de {@link ServoOrder} créée par un {@link OrderBuilder}
 *
 * @author jglrxavpok
 */
class BuiltServoOrder implements ServoOrder {

    private final String system;
    private final Servo servo;
    private final float angle;

    BuiltServoOrder(String system, Servo servo, float angle) {
        this.system = system;
        this.servo = servo;
        this.angle = angle;
    }

    @Override
    public Servo servo() {
        return servo;
    }

    @Override
    public float angle() {
        return angle;
    }

    @Override
    public String toLL() {
        return system+" "+servo.id()+" "+angle;
    }
}
