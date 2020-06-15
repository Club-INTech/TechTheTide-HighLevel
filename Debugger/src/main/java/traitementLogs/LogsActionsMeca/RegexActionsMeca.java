package traitementLogs.LogsActionsMeca;


import graphique.FenetreLog;
import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexActionsMeca {

    static public void regexActionsMeca(String log, String couleurZone) {
        Robot.SetPhareManche(couleurZone);

        Matcher Phare = Pattern.compile("Bras phare active :").matcher(log);
            if (Phare.find()) {
                String phareActive = log.substring(56);
                //System.out.println("phareActive="+phareActive);
                Robot.PhareCouleur(phareActive);
                FenetreLog.addLogTextln("PhareCôté = " + couleurZone);
                FenetreLog.addLogTextln("PhareActivé ="+ phareActive);
            }

       //affichage des autres actions
            else {
                int icrochet = log.substring(3).indexOf("]") ;
                String action = log.substring(3).substring(icrochet + 2 );
                FenetreLog.addLogTextln("actionMeca = " + action);
        }
        Matcher Manche = Pattern.compile("Bras manche a air :pos1 :").matcher(log);
        if (Manche.find()) {
            String mancheActive1 = log.substring(62,65);
            String mancheActive2 = log.substring(72);
            //System.out.println("mancheActive1 =" + mancheActive1+ "_mancheActive2 ="+ mancheActive2);
            Robot.MancheCouleur(mancheActive1,mancheActive2);
            FenetreLog.addLogTextln("mancheActive1 =" + mancheActive1+ "et mancheActive2 ="+ mancheActive2);
        }
    }
}

