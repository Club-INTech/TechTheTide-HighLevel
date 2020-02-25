package scripts;


import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import utils.math.VectCartesian;

// @author : yasmine (AznekEnimsay), last modification 24/02/20

// Largeur : 28 cm
// Longeur : 20 cm


public class TestScriptRecupEcueilPrive extends Script {


    protected TestScriptRecupEcueilPrive(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire

        try {

            // on attrape les 3 gobelets dans l'écueil à l'arrière du robot
            //followPathTo(new VectCartesian(ray, 1525));
            moveLengthwise(555, false); // y = 1525
            turnTowards(0);
            /**ATTRAPAGE DE GOBEULETS**/
            turnTowards(-Math.PI / 2);
            moveLengthwise(-225, false); // y = 1750
            turnTowards(0);
            /**ATTRAPAGE DE GOBEULETS**/


            // On attrape les deux gobelets restants
            turnTowards(-Math.PI/2);
            moveLengthwise(150, false); // y = 1600
            turnTowards(Math.PI);
            /**ATTRAPAGE DE GOBEULETS**/

            // Retour au port pour déposer les gobelets
            turnTowards(3*(Math.PI)/2);
            moveLengthwise(535, false); // y = 1065
            /**DEPOT DE GOBEULETS**/
            moveLengthwise(570, false); // y = 495
            turnTowards(5*(Math.PI)/2);
            /**DEPOT DE GOBEULETS**/
            // Position Repos


        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(140, 970); //1070 - 100 ( = moitié du petit côté du robot); 140 = moitié du grand côté
    }

    @Override
    public void finalize(Exception e) {

    }

}