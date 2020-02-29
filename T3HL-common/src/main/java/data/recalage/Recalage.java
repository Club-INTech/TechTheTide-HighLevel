package data.recalage;

import data.Sick;
import data.XYO;
import utils.math.InternalVectCartesian;

import java.util.function.Function;

public enum Recalage {


   MASTER__BAS_GAUCHE_0((Integer) -> {

       int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ; //TODO : DEMANDER A LA MECA
       double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
       double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
       double YSick = Sick.SICK_DROIT_PRINCIPAL.getLastMeasure();
       double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
       double alpha = Math.atan(rate);
       double yValue = Sick.SICK_DROIT_PRINCIPAL.getyRelativeRobot() + YSick * Math.cos(alpha);
       double xValue = XSickArriereGauche * Math.cos(alpha) + Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot();
       return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
   }),

    MASTER_HAUT_GAUCHE_0 ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_GAUCHE_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = 2000 - YSick * Math.cos(alpha) + Sick.SICK_GAUCHE_PRINCIPAL.getyRelativeRobot();
        double xValue = XSickArriereGauche * Math.cos(alpha) + Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot();
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha) ;
    }),

    MASTER_BAS_DROITE_PI ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_GAUCHE_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = YSick * Math.cos(alpha) + Sick.SICK_GAUCHE_PRINCIPAL.getyRelativeRobot();
        double xValue = 3000 -  XSickArriereGauche * Math.cos(alpha) + Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot();
        return new XYO(new InternalVectCartesian(xValue, yValue), alpha);

    }),

    MASTER_HAUT_DROITE_PI ((Integer) -> {

        int distanceInterSick = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot() - Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getxRelativeRobot() ;
        double XSickArriereDroit = Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getLastMeasure();
        double XSickArriereGauche = Sick.SICK_ARRIERE_GAUCHE_PRINCIPAL.getLastMeasure();
        double YSick = Sick.SICK_DROIT_PRINCIPAL.getLastMeasure();
        double rate = (XSickArriereDroit - XSickArriereGauche / distanceInterSick) ; // rapport < 0 => en dessous de l'axe, rapport > 0 => au dessus de l'axe
        double alpha = Math.atan(rate);
        double yValue = 2000 - YSick * Math.cos(alpha) + Sick.SICK_DROIT_PRINCIPAL.getyRelativeRobot();
        double xValue = 3000 -  XSickArriereGauche * Math.cos(alpha) + Sick.SICK_ARRIERE_DROIT_PRINCIPAL.getxRelativeRobot();
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
