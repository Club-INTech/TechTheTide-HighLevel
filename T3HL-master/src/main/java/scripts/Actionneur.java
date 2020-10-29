package scripts;

import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Actionneur extends Script {

    //private static final int DISTANCE_INTERPALET = 300;

    private int xEntry = 225;
    private int yEntry = 640;

    public Actionneur(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        try {
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


            moveLengthwise(160,false);
            //ActuatorsOrders.SetGate(40);

            /*moveLengthwise(1675,false);
            turnTowards(Math.PI/2);
            moveLengthwise(960,false);*/
            // moveLengthwise(-600,false);


            // On peut attendre que des actions en parallèle aient fini
            join(myParallelAction);
        } catch (UnableToMoveException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(xEntry, yEntry);
    }

    @Override
    public void finalize(Exception e) {
    }
}

