package LogRegex;

 import traitementLogs.LogsActionsMeca.RegexActionsMeca;
 import traitementLogs.LogsCommunications.ComBuddy.RegexComBuddy;
 import traitementLogs.LogsDeplacement.RegexActions;
 import traitementLogs.LogsDeplacement.RegexDeplacement;
 import traitementLogs.LogsLIDAR.RegexLidar;
 import traitementLogs.LogsStrategy.RegexStrategy;

 import java.io.*;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 // @author AznekEnimsay (yam)
// last modification 13/03/20

public class Regex {

    static public void regex (String logFile) throws IOException {

        FileInputStream fstream = new FileInputStream(logFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String log;
        /* read log line by line */
        while ((log = br.readLine()) != null) {

            Matcher logLocomotion = Pattern.compile("LOCOMOTION").matcher(log);
            Matcher logOrders = Pattern.compile("ORDERS").matcher(log);
            Matcher logLidar = Pattern.compile("LIDAR").matcher(log);
            Matcher logLidarProcess = Pattern.compile("LIDAR_PROCESS").matcher(log);
            Matcher logPosition = Pattern.compile("POSITION").matcher(log);
            Matcher logLLDebug = Pattern.compile("LL_DEBUG").matcher(log);
            Matcher logDynamixel = Pattern.compile("DYNAMIXEL").matcher(log);
            Matcher logCommunication = Pattern.compile("COMMUNICATION").matcher(log);
            Matcher logStrategy =Pattern.compile("STRATEGY").matcher(log);

            try {


                if (logLocomotion.find()) {
                    RegexDeplacement.regexDeplacement(log);
                } else if (logOrders.find()) {
                    RegexActions.regexActions(log);
                } else if (logLidar.find()) {
                    RegexLidar.regexLidar(log);
                } else if (logLidarProcess.find()) {
                    RegexLidar.regexLidar(log);
                } else if (logPosition.find()) {
                    //A voir
                } else if (logLLDebug.find()) {
                    //A voir
                } else if (logDynamixel.find()) {
                    RegexActionsMeca.regexActionsMeca(log);
                } else if (logStrategy.find()){
                    RegexStrategy.regexStrategy(log);
                } else if (logCommunication.find()) {
                    Matcher handleConfig = Pattern.compile("handleConfig").matcher(log);

                    if (handleConfig.find()) {
                       traitementLogs.LogsCommunications.ComNUC.RegexComNUC.regexComNuc(log);
                    } else{
                        RegexComBuddy.regexComBuddy(log);
                    }
                }


            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
        fstream.close();
    }

    public static void main(String[] args) throws IOException {
        String logfile = "../TechTheTide-HighLevel/logs/Wed May 13 18:42:24 CEST 2020 #7.log";
        Regex.regex(logfile);
    }
}

