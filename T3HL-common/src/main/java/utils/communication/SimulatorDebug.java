package utils.communication;

import connection.Connection;
import connection.ConnectionManager;
import data.CouleurPalet;
import data.CouleurVerre;
import pfg.config.Config;
import pfg.config.Configurable;
import utils.ConfigData;
import utils.RobotSide;
import utils.container.Module;

import java.util.Stack;

public class SimulatorDebug implements Module {

    private final ConnectionManager manager;
    @Configurable("simulation")
    private boolean active;
    private int senderPort;

    public SimulatorDebug(ConnectionManager manager) {
        this.manager = manager;
    }

    public void sendElevatorContents(RobotSide side, Stack<CouleurPalet> elevator) {
        if(active) {
            StringBuilder builder = new StringBuilder(getSenderPort()+" elevatorContents "+side.toString());
            for(CouleurPalet colour : elevator) {
                builder.append(" ");
                builder.append(colour.toString());
            }
            try {
                Connection.DEBUG_SIMULATEUR.send(builder.toString());
            } catch (CommunicationException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendCouloirContents(RobotSide side, Stack<CouleurVerre> couloir) {
        if(active) {
            StringBuilder builder = new StringBuilder(getSenderPort()+" couloirContents "+side.toString());
            for(CouleurVerre colour : couloir) {
                builder.append(" ");
                builder.append(colour.toString());
            }
            try {
                Connection.DEBUG_SIMULATEUR.send(builder.toString());
            } catch (CommunicationException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendLighthouseContents(boolean lighthouse) {
        if(active) {
            StringBuilder builder = new StringBuilder(getSenderPort()+" lighthouseState ");
            builder.append(lighthouse);

            try {
                Connection.DEBUG_SIMULATEUR.send(builder.toString());
            } catch (CommunicationException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendWindsocksContents(int windsocks) {
        if(active) {
            StringBuilder builder = new StringBuilder(getSenderPort()+" windsocksState ");
            builder.append(windsocks);

            try {
                Connection.DEBUG_SIMULATEUR.send(builder.toString());
            } catch (CommunicationException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSenderPort() {
        return senderPort;
    }

    public void setSenderPort(int senderPort) {
        this.senderPort = senderPort;
    }

}
