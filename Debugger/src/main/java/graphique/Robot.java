package graphique;

import traitementLogs.LogsDeplacement.RegexActions;
import traitementLogs.LogsDeplacement.RegexDeplacement;


import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    private static final int TABLEGAME_PIXEL_WIDTH = 893; // largeur de la table de jeu en pixels
    private static final int TABLEGAME_PIXEL_HEIGHT = 573; //hauteur de la table de jeu en pixels
    private static final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private static final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre


    static TableVisualisation robot = FenetreTable.robot;


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
        double theta = RegexActions.getOrientationSet(log);
        robot.setOrientation(theta);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Move(String log){
        int d = RegexDeplacement.MoveLengthWiseFonction(log);
        int distanceInterface = (int) transformTableDistanceToInterfaceDistance(d);
        double theta = robot.getOrientation();
        double tx =Math.cos(theta) * distanceInterface;
        double ty =Math.sin(theta)*distanceInterface;
        float x = robot.getPosX();
        float y = robot.getPosY();
        int xfinale = (int) (robot.getPosX() + tx);
        int yfinale = (int) (robot.getPosY() + ty);
        float distanceParcourue = 0;
        while (distanceParcourue <= distanceInterface) {
            distanceParcourue+=distanceInterface*0.01;
            x+=tx/100;
            y+=ty/100;
            robot.setPosX((int) x);
            robot.setPosY((int) y);
            robot.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        robot.setPosY(yfinale);
        robot.setPosX(xfinale);
    }



    public static void Turn (String log) {
        try {
            double theta = - RegexActions.turn(log);
            double orientation = robot.getOrientation();
            if (theta <= 0) {
                if (theta >= orientation) {
                    while (theta>orientation) {
                        orientation+=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                }
                else {
                    while (theta<orientation) {
                        orientation -=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                    robot.setOrientation(theta);
                    robot.repaint();
                }
            }
            else {
                if (theta >= orientation) {
                    while (theta>orientation) {
                        orientation+=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                }
                else {
                    while (theta<orientation) {
                        orientation -=0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                    robot.setOrientation(theta);
                    robot.repaint();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void SetEceuilCommun (TableVisualisation robot, String compo,String couleurZone) {
        try {
            if (couleurZone == "jaune") {
                try {
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
            } else if (couleurZone == "bleue") {
                try {
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
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("erreur Configuration" + e.getMessage());
        }
    }


    static double transformTableDistanceToInterfaceDistance(double distanceOnTable) {
        return distanceOnTable * (TABLEGAME_PIXEL_WIDTH / (double) WIDTH_TABLEGAME);

    }

    static Point LLtransformTableCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x  = (int) ((WIDTH_TABLEGAME/2 + point.x) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME));
        newPoint.y = (int) ((HEIGHT_TABLEGAME - point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME));
        return newPoint;
    }



}
