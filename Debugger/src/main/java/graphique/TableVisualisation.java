package graphique;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TableVisualisation extends JPanel {


    /**
     * La taille de l'image est 893x573
     * Le bord de la table en haut à gauche est aux coordonnées (43,21)
     * La surface de jeu fait 982x690
     *
     * Une légende concernant la numérotation des gobelets est fournis dans le dossier ressources
     * (TableLegendeGobeletsNumerotation.png)
     **/

    private final int CoinHautGaucheX = 43;
    private final int CoinHautGaucheY = 21;
    private final int TABLE_PIXEL_WIDTH = 982; //en pixels
    private final int TABLE_PIXEL_HEIGHT = 690;//en pixels
    private final int TABLEGAME_PIXEL_WIDTH = 893; // largeur de la table de jeu en pixels
    private final int TABLEGAME_PIXEL_HEIGHT = 573; //hauteur de la table de jeu en pixels
    private final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre
    private final int GobeletRay = 27; // rayon d'un gobelet en  millimetre


    final int PrincipalWidth = 350;
    final int PrincipalHeigh = 220;

    /* ============ Affichage de la table et des robots  ============= */


    //TODO: changer le chemin de l'image (ide qui comprend pas le chemin raccourci)

    String FileTableImage = "Debugger/src/main/java/graphique/ressources/tableComplete2020Fond.png";
    String FilePrincipalImage = "Debugger/src/main/java/graphique/ressources/PrincipalVuDessus.png";
    String FileSecondaireImage = "Debugger/src/main/java/graphique/ressources/SecondaireVuDessus.png";

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
        drawGobelets(g, GobeletsRouge, Color.RED.darker());
        drawGobelets(g, GobeletsVert, Color.GREEN.darker().darker());


        /**VISUALISATION DE NOTRE ROBOT (celui qui joue) **/

        try {
            Image principal = ImageIO.read(new File(FilePrincipalImage));
            g.drawImage(principal, posX, posY, (int) (transformTableDistanceToInterfaceDistance(PrincipalHeigh)), (int) (transformTableDistanceToInterfaceDistance(PrincipalWidth)), this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* ================================= Traitement des gobelets sur la table ======================================= */


    private final ArrayList<Point> GobeletsRouge = new ArrayList<>();
    private final ArrayList<Point> GobeletsVert = new ArrayList<>();

    private void addGobeletsRouges(Point gobelet) {
        synchronized (GobeletsRouge) {
            GobeletsRouge.add(gobelet);
        }
    }

    private void addGobeletsVerts(Point gobelet) {
        synchronized (GobeletsVert) {
            GobeletsVert.add(gobelet);
        }
    }


    private void drawCenteredCircle(Graphics g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.fillOval(x, y, r, r);
    }

    private void drawGobelets(Graphics g, ArrayList<Point> Gob, Color couleur) {
        for (int i = 0; i < Gob.size(); i++) {
            Point GobCenter = transformTableCoordonateToInterfaceCoordonate(Gob.get(i));
            g.setColor(couleur);
            drawCenteredCircle(g, GobCenter.x, GobCenter.y, 2 * (int) transformTableDistanceToInterfaceDistance(GobeletRay));
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

        //Les gobelets dans les éceuils sont numérotés de haut en bas

        Point EcueilJauneVert1 = new Point(3067,1750);
        addGobeletsVerts(EcueilJauneVert1);

        Point EcueilJauneVert2 = new Point (3067,1600);
        addGobeletsVerts(EcueilJauneVert2);

        Point EcueilJauneVert3 = new Point (3067, 1450);
        addGobeletsVerts(EcueilJauneVert3);

        Point EceuilBleuVert1 = new Point (-67, 1675);
        addGobeletsVerts(EceuilBleuVert1);

        Point EceuilBleuVert2 = new  Point (-67, 1525);
        addGobeletsVerts(EceuilBleuVert2);
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

        //Les gobelets dans les éceuils sont numérotés de haut en bas

        Point EcueilJauneRouge1 = new Point(3067,1675);
        addGobeletsRouges(EcueilJauneRouge1);

        Point EcueilJauneRouge2 = new Point (3067,1525);
        addGobeletsRouges(EcueilJauneRouge2);

        Point EceuilBleuRouge1 = new Point (-67, 1750);
        addGobeletsRouges(EceuilBleuRouge1);

        Point EceuilBleuRouge2 = new  Point (-67, 1600);
        addGobeletsRouges(EceuilBleuRouge2);

        Point EcueilBleuRouge3 = new Point (-67, 1450);
        addGobeletsRouges(EcueilBleuRouge3);
    }


    /* ============ Méthodes de transformation des distances entre la table et la fenêtre graphique ============= */

    private float transformTableDistanceToInterfaceDistance(float distanceOnTable) {
        return distanceOnTable * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME);

    }

    private Point transformTableCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x  = (int) ((WIDTH_TABLEGAME - point.x) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME) + CoinHautGaucheX);
        newPoint.y = (int) ((-point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME) + CoinHautGaucheY + TABLEGAME_PIXEL_HEIGHT);
        return newPoint;
    }



}