package scripts;

import data.XYO;
import locomotion.UnableToMoveException;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

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

            turnTowards(-Math.PI/2);
            //225 640
            moveLengthwise(185,false);
            //225 455
            turnTowards(0);
            moveLengthwise(225,false);
            //430 455
            turnTowards(-Math.PI/2);
            moveLengthwise(305,false);
            //450 150
            turnTowards(0);
            moveLengthwise(575,false);
            //1025 150
            turnTowards(Math.PI/2);
            moveLengthwise(825,false);
            //1025 1100
            turnTowards(0);
            moveLengthwise(775,false);
            //1800 1100
            turnTowards(Math.PI/2);
            moveLengthwise(700,false);
            //1800 1800
            moveLengthwise(-500,false);
            //1800 1300

            /*
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

            turnTowards(Math.PI);
            moveLengthwise(1800-200,false);

            turnTowards(Math.PI/2);
            moveLengthwise(1140-170,false);

            turnTowards(Math.PI);
            moveLengthwise(200-150,false);
            moveLengthwise(150-740,false);
             */

            System.out.println(robot.getXyo());




        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) { return new VectCartesian(225,640); }

    @Override
    public void finalize(Exception e) {

    }
}
