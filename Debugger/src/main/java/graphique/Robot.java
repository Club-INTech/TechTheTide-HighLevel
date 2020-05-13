package graphique;

import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    static void go(TableVisualisation robot) {

        for (int i = 31; i < robot.getWidth(); i++) {
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

