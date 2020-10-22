package lowlevel.actuators;

import orders.OrderWrapper;
import utils.container.Module;

/**
 * Module contenant la liste des actuateurs disponibles
 *
 * @author jglrxavpok
 */
public class ActuatorsModule implements Module {

    private OrderWrapper wrapper;
//Used in script homologation de l'an dernier (don't delete yet)


    public ActuatorsModule(OrderWrapper wrapper) {
        this.wrapper = wrapper;

        // Initialisation des différents actuateurs


/*        leftElevator = new ElevatorActuator(wrapper, ElevatorOrders.RaiseLeftElevator, ElevatorOrders.LowerLeftElevator,
                ElevatorOrders.RaiseThenLowerLeftElevator, ElevatorOrders.RaiseThenLowerLeftElevator, SensorState.LEFT_ELEVATOR_MOVING);
        rightElevator = new ElevatorActuator(wrapper, ElevatorOrders.RaiseRightElevator, ElevatorOrders.LowerRightElevator,
                ElevatorOrders.RaiseThenLowerRightElevator, ElevatorOrders.RaiseThenLowerRightElevator, SensorState.RIGHT_ELEVATOR_MOVING);
*/
    }
}