package scripts;

import locomotion.UnableToMoveException;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

public class ScriptTestSorya extends Script {

    private static final int DISTANCE = 800;

    private int xEntry = 3000-191-65+20;
    private int yEntry = 430;

    public ScriptTestSorya(HLInstance hl) { super(hl);
    }

    @Override
    public void execute(int version) {
        try {
            moveLengthwise(DISTANCE* 2, false);
            turnTowards(-Math.PI / 2);
        }
        catch (UnableToMoveException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vec2 entryPosition(int version)  { return new VectCartesian(xEntry, yEntry);
    }


    @Override
    public void finalize(Exception e) {

    }
}
