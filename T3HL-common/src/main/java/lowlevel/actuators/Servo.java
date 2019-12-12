package lowlevel.actuators;

/**
 * Classe représentant un servomoteur
 *
 * @author jglrxavpok
 */
public class Servo implements Actuator {

    private Servo symetrized = this;

    /**
     * Identifiant du servomoteur
     */
    private int id;

    /**
     * Instancies le servomoteur
     * @param id l'identifiant du servomoteur
     */
    public Servo(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    public Servo getSymetrized() {
        return symetrized;
    }

    public Servo setSymetrized(Servo symetrized) {
        this.symetrized = symetrized;
        symetrized.symetrized = this;
        return this;
    }
}
