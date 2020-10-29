package scripts;

import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.sql.Time;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ScriptHomologation extends Script {

    //private static final int DISTANCE_INTERPALET = 300;

    private int xEntry = 225;
    private int yEntry = 640;

    public ScriptHomologation(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        // TODO: remplacer ce code d
        //  'exemple par un code qui marche
        Future<Void> myParallelAction = async("Parallel action", () -> {
           // on peut faire des actions en parallèle avec 'async'

            // on attend 1s
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //action
        });


       /** try {
            moveLengthwise(160,false);
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }**/
       ActuatorsOrders.Gate40.toLL();
        ActuatorsOrders.Gate90.toLL();
        ActuatorsOrders.Gate149.toLL();
        //ActuatorsOrders.SetGate(40);
        //ActuatorsOrders.SetGate(90);
        //ActuatorsOrders.SetGate(149);
        //ActuatorsOrders.FlagUp();
            /*moveLengthwise(1675,false);
            turnTowards(Math.PI/2);
            moveLengthwise(960,false);*/
        // moveLengthwise(-600,false); */


        // On peut attendre que des actions en parallèle aient fini
        join(myParallelAction);
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(xEntry, yEntry);
    }

    @Override
    public void finalize(Exception e) {
    }
}
