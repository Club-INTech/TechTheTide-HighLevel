package scripts;

import locomotion.UnableToMoveException;
import lowlevel.order.Order;
import orders.OrderWrapper;
import orders.order.ActuatorsOrders;
import utils.HLInstance;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptPierrePrincipal extends Script {
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
            perform(ActuatorsOrders.Valve7Off);
            turnTowards(-0.63);
            moveLengthwise(186,false);
            //Portes ouvertes à 90 degrés
            turnTowards(-Math.PI/2);
            moveLengthwise(260,false);
            perform(ActuatorsOrders.LiftDown);
            perform(ActuatorsOrders.Pump7On);
            perform(ActuatorsOrders.LiftUp);
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
            perform(ActuatorsOrders.LiftDown);
            perform(ActuatorsOrders.Pump7Off);
            perform(ActuatorsOrders.Valve7On);
            moveLengthwise(-310,false);
            perform(ActuatorsOrders.GateClose);
            turnTowards(Math.PI);
            moveLengthwise(1800-762,false);
            turnTowards(Math.PI/2);
            moveLengthwise(500,false);
            turnTowards(Math.PI);
            moveLengthwise(400,false);
            perform(ActuatorsOrders.RightArmOut);
            perform(ActuatorsOrders.RightArmIn);
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
