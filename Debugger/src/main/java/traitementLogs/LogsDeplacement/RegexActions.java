package traitementLogs.LogsDeplacement;


import graphique.Robot;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexActions {

    private static final double defaultOrientation = 0.0;

    static public void regexActions(String log) throws Exception {
        Matcher turnTowards = Pattern.compile("Sent to LL: t").matcher(log);
        Matcher setPosition = Pattern.compile("setPositionAndOrientation").matcher(log);
        Matcher cxyo = Pattern.compile("!cxyo").matcher(log);
         if (turnTowards.find()) {
             turn(log);
             Robot.Turn(log);
         }
         if (setPosition.find() && cxyo.find()) {
             setPosition(log);
             Robot.SetPosition(log);
             Robot.SetOrientation(log);
         }
    }

    static public double turn (String log) {
            int iend = log.substring(111).indexOf("\u001B") ; // ! \\ ATTENTION: après la valeur de rotation, dans le log y a un caractère spécial (voir les logs sous gedit)
            String turn = log.substring(111).substring(0, iend);
            return Double.parseDouble(turn);
        }

    static public Map<Point, Double> setPosition(String log) {
        int iend = log.substring(130).indexOf("\u001B") ; // ! \\ ATTENTION: après la valeur de position, dans le log y a un caractère spécial (voir les logs sous gedit)
        int separationX = log.substring(130).indexOf(" ") ;
        int separationY = log.substring(130).substring(separationX+1).indexOf(" ") + separationX+1 ;
        int x = Integer.parseInt(log.substring(130).substring(0, separationX));
        int y = Integer.parseInt(log.substring(130).substring(separationX+1, separationY));
        Point positionSet = new Point();
        positionSet.x = x;
        positionSet.y = y;
        double orientation= Double.parseDouble(log.substring(130).substring(separationY+1, iend ));
        Map<Point, Double> xyo = new HashMap<>();
        xyo.put(positionSet,orientation);
        return  xyo;
    }

    static public Point getPositionSet(String log) throws Exception {
        Map<Point, Double> xyo = setPosition(log);
        Point position = new Point();
        if (xyo.size()>1) {
            throw new Exception("Plusieurs positions de départs");
        }
        else if (xyo.size() == 0) {
            throw new Exception("Aucune posistion de départ entrée");
        }
        else {
            for (Point key : xyo.keySet()) {
                    position = key;
            }
        }
        return position;
    }

    static public double getOrientationSet(String log) throws Exception {
        Map<Point, Double> xyo = setPosition(log);
        double orientation = defaultOrientation; //Valeur d'orientation par défault
        if (xyo.size()>1) {
            throw new Exception("Plusieurs positions de départs");
        }
        else if (xyo.size() == 0) {
            throw new Exception("Aucune posistion de départ entrée");
        }
        else {
            for (double value : xyo.values()) {
                orientation = value;
            }
        }
        return orientation;
    }



}
