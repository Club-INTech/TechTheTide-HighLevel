package graphique;

import data.table.MobileCircularObstacle;
import data.table.Obstacle;
import simulator.IRobot;
import utils.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/* @author AznekEnimsay
* Last modification 01/03/2020
* */

public class Interface extends JFrame {

    // Pour l'affichage de la table
    private BufferedImage tableImage;
    private ArrayList<Obstacle> fixedObstacles;
    private ArrayList<Obstacle> temporaryObstacles;
    private ConcurrentLinkedQueue<MobileCircularObstacle> mobileObstacles;
    private JPanel panel;
    private final Font font;
    private static final float ASPECT_RATIO = 16f/9f;
    private int TABLE_PIXEL_WIDTH = 980;      //in pixels
    private int STACKS_PIXEL_WIDTH = 300;
    private int TOTAL_PIXEL_WIDTH = this.TABLE_PIXEL_WIDTH+this.STACKS_PIXEL_WIDTH;
    private int TABLE_PIXEL_HEIGHT = (int) ((TABLE_PIXEL_WIDTH+STACKS_PIXEL_WIDTH)/ASPECT_RATIO);      //in pixels

    // Pour le dessin des robots

    private Color ROBOT_COLOR_MASTER = new Color(255,255,0,255); // Couleur par défaut
    private Color ROBOT_COLOR_SLAVE = new Color(0,0,255,255);
    private Color ORIENTATION_COLOR = new Color(0,0,0,255);
    private Color ENNEMI_COLOR = new Color(255, 0, 0, 255);


/*********************************************CONSTRUCTION GRAPHIQUE***************************************************/
    Interface() {

        fixedObstacles = new ArrayList<>();
        temporaryObstacles = new ArrayList<>();
        mobileObstacles = new ConcurrentLinkedQueue<>();

        try {
            tableImage = ImageIO.read(getClass().getResourceAsStream("/Table2020.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTitle("DEBUGGER");
        this.font = new Font("Consolas", Font.PLAIN, 15);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Un JPanel est un conteneur élémentaire destiné à contenir d'autres composants.
         * Il est muni d'un gestionnaire de placement qui gère la stratégie de placement des différents composants
         * contenus dans le panneau.
         **/
        this.panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                graphics.setFont(font);
              //  updateGraphics(graphics);
                Toolkit.getDefaultToolkit().sync();
            }
        };
        this.panel.setDoubleBuffered(true);
        this.panel.setVisible(true);
        this.panel.setPreferredSize(new Dimension(TOTAL_PIXEL_WIDTH, this.TABLE_PIXEL_HEIGHT));
        this.getContentPane().add(this.panel);
        this.setVisible(true);
        this.pack();

    }

    /** Affiche le background */
    private void drawBackground(Graphics g){
        g.drawImage(this.tableImage,0,0, this.TABLE_PIXEL_WIDTH, this.TABLE_PIXEL_HEIGHT, null);
    }


    /************************************* DESSIN D'UN ROBOT **********************************************************/

    private void drawRobot(IRobot simulatedRobot, Graphics g, int x, int y, double orientation, int diameter, int index, String robot){
        Color color = ROBOT_COLOR_MASTER;
        try {
            if (robot.equals("master")) {
                color = ROBOT_COLOR_MASTER;
            } else if (robot == "slave") {
                color = ROBOT_COLOR_SLAVE;
            }
            Log.DEBUGGER.debug("Mise en place du robot" + robot);
        } catch (Exception e) {
            Log.DEBUGGER.warning("Le robot n'existe pas" + robot);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            e.printStackTrace();
        }
        g.setColor(color);
        g.fillOval(x-diameter/2,y-diameter/2, diameter,diameter);
        g.setColor(ORIENTATION_COLOR);
        g.drawLine(x, y, Math.round(x+(float)Math.cos(orientation)*diameter), Math.round(y-(float)Math.sin(orientation)*diameter));

    }

    // Manque les obstacles fixes et mobiles et la fonction pour lancer l'affichage du debuger

}
