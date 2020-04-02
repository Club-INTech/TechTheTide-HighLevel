package graphique;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TableJeu extends JPanel {

    //TODO: changer le chemin de l'image (ide qui comprend pas le chemin raccourci)

    String FileTableImage = "/home/yasmine/TechTheTide-HighLevel/Debugger/src/main/java/graphique/ressources/tableComplete2020.png";

    public void paintComponent(Graphics g){
        try {
            Image img = ImageIO.read(new File(FileTableImage));
            g.drawImage(img, 0, 0, 802, 538, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font font = new Font("Liberation", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.blue);
        g.drawString("Logs robot", 3*getWidth()/4, getHeight()/10);
    }
}
