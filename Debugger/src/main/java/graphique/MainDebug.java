package graphique;

import LogRegex.Regex;
import traitementLogs.LogsDeplacement.RegexActions;


public class MainDebug {
    public static void main(String[] args) throws Exception {
        String logfile = "logs/Sat Mar 07 23_46_45 CET 2020 #304.log";
        Regex.regex(logfile);
       new FenetreTable(RegexActions.LOG_POSITIONSET);
    }
}
