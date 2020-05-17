package traitementLogs.LogsLIDAR;
import LogRegex.*;
import graphique.*;
import java.util.regex.*;

public class RegexLidar {



    static public void regexLidar (String log) {

        Matcher obstacle = Pattern.compile("Obstacle mobile ajouté").matcher(log);

        if (obstacle.find()){
            String pos = log.substring(170);
            int posXend = pos.indexOf(",");
            int posX = Integer.parseInt(pos.substring(0,posXend));

            pos = pos.substring(posXend + 1);
            int posYend = pos.indexOf(")");
            int posY = Integer.parseInt(pos.substring(0,posYend));
            System.out.println(posX);
            System.out.println(posY);
        }
    }

}
