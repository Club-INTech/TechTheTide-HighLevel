package scripts;

import data.synchronization.SynchronizationWithBuddy;
import locomotion.UnableToMoveException;
import pfg.config.Configurable;
import utils.ConfigData;
import utils.HLInstance;
import utils.container.ContainerException;
import utils.math.Vec2;

public class Match extends Script {
    private final ScriptManagerMaster scriptManagerMaster;
    private SynchronizationWithBuddy syncBuddy;

    public Match(HLInstance hl, ScriptManagerMaster scriptManagerMaster, SynchronizationWithBuddy syncBuddy) {
        super(hl);
        this.scriptManagerMaster = scriptManagerMaster;
        this.syncBuddy = syncBuddy;
    }


    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du principal
        //scriptManagerMaster.getScript(ScriptNamesMaster.HOMOLOGATION).timedExecute(0);
        //Test
        //scriptManagerMaster.getScript(ScriptNamesMaster.TESTBASIC).timedExecute(0);
       // scriptTest // A rajouter
        scriptManagerMaster.getScript(ScriptNamesMaster.PIERRE).timedExecute();
        try {
            moveLengthwise(-100, false);
        } catch (UnableToMoveException e) {
            e.printStackTrace();

        }
        // TODO: A vous de jouer les 1As!
    }

    @Override
    public Vec2 entryPosition(int version) {
        return scriptManagerMaster.getScript(ScriptNamesMaster.PIERRE).entryPosition(0);

    }

    @Override
    public void finalize(Exception e) {

    }
}
