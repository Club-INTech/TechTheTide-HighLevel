package scripts;

import locomotion.UnableToMoveException;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptTest extends Script {

    public ScriptTest(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        try {
            moveLengthwise(1000, false);
            turnTowards(Math.PI);

            followPathTo(new VectCartesian(1500, 500)); // pathfinding vers environ milieu de la table
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(1000, 300); // là où on commence
    }

    @Override
    public void finalize(Exception e) {
        // TODO: on fait quoi quand on a fini ? (erreur ou fin normale)
    }
}
