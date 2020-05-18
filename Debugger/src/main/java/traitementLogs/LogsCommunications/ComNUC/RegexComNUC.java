package traitementLogs.LogsCommunications.ComNuc;

import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexComNUC {

    static public void regexComNuc (String log, String couleurZone) {
        Matcher Configuration = Pattern.compile("Configuration de l'ecueil commun :").matcher(log);

        if (Configuration.find()) {
            String compo = log.substring(156);
            System.out.println("composition = " + compo);
            Robot.SetEceuilCommun(compo,couleurZone);
        }
    }
}
