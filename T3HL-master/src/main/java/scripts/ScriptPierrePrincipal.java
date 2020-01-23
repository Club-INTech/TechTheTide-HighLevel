package scripts;

import data.XYO;
import locomotion.UnableToMoveException;
import lowlevel.order.Order;
import orders.OrderWrapper;
import orders.order.ActuatorsOrders;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptPierrePrincipal extends Script {

    @Configurable
    private int robotRay;
    /**
     * Construit un script
     *
     * @param hl le container
     */
    protected ScriptPierrePrincipal(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {


        try {
            //Portes ouvertes à 135 degrés
            wrapper.perform(ActuatorsOrders.Valve7Off);
            turnTowards(-0.63);
            moveLengthwise(186,false);

            table.removeAnyIntersectedMobileObstacle(XYO.getRobotInstance().getPosition(),robotRay);

            //Portes ouvertes à 90 degrés
            turnTowards(-Math.PI/2);
            moveLengthwise(260,false);
            wrapper.perform(ActuatorsOrders.LiftDown);
            wrapper.perform(ActuatorsOrders.Pump7On);
            wrapper.perform(ActuatorsOrders.LiftUp);
            turnTowards(0);
            moveLengthwise(350,false);
            turnTowards(0.88);
            //Portes ouvertes à 135 degrés
            moveLengthwise(426,false);
            turnTowards(Math.PI/2);
            moveLengthwise(430,false);
            turnTowards(0.96);
            moveLengthwise(488,false);
            turnTowards(0);
            moveLengthwise(450,false);
            turnTowards(Math.PI/2);
            //Portes ouvertes à 180 degrés
            moveLengthwise(400,false);
            moveLengthwise(-90,false);
            wrapper.perform(ActuatorsOrders.LiftDown);
            wrapper.perform(ActuatorsOrders.Pump7On);
            wrapper.perform(ActuatorsOrders.Valve7On);
            moveLengthwise(-310,false);
            wrapper.perform(ActuatorsOrders.GateClose);
            turnTowards(Math.PI);
            moveLengthwise(1800-762,false);
            turnTowards(Math.PI/2);
            moveLengthwise(500,false);
            turnTowards(Math.PI);
            moveLengthwise(400,false);
            wrapper.perform(ActuatorsOrders.RightArmOut);
            wrapper.perform(ActuatorsOrders.RightArmIn);
            moveLengthwise(-400,false);
            turnTowards(-Math.PI/2);
            moveLengthwise(400,false);
            moveLengthwise(200,false);
            turnTowards(0);
            moveLengthwise(1500-762,false);
            turnTowards(-Math.PI/2);
            //Lire girouette



        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) { return new VectCartesian(300,510); }

    @Override
    public void finalize(Exception e) {

    }
}
