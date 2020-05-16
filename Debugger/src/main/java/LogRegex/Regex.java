package LogRegex;

 import traitementLogs.LogsActionsMeca.RegexActionsMeca;
 import traitementLogs.LogsDeplacement.RegexDeplacement;
 import traitementLogs.LogsLIDAR.RegexLidar;

 import java.io.*;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 import graphique.*;

 // @author AznekEnimsay (yam)
// last modification 13/03/20

public class Regex {


    static public void regex (String logFile) throws IOException {


        FenetreTable debug = new FenetreTable();

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


//                System.out.println(log);
//                Pattern logLocomotion = Pattern.compile(Locomotion);
//                Pattern logOrders = Pattern.compile(Orders);
//                Pattern logLidar = Pattern.compile(Lidar);
//                Pattern logLidarProcess = Pattern.compile(LidarProcess);
//                Pattern logPosition = Pattern.compile(Position);
//                Pattern logLLDebug = Pattern.compile(LLDebug);
//                Pattern logDynamixel = Pattern.compile(Dynamixel);
//                Pattern logCommunication = Pattern.compile(Communication);


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
                        RegexLidar.regexLidar(log, debug);
                    } else if (logLidarProcess.find()) {
                        RegexLidar.regexLidar(log, debug);
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
        String logfile = "/home/jasmin/Documents/Intech/TechTheTide-HighLevel/logs/Sat May 04 11:47:19 CEST 2019 #27 - everything.log";
//        String logfile = "/home/jasmin/Documents/Intech/TechTheTide-HighLevel/logs/Sat May 04 11:47:54 CEST 2019 #28 - everything.log";

        Regex.regex(logfile);
    }
}

