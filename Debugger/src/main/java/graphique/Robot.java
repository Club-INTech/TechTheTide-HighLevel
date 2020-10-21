package graphique;

import traitementLogs.LogsDeplacement.RegexActions;
import traitementLogs.LogsDeplacement.RegexDeplacement;
import utils.math.Vec2;
import java.lang.Thread;

import javax.swing.*;
import java.awt.*;

public class Robot extends JPanel {

    private static final int CoinHautGaucheX = 43;
    private static final int CoinHautGaucheY = 21;
    private static final int TABLEGAME_PIXEL_WIDTH = 893; // largeur de la table de jeu en pixels
    private static final int TABLEGAME_PIXEL_HEIGHT = 573; //hauteur de la table de jeu en pixels
    private static final int WIDTH_TABLEGAME = 3000;      // vrai largeur de la table en millimetre
    private static final int HEIGHT_TABLEGAME = 2000;     // vrai hauteur de la table en millimetre


    static TableVisualisation robot = FenetreTable.robot;

    /* =================================== Affichage du robot jouant ========================================= */

    public static void SetPosition(String log) throws Exception {
        //activation de la visualisation sur robot jouant dans la fenêtre
        robot.etatPrincipal = true;
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


    public static void Move(String log) {
        int d = RegexDeplacement.MoveLengthWiseFonction(log);
        FenetreLog.addLogTextln("Robot move : " + d);
        int distanceInterface = (int) transformTableDistanceToInterfaceDistance(d);
        double theta = robot.getOrientation();
        double tx = Math.cos(theta) * distanceInterface;
        double ty = Math.sin(theta) * distanceInterface;
        float x = robot.getPosX();
        float y = robot.getPosY();
        int xfinale = (int) (robot.getPosX() + tx);
        int yfinale = (int) (robot.getPosY() + ty);
        float distanceParcourue = 0;
        while (distanceParcourue <= distanceInterface) {
            distanceParcourue += distanceInterface * 0.01;
            x += tx / 100;
            y += ty / 100;
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


    public static void Turn(String log) {
        try {
            double theta = -RegexActions.turn(log);
            FenetreLog.addLogTextln("Robot turn : " + theta);
            double orientation = robot.getOrientation();
            if (theta <= 0) {
                if (theta >= orientation) {
                    while (theta > orientation) {
                        orientation += 0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                } else {
                    while (theta < orientation) {
                        orientation -= 0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                    robot.setOrientation(theta);
                    robot.repaint();
                }
            } else {
                if (theta >= orientation) {
                    while (theta > orientation) {
                        orientation += 0.01;
                        robot.setOrientation(orientation);
                        TableVisualisation.RobotPrincipal = robot.rotate(TableVisualisation.Principal, orientation);
                        robot.repaint();
                        Thread.sleep(10);
                    }
                } else {
                    while (theta < orientation) {
                        orientation -= 0.01;
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



    /* ================================= Affichage de l'ami sur la table ======================================= */

    public static void SetPositionEtOrientationAmi(int posX, int posY, double posO) {
        //activation de la visualisation sur robot ami dans la fenêtre
        robot.etatSecondaire = true;
        //changement de position
        Point PositionAmi = new Point();
        PositionAmi.x = posX;
        PositionAmi.y = posY;
        PositionAmi = transformLidarCoordonateToInterfaceCoordonate(PositionAmi);
        robot.setSPosX(PositionAmi.x);
        robot.setSPosY(PositionAmi.y);
        robot.setOrientationS(posO);
        robot.repaint();
        //changement d'orientation
        TableVisualisation.RobotSecondaire = robot.rotate(TableVisualisation.Secondaire, posO);
        robot.setOrientation(posO);
        robot.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* =============================== Actualise la configuration des écueils communs ============================== */
    //TODO: mettre des cases+mettre (-67)+ cas où on a pas la positon de départ
    public static void SetEceuilCommun(String compo, String couleurZone) {
        try {
            if (couleurZone.equals("jaune")) {
                try {
                    if (compo.equals("RVRVV")) {
                        //System.out.println("RVRVV");
                        robot.RVRVV_J();
                    } else if (compo.equals("RVVRV")) {
                        //System.out.println("RVVRV");
                        robot.RVVRV_J();
                    } else if (compo.equals("RRVVV")) {
                        //System.out.println("RRVVV");
                        robot.RRVVV_J();
                    } else if (compo.equals("VRRVR")) {
                        //System.out.println("VRRVR");
                        robot.VRRVR_J();
                    } else if (compo.equals("VRVRR")) {
                        //System.out.println("VRVRR");
                        robot.VRVRR_J();
                    } else if (compo.equals("VVRRR")) {
                        //System.out.println("VVRRR");
                        robot.VVRRR_J();
                    }
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else if (couleurZone.equals("bleue")) {
                try {
                    if (compo.equals("RVRVV")) {
                        //System.out.println("RVRVV");
                        robot.RVRVV_B();
                    } else if (compo.equals("RVVRV")) {
                        //System.out.println("RVVRV");
                        robot.RVVRV_B();
                    } else if (compo.equals("RRVVV")) {
                        //System.out.println("RRVVV");
                        robot.RRVVV_B();
                    } else if (compo.equals("VRRVR")) {
                        //System.out.println("VRRVR");
                        robot.VRRVR_B();
                    } else if (compo.equals("VRVRR")) {
                        //System.out.println("VRVRR");
                        robot.VRVRR_B();
                    } else if (compo.equals("VVRRR")) {
                        //System.out.println("VVRRR");
                        robot.VVRRR_B();
                    }
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("erreur Configuration00" + e.getMessage());
        }
        robot.repaint();
    }


    static double transformTableDistanceToInterfaceDistance(double distanceOnTable) {
        return distanceOnTable * (TABLEGAME_PIXEL_WIDTH / (double) WIDTH_TABLEGAME);

    }

    static Point LLtransformTableCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x = (int) ((WIDTH_TABLEGAME / 2 + point.x) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME));
        newPoint.y = (int) ((HEIGHT_TABLEGAME - point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME));
        return newPoint;
    }



    static Point transformLidarCoordonateToInterfaceCoordonate(Point point) {
        Point newPoint = new Point();
        newPoint.x = (int) ((point.x + WIDTH_TABLEGAME / 2) * (TABLEGAME_PIXEL_WIDTH / (float) WIDTH_TABLEGAME) + CoinHautGaucheX);
        newPoint.y = (int) ((HEIGHT_TABLEGAME / 2 - point.y) * ((TABLEGAME_PIXEL_HEIGHT) / (float) HEIGHT_TABLEGAME) + CoinHautGaucheY);
        return newPoint;
    }


    //=============Phare et Manche position en fonction couleur zone table==================
    public static void SetPhareManche(String couleurZone) {
        try {
            if (couleurZone.equals("jaune")) {
                try {
                    robot.PhareJaune();
                    robot.MancheJaune();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else if (couleurZone.equals("bleue")) {
                try {
                    robot.PhareBleu();
                    robot.MancheBleu();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("erreur Configuration00" + e.getMessage());
        }
        robot.repaint();
    }

    //========Phare changement couleur activé======================
    public static void PhareCouleur(String phareActive) {
        try {
            if (phareActive.equals("oui")) {
                try {
                    Thread.sleep(1000);
                    robot.PhareAllume();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else {
                try {
                    Thread.sleep(1000);
                    robot.PhareEteint();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("erreur Configuration00" + e.getMessage());
        }
        robot.repaint();
    }

    //========Manche changement couleur activé======================
    public static void MancheCouleur(String mancheActive1, String mancheActive2) {
        try {
            if (mancheActive1.equals("oui") && mancheActive2.equals("oui")) {
                try {
                    Thread.sleep(1000);
                    robot.MancheAA();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else if (mancheActive1.equals("oui") && mancheActive2.equals("non")) {
                try {
                    Thread.sleep(1000);
                    robot.MancheAD();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else if (mancheActive1.equals("non") && mancheActive2.equals("oui")) {
                try {
                    Thread.sleep(1000);
                    robot.MancheDA();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            } else {
                try {
                    Thread.sleep(1000);
                    robot.MancheDD();
                } catch (Exception e) {
                    System.out.println("erreur Configuration" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("erreur Configuration00" + e.getMessage());
        }
        robot.repaint();
    }
}
