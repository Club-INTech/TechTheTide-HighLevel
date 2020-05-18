package graphique;

import traitementLogs.LogsDeplacement.RegexActions;
import traitementLogs.LogsLIDAR.RegexLidar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Robot extends JPanel {

    private static final int TABLEGAME_PIXEL_WIDTH = 893; // largeur de la table de jeu en pixels
    private static final int TABLEGAME_PIXEL_HEIGHT = 573; //hauteur de la table de jeu en pixels
    private static final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private static final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre


    static TableVisualisation robot = FenetreTable.robot;


    static void go() {
        for (int i = 0; i < 10000; i++) {

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

    public static void SetPosition (String log) throws Exception {
        Point PositionTableSet = RegexActions.getPositionSet(log);
       Point PositionSet = LLtransformTableCoordonateToInterfaceCoordonate(PositionTableSet);
       robot.setPosX(PositionSet.x);
       robot.setPosY(PositionSet.y);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void SetOrientation(String log) throws Exception {
        double t = RegexActions.getOrientationSet(log);
        robot.setOrientation(t);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Turn (String log) {
        try {
            double t = - RegexActions.turn(log);
            double orientation = robot.getOrientation();
            if (t <= 0) {
                if (t >= orientation) {
                    while (t>orientation) {
                        orientation+=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                }
                else {
                    while (t<orientation) {
                        orientation -=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                    robot.setOrientation(t);
                    robot.repaint();
                }
            }
            else {
                if (t >= orientation) {
                    while (t>orientation) {
                        orientation+=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                }
                else {
                    while (t<orientation) {
                        orientation -=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                    robot.setOrientation(t);
                    robot.repaint();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void SetEceuilCommun (String compo ) {
        try{
            if (compo == "RVRVV"){
                System.out.println("RVRVV");
                //TableVisualisation.RVRVV();
            }
            else if (compo == "RVVRV"){
                System.out.println("RVVRV");
                //TableVisualisation.RVVRV();
            }
            else if (compo == "RRVVV"){
                System.out.println("RRVVV");
                //TableVisualisation.RRVVV();
            }
            else if (compo == "VRRVR"){
                System.out.println("VRRVR");
                //TableVisualisation.VRRVR();
            }
            else if (compo == "VRVRR"){
                System.out.println("VRVRR");
                //TableVisualisation.VRVRR();
            }
            else if(compo == "VVRRR"){
                System.out.println("VVRRR");
                //TableVisualisation.VVRRR();
            }

        }catch(Exception e){
            System.out.println("erreur composition impossible:"+ e.getMessage());
        }
    }


    static float transformTableDistanceToInterfaceDistance(float distanceOnTable) {
        return distanceOnTable * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME);

    }
     static Point LLtransformTableCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x  = (int) ((WIDTH_TABLEGAME/2 + point.x) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME));
        newPoint.y = (int) ((HEIGHT_TABLEGAME - point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME));
        return newPoint;
    }



}

