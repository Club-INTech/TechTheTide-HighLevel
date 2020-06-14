package traitementLogs.LogsCommunications.ComBuddy;

import graphique.FenetreLog;
import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class RegexComBuddy {
    static public void regexComBuddy (String log) {
        Matcher position = Pattern.compile("confirmOrder cxyo").matcher(log);
        if (position.find()){
            String pos = log.substring(149);
            System.out.println("pos = " + pos);
//affichage des coordonnées dans la fenêtre des logs
            FenetreLog.addLogTextln("positionBuddy = " + pos);

//extraction de la position en X
            int posXend = pos.indexOf(" ");
            int posX = Integer.parseInt(pos.substring(0,posXend));

//extraction de la position en Y
            pos = pos.substring(posXend + 1);
            int posYend = pos.indexOf(" ");
            int posY = Integer.parseInt(pos.substring(0,posYend));

//extraction de l'orientation
            pos = pos.substring(posYend + 1);
            double posO = Double.parseDouble(pos);
            Robot.SetPositionEtOrientationAmi(posX, posY, posO);
        }
        /** Affichage des autres actions**/
        else {
            int icrochet = log.substring(3).indexOf("]") ;
            String action = log.substring(3).substring(icrochet + 2 );
//affichage dans la fenêtre des logs
            FenetreLog.addLogTextln("actionBuddy = " + action);
        }
    }
}

