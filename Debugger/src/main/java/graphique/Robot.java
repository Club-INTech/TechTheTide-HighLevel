package graphique;

import traitementLogs.LogsActionsMeca.RegexActionsMeca;
import traitementLogs.LogsDeplacement.RegexActions;
import traitementLogs.LogsDeplacement.RegexDeplacement;

import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    static int PrincipalWidth = 350;
    static int PrincipalHeigh = 220;

    static void go(TableVisualisation robot) {

        for (int i = 31; i < robot.getWidth(); i++) {
            int x = robot.getPosX(), y = robot.getPosY();
            x++;
            y--;
            robot.setPosX(x);
            robot.setPosY(y);
            robot.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void SetPosition (TableVisualisation robot, String log) throws Exception {
       Point PositionSet =  RegexActions.getPositionSet(log);
       robot.setPosY(PositionSet.x);
       robot.setPosY(PositionSet.y);
    }
}

