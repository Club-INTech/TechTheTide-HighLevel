package scripts;

import locomotion.UnableToMoveException;
import utils.HLInstance;
import utils.math.InternalVectCartesian;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptPierre extends Script {
    /**
     * Construit un script
     *
     * @param hl le container
     */
    protected ScriptPierre(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        Vec2 point1 = new VectCartesian(375,1400);
        Vec2 point2 = new VectCartesian(375,1800);
        Vec2 point3 = new VectCartesian(1050,1490);
        Vec2 point4 = new VectCartesian(1150,1200);
        Vec2 point5 = new VectCartesian(1270,800);
        Vec2 point6 = new VectCartesian(1800,350);
        Vec2 point7 = new VectCartesian(1800,250);
        try {
            followPathTo(point1);
            followPathTo(point2);
            turnTowards(0);
            moveLengthwise(400, false);
            followPathTo(point3);
            followPathTo(point4);
            followPathTo(point5);
            followPathTo(point6);
            followPathTo(point7);
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
        try {
            moveLengthwise(-300, false);
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(300,1200);
    }

    @Override
    public void finalize(Exception e) {

    }
}
