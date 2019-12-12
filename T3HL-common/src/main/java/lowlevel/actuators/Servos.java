package lowlevel.actuators;

/**
 * Servomoteurs disponibles pour le HL
 *
 * @author jglrxavpok
 */
public interface Servos {
    Servo oust = new Servo(7);

    Servo rightArmBase = new Servo(1);
    Servo rightArmElbow = new Servo(2);
    Servo rightArmWrist = new Servo(3);

    Servo leftArmBase = new Servo(4).setSymetrized(rightArmBase);
    Servo leftArmElbow = new Servo(5).setSymetrized(rightArmElbow);
    Servo leftArmWrist = new Servo(6).setSymetrized(rightArmWrist);
}
