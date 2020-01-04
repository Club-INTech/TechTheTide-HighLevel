package lowlevel.actuators;

/**
 * Servomoteurs disponibles pour le HL
 *
 * @author jglrxavpok
 */
public interface Servos {
 //   Servo oust = new Servo(7);

    /////Pour ecueils Secondaire
    Servo frontArm1 = new Servo(1);
    Servo frontArm2 = new Servo(2);
    Servo frontArm3 = new Servo(3);

    Servo backArm1 = new Servo(4);
            //.setSymetrized(frontArm1);
    Servo backArm2 = new Servo(5);
    Servo backArm3 = new Servo(6);

    ////Pour Phare et Manches
    Servo leftArm = new Servo(7);
    Servo rightArm = new Servo(8).setSymetrized(leftArm);
}
