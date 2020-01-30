package scripts;

import data.XYO;
import data.synchronization.SynchronizationWithBuddy;
import pfg.config.Config;
import utils.HLInstance;
import utils.math.Vec2;
import data.Table;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import orders.order.MotionOrders;
import orders.order.PositionAndOrientationOrders;
import orders.order.SpeedOrders;
import pfg.config.ConfigInfo;
import robot.Slave;
import utils.ConfigData;
import utils.math.VectCartesian;

// @author : yam(AznekEnimsay), last modification 05/01/20

public class MatchSlave extends Script {
    private final ScriptManagerSlave scriptManagerSlave;
    private SynchronizationWithBuddy syncBuddy;

    public MatchSlave(HLInstance hl, ScriptManagerSlave scriptManagerSlave, SynchronizationWithBuddy syncBuddy) {
        super(hl);
        this.scriptManagerSlave = scriptManagerSlave;
        this.syncBuddy = syncBuddy;
    }

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire
        scriptManagerSlave.getScript(ScriptNamesSlave.ECUEIL_PRIVE).timedExecute();
        //scriptManagerSlave.getScript(ScriptNamesSlave.TEST_MCS).timedExecute();
        //scriptManagerSlave.getScript(ScriptNamesSlave.ECUEIL_PRIVE).timedExecute();
    }
    // TODO : à vous de jouer le 1As!

    @Override
    public Vec2 entryPosition(int version) {
        return scriptManagerSlave.getScript(ScriptNamesSlave.ECUEIL_PRIVE).entryPosition(0);
    }

    @Override
    public void finalize(Exception e) {

    }

    @Override
    public void updateConfig(Config config) {
            super.updateConfig(config);
        }
}
