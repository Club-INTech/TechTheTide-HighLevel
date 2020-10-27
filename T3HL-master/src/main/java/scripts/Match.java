package scripts;

import data.synchronization.SynchronizationWithBuddy;
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
        boolean homologation = true;
        if(homologation) { //homologation
            scriptManagerMaster.getScript(ScriptNamesMaster.HOMOLOGATION).timedExecute(0);
        }else {
            scriptManagerMaster.getScript(ScriptNamesMaster.RECUP_GOBELETS_NON_AGRESSIVE).timedExecute();
        }


       /*
        try {
            moveLengthwise(-100, false);
        } catch (UnableToMoveException e) {
            e.printStackTrace();

        }
        */


        // TODO: A vous de jouer les 1As!
    }

    @Override
    public Vec2 entryPosition(int version) {
        return scriptManagerMaster.getScript(ScriptNamesMaster.RECUP_GOBELETS_NON_AGRESSIVE).entryPosition(0);
        //return scriptManagerMaster.getScript(ScriptNamesMaster.RECUPFACULTATIF).entryPosition(0);
    }

    @Override
    public void finalize(Exception e) {

    }
}
