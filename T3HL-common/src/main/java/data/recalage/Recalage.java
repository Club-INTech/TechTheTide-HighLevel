package data.recalage;

import data.Sick;
import data.XYO;
import utils.math.InternalVectCartesian;

import java.util.function.Function;

public enum Recalage {

    //TODO recoder ici tous les calculs

    MASTER_BAS_DROITE_PI((Integer) -> {
        double XSickAvant= Sick.SICK_AVANT_DROIT.getLastMeasure() ;
        double XSickArriere=Sick.SICK_ARRIERE_DROIT.getLastMeasure();
        int YSick= Sick.SICK_AVANT.getLastMeasure();
        double rapport = ((double)XSickAvant - XSickArriere)/(Sick.SICK_AVANT_DROIT.getxRelativeRobot() - Sick.SICK_ARRIERE_DROIT.getxRelativeRobot());
        double teta = Math.atan(rapport);
        int yCalcule = 2000-(int) Math.round(((XSickAvant+XSickArriere+2*Sick.SICK_AVANT_DROIT.getyRelativeRobot()) * Math.cos(teta))/2);
        int xCalcule = 1500 - (int) ((YSick+Sick.SICK_AVANT.getyRelativeRobot()) * Math.cos(teta));
        return new XYO(new InternalVectCartesian(xCalcule, yCalcule), teta);
    }),
   MASTER__BAS_DROITE_O((Void) -> {

       return new XYO(new InternalVectCartesian(0, 0), 1);
   }),
    ;

    private Function<Integer, XYO> calcul;

    Recalage(Function<Integer, XYO> calcul){
        this.calcul = calcul;
    }

    public XYO getNewPosition(){
        return this.calcul.apply(0);
    }


}
