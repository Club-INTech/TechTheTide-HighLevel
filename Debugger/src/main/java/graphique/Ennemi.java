package graphique;

public class Ennemi {
    int posX;
    int posY;
    int timeLeft;

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

    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
    public int getTimeLeft(){
        return(timeLeft);
    }

    public Ennemi(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.timeLeft = 50;
    }
}
