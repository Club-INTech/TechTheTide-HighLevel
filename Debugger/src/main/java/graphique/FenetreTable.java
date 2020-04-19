package graphique;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    //TODO: voir pourquoi le chemin entier est necessaire

    String FileImageRobot = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/bouee_SailTheWorld.png";
    private TableJeu robot = new TableJeu();

    public Fenetre () {
        setTitle("Debugger : Table de Jeu");
        setSize(802, 538);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mise en place de la table de jeu
        setContentPane(robot);
        setVisible(true);
        go();

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);

    }

        private void go() {
            for(int i = 31; i < robot.getWidth(); i++){
                int x = robot.getPosX(), y = robot.getPosY();
                x++;
                y--;
                robot.setPosX(x);
                robot.setPosY(y);
                robot.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }


}
