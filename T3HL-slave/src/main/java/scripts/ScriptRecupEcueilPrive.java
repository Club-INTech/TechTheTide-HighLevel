package scripts;


import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import locomotion.UnableToMoveException;
import orders.order.ActuatorsOrders;
import utils.math.VectCartesian;

// @author : yam(AznekEnimsay), last modification 06/01/20

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
            grab(true, 4,6);
            turnTowards(-Math.PI/2);
            moveLengthwise(-225,false);
            turnTowards(0);
            grab(true,5);
            followPathTo( new VectCartesian(ray, 1600));
            turnTowards(Math.PI);

            // On attrape les deux gobelets restants
            grab(false,1,3);

            // Retour au port pour déposer les gobelets
            followPathTo( new VectCartesian(ray, 1600-550-ray));
            turnTowards(-Math.PI/2);
            drop(4,5,6);
           //fixme  followPathTo( new VectCartesian(ray, 510 - ray));
            turnTowards(Math.PI/2);
            drop(1,3);
            // Position Repos
            BrasRepos(1,3,4,5,6);

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
        }
    }

    // Pour attraper les gobelets

    private void grab(boolean reverse ,int... indices) throws UnableToMoveException {
        for (int index : indices) {
            perform(ActuatorsOrders.AllBrasEcueil[index]);
            perform(ActuatorsOrders.ValveOn[index]);
            perform(ActuatorsOrders.PumpOn[index]);
            if(reverse) {
            moveLengthwise(7,true);
            perform(ActuatorsOrders.AllBrasStock[index]);
            moveLengthwise(-7,true);
        }
            else {
                moveLengthwise(-7,true);
                perform(ActuatorsOrders.AllBrasStock[index]);
                moveLengthwise(7,true);
            }
        }
    }

    @Override
    public Vec2 entryPosition(int version) {
        return new VectCartesian(ray,1050-ray);
    }

    @Override
    public void finalize(Exception e) {

    }

}