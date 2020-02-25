package scripts;

import locomotion.UnableToMoveException;
import utils.HLInstance;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptTestMCS extends Script {
    /**
     * Construit un script
     *
     * @param hl le container
     */
    protected ScriptTestMCS(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        try {
            robot.setPositionAndOrientation(new InternalVectCartesian(0,0), 0.0);
                moveLengthwise(1000, false);
                turnTowards(Math.PI/2);
                moveLengthwise(500, false);
                turnTowards(Math.PI);
                moveLengthwise(1000, false);
                turnTowards(-Math.PI/2);
                moveLengthwise(500, false);
                turnTowards(0);


               // gotoPoint(new InternalVectCartesian(0, 1000));
               // gotoPoint(new InternalVectCartesian(1000, 1000));
               // gotoPoint(new InternalVectCartesian(1000, 0));
               // gotoPoint(new InternalVectCartesian(0, 0));

        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(0,0);
    }

    @Override
    public void finalize(Exception e) {

    }
}
