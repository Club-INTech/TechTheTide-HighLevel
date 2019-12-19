package lowlevel.actuators;

/**
 * Liste des groupes de servomoteurs disponibles pour le HL
 *
 * @author jglrxavpok
 */
public class ServoGroups implements Servos {

    /////////////////////2020
    ///////////////////////Principal

    /////////////////Secondaire
    public static final ServoGroup LeftArms = new ServoGroup(2, leftArm1, leftArm2, leftArm3);
    public static final ServoGroup RightArms = new ServoGroup(3, rightArm1, rightArm2, rightArm3);

    static {
        LeftArms.symetrized = RightArms;
        RightArms.symetrized = LeftArms;
    }
}
