package traitementLogs.LogsCommunications.ComBuddy;

import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexComBuddy {

    static public void regexComBuddy (String log) {
        Matcher position = Pattern.compile("confirmOrder cxyo").matcher(log);
        if (position.find()){
            String pos = log.substring(149);
            //System.out.println("pos = " + pos);

            int posXend = pos.indexOf(" ");
            int posX = Integer.parseInt(pos.substring(0,posXend));
            //System.out.println(posX);

            pos = pos.substring(posXend + 1);
            int posYend = pos.indexOf(" ");
            int posY = Integer.parseInt(pos.substring(0,posYend));
            //System.out.println(posY);

            pos = pos.substring(posXend + 1);
            double posO = Double.parseDouble(pos);
            //System.out.println(posR);


            //TODO : mettre la position sur le simulateur
            //TODO: ajouter l'orientatation
            Robot.SetPositionEtOrientationAmi(posX, posY, posO);

        }
        /** Affichage des autres actions**/
        else {
            int icrochet = log.substring(3).indexOf("]") ;
            String action = log.substring(3).substring(icrochet + 2 );
            //System.out.println("action = " + action);
        }
    }
}

