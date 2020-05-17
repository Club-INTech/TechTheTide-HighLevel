package graphique;

import LogRegex.Regex;
import traitementLogs.LogsDeplacement.RegexActions;


public class MainDebug {
    public static void main(String[] args) throws Exception {
        String logfile = "../TechTheTide-HighLevel/logs/Sat May 04 11:47:54 CEST 2019 #28 - everything.log";

        Regex.regex(logfile);
        String log = RegexActions.regexActions(logfile);
        FenetreTable f = new FenetreTable();
    }
}
