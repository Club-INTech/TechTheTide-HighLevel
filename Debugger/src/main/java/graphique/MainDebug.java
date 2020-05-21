package graphique;
import java.awt.*;

public class MainDebug {
    public static void main(String[] args) throws Exception {
        FenetreDemarrage fenetreDemarrage = new FenetreDemarrage();

        new FenetreTable(fenetreDemarrage.getChoixRobot(), fenetreDemarrage.getLogFile());
    }
}
