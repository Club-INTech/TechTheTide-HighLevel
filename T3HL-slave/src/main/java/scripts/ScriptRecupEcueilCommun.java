package scripts;

import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import utils.math.VectCartesian;

public class ScriptRecupEcueilCommun extends Script {

    @Configurable("buddyRay")
    private int ray;

    //@Configurable("config")
    private String config ;

    protected ScriptRecupEcueilCommun(HLInstance hl) {
        super(hl);}

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire
        try {
            switch (config) {
                case "RRVRV":
                    recupConfig2();
                    break;
                case "VVRRR":
                    recupConfig3();
                    break;
                default:
                    recupConfig1();
                    break;
            }
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

     // La numérotation des configs est celle de la coupe (l'ordre des gobelets part du mat vers l'extérieur)
     // Configuration numéro 3 (RRVVV) : attrapage de gobelets go !

    private void recupConfig3() throws UnableToMoveException {
        followPathTo(new VectCartesian(925, ray));
        turnTowards(-Math.PI/2);
        GobeletActions.grab(this,false, 0,1);
        followPathTo(new VectCartesian(775, ray));
        turnTowards(Math.PI/2);
        GobeletActions.grab(this, true, 3,4,5);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this,3,4,5);
        followPathTo(new VectCartesian(ray, 1080+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this, 0,1);
        GobeletActions.BrasRepos(this,0,2,3,4,5);
    }

    //Configuration numéro 2 (RRVRV)

    private void recupConfig2() throws  UnableToMoveException {
        followPathTo(new VectCartesian(775, ray));
        turnTowards(-Math.PI/2);
        GobeletActions.grab(this,false, 0,2);
        followPathTo(new VectCartesian(850, ray));
        turnTowards(Math.PI/2);
        GobeletActions.grab(this,true, 5);
        followPathTo(new VectCartesian(925, ray));
        turnTowards(Math.PI/2);
        GobeletActions.grab(this,true, 4,3);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this,3,4,5);
        followPathTo(new VectCartesian(ray, 1080+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this,0,2);
        GobeletActions.BrasRepos(this,0,2,3,4,5);
    }

    //Configuration numéro 1 (RVRRV)

    private void recupConfig1() throws  UnableToMoveException {
        followPathTo(new VectCartesian(925, ray));
        turnTowards(Math.PI/2);
        GobeletActions.grab(this,true, 3,5);
        turnTowards(-Math.PI/2);
        GobeletActions.grab(this,false, 1);
        followPathTo(new VectCartesian(775, ray));
        turnTowards(Math.PI/2);
        GobeletActions.grab(this,true, 4);
        turnTowards(-Math.PI/2);
        GobeletActions.grab(this,false, 2);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this,3,4,5);
        followPathTo(new VectCartesian(ray, 1080+ray));
        turnTowards(Math.PI/2);
        GobeletActions.drop(this,1,2);
        GobeletActions.BrasRepos(this,0,2,3,4,5);
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(500, ray);
    }

    @Override
    public void finalize(Exception e) {

    }

}