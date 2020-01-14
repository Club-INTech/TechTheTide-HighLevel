package scripts;

import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.math.VectCartesian;

public class ScriptRecupEcueilCommun extends Script {

    @Configurable("buddyRay")
    private int ray;

    //@Configurable("config")
    private char config[] = {'R', 'R', 'R','R', 'R'};

    protected ScriptRecupEcueilCommun(HLInstance hl) {
        super(hl);}

    @Override
    public void execute(int version) {
        // Code lançant les différents scripts du secondaire
        try {
            recupConfig3();
            recupConfig2();


        } catch (UnableToMoveException e) {
            e.printStackTrace();
        }
    }


    // Pour mettre tous les bras en position de stockage : retour à la config de base du robot

    private void BrasRepos(int...indices) throws UnableToMoveException {
        moveLengthwise(-7,false);
        for (int index : indices)
            perform(ActuatorsOrders.AllBrasStock[index]);
    }

    // Pour déposer les gobelets

    private void drop(int... indices) {
        for (int index : indices) {
            perform(ActuatorsOrders.AllBrasDepot[index]);
            perform(ActuatorsOrders.ValveOff[index]);
            perform(ActuatorsOrders.PumpOff[index]);
            perform(ActuatorsOrders.AllBrasStock[index]);
        }
    }

    // Pour attraper les gobelets

    private void grab(boolean reverse ,int... indices) throws UnableToMoveException {
        for (int index : indices) {
            perform(ActuatorsOrders.AllBrasEcueil[index]);
            perform(ActuatorsOrders.ValveOn[index]);
            perform(ActuatorsOrders.PumpOn[index]);
            if(reverse) {
                moveLengthwise(-7,true);
                perform(ActuatorsOrders.AllBrasStock[index]);
                moveLengthwise(7,false);
            }
            else {
                moveLengthwise(7,true);
                perform(ActuatorsOrders.AllBrasStock[index]);
                moveLengthwise(-7,false);
            }
        }
    }
     // La numérotation des configs est celle de la coupe (l'ordre des gobelets part du mat vers l'extérieur)
     // Configuration numéro 3 (RRVVV) : attrapage de gobelets go !

    private void recupConfig3() throws UnableToMoveException {
        followPathTo(new VectCartesian(925, ray));
        turnTowards(-Math.PI/2);
        grab(false, 5,6);
        followPathTo(new VectCartesian(775, ray));
        turnTowards(Math.PI/2);
        grab(true, 1,2,3);
        followPathTo(new VectCartesian(ray, 1080-ray));
        turnTowards(Math.PI/2);
        drop(1,2);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        drop(4,5,6);
    }

    private void recupConfig2() throws  UnableToMoveException {
        followPathTo(new VectCartesian(925, ray));
        turnTowards(-Math.PI/2);
        grab(false, 1,3);
        turnTowards(Math.PI/2);
        grab(true, 5);
        followPathTo(new VectCartesian(775, ray));
        turnTowards(Math.PI/2);
        grab(true, 4,6);
        followPathTo(new VectCartesian(ray, 1080-ray));
        turnTowards(Math.PI/2);
        drop(1,3);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        drop(4,5,6);
    }

    private void recupConfig1() throws  UnableToMoveException {
        followPathTo(new VectCartesian(925, ray));
        turnTowards(-Math.PI/2);
        grab(false, 1,3);
        turnTowards(Math.PI/2);
        grab(true, 5);
        followPathTo(new VectCartesian(775, ray));
        turnTowards(Math.PI/2);
        grab(true, 4,6);
        followPathTo(new VectCartesian(ray, 1080-ray));
        turnTowards(Math.PI/2);
        drop(1,3);
        followPathTo(new VectCartesian(ray, 510+ray));
        turnTowards(Math.PI/2);
        drop(4,5,6);
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(500, ray);
    }

    @Override
    public void finalize(Exception e) {

    }

}