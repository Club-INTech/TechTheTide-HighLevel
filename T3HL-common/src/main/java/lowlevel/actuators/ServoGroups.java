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
    public static final ServoGroup FrontArms = new ServoGroup(2, frontArm1, frontArm2, frontArm3);
    public static final ServoGroup BackArms = new ServoGroup(3, backArm1, backArm2, backArm3);

    static {
      //  FrontArms.symetrized = BackArms;
      //  BackArms.symetrized = FrontArms;
    }
}
