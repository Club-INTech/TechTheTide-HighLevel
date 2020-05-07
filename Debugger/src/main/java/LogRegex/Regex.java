package LogRegex;

 import traitementLogs.LogsActionsMeca.RegexActionsMeca;
 import traitementLogs.LogsDeplacement.RegexDeplacement;
 import traitementLogs.LogsLIDAR.RegexLidar;

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

            try {


                if (logLocomotion.find()) {
                    RegexDeplacement.regexDeplacement(log);
                } else if (logOrders.find()) {
                    RegexActionsMeca.regexActions(log);
                } else if (logLidar.find()) {
                    RegexLidar.regexLidar(log);
                } else if (logLidarProcess.find()) {
                    RegexLidar.regexLidar(log);
                } else if (logPosition.find()) {
                    //A voir
                } else if (logLLDebug.find()) {
                    //A voir
                } else if (logDynamixel.find()) {
                    RegexActionsMeca.regexActions(log);
                } else if (logCommunication.find()) {
                    //Comm buddy ou NUC ?
                }

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
        fstream.close();
    }

    public static void main(String[] args) throws IOException {
        String logfile = "/home/yasmine/TechTheTide-HighLevel/logs/Fri Jan 24 11_04_25 CET 2020 #60.log";
        Regex.regex(logfile);
    }
}

