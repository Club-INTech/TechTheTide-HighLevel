package scripts;

import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptHomologationSlave extends Script {

    @Configurable("buddyRay")
    private int ray;


    public ScriptHomologationSlave(HLInstance hl) {
        super(hl);
    }
    @Override
    public void execute(int version) {
        try {
          //  turnTowards(Math.PI);
          //  perform(ActuatorsOrders.AllBrasDepot[1]);
            moveLengthwise(845, true);
            moveLengthwise(-845, true);
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(ray + 10,1050-ray);
    }

    @Override
    public void finalize(Exception e) {

    }

}
