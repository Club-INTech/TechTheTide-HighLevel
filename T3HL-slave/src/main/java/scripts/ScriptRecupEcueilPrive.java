package scripts;


import pfg.config.Config;
import pfg.config.Configurable;
import utils.ConfigData;
import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import utils.math.VectCartesian;

// @author : yam(AznekEnimsay), last modification 14/01/20

public class ScriptRecupEcueilPrive extends Script {

    @Configurable("buddyRay")
    private int ray;

   protected ScriptRecupEcueilPrive(HLInstance hl) {
        super(hl);}

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire

        try {

            // on attrape les 3 gobelets dans l'écueil à l'arrière du robot
            followPathTo(new VectCartesian(ray, 1525));
            turnTowards(0);
            GobeletActions.grab(this,true, 3,5);
            turnTowards(-Math.PI/2);
            moveLengthwise(-225,false);
            turnTowards(0);
            GobeletActions.grab(this,true,4);


            // On attrape les deux gobelets restants
            followPathTo( new VectCartesian(ray, 1600));
            turnTowards(Math.PI);
            GobeletActions.grab(this,false,0,2);

            // Retour au port pour déposer les gobelets
            followPathTo( new VectCartesian(ray, 1600-550-ray));
            turnTowards(-Math.PI/2);
            GobeletActions.drop(this,3,4,5);
            followPathTo( new VectCartesian(ray, 510 - ray));
            turnTowards(Math.PI/2);
            GobeletActions.drop(this,0,2);
            // Position Repos
            GobeletActions.BrasRepos(this,0,2,3,4,5);

        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(ray,1050-ray);
    }

    @Override
    public void finalize(Exception e) {

    }

    @Override
    public void updateConfig(Config config) {
        System.out.println(">>> "+config.get(ConfigData.COULEUR));
    }
}