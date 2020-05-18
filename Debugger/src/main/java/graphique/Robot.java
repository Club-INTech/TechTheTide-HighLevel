package graphique;

import LogRegex.Regex;
import traitementLogs.LogsDeplacement.RegexActions;
import traitementLogs.LogsCommunications.ComNuc.RegexComNuc;

import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    private static final int CoinHautGaucheX = 43;
    private static final int CoinHautGaucheY = 21;
    private static final int TABLE_PIXEL_WIDTH = 982; //en pixels
    private static final int TABLE_PIXEL_HEIGHT = 690;//en pixels
    private static final int TABLEGAME_PIXEL_WIDTH = 893; // largeur de la table de jeu en pixels
    private static final int TABLEGAME_PIXEL_HEIGHT = 573; //hauteur de la table de jeu en pixels
    private static final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private static final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre
    private static final int GobeletRay = 27; // rayon d'un gobelet en  millimetre

    private static final int PrincipalWidth = 350;
    private static final int PrincipalHeigh = 220;

    static void go(TableVisualisation robot) {
        for (int i = 0; i < 100000000; i++) {
            int x = robot.getPosX(), y = robot.getPosY();
            x++;
            y++;
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

    static void SetPosition (TableVisualisation robot, String log) throws Exception {
        Point PositionTableSet = RegexActions.getPositionSet(log);
       Point PositionSet = LLtransformTableCoordonateToInterfaceCoordonate(PositionTableSet);
       robot.setPosX(PositionSet.x);
       robot.setPosY(PositionSet.y);
        System.out.println(PositionTableSet.y);
       System.out.println(robot.getPosX() + " " + robot.getPosY());
    }

    static float transformTableDistanceToInterfaceDistance(float distanceOnTable) {
        return distanceOnTable * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME);

    }


    public static void SetEceuilCommun (TableVisualisation robot, String compo,String couleurZone) {
        try{
            if (couleurZone == "jaune") {
                try{
                    if (compo == "RVRVV") {
                        System.out.println("RVRVV");
                        //robot.RVRVV_J();
                    } else if (compo == "RVVRV") {
                        System.out.println("RVVRV");
                        //robot.RVVRV_J();
                    } else if (compo == "RRVVV") {
                        System.out.println("RRVVV");
                        //robot.RRVVV_J();
                    } else if (compo == "VRRVR") {
                        System.out.println("VRRVR");
                        //robot.VRRVR_J();
                    } else if (compo == "VRVRR") {
                        System.out.println("VRVRR");
                        //robot.VRVRR_J();
                    } else if (compo == "VVRRR") {
                        System.out.println("VVRRR");
                        //robot.VVRRR_J();
                    }
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }else if (couleurZone == "bleue"){
                try{
                    if (compo == "RVRVV") {
                        System.out.println("RVRVV");
                        //robot.RVRVV_B();
                    } else if (compo == "RVVRV") {
                        System.out.println("RVVRV");
                        //robot.RVVRV_B();
                    } else if (compo == "RRVVV") {
                        System.out.println("RRVVV");
                        //robot.RRVVV_B();
                    } else if (compo == "VRRVR") {
                        System.out.println("VRRVR");
                        //robot.VRRVR_B();
                    } else if (compo == "VRVRR") {
                        System.out.println("VRVRR");
                        //robot.VRVRR_B();
                    } else if (compo == "VVRRR") {
                        System.out.println("VVRRR");
                        //robot.VVRRR_B();
                    }
                }catch(Exception e){
                    System.out.println("erreur Configuration"+ e.getMessage());
                }
            }
        }catch(Exception e){
            System.out.println("erreur Zonecote"+ e.getMessage());
        }
    }


     static Point LLtransformTableCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x  = (int) ((WIDTH_TABLEGAME/2 + point.x) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME));
        newPoint.y = (int) ((HEIGHT_TABLEGAME - point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME));
        return newPoint;
    }

}

