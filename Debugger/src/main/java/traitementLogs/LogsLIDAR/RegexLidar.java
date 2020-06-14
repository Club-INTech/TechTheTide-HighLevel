package traitementLogs.LogsLIDAR;
import LogRegex.*;
import data.Table;
import graphique.*;

import java.awt.*;
import java.util.regex.*;

public class RegexLidar {



    static public void regexLidar (String log) {

        Matcher obstacle = Pattern.compile("Obstacle mobile ajouté : Obstacle mobile circulaire Circle").matcher(log);

        try {
            if (obstacle.find()) {
                String pos = log.substring(170);
                int posXend = pos.indexOf(",");
                int posX = Integer.parseInt(pos.substring(0, posXend));

                pos = pos.substring(posXend + 1);
                int posYend = pos.indexOf(")");
                int posY = Integer.parseInt(pos.substring(0, posYend));

                Ennemi.actualizeEnnemi(posX, posY);
                Ennemi.afficherEnnemi();
                FenetreLog.addLogTextln("ennemi détecté à la position ("+posX+","+posY+")");
            }

            else {
                String pos = log.substring(101);
                FenetreLog.addLogTextln(pos);
            }
        }
        catch(Exception e){
            System.out.println(log);

        }
    }

}
