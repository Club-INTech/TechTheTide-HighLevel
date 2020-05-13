package traitementLogs.LogsLIDAR;
import LogRegex.*;
import graphique.*;
import java.util.regex.*;

public class RegexLidar {

    static public void regexLidar (String log, FenetreTable debug) {
        int i;
        String center = "center: ";
        Pattern logcenter = Pattern.compile(center);
        Matcher mcenter = logcenter.matcher(log);
        String virgule = ",";
        Pattern logvirgule = Pattern.compile(virgule);
        Matcher mvirgule = logvirgule.matcher(log);

        String a = "(/d*,/d*)";

        if(mcenter.find()) {

            String[] s1 = logcenter.split(log);
            String s2 = s1[1];

            String[] s3 = logvirgule.split(s2);
            System.out.println(s3[0]);
            System.out.println(s3[1]);

            String PosX = new String();
            for (i = 1; i < s3[0].length(); i++) {
                PosX += s3[0].charAt(i);
            }
            int posX = Integer.parseInt(PosX);

            String PosY = new String();
            for (i = 0; i < s3[1].length() - 1; i++) {
                PosY += s3[1].charAt(i);
            }
            int posY = Integer.parseInt(PosY);

            System.out.println(posX);
            System.out.println(posY);

            Ennemi en = new Ennemi(posX, posY);
            debug.robot.addEnnemi(en);
        }

//        debug.robot.addEnnemi();
        debug.robot.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
