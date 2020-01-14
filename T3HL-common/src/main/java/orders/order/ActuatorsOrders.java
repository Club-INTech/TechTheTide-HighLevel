package orders.order;

import locomotion.PathFollower;
import locomotion.UnableToMoveException;
import lowlevel.actuators.ServoGroups;
import lowlevel.order.*;
import lowlevel.actuators.Servos;
import lowlevel.order.*;
import orders.OrderWrapper;
import utils.RobotSide;
import utils.communication.Formatting;

import java.text.Format;

/**
 * Liste des ordres liés aux actuateurs.
 *
 * Les noms sont en PascalCase juste par esthétisme.
 *
 * @author jglrxavpok
 */
public final class ActuatorsOrders {

    // TODO:enlever les ordres de l'annee derniere

    private static final String PUMPS = "pump";
    private static final String VALVES = "valve";
    private static final String SERVOS = "servos";

    public static final SidedOrder ActivateRightPump = OrderBuilder
            .create(PUMPS)
            .side(RobotSide.RIGHT)
            .on();
    public static final SidedOrder ActivateLeftPump = ActivateRightPump.symetrize();

    public static final SidedOrder DeactivateRightPump = OrderBuilder
            .create(PUMPS)
            .side(RobotSide.RIGHT)
            .off();
    public static final SidedOrder DeactivateLeftPump = DeactivateRightPump.symetrize();

    public static final SidedOrder ActivateRightValve = OrderBuilder
            .create(VALVES)
            .side(RobotSide.RIGHT)
            .on();
    public static final SidedOrder ActivateLeftValve = ActivateRightValve.symetrize();

    public static final SidedOrder DeactivateRightValve = OrderBuilder
            .create(VALVES)
            .side(RobotSide.RIGHT)
            .off();
    public static final SidedOrder DeactivateLeftValve = DeactivateRightValve.symetrize();

    private ActuatorsOrders() {
    }

    //NEW 2020
    /////////////////////////////////////////////PUMPS and VALVES (SECONDAIRE)

    // Pompes et valves de 0 à 5 pour le secondaire
    // Pompes et valves 6 pour le principal

    //  Valve : pour activer l'électrovanne
    public static final OrderWithArgument Valve = OrderBuilder
            .createWithArgs("Valve", Formatting.INT, Formatting.STRING);

    public static final Order[] ValveOn = Valve.batchCompile(7, (index) -> new Object[]{index, "on"});
    public static final Order[] ValveOff = Valve.batchCompile(7, (index) -> new Object[]{index, "off"});


    // Valve du principal
    public static Order ValvePOff = ValveOff[6];
    public static Order ValvePOn = ValveOn[6];

    // Pump : pour activer la pompe

    public static final OrderWithArgument Pump = OrderBuilder
            .createWithArgs("Suck", Formatting.INT, Formatting.STRING);

    public static final Order[] PumpOn = Pump.batchCompile(7, (index) -> new Object[]{index, "on"});
    public static final Order[] PumpOff = Pump.batchCompile(7, (index) -> new Object[]{index, "off"});


    // Pompe du principal
    public static Order PumpPOff = ValveOff[6];
    public static Order PumpPOn = ValveOn[6];

//////////////////BRAS (COMMUNS POUR PHARE ET MANCHES)

//Bras in/ Brasout ==> LeftArmIn/Out et RightArmIn/Out: common order pour manche à air et phare

    public static final SidedServoOrder LeftArmIn = (SidedServoOrder) OrderBuilder
            .create(SERVOS)
            .side(RobotSide.LEFT)
            .moveServo(Servos.leftArm, 180);

    public static final SidedOrder RightArmIn = LeftArmIn.symetrize();

    public static final SidedServoOrder LeftArmOut = (SidedServoOrder) OrderBuilder
            .create(SERVOS)
            .side(RobotSide.LEFT)
            .moveServo(Servos.leftArm, 180);

    public static final SidedOrder RightArmOut = LeftArmOut.symetrize();

///////POSITION BRAS (SECONDAIRE)


    // Position basic des bras pour stocker les gobelets
    public static final OrderWithArgument BrasStock = OrderBuilder
            .createWithArgs("BrasStock", Formatting.INT);

    //Permet de stocker plusieurs gobelets en même temps
    public static final Order[] AllBrasStock = BrasStock.batchCompile(6, (index) -> new Object[]{index}); // 0 à 5

    // Bras ecueil : bras out bonne hauteur pour choper gobelet
    public static final OrderWithArgument BrasEcueil = OrderBuilder
            .createWithArgs("BrasEcueil", Formatting.INT);

    //Permet de choper plusieurs gobelets en même temps
    public static final Order[] AllBrasEcueil = BrasEcueil.batchCompile(6, (index) -> new Object[]{index}); // 0 à 5

    // Bras depot : bras out bonne hauteur pour poser par terre dans le port
    public static final OrderWithArgument BrasDepot = OrderBuilder
            .createWithArgs("BrasDepot", Formatting.INT);

    //Permet de déposer plusieurs gobelets en même temps
    public static final Order[] AllBrasDepot = BrasDepot.batchCompile(6, (index) -> new Object[]{index}); // 0 à 5


//////////////////GATE (PRINCIPAL)

    public static final Order GateOpen = OrderBuilder
            .createSimple("GateOpen");
    public static final Order GateClose = OrderBuilder
            .createSimple("GateClose");

//////////////////ELEVATOR (PRINCIPAL)
// LiftUp/ LiftDown : pour monter/descendre le profilet pour remonter les 2 gobelets en hauteur

    public static final Order LiftUp = OrderBuilder
            .createSimple("LiftUp");
    public static final Order LiftDown = OrderBuilder
            .createSimple("LiftDown");

//////////////////FLAG (COMMUNS)

    // Pour hisser haut le drapeau
    public static final Order FlagUp = OrderBuilder
            .createSimple("FlagUp");
    public static final Order FlagDown = OrderBuilder
            .createSimple("FlagDown");
}