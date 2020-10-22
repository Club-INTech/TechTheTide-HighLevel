package scripts;

import data.synchronization.SynchronizationWithBuddy;
import locomotion.UnableToMoveException;
import utils.HLInstance;
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
        //scriptManagerMaster.getScript(ScriptNamesMaster.RECUPFACULTATIF).timedExecute(0);
       // scriptTest // A rajouter
       scriptManagerMaster.getScript(ScriptNamesMaster.RECUPBASIQUE).timedExecute();


        try {
            moveLengthwise(-100, false);
        } catch (UnableToMoveException e) {
            e.printStackTrace();

        }


        // TODO: A vous de jouer les 1As!

        //Update
        // TODO Gagner la coupe
    }

    @Override
    public Vec2 entryPosition(int version) {
        return scriptManagerMaster.getScript(ScriptNamesMaster.RECUPBASIQUE).entryPosition(0);
        //return scriptManagerMaster.getScript(ScriptNamesMaster.RECUPFACULTATIF).entryPosition(0);
    }

    @Override
    public void finalize(Exception e) {

    }
}
