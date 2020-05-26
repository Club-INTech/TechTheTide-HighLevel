package graphique;
import java.awt.*;

public class Ennemi {
    private int posX;
    private int posY;
    private boolean affichageEnnemi;

    static TableVisualisation robot = FenetreTable.robot;

    public int getPosX(){
        return(posX);
    }
    public int getPosY(){
        return(posY);
    }

    public void setPosX(int posX){
        this.posX = posX;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setAffichageEnnemi(Boolean affichageEnnemi){
        this.affichageEnnemi = affichageEnnemi;
    }
    public Boolean getAffichageEnnemi(){
        return(affichageEnnemi);
    }

    public Ennemi(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.affichageEnnemi = false;
    }

    public Ennemi(){
        this.posX = 0;
        this.posY = 0;
        this.affichageEnnemi = false;
    }


    public static void actualizeEnnemi(int posX, int posY){
        TableVisualisation robot = FenetreTable.robot;
        if (robot == null) {
            throw new IllegalArgumentException("La variable Objet ne doit pas être null");
        }
        robot.setEnnemiPos(posX, posY);
    }


    public static void afficherEnnemi(){
        TableVisualisation robot = FenetreTable.robot;
        robot.setAffichageEnnemi(true);
        robot.repaint();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.setAffichageEnnemi(false);
//        robot.repaint();
    }

}
