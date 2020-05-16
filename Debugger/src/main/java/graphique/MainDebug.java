package graphique;

import LogRegex.Regex;
import traitementLogs.LogsDeplacement.RegexActions;


public class MainDebug {
    public static void main(String[] args) throws Exception {
        String logfile = "../TechTheTide-HighLevel/logs/Wed May 13 18:42:24 CEST 2020 #7.log";
        Regex.regex(logfile);
       new FenetreTable(RegexActions.LOG_POSITIONSET);
    }
}
