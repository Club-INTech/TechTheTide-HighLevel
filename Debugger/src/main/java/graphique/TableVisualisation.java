package graphique;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.Double;

public class TableVisualisation extends JPanel {

    //TODO: changer le chemin de l'image (ide qui comprend pas le chemin raccourci)

    String FileTableImage = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/tableComplete2020Fond.png";

    //Mise à jour de la position du robot

    private int posX = 31;
    private int posY = 445;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void paintComponent(Graphics g){

        /**DESSIN DE LA TABLE DE JEU**/

        /**La taille de l'image est 802x538
         Le bord de la table en haut à gauche est aux coordonnées (31,48)
         Le bord de la table en bas à gauche est aux coordonnées (31,445)
         Le bord de la table en bas à droite est aux cordonnées (625,445)
         Le bord de la table en haut à droite est aux coordonnées (625,48)
         La surface de jeu fait donc 594x397**/

        try {
            Image img = ImageIO.read(new File(FileTableImage));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**VISUALISATION DE NOTRE ROBOT (celui qui joue) **/

        g.setColor(Color.yellow);
        g.fillOval(posX,posY,60,60);

        /**Affichage des gobelets rouges**/



        /**Affichage des gobelets verts**/




      /**  Font font = new Font("Liberation", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.blue);
        g.drawString("Logs robot", 3*getWidth()/4, getHeight()/10); **/
    }
}
