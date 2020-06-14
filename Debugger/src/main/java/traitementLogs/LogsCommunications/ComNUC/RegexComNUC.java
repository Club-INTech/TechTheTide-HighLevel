package traitementLogs.LogsCommunications.ComNuc;

import graphique.FenetreLog;
import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexComNUC {

    static public void regexComNuc (String log, String couleurZone) {
        Matcher Configuration = Pattern.compile("Configuration de l'ecueil commun :").matcher(log);

        if (Configuration.find()) {
            String compo = log.substring(156);

            //System.out.println("composition = " + compo + " " + couleurZone );
            Robot.SetEceuilCommun(compo,couleurZone);
            FenetreLog.addLogTextln("compositionEcueuilsNuc = " + compo);
        }
        else{
            int icrochet = log.substring(3).indexOf("]") ;
            String action = log.substring(3).substring(icrochet + 2 );
            FenetreLog.addLogTextln("actionNuc = " + action);
        }

    }
}
