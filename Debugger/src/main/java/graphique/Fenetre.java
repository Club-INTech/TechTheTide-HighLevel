package graphique;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    //TODO: voir pourquoi le chemin entier est necessaire

    String FileImageRobot = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/bouee_SailTheWorld.png";

    public Fenetre () {
        setTitle("Debugger");
        setSize(1600,800);
        setLocationRelativeTo(null);
        setResizable(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mise en place de la table de jeu
        setContentPane(new TableJeu());

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);


        setVisible(true);
    }

}
