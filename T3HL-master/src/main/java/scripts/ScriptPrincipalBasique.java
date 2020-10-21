package scripts;

import data.CouleurPalet;
import data.CouleurVerre;
import data.XYO;
import data.table.Obstacle;
import data.table.StillCircularObstacle;
import locomotion.UnableToMoveException;
import lowlevel.order.Order;
import orders.OrderWrapper;
import orders.order.ActuatorsOrders;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Circle;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;
import utils.math.VectCartesian;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


// @author Pierre, last modification 14/01/20

public class ScriptPrincipalBasique extends Script {
    @Configurable
    private int robotRay;
    private int[] state = new int[5]; //[GinRobot,RinRobot,GinRight,RinRight,GinLeft,RinLeft]
    /**
     * Construit un script
     *
     * @param hl le container
     */
    protected ScriptPrincipalBasique(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {



        async("Parallel action", () -> {

            while(true){
                table.removeAnyIntersectedTemporaryObstacle(XYO.getRobotInstance().getPosition());
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        async("Parallel action", () -> {

            try {
                Thread.sleep(95000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            perform(ActuatorsOrders.FlagUp);

        });

        try {
            System.out.println(robot.getXyo());
            perform(ActuatorsOrders.FlagUp);

            Actions.recupGobeletsNordOurSide(this);
            moveLengthwise(1800-1025,false);
            turnTowards(Math.PI/2);
            moveLengthwise(1800-1400,false);
            moveLengthwise(1140-1800,false);

            perform(ActuatorsOrders.SetGate(Math.PI));

            Actions.unstack(this);


            robot.increaseScore(18);

            turnTowards(Math.PI);
            moveLengthwise(1800-200,false);

            turnTowards(Math.PI/2);
            moveLengthwise(1140-170,false);

            turnTowards(Math.PI);
            moveLengthwise(200-150,false);
            moveLengthwise(150-740,false);

            System.out.println(robot.getXyo());





        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) { return new VectCartesian(206,636); }

    @Override
    public void finalize(Exception e) {

    }
}