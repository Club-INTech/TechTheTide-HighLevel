package graphique;

import LogRegex.Regex;

import javax.swing.*;
import java.awt.*;

public class FenetreTable extends JFrame{

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";
    public static TableVisualisation robot = new TableVisualisation();

//    String logfile = "logs/Sat May 04 11:47:54 CEST 2019 #28 - everything.log";

//    String logfile = "Debugger/src/main/java/LogsTests/testLogsComNuc1.log";


    public FenetreTable(Boolean principal, String logFile) throws Exception {
        setTitle("Debugger : Table de Jeu");
        setSize(982, 690);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);

        setContentPane(robot);
        setVisible(true);
        Regex.regex(logFile);


    }

}

