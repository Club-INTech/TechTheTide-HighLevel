package data.recalage;

import data.Sick;
import data.XYO;
import utils.math.InternalVectCartesian;

import java.util.function.Function;

public enum Recalage {

        //TODO Corriger les calculs pour master (double -> int ; 3000 -> 1500 ; val absolu etc etc)

   MASTER__BAS_GAUCHE_0((Integer) -> {

       int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ; //TODO : DEMANDER A LA MECA
       double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
       double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
       double YSick = Sick.SICK_DROIT_PRINCIPAL.getLastMeasure();
       double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
       double alpha = Math.atan(rate);
       double yValue = (Sick.SICK_DROIT_PRINCIPAL.getyRelativeRobot() + YSick) * Math.cos(alpha);
       double xValue = (XSickArriereGauche  + Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot())* Math.cos(alpha);
       return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
   }),

    MASTER_HAUT_GAUCHE_0 ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_GAUCHE_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = 2000 - (YSick + Sick.SICK_GAUCHE_PRINCIPAL.getyRelativeRobot())* Math.cos(alpha);
        double xValue = (XSickArriereGauche + Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot())* Math.cos(alpha);
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
    }),

    MASTER_BAS_DROITE_PI ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_GAUCHE_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = (YSick + Sick.SICK_GAUCHE_PRINCIPAL.getyRelativeRobot())* Math.cos(alpha);
        double xValue = 3000 -  (XSickArriereGauche  + Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot())* Math.cos(alpha);
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha);

    }),

    MASTER_HAUT_DROITE_PI ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_DROIT_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = 2000 - (YSick + Sick.SICK_DROIT_PRINCIPAL.getyRelativeRobot())* Math.cos(alpha);
        double xValue = 3000 -  (XSickArriereGauche + Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot())* Math.cos(alpha);
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha);

    }),
    SECONDAIRE__BAS_GAUCHE_PI((Integer) -> {

        int distanceInterSick = Math.abs(Sick.SICK_AVANT_DROIT_SECONDAIRE.getxRelativeRobot() - Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getxRelativeRobot()) ;
        int XSickAvantGauche = Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getLastMeasure();
        int XSickAvantDroit = Sick.SICK_AVANT_DROIT_SECONDAIRE.getLastMeasure();
        int XSickMiddle=(XSickAvantDroit+XSickAvantGauche)/2;
        int YSick = Sick.SICK_GAUCHE_SECONDAIRE.getLastMeasure();
        double rate = ((double)(XSickAvantGauche - XSickAvantDroit)) / distanceInterSick ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        int yValue = (int) ((Sick.SICK_GAUCHE_SECONDAIRE.getxRelativeRobot() + YSick )* Math.cos(alpha)); //TODO Trouver moyen d'améliorer la précison sur y
        int xValue = -1500 + (int) ((XSickMiddle + Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getyRelativeRobot())* Math.cos(alpha));
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
    }),

    SECONDAIRE_HAUT_GAUCHE_PI ((Integer) -> {

        int distanceInterSick = Math.abs(Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getxRelativeRobot() - Sick.SICK_AVANT_DROIT_SECONDAIRE.getxRelativeRobot()) ;
        int XSickAvantGauche = Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getLastMeasure();
        int XSickAvantDroit = Sick.SICK_AVANT_DROIT_SECONDAIRE.getLastMeasure();
        int XSickMiddle=(XSickAvantDroit+XSickAvantGauche)/2;
        int YSick = Sick.SICK_DROIT_SECONDAIRE.getLastMeasure();
        double rate = ((double)(XSickAvantGauche - XSickAvantDroit) / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        int yValue = (int) (2000 - (YSick + Sick.SICK_DROIT_SECONDAIRE.getxRelativeRobot())* Math.cos(alpha));
        int xValue = -1500 + (int) ((XSickMiddle+ Sick.SICK_AVANT_DROIT_SECONDAIRE.getyRelativeRobot())* Math.cos(alpha));
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
    }),

    SECONDAIRE_BAS_DROITE_0 ((Integer) -> {

        int distanceInterSick = Math.abs(Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot());
        int XSickAvantGauche = Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getLastMeasure();
        int XSickAvantDroit = Sick.SICK_AVANT_DROIT_SECONDAIRE.getLastMeasure();
        int XSickMiddle=(XSickAvantDroit+XSickAvantGauche)/2;
        int YSick = Sick.SICK_DROIT_SECONDAIRE.getLastMeasure();
        double rate = ((double)(XSickAvantGauche - XSickAvantDroit) / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        int yValue = (int)((YSick + Sick.SICK_DROIT_SECONDAIRE.getxRelativeRobot())* Math.cos(alpha));
        int xValue = 1500 - (int) ((XSickMiddle + Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getyRelativeRobot())* Math.cos(alpha));
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha);

    }),

    SECONDAIRE_HAUT_DROITE_0 ((Integer) -> {

        int distanceInterSick = Math.abs(Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot()) ;
        int XSickAvantGauche = Sick.SICK_AVANT_GAUCHE_SECONDAIRE.getLastMeasure();
        int XSickAvantDroit = Sick.SICK_AVANT_DROIT_SECONDAIRE.getLastMeasure();
        int XSickMiddle=(XSickAvantDroit+XSickAvantGauche)/2;
        int YSick = Sick.SICK_GAUCHE_SECONDAIRE.getLastMeasure();
        double rate = ((double)(XSickAvantGauche - XSickAvantDroit )/ distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        int yValue = 2000 - (int)((YSick  + Sick.SICK_DROIT_PRINCIPAL.getxRelativeRobot())* Math.cos(alpha));
        int xValue = 1500 - (int)(( XSickMiddle+ Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getyRelativeRobot())* Math.cos(alpha));
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha);

    })

    ;

   private Function<Integer, XYO> calcul;

    Recalage(Function<Integer, XYO> calcul){
        this.calcul = calcul;
    }

    public XYO getNewPosition(){
        return this.calcul.apply(0);
    }


}
