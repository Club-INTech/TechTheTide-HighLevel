package graphique;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TableVisualisation extends JPanel {

    int PrincipalWidth = 350;
    int PrincipalHeigh = 220;

    /* ============ Affichage de la table et des robots  ============= */


    //TODO: changer le chemin de l'image (ide qui comprend pas le chemin raccourci)

    String FileTableImage = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/tableComplete2020Fond.png";
    String FilePrincipalImage = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/PrincipalVuDessus.png";


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

    public void paintComponent(Graphics g) {

        /**DESSIN DE LA TABLE DE JEU**/


        try {
            Image img = ImageIO.read(new File(FileTableImage));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /** Visualisation des gobelets sur la table**/

        initGobeletsRouges();
        initGobeletsVerts();
        drawGobelets(g, GobeletsRouge, Color.RED);
        drawGobelets(g, GobeletsVert, Color.GREEN);


        /**VISUALISATION DE NOTRE ROBOT (celui qui joue) **/

        try {
            Image principal = ImageIO.read(new File(FilePrincipalImage));
            g.drawImage(principal, posX, posY, (int) (transformTableDistanceToInterfaceDistance(PrincipalWidth)), (int) (transformTableDistanceToInterfaceDistance(PrincipalHeigh)), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* ================================= Traitement des gobelets sur la table ======================================= */

    /**
     * La taille de l'image est 655x460
     * Le bord de la table en haut à gauche est aux coordonnées (29,14)
     * Le bord de la table en bas à gauche est aux coordonnées (29,411)
     * Le bord de la table en bas à droite est aux cordonnées (624,411)
     * Le bord de la table en haut à droite est aux coordonnées (624,14)
     * La surface de jeu fait 595x371
     * <p>
     * Une légende concernant la numérotation des gobelets est fournis dans le dossier ressources
     * (TableLegendeGobeletsNumerotation.png)
     **/

    private final int CoinHautGaucheX = 29;
    private final int CoinHautGaucheY = 14;
    private final int TABLE_PIXEL_WIDTH = 655; //en pixels
    private final int TABLE_PIXEL_HEIGHT = 460; //en pixels
    private final int TABLEGAME_PIXEL_WIDTH = 595; // largeur de la table de jeu en pixels
    private final int TABLEGAME_PIXEL_HEIGHT = 371; //hauteur de la table de jeu en pixels
    private final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre
    private final int GobeletRay = 53; // rayon d'un gobelets en  millimetre


    private final ArrayList<Point> GobeletsRouge = new ArrayList<>();
    private final ArrayList<Point> GobeletsVert = new ArrayList<>();

    public void addGobeletsRouges(Point gobelet) {
        synchronized (GobeletsRouge) {
            GobeletsRouge.add(gobelet);
        }
    }

    public void addGobeletsVerts(Point gobelet) {
        synchronized (GobeletsVert) {
            GobeletsVert.add(gobelet);
        }
    }


    public void drawCenteredCircle(Graphics g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval(x, y, r, r);
    }

    private void drawGobelets(Graphics g, ArrayList<Point> Gob, Color couleur) {
        for (int i = 0; i < Gob.size(); i++) {
            double x = (this.WIDTH_TABLEGAME - Gob.get(i).getX()) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME) + CoinHautGaucheX;
            double y = (-Gob.get(i).getY()) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME) + CoinHautGaucheY + TABLEGAME_PIXEL_HEIGHT;
            g.setColor(couleur);
            drawCenteredCircle(g, (int) x, (int) y, 2 * (int) transformTableDistanceToInterfaceDistance(GobeletRay));
        }

    }

    public void initGobeletsVerts() {


        Point Vert1 = new Point(300, 1200);
        addGobeletsVerts(Vert1);

        Point Vert2 = new Point(450, 510);
        addGobeletsVerts(Vert2);

        Point Vert3 = new Point(950, 400);
        addGobeletsVerts(Vert3);

        Point Vert4 = new Point(1065, 1650);
        addGobeletsVerts(Vert4);

        Point Vert5 = new Point(1270, 1200);
        addGobeletsVerts(Vert5);

        Point Vert6 = new Point(1395, 1955);
        addGobeletsVerts(Vert6);

        Point Vert7 = new Point(1665, 1650);
        addGobeletsVerts(Vert7);

        Point Vert8 = new Point(1900, 800);
        addGobeletsVerts(Vert8);

        Point Vert9 = new Point(1995, 1955);
        addGobeletsVerts(Vert9);

        Point Vert10 = new Point(2330, 100);
        addGobeletsVerts(Vert10);

        Point Vert11 = new Point(2550, 1080);
        addGobeletsVerts(Vert11);

        Point Vert12 = new Point(2700, 400);
        addGobeletsVerts(Vert12);
    }


    public void initGobeletsRouges() {

        Point Rouge1 = new Point(300, 400);
        addGobeletsRouges(Rouge1);

        Point Rouge2 = new Point(450, 1080);
        addGobeletsRouges(Rouge2);

        Point Rouge3 = new Point(670, 100);
        addGobeletsRouges(Rouge3);

        Point Rouge4 = new Point(1005, 1955);
        addGobeletsRouges(Rouge4);

        Point Rouge5 = new Point(1100, 800);
        addGobeletsRouges(Rouge5);

        Point Rouge6 = new Point(1335, 1650);
        addGobeletsRouges(Rouge6);

        Point Rouge7 = new Point(1605, 1955);
        addGobeletsRouges(Rouge7);

        Point Rouge8 = new Point(1730, 1200);
        addGobeletsRouges(Rouge8);

        Point Rouge9 = new Point(1935, 1650);
        addGobeletsRouges(Rouge9);

        Point Rouge10 = new Point(2050, 400);
        addGobeletsRouges(Rouge10);

        Point Rouge11 = new Point(2550, 510);
        addGobeletsRouges(Rouge11);

        Point Rouge12 = new Point(2700, 1200);
        addGobeletsRouges(Rouge12);
    }


    /* ============ Méthodes de transformation des distances entre la table et la fenêtre graphique ============= */

    private float transformTableDistanceToInterfaceDistance(float distanceOnTable) {
        return distanceOnTable * ((this.TABLEGAME_PIXEL_WIDTH - CoinHautGaucheY) / (float) this.WIDTH_TABLEGAME);

    }
}