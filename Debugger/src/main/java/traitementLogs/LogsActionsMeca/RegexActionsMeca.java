package traitementLogs.LogsActionsMeca;


import graphique.FenetreLog;
import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexActionsMeca {

    static public void regexActionsMeca(String log, String couleurZone) {
        Robot.SetPhare(couleurZone);

        Matcher Phare = Pattern.compile("Bras phare active :").matcher(log);
            if (Phare.find()) {
                String phareActive = log.substring(56);
                //System.out.println(phareActive);
                Robot.PhareCouleur(phareActive);
                FenetreLog.addLogTextln("PhareCôté = " + couleurZone);
                FenetreLog.addLogTextln("PhareActivé = oui");
            }

       //affichage des autres actions
            else {
                int icrochet = log.substring(3).indexOf("]") ;
                String action = log.substring(3).substring(icrochet + 2 );
                FenetreLog.addLogTextln("actionNuc = " + action);
        }


    }
}
