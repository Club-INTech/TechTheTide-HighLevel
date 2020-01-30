package scripts;

import locomotion.UnableToMoveException;
import utils.HLInstance;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;

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
            for (int i = 0; i < 10; i++) {
                gotoPoint(new InternalVectCartesian(500, 500));
                gotoPoint(new InternalVectCartesian(900, 0));
            }
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return null;
    }

    @Override
    public void finalize(Exception e) {

    }
}
