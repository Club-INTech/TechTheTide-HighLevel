package data;

import utils.math.InternalVectCartesian;
import utils.math.Vec2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/* @author mikail, AznekEnimsay */

//DISTANCE SICK / AXE CENTRALE DU ROBOT

//arrière droit : 91.802 mm
//arrière gauche : 91.802 mm
//droit : 134.33 mm
//gauche : 134.33 mm


/**
 * Enum des sicks, les numérotations respectent la convention avec le LL
 */
public enum Sick {

    // sicks de l'année dernière à enlever au fur et à mesure

    SICK_ARRIERE_DROIT(-1, -1, 0, 0, 6),
    SICK_ARRIERE_GAUCHE(-1, -1, 0, 0, 6),
    SICK_AVANT_DROIT(-1, -1, 0, 0, 6),
    SICK_AVANT_GAUCHE(-1, -1, 0, 0, 6),
    SICK_DROIT(-1, -1, 0, 0, 6),
    SICK_GAUCHE(-1, -1, 0, 0, 6),
    SICK_AVANT(-1, -1, 0, 0, 6),


    // Indices choisis avec le LL don't worry

    SICK_ARRIERE_GAUCHE_PRINCIPAL(0, -1,0,0,6),
    SICK_GAUCHE_PRINCIPAL(1, -1, 0, 0, 6),
    SICK_DROIT_PRINCIPAL(2, -1, 0, 0, 6),
    SICK_ARRIERE_DROIT_PRINCIPAL(3, -1,0,0,6),

    SICK_AVANT_GAUCHE_SECONDAIRE(0, -1,0,0,6),
    SICK_GAUCHE_SECONDAIRE(1, -1, 0, 0, 6),
    SICK_DROIT_SECONDAIRE(2, -1, 0, 0, 6),
    SICK_AVANT_DROIT_SECONDAIRE(3, -1,0,0,6),



    ;

    // =====================================================================
    // ==== Capteurs SICK à utiliser selon l'orientation et la position ====
    // =====================================================================

    //RIEN DU TOUT
    public static final Sick[] NOTHING = {};

    public static final Sick[] UPPER_LEFT_CORNER_TOWARDS_0 = {SICK_ARRIERE_DROIT_PRINCIPAL, SICK_ARRIERE_GAUCHE_PRINCIPAL, SICK_GAUCHE_PRINCIPAL};
    public static final Sick[] UPPER_LEFT_CORNER_TOWARDS_PI = {SICK_DROIT_PRINCIPAL};

    public static final Sick[] LOWER_LEFT_CORNER_TOWARDS_0 = {SICK_ARRIERE_DROIT_PRINCIPAL, SICK_ARRIERE_GAUCHE_PRINCIPAL, SICK_DROIT_PRINCIPAL};
    public static final Sick[] LOWER_LEFT_CORNER_TOWARDS_PI = {SICK_GAUCHE_PRINCIPAL};

    public static final Sick[] LOWER_RIGHT_CORNER_TOWARDS_0 = {SICK_DROIT_PRINCIPAL};
    public static final Sick[] LOWER_RIGHT_CORNER_TORWARDS_PI = {SICK_ARRIERE_DROIT_PRINCIPAL, SICK_ARRIERE_GAUCHE_PRINCIPAL, SICK_GAUCHE_PRINCIPAL};

    public static final Sick[] UPPER_RIGHT_CORNER_TOWARDS_0 = {SICK_GAUCHE_PRINCIPAL};
    public static final Sick[] UPPER_RIGHT_CORNER_TORWARDS_PI = {SICK_ARRIERE_DROIT_PRINCIPAL, SICK_ARRIERE_GAUCHE_PRINCIPAL, SICK_DROIT_PRINCIPAL};

    //Secondaire

   // public static final Sick[] SECONDAIRE= {SICK_AVANT, SICK_AVANT_GAUCHE, SICK_ARRIERE_GAUCHE};

    /**
     * Indice du capteur sick
     */
    private final int indiceSick;

    /**
     * Dernière mesure
     */
    private int lastMeasure;

    /**
     * position en x et y du SICK par rapport au centre du robot
     */
    private int xRelativeRobot;
    private int yRelativeRobot;

    /**
     * Offset de chaque SICK
     */

    private int offset;


    private static Sick[] significantSicks = LOWER_LEFT_CORNER_TOWARDS_PI;

    /**
     * La nouvelle position et orientation du robot après calcul
     */
    private static CompletableFuture<XYO> newXYO;
    /**
     * Bloc statique pour instancier les variables statiques
     */
    static {
        newXYO = new CompletableFuture<>();
    }

    /**
     * Constructeur de l'enum
     * @param indiceSick
     */
    Sick(int indiceSick, int lastMeasure, int xRelativeRobot, int yRelativeRobot, int offset){
        this.indiceSick=indiceSick;
        this.lastMeasure = lastMeasure;
        this.xRelativeRobot = xRelativeRobot;
        this.yRelativeRobot = yRelativeRobot;
        this.offset=offset;
    }

    /**
     * Set la valeur du completable
     * @param newXYO
     */
    public static void setNewXYO(XYO newXYO) {
        Sick.newXYO.complete(newXYO);
    }

    /**
     * Get la valeur du XYO
     * @return
     */
    public static XYO getNewXYO() {
        XYO xyo = null;
        try {
            xyo = newXYO.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  xyo;
    }

    /**
     * Renvoie les indices des sicks qui sont activés
     * @return
     */
    public static Sick[] getSignificantSicks() {
        return significantSicks;
    }

    /**
     * Renvoie la dernière mesure réalisée par le sick
     * @return int
     */
    public int getLastMeasure(){
        return this.lastMeasure + this.offset;
    } //L'Offset de chaque SICK est directement ajouté

    /**
     * Set la dernière mesure réalisée par le sick
     */
    public void setLastMeasure(int lastMeasure){
        this.lastMeasure = lastMeasure;
    }

    /**
     * Renvoie toutes les dernières mesures des sicks
     * @return int[]
     */
    public static int[] getLastMeasures(){
        int[] lastMeasures = new int[6];
        Sick[] sicks = Sick.values();
        for (int i = 0; i < sicks.length; i++){
            lastMeasures[i] = sicks[i].getLastMeasure();
        }
        return lastMeasures;
    }


    public static void setSignificantSicks(Sick[] sicks) {
        significantSicks = sicks;
    }

    public static void resetNewXYO() {
        // on ne peut pas complete un CompletableFuture deux fois de suite
        newXYO = new CompletableFuture<>();
    }

    public int getIndex() {
        return indiceSick;
    }
    public int getxRelativeRobot(){
        return this.xRelativeRobot;
    }

    public int getyRelativeRobot(){
        return this.yRelativeRobot;
    }


}
