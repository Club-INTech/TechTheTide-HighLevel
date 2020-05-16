package graphique;

import LogRegex.Regex;
import traitementLogs.LogsDeplacement.RegexActions;


public class MainDebug {
    public static void main(String[] args) throws Exception {
        String logfile = "../TechTheTide-HighLevel/logs/Fri Jan 24 11_04_25 CET 2020 #60.log";
        Regex.regex(logfile);
        String log = RegexActions.regexActions(logfile);
        new FenetreTable(log);
    }
}
