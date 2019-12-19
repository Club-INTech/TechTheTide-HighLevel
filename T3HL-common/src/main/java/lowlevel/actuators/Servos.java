package lowlevel.actuators;

/**
 * Servomoteurs disponibles pour le HL
 *
 * @author jglrxavpok
 */
public interface Servos {
    Servo oust = new Servo(7);

    Servo rightArm1 = new Servo(1);
    Servo rightArm2 = new Servo(2);
    Servo rightArm3 = new Servo(3);

    Servo leftArm1 = new Servo(4).setSymetrized(rightArm1);
    Servo leftArm2 = new Servo(5).setSymetrized(rightArm2);
    Servo leftArm3 = new Servo(6).setSymetrized(rightArm3);

    Servo leftArm = new Servo(7);
    Servo rightArm = new Servo(8).setSymetrized(leftArm);
}
