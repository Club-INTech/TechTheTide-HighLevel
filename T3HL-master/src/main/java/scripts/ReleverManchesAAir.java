package scripts;

import data.XYO;
import locomotion.UnableToMoveException;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.util.concurrent.TimeUnit;


// @author Pierre, last modification 14/01/20

public class ReleverManchesAAir extends Script {
    @Configurable
    private int robotRay;
    /**
     * Construit un script
     *
     * @param hl le container
     */
    protected ReleverManchesAAir(HLInstance hl) {
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
            followPathTo(new VectCartesian(670,1300));




        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) { return new VectCartesian(1800,1300); }

    @Override
    public void finalize(Exception e) {

    }
}
