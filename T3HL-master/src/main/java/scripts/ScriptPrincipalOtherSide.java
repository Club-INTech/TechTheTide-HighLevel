package scripts;

import data.XYO;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.HLInstance;
import utils.Offsets;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ScriptPrincipalOtherSide extends Script {

    public ScriptPrincipalOtherSide(HLInstance hl) {super(hl); }

    @Override
    public void execute(int version) {

        Future<Void> myParallelAction = async("Parallel action", () -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true){
                table.removeAnyIntersectedTemporaryObstacle(XYO.getRobotInstance().getPosition());
            }
        });

        try {
            perform(ActuatorsOrders.ValvePOff);
          /*  double myOffset = Offsets.get(Offsets.ACCELERATEUR_THETA_RECALAGE_JAUNE);
            if(symetry) {
                myOffset = Offsets.get(Offsets.ACCELERATEUR_THETA_RECALAGE_VIOLET);
            }
*/
            turnTowards(-0.82);
//            moveLengthwise((int) (300+myOffset),false);
            moveLengthwise((int) (300),false);
            TimeUnit.SECONDS.sleep (1);


            turnTowards(-1.16);
            TimeUnit.SECONDS.sleep (1);

            moveLengthwise(434,false);
            TimeUnit.SECONDS.sleep (1);
            turnTowards(-1.21);
            TimeUnit.SECONDS.sleep (1);
            moveLengthwise(427,false);
            TimeUnit.SECONDS.sleep (1);
            turnTowards(-0.82);
            TimeUnit.SECONDS.sleep (1);
            moveLengthwise(410,false);

            turnTowards(Math.PI);

            followPathTo(new VectCartesian(1450, 1000)); // pathfinding vers environ milieu de la table
            moveLengthwise(410,false);
            followPathTo(new VectCartesian(1000, 500));
            followPathTo(new VectCartesian(50, 100));





        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(1450,1500); // là où on commence
    }

    @Override
    public void finalize(Exception e) {
        // TODO: on fait quoi quand on a fini ? (erreur ou fin normale)
    }
}



