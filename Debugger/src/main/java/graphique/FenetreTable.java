package graphique;

import javax.swing.*;
import java.awt.*;

public class FenetreTable extends JFrame{

    //TODO: voir pourquoi le chemin entier est necessaire

    String FileImageRobot = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/bouee_SailTheWorld.png";
    private TableVisualisation robot = new TableVisualisation();

    public FenetreTable() {
        setTitle("Debugger : Table de Jeu");
        setSize(655, 460);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mise en place de la table de jeu
        setContentPane(robot);
        setVisible(true);
        Robot.go(robot);

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);

    }


    }

