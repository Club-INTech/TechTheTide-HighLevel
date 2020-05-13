package traitementLogs.LogsActionsMeca;
import LogRegex.*;
import graphique.FenetreTable;

public class RegexActionsMeca {

    static public void regexActions (String log, FenetreTable debug) {
        debug.robot.setPosX(300);
        debug.robot.setPosY(300);
        debug.robot.repaint();

    }

}
