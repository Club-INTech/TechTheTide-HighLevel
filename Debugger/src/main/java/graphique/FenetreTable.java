package graphique;

import LogRegex.Regex;
import data.Table;

import javax.swing.*;
import java.awt.*;

public class FenetreTable extends JFrame{

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";
    public static TableVisualisation robot = new TableVisualisation();

    private Dimension dimensionsEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public final int TABLE_PIXEL_WIDTH = 982; //en pixels
    public final int TABLE_PIXEL_HEIGHT = 690; //en pixels
    public final int LOG_PIXEL_WIDTH = 500; //largeur de la fenètre de log en pixels
    public final int LOG_PIXEL_HEIGHT = TABLE_PIXEL_HEIGHT; //hauteur de la fenètre de log en pixels


//    String logfile = "Debugger/src/main/java/LogsTests/testLogsComNuc1.log";
//    String logfile = "Debugger/src/main/java/LogsTests/testLogsLidar.log";

    public FenetreTable(Boolean principal, String logFile) throws Exception {
        setTitle("Debugger : Table de Jeu");
        setSize(TABLE_PIXEL_WIDTH, TABLE_PIXEL_HEIGHT);
//        setLocationRelativeTo(null);
        setLocation((dimensionsEcran.width-TABLE_PIXEL_WIDTH-LOG_PIXEL_WIDTH)/2, (dimensionsEcran.height-TABLE_PIXEL_HEIGHT)/2);
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

