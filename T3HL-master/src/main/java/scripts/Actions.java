package scripts;

import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;

public class Actions {

    public static void stack(Script script) {
        script.perform(ActuatorsOrders.PumpPOn);
        script.perform(ActuatorsOrders.LiftDown);
        script.perform(ActuatorsOrders.LiftUp);
    }

    public static void unstack(Script script) {
        script.perform(ActuatorsOrders.LiftDown);
        script.perform(ActuatorsOrders.PumpPOff);
        script.perform(ActuatorsOrders.ValvePOff);
        script.perform(ActuatorsOrders.LiftUp);

    }
    public static void recupGobeletsNordOurSide(Script script){
        try{
            script.turnTowards(-Math.PI/2);
            script.moveLengthwise(632-455,false);
            script.turnTowards(0);
            script.moveLengthwise(450-206-10,false);
            Actions.stack(script);
            script.turnTowards(-Math.PI/2);
            script.moveLengthwise(455-170,false);
            script.turnTowards(0);
            script.moveLengthwise(1025-450,false);
            script.turnTowards(Math.PI/2);
            script.moveLengthwise(1400-170,false);
            script.turnTowards(0);
        } catch (
                UnableToMoveException e) {
            e.printStackTrace();
        }
    }
}