package scripts;

import connection.Connection;
import data.GameState;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.communication.CommunicationException;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import utils.math.VectCartesian;

import java.util.concurrent.TimeUnit;

public class ScriptRecupEcueilCommun extends Script {

    @Configurable("buddyRay")
    private int ray;

    protected ScriptRecupEcueilCommun(HLInstance hl) {
        super(hl);
    }

    @Override
    public void execute(int version) {
        try {
            Connection.CONFIG_ECUEIL.send("test");
            //TODO : mettre une limite de temps
            while (GameState.CONFIG_ECUEIL.getData().equals("RRRRR")){
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (CommunicationException | InterruptedException e) {
            e.printStackTrace();
        }
        String configEcueil = (String) GameState.CONFIG_ECUEIL.getData(); // Pour être sur d'avoir la valeur à jour de la config dans l'éceuil
        // Code lançant les différents scripts du secondaire
        try {
            switch (configEcueil) {
                case "RRVRV":
                    recupConfigRRVRV();
                    System.out.println("RRVRV");
                    break;
                case "VVRRR":
                    recupConfigVVRRR();
                    System.out.println("VVRRR");
                    break;
                default:
                    recupConfigRVRRV();
                    System.out.println("RVRRV");
                    break;
            }
        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }

     // La numérotation des configs est celle de la coupe (l'ordre des gobelets part du mat vers l'extérieur)
     // Configuration numéro 3 (RRVVV) : attrapage de gobelets go !

    private void recupConfigVVRRR() throws UnableToMoveException {
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

    private void recupConfigRRVRV() throws  UnableToMoveException {
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

    private void recupConfigRVRRV() throws  UnableToMoveException {
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