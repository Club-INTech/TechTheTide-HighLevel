package lowlevel.order;

import lowlevel.actuators.Servo;
import lowlevel.actuators.ServoGroup;
import utils.RobotSide;

/**
 * Instance de {@link ServoGroupOrder} créée par un {@link OrderBuilder}, qui peut être symétrisée
 *
 * @author jglrxavpok
 */
class BuiltSidedServoOrder implements SidedServoOrder {

    private final String system;
    private final RobotSide side;
    private final Servo servo;
    private final float angle;
    public SidedOrder symetrized;

    BuiltSidedServoOrder(String system, RobotSide side, Servo servo, float angle) {
        this.system = system;
        this.side = side;
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
    public RobotSide side() {
        return side;
    }

    @Override
    public SidedOrder createSymetrized() {
        return new BuiltSidedServoOrder(system, side.opposite(), servo.getSymetrized(), angle);
    }

    @Override
    public SidedOrder symetrize() {
        return symetrized;
    }

    @Override
    public String toLL() {
        return system+" "+side.toString()+" "+servo.id()+" "+angle;
    }
}
