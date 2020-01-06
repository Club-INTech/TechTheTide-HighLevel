package scripts;

import data.XYO;
import data.synchronization.SynchronizationWithBuddy;
import pfg.config.Config;
import utils.HLInstance;
import utils.math.Vec2;
import data.Table;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import orders.order.MotionOrders;
import orders.order.PositionAndOrientationOrders;
import orders.order.SpeedOrders;
import pfg.config.ConfigInfo;
import robot.Slave;
import utils.ConfigData;
import utils.math.VectCartesian;

// @author : yam(AznekEnimsay), last modification 05/01/20

public class ScriptRecupEcueilPrive extends Script {

   protected ScriptRecupEcueilPrive(HLInstance hl) {
        super(hl);}

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire

        try {
            int ray = hl.getConfig().getInt(ConfigData.BUDDY_RAY);
            MotionOrders.MoveToPoint.compileWith(ray, 1525);
            turnTowards(-Math.PI/2);
            wrapper.perform(ActuatorsOrders.AllBrasEcueil[4]);
            wrapper.perform(ActuatorsOrders.AllBrasEcueil[6]);
            wrapper.perform(ActuatorsOrders.Valve.compileWith(4,"on"));
            wrapper.perform(ActuatorsOrders.Valve.compileWith(6,"on"));
            wrapper.perform(ActuatorsOrders.Pump.compileWith(4,"on"));
            wrapper.perform(ActuatorsOrders.Pump.compileWith(6,"on"));
            moveLengthwise(-7,true);
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(4));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(6));
            moveLengthwise(7,true);
            turnTowards(-Math.PI/2);
            moveLengthwise(-225,false);
            turnTowards(Math.PI/2);
            wrapper.perform(ActuatorsOrders.AllBrasEcueil[5]);
            wrapper.perform(ActuatorsOrders.Valve.compileWith(5,"on"));
            wrapper.perform(ActuatorsOrders.Pump.compileWith(5,"on"));
            moveLengthwise(-7,true);
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(5));
            moveLengthwise(7,true);
            MotionOrders.MoveToPoint.compileWith(ray, 1600);
            turnTowards(-Math.PI/2);
            wrapper.perform(ActuatorsOrders.AllBrasEcueil[1]);
            wrapper.perform(ActuatorsOrders.AllBrasEcueil[3]);
            wrapper.perform(ActuatorsOrders.Valve.compileWith(1,"on"));
            wrapper.perform(ActuatorsOrders.Valve.compileWith(3,"on"));
            wrapper.perform(ActuatorsOrders.Pump.compileWith(1,"on"));
            wrapper.perform(ActuatorsOrders.Pump.compileWith(3,"on"));
            moveLengthwise(7,true);
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(1));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(3));
            moveLengthwise(-7,true);
            turnTowards(Math.PI/2);
            moveLengthwise(550+ray,false);
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(4));
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(5));
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(6));
            MotionOrders.MoveToPoint.compileWith(ray,493-ray);         //493 = 500 - 7
            turnTowards(Math.PI);
            moveLengthwise(7, false);
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(1));
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(2));
            wrapper.perform(ActuatorsOrders.BrasDepot.compileWith(3));
            moveLengthwise(-7,false);
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(1));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(2));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(3));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(4));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(5));
            wrapper.perform(ActuatorsOrders.BrasStock.compileWith(6));
            MotionOrders.MoveToPoint.compileWith(ray,ray);
            turnTowards(Math.PI);
            // Déploiment du phare
            // Config des gobelets ecueil commun
            //Recupération des gobelets eceuil commun


        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        int ray = hl.getConfig().getInt(ConfigData.BUDDY_RAY);
        return new VectCartesian(ray,1050-ray);
    }

    @Override
    public void finalize(Exception e) {

    }

}