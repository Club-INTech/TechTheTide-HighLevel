package scripts;

import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.math.VectCartesian;


// @author yam(AznekEnimsay); last modification 14/01/20

public class GobeletActions{


    // Pour mettre tous les bras en position de stockage : retour à la config de base du robot

    public static void BrasRepos(Script script, int...indices) throws UnableToMoveException {
        script.moveLengthwise(-7,false);
        for (int index : indices)
            script.perform(ActuatorsOrders.AllBrasStock[index]);
    }

    // Pour déposer les gobelets

    public static void drop(Script script, int... indices) {
        for (int index : indices) {
            script.perform(ActuatorsOrders.AllBrasDepot[index]);
            script.perform(ActuatorsOrders.ValveOff[index]);
            script.perform(ActuatorsOrders.PumpOff[index]);
        }
    }

    // Pour attraper les gobelets

    public static void grab(Script script, boolean reverse ,int... indices) throws UnableToMoveException {
        for (int index : indices) {
            script.perform(ActuatorsOrders.AllBrasEcueil[index]);
            script.perform(ActuatorsOrders.ValveOn[index]);
            script.perform(ActuatorsOrders.PumpOn[index]);
            if(reverse) {
                script.moveLengthwise(-7,true);
                script.perform(ActuatorsOrders.AllBrasStock[index]);
                script.moveLengthwise(7,false);
            }
            else {
                script.moveLengthwise(7,true);
                script.perform(ActuatorsOrders.AllBrasStock[index]);
                script.moveLengthwise(-7,false);
            }
        }
    }

}
