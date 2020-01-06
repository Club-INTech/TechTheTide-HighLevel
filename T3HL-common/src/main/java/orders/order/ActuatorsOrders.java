package orders.order;

import lowlevel.actuators.ServoGroups;
import lowlevel.order.*;
import lowlevel.actuators.Servos;
import lowlevel.order.*;
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

    private static final String PUMPS = "pump";
    private static final String VALVES = "valve";
    private static final String SERVOGROUPS = "servogroup";
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

    private ActuatorsOrders(){}

    //NEW 2020
    /////////////////////////////////////////////PUMPS and VALVES (SECONDAIRE)

    //  Valve : pour activer l'électrovanne
    public static final OrderWithArgument Valve = OrderBuilder
            .createWithArgs("Valve", Formatting.INT, Formatting.STRING);

    public static final Order[] ValveOn = Valve.batchCompile(8, (index) -> new Object[] { index, "on" });
    public static final Order[] ValveOff = Valve.batchCompile(8, (index) -> new Object[] { index, "off" });

    // Pump : pour activer la pompe

    public static final OrderWithArgument Pump = OrderBuilder
            .createWithArgs("Suck", Formatting.INT, Formatting.STRING);

    public static final Order[] PumpOn = Pump.batchCompile(8, (index) -> new Object[] {index, "on"});
    public static final Order[] PumpOff = Pump.batchCompile(8, (index) -> new Object[] {index, "off"});



    // Les 6 valves du secondaires

    public static Order Valve1On = Valve.compileWith(1, "on");
    public static Order Valve1Off = Valve.compileWith(1, "off");
    public static Order Valve2On = Valve.compileWith(2, "on");
    public static Order Valve2Off = Valve.compileWith(2, "off");
    public static Order Valve3On = Valve.compileWith(3, "on");
    public static Order Valve3Off = Valve.compileWith(3, "off");

    public static Order Valve4On = Valve.compileWith(4, "on");
    public static Order Valve4Off = Valve.compileWith(4, "off");
    public static Order Valve5On = Valve.compileWith(5, "on");
    public static Order Valve5Off = Valve.compileWith(5, "off");
    public static Order Valve6On = Valve.compileWith(6, "on");
    public static Order Valve6Off = Valve.compileWith(6, "off");
    // Valve du principal
    public static Order Valve7Off = Valve.compileWith(7, "off");
    public static Order Valve7On = Valve.compileWith(7, "on");

// Pompes du secondaire
//  Suck ==> Pump : Aspirer avec la ventouse
    public static Order Pump1On = Pump.compileWith(1, "on");
    public static Order Pump1Off = Pump.compileWith(1, "off");
    public static Order Pump2On = Pump.compileWith(2, "on");
    public static Order Pump2Off = Pump.compileWith(2, "off");
    public static Order Pump3On = Pump.compileWith(3, "on");
    public static Order Pump3Off = Pump.compileWith(3, "off");

    public static Order Pump4On = Pump.compileWith(4, "on");
    public static Order Pump4Off = Pump.compileWith(4, "off");
    public static Order Pump5On = Pump.compileWith(5, "on");
    public static Order Pump5Off = Pump.compileWith(5, "off");
    public static Order Pump6On = Pump.compileWith(6, "on");
    public static Order Pump6Off = Pump.compileWith(6, "off");
// Pompe du principal
    public static Order Pump7Off = Valve.compileWith(7, "off");
    public static Order Pump7On = Valve.compileWith(7, "on");

//////////////////BRAS (COMMUNS POUR PHARE ET MANCHES)

//Bras in/ Brasout ==> LeftArmIn/Out et RightArmIn/Out: common order pour manche à air et le phare

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

    //Bras stock : bras in rangé ds le robot

    // Position basic des bras pour stocker les gobelets
    public static final OrderWithArgument BrasStock = OrderBuilder
            .createWithArgs("BrasStock", Formatting.INT);

    public static final Order[] AllBrasStock = BrasStock.batchCompile(7, (index) -> new Object[] { index } ); // 0 à 6

    // Bras ecueil : bras out bonne hauteur pour choper gobelet
    public static final OrderWithArgument BrasEcueil = OrderBuilder
            .createWithArgs("BrasEcueil", Formatting.INT);

    public static final Order[] AllBrasEcueil = BrasEcueil.batchCompile(7, (index) -> new Object[] { index } ); // 0 à 6

    // Bras depot : bras out bonne hauteur pour poser par terre ds le port

    //public static final Order BrasDepot = OrderBuilder.createSimple("BrasDepot");
    public static final OrderWithArgument BrasDepot = OrderBuilder
            .createWithArgs("BrasDepot", Formatting.INT);

    public static final Order[] AllBrasDepot = BrasDepot.batchCompile(7, (index) -> new Object[] { index } ); // 0 à 6

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


    //Bras in/ Brasout ==> LeftArmIn/Out et RightArmIn/Out: common order pour manche à air et le phare
    //Bras stock : bras in rangé ds le robot
   // Bras ecueil : bras out bonne hauteur pour choper gobelet
   // Bras depot : bras out bonne hauteur pour poser par terre ds le port

  //  Valve : pour activer l'électrovanne
  //  Suck ==> Pump : Aspirer avec la ventouse

   // Gate Open/Close ==> GateGreenOpen/Close et GateRedOpen/Close

   // LiftUp/ LiftDown : pour monter/descendre le profilet pour remonter les 2 gobelets en hauteur
    //FlagUp/Down



}
