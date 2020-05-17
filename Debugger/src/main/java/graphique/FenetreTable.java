package graphique;

import LogRegex.Regex;

import javax.swing.*;
import java.awt.*;

public class FenetreTable extends JFrame{

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";
    public static TableVisualisation robot = new TableVisualisation();
    String logfile = "logs/Sat Mar 07 23_46_45 CET 2020 #304.log";

    public FenetreTable() throws Exception {
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
        Regex.regex(logfile);
       //Robot.go(robot);


    }

}

