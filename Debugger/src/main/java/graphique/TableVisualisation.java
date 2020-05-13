package graphique;

import data.table.MobileCircularObstacle;
import data.table.Obstacle;
import data.table.StillCircularObstacle;
import utils.math.*;
import utils.math.Rectangle;
import utils.math.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TableVisualisation extends JPanel {

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

        /**VISUALISATION DE NOTRE ROBOT (celui qui joue) **/

        try {
            Image principal = ImageIO.read(new File(FilePrincipalImage));
            g.drawImage(principal, posX, posY, this.getWidth() / 4, this.getHeight() / 4, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Visualisation des gobelets sur la table**/

        initGobeletsRouges();
        initGobeletsVerts();
        drawRougeGobelets(g);
        drawVertGobelets(g);

    }

    /* ============ Traitement des gobelets sur la table ============= */


    /**
     * La taille de l'image est 802x538
     * Le bord de la table en haut à gauche est aux coordonnées (31,48)
     * Le bord de la table en bas à gauche est aux coordonnées (31,445)
     * Le bord de la table en bas à droite est aux cordonnées (625,445)
     * Le bord de la table en haut à droite est aux coordonnées (625,48)
     * La surface de jeu fait donc 594x397
     **/


    private final int TABLE_PIXEL_WIDTH = 594; //in pixels
    private final int TABLE_PIXEL_HEIGHT = 397; //in pixels
    private final int WIDTH_TABLE = 3000;      //in millimeters
    private final int HEIGHT_TABLE = 2000;     //in millimeters
    private final int GobeletRay = 53 ;


    private final  ArrayList<Obstacle> GobeletsRouge = new ArrayList<>() ;
    private final ArrayList<Obstacle> GobeletsVert = new ArrayList<>();

    public void addGobeletRouge(Obstacle obstacle) {
        synchronized (GobeletsRouge) {
            GobeletsRouge.add(obstacle);
        }
    }

    public void addGobeletVert(Obstacle obstacle) {
        synchronized (GobeletsVert) {
            GobeletsVert.add(obstacle);
        }
    }

    public void deleteGobeletRouge(Obstacle obstacle) {
        synchronized (GobeletsRouge) {
            if (GobeletsRouge.contains(obstacle)) {
                GobeletsRouge.remove(GobeletsRouge.indexOf(obstacle));
            }
        }
    }

    public void deleteGobeletVert(Obstacle obstacle) {
        synchronized (GobeletsVert) {
            if (GobeletsVert.contains(obstacle)) {
                GobeletsVert.remove(GobeletsVert.indexOf(obstacle));
            }
        }
    }

    private void drawRougeGobelets(Graphics g) {
        for (Obstacle obstacle : this.GobeletsRouge) {
            utils.math.Shape shape = obstacle.getShape();
            Vec2 centerOnTable = shape.getCenter();
            Vec2 center = transformTableCoordsToInterfaceCoords(centerOnTable);
            float diameter = transformTableDistanceToInterfaceDistance(((Circle) shape).getRadius() * 2);
            g.setColor(Color.RED);
            g.fillOval(center.getX() - Math.round(diameter / 2), center.getY() - Math.round(diameter / 2), Math.round(diameter), Math.round(diameter));
        }
    }

    private void drawVertGobelets(Graphics g) {
        for (Obstacle obstacle : this.GobeletsVert) {
            utils.math.Shape shape = obstacle.getShape();
            Vec2 centerOnTable = shape.getCenter();
            Vec2 center = transformTableCoordsToInterfaceCoords(centerOnTable);
            float diameter = transformTableDistanceToInterfaceDistance(((Circle) shape).getRadius() * 2);
            g.setColor(Color.GREEN);
            g.fillOval(center.getX() - Math.round(diameter / 2), center.getY() - Math.round(diameter / 2), Math.round(diameter), Math.round(diameter));
        }
    }


public void initGobeletsRouges() {

    Vec2 positionRouge1 = new VectCartesian(300, 1200);
    Circle formeRouge1 = new Circle(positionRouge1, GobeletRay);
    Obstacle rouge1 = new StillCircularObstacle(formeRouge1);
    this.addGobeletRouge(rouge1);

    Vec2 positionRouge2 = new VectCartesian(450, 510);
    Circle formeRouge2 = new Circle(positionRouge2, GobeletRay);
    Obstacle rouge2 = new StillCircularObstacle(formeRouge2);
    this.addGobeletRouge(rouge2);


    Vec2 positionRouge3 = new VectCartesian(950, 400);
    Circle formeRouge3 = new Circle(positionRouge3, GobeletRay);
    Obstacle rouge3 = new StillCircularObstacle(formeRouge3);
    this.addGobeletRouge(rouge3);


    Vec2 positionRouge4 = new VectCartesian(1065, 1650);
    Circle formeRouge4 = new Circle(positionRouge4, GobeletRay);
    Obstacle verre8 = new StillCircularObstacle(formeRouge4);
    this.addGobeletRouge(verre8);

    Vec2 positionRouge5 = new VectCartesian(1270, 1200);
    Circle formeRouge5 = new Circle(positionRouge5, GobeletRay);
    Obstacle rouge5 = new StillCircularObstacle(formeRouge5);
    this.addGobeletRouge(rouge5);

    Vec2 positionRouge6 = new VectCartesian(3000 - 1605, 1955);
    Circle formeRouge6 = new Circle(positionRouge6, GobeletRay);
    Obstacle rouge6 = new StillCircularObstacle(formeRouge6);
    this.addGobeletRouge(rouge6);

    Vec2 positionRouge7 = new VectCartesian(1665, 1650);
    Circle formerouge7 = new Circle(positionRouge7, GobeletRay );
    Obstacle rouge7 = new StillCircularObstacle(formerouge7);
    this.addGobeletRouge(rouge7);

    Vec2 positionRouge8 = new VectCartesian(1900, 800);
    Circle formeRouge8 = new Circle(positionRouge8, GobeletRay );
    Obstacle rouge8 = new StillCircularObstacle(formeRouge8);
    this.addGobeletRouge(rouge8);


    Vec2 positionRouge9 = new VectCartesian(1995, 1955);
    Circle formeRouge9 = new Circle(positionRouge9, GobeletRay );
    Obstacle rouge9 = new StillCircularObstacle(formeRouge9);
    this.addGobeletRouge(rouge9);


    Vec2 positionRouge10 = new VectCartesian(2330, 100);
    Circle formeRouge10 = new Circle(positionRouge10, GobeletRay );
    Obstacle rouge10 = new StillCircularObstacle(formeRouge10);
    this.addGobeletRouge(rouge10);


    Vec2 positionRouge11 = new VectCartesian(2550, 1080);
    Circle formeRouge11 = new Circle(positionRouge11, GobeletRay );
    Obstacle rouge11 = new StillCircularObstacle(formeRouge11);
    this.addGobeletRouge(rouge11);

    Vec2 positionRouge12 = new VectCartesian(2700, 400);
    Circle formerouge12 = new Circle(positionRouge12, GobeletRay );
    Obstacle rouge12 = new StillCircularObstacle(formerouge12);
    this.addGobeletRouge(rouge12);


}



    public void initGobeletsVerts() {

        Vec2 positionVert1 = new VectCartesian(300, 400);
        Circle formeVert1 = new Circle(positionVert1, GobeletRay);
        Obstacle vert1 = new StillCircularObstacle(formeVert1);
        this.addGobeletVert(vert1);

        Vec2 positionVert2 = new VectCartesian(450, 1080);
        Circle formeVert2 = new Circle(positionVert2, GobeletRay);
        Obstacle vert2 = new StillCircularObstacle(formeVert2);
        this.addGobeletVert(vert2);

        Vec2 positionVert3 = new VectCartesian(670, 100);
        Circle formeVert3 = new Circle(positionVert3, GobeletRay);
        Obstacle vert3 = new StillCircularObstacle(formeVert3);
        this.addGobeletVert(vert3);


        Vec2 positionVert4 = new VectCartesian(1005, 1955);
        Circle formeVert4 = new Circle(positionVert4, GobeletRay);
        Obstacle vert4 = new StillCircularObstacle(formeVert4);
        this.addGobeletVert(vert4);

        Vec2 positionVert5 = new VectCartesian(1100, 800);
        Circle formeVert5 = new Circle(positionVert5, GobeletRay);
        Obstacle vert5 = new StillCircularObstacle(formeVert5);
        this.addGobeletVert(vert5);


        Vec2 positionVert6 = new VectCartesian(3000 - 1665, 1650);
        Circle formeVert6 = new Circle(positionVert6, GobeletRay);
        Obstacle vert6 = new StillCircularObstacle(formeVert6);
        this.addGobeletVert(vert6);

        Vec2 positionVert7 = new VectCartesian(1605, 1955);
        Circle formeVert7 = new Circle(positionVert7, GobeletRay);
        Obstacle vert7 = new StillCircularObstacle(formeVert7);
        this.addGobeletVert(vert7);

        Vec2 positionVert8 = new VectCartesian(1730, 1200);
        Circle formeVert8 = new Circle(positionVert8, GobeletRay );
        Obstacle vert8 = new StillCircularObstacle(formeVert8);
        this.addGobeletVert(vert8);


        Vec2 positionVert9 = new VectCartesian(1935, 1650);
        Circle formeVert9 = new Circle(positionVert9, GobeletRay );
        Obstacle vert9 = new StillCircularObstacle(formeVert9);
        this.addGobeletVert(vert9);

        Vec2 positionVert10 = new VectCartesian(2050, 400);
        Circle formeVert10 = new Circle(positionVert10, GobeletRay );
        Obstacle vert10 = new StillCircularObstacle(formeVert10);
        this.addGobeletVert(vert10);

        Vec2 positionVert11 = new VectCartesian(2550, 510);
        Circle formeVert11 = new Circle(positionVert11, GobeletRay );
        Obstacle vert11 = new StillCircularObstacle(formeVert11);
        this.addGobeletVert(vert11);

        Vec2 positionVert12 = new VectCartesian(2700, 1200);
        Circle formeVert12 = new Circle(positionVert12, GobeletRay );
        Obstacle vert12 = new StillCircularObstacle(formeVert12);
        this.addGobeletVert(vert12);
    }


    /* ============ Méthodes de transformation des coordonnées entre la table et la fenêtre graphique ============= */

    /**
     * Transforme une distance de la table pour qu'elle soit affichée correction sur l'interface
     */
    private int transformTableDistanceToInterfaceDistance(int distanceOnTable) {
        return Math.round(distanceOnTable * (this.TABLE_PIXEL_WIDTH / (float) this.WIDTH_TABLE));
    }

    /**
     * Transforme une distance de la table pour qu'elle soit affichée correction sur l'interface
     */
    private float transformTableDistanceToInterfaceDistance(float distanceOnTable) {
        return distanceOnTable * (this.TABLE_PIXEL_WIDTH / (float) this.WIDTH_TABLE);
    }

    /**
     * Transforme les coordonnées de la table pour qu'ils soient affichés correction sur l'interface
     */
    private Vec2 transformTableCoordsToInterfaceCoords(int xOnTable, int yOnTable) {
        return new InternalVectCartesian(
                (xOnTable + (this.WIDTH_TABLE / 2.0f)) * (this.TABLE_PIXEL_WIDTH / (float) this.WIDTH_TABLE),
                (this.HEIGHT_TABLE - yOnTable) * (this.TABLE_PIXEL_HEIGHT / (float) this.HEIGHT_TABLE)
        );
    }

    /**
     * Transforme les coordonnées de la table pour qu'ils soient affichés correctement sur l'interface
     */
    private Vec2 transformTableCoordsToInterfaceCoords(Vec2 positionOnTable) {
        return transformTableCoordsToInterfaceCoords(positionOnTable.getX(), positionOnTable.getY());
    }


    /**
     * Transforme les coordonnées de l'interface pour qu'ils correspondent à ceux de la table
     */
    private Vec2 transformInterfaceCoordsToTableCoords(int xOnInterface, int yOnInterface) {
        return new InternalVectCartesian(
                (xOnInterface - (this.TABLE_PIXEL_WIDTH / 2.0f)) * (this.WIDTH_TABLE / (float) this.TABLE_PIXEL_WIDTH),
                (this.TABLE_PIXEL_HEIGHT - yOnInterface) * (this.HEIGHT_TABLE / (float) this.TABLE_PIXEL_HEIGHT)
        );
    }

    /**
     * Transforme les coordonnées de l'interface pour qu'ils correspondent à ceux de la table
     */
    private Vec2 transformInterfaceCoordsToTableCoords(Vec2 positionOnInterface) {
        return transformInterfaceCoordsToTableCoords(positionOnInterface.getX(), positionOnInterface.getY());
    }



}