package LogRegex;

 import graphique.FenetreLog;
 import graphique.FenetreTable;
 import traitementLogs.LogsActionsMeca.RegexActionsMeca;
 import traitementLogs.LogsCommunications.ComBuddy.RegexComBuddy;
 import traitementLogs.LogsCommunications.ComNuc.RegexComNUC;
 import traitementLogs.LogsDeplacement.RegexActions;
 import traitementLogs.LogsDeplacement.RegexDeplacement;
 import traitementLogs.LogsLIDAR.RegexLidar;
 import traitementLogs.LogsStrategy.RegexStrategy;

 import java.io.*;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 import static java.lang.Thread.sleep;

// @author Intechien
// last modification 13/06/20

public class Regex {

    static public void regex (String logFile) throws IOException {

        Boolean boutonPlay;
        Boolean boutonPasAPas;

        FileInputStream fstream = new FileInputStream(logFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String log;
        String couleurZone= null;

        /* lecture  du fichier log ligne par ligne */
        while ((log = br.readLine()) != null){
            //lectures des états des boutons
            boutonPlay= graphique.FenetreTable.getboutonPlay();
            boutonPasAPas = graphique.FenetreTable.getboutonPasAPas();

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Cas le bouton play est  désactivé
            if (boutonPlay==false){
                //2 cas: on attend l'activation soit du bouton play soit du bouton pas à pas
                while(boutonPlay == false && boutonPasAPas == false){
                    boutonPlay= graphique.FenetreTable.getboutonPlay();
                    boutonPasAPas = graphique.FenetreTable.getboutonPasAPas();
                    try{
                        Thread.sleep(1);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }


            //Traitement de Log (ici soit le bouton play est activé soit le bouton pas à pas l'est
            TraitementLog(log, couleurZone);

            //on réinitialise le paramétre boutonPasAPas
            graphique.FenetreTable.boutonPasAPas = false;
        }
        fstream.close();
    }

    public static void TraitementLog(String log, String couleurZone){
        Matcher logLocomotion = Pattern.compile("LOCOMOTION").matcher(log);
        Matcher logOrders = Pattern.compile("ORDERS").matcher(log);
        Matcher logLidar = Pattern.compile("LIDAR").matcher(log);
        Matcher logLidarProcess = Pattern.compile("LIDAR_PROCESS").matcher(log);
        Matcher logPosition = Pattern.compile("POSITION").matcher(log);
        Matcher logLLDebug = Pattern.compile("LL_DEBUG").matcher(log);
        Matcher logDynamixel = Pattern.compile("DYNAMIXEL").matcher(log);
        Matcher logCommunication = Pattern.compile("COMMUNICATION").matcher(log);
        Matcher logStrategy =Pattern.compile("STRATEGY").matcher(log);
/*
        int icrochet = log.substring(3).indexOf("]") ;
        String action = log.substring(3).substring(icrochet + 2 );
        //System.out.println("action = " + action);

 */

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
                //on stocke l'information de la couleur de la zone de départ
                couleurZone = RegexStrategy.regexStrategy(log);
            } else if (logCommunication.find()) {
                Matcher handleConfig = Pattern.compile("handleConfig").matcher(log);

                if (handleConfig.find()) {
                    RegexComNUC.regexComNuc(log, couleurZone);
                    //FenetreLog.addLogTextln("CommunicationNuc : " + action);
                } else{
                    RegexComBuddy.regexComBuddy(log);
                    //FenetreLog.addLogTextln("CommunicationBuddy : " + action);
                }
            }


        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

}


