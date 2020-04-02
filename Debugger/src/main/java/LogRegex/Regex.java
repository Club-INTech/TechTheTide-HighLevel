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

        String Locomotion = "LOCOMOTION";
        String Orders = "ORDERS";
        String Lidar = "LIDAR";
        String LidarProcess = "LIDAR_PROCESS";
        String Position = "POSITION";
        String LLDebug = "LL_DEBUG";
        String Dynamixel = "DYNAMIXEL";
        String Communication = "COMMUNICATION";

        FileInputStream fstream = new FileInputStream(logFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String log;
        /* read log line by line */
        while ((log = br.readLine()) != null) {
            try {

                Pattern logLocomotion = Pattern.compile(Locomotion);
                Pattern logOrders = Pattern.compile(Orders);
                Pattern logLidar = Pattern.compile(Lidar);
                Pattern logLidarProcess = Pattern.compile(LidarProcess);
                Pattern logPosition = Pattern.compile(Position);
                Pattern logLLDebug = Pattern.compile(LLDebug);
                Pattern logDynamixel = Pattern.compile(Dynamixel);
                Pattern logCommunication = Pattern.compile(Communication);

                Matcher mlogLocomotion = logLocomotion.matcher(log);
                Matcher mlogOrders = logOrders.matcher(log);
                Matcher mlogLidar = logLidar.matcher(log);
                Matcher mlogLidarProcess = logLidarProcess.matcher(log);
                Matcher mlogPosition = logPosition.matcher(log);
                Matcher mlogLLDebug = logLLDebug.matcher(log);
                Matcher mlogDynamixel = logDynamixel.matcher(log);
                Matcher mlogCommunication = logCommunication.matcher(log);

                if (mlogLocomotion.find()) {
                    RegexDeplacement.regexDeplacement(log);
                } else if (mlogOrders.find()) {
                    RegexActionsMeca.regexActions(log);
                } else if (mlogLidar.find()) {
                    RegexLidar.regexLidar(log);
                } else if (mlogLidarProcess.find()) {
                    RegexLidar.regexLidar(log);
                } else if (mlogPosition.find()) {
                    //A voir
                } else if (mlogLLDebug.find()) {
                    //A voir
                } else if (mlogDynamixel.find()) {
                    RegexActionsMeca.regexActions(log);
                } else if (mlogCommunication.find()) {
                    //Comm buddy ou NUC ?
                }

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
        fstream.close();
    }

    public static void main(String[] args) throws IOException {
        String logfile = "/home/yasmine/TechTheTide-HighLevel/logs/Sat Mar 07 21:07:46 CET 2020 #0.log";
        Regex.regex(logfile);
    }
}

