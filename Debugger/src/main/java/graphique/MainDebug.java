package graphique;

public class MainDebug {
    public static void main(String[] args) throws Exception {
        FenetreDemarrage fenetreDemarrage = new FenetreDemarrage();

        new FenetreTable(fenetreDemarrage.getChoixRobot(), fenetreDemarrage.getLogFile());
    }
}
