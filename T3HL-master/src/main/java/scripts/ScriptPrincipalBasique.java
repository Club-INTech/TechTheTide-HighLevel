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

        try {
            System.out.println(robot.getXyo());

            ActuatorsOrders.Gate.compileWith(3 * Math.PI/4);


            turnTowards(-Math.PI/2);
            moveLengthwise(632-455,false);

            turnTowards(0);
            moveLengthwise(450-206-10,false);

            turnTowards(-Math.PI/2);
            moveLengthwise(455-170,false);

            turnTowards(0);
            moveLengthwise(1025-450,false);

            turnTowards(Math.PI/2);
            moveLengthwise(1400-170,false);

            turnTowards(0);
            moveLengthwise(1800-1025,false);


            turnTowards(Math.PI/2);
            moveLengthwise(1800-1400,false);

            moveLengthwise(1140-1800,false);

            robot.increaseScore(18);

            turnTowards(Math.PI);
            moveLengthwise(1800-200,false);

            turnTowards(Math.PI/2);
            moveLengthwise(1140-170,false);

            turnTowards(Math.PI);
            moveLengthwise(200-150,false);
            moveLengthwise(150-740,false);

            System.out.println(robot.getXyo());

            /*
            Vec2 depart = new VectCartesian(300, 510);



            //robot.followPathTo(depart);

            //catchVerre(positionVerre6,true);

            //Portes ouvertes à 135 degrés

            //perform(ActuatorsOrders.ValvePOff);


            turnTowards(-0.63);
            moveLengthwise(186+218,false);
            robot.pushCouloirDroit(CouleurVerre.VERT);
            robot.pushCouloirGauche(CouleurVerre.ROUGE);




            //Portes ouvertes à 90 degrés

            //Verre 1 (Vert)

            turnTowards(-Math.PI/2);
            moveLengthwise(200,false);
            perform(ActuatorsOrders.LiftDown);
            perform(ActuatorsOrders.PumpPOn);
            perform(ActuatorsOrders.LiftUp);
            turnTowards(0);
            //350
            moveLengthwise(380,false);
            robot.pushCouloirDroit(CouleurVerre.VERT);
            //0.88
            turnTowards(Math.PI/4);




            //Portes ouvertes à 135 degrés
            //426

            moveLengthwise(226,false);
            robot.pushCouloirGauche(CouleurVerre.ROUGE);
            turnTowards(Math.PI/2);

            //430
            moveLengthwise(350,false);
            robot.pushCouloirDroit(CouleurVerre.VERT);
            turnTowards(0.96);
            moveLengthwise(488,false);
            robot.pushCouloirGauche(CouleurVerre.ROUGE);
            turnTowards(Math.PI/10);
            moveLengthwise(530,false);
            robot.pushCouloirDroit(CouleurVerre.VERT);
            turnTowards(Math.PI/2);
            robot.pushCouloirGauche(CouleurVerre.ROUGE);

            //Portes ouvertes à 180 degrés
            moveLengthwise(400,false);
            moveLengthwise(-90,false);

            robot.emptyBothCouloirs();

            perform(ActuatorsOrders.LiftDown);
            perform(ActuatorsOrders.PumpPOff);
            perform(ActuatorsOrders.ValvePOn);

            moveLengthwise(-310,false);
            perform(ActuatorsOrders.GateClose);

            turnTowards(Math.PI);
            moveLengthwise(1800-762,false);
            turnTowards(Math.PI/2);
            moveLengthwise(700-robotRay,false);



            //Vec2 a1 = new VectCartesian(762, 2000-robotRay);
            //gotoPoint(a1);

            turnTowards(Math.PI);
            moveLengthwise( 762 - robotRay,false);
            perform(ActuatorsOrders.RightArmOut);
            robot.increaseScore(15);
            robot.validateWindsocks(1);
            moveLengthwise(-400,false);
            robot.validateWindsocks(3);
            moveLengthwise(robotRay-362,false);
            perform(ActuatorsOrders.RightArmIn);
            turnTowards(-Math.PI/2);
            moveLengthwise(700-robotRay,false);
            moveLengthwise(200,false);
            turnTowards(0);
            int i = robot.score;
            moveLengthwise(1500-762,false);
            turnTowards(-Math.PI/2);

            robot.validateLighthouse();


            //Lire girouette
            //System.out.print(robot.score);

*/

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
