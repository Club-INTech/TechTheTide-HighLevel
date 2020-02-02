package simulator;

import data.CouleurPalet;
import data.CouleurVerre;
import utils.RobotSide;
import utils.math.Vec2;

import java.util.List;

public interface IRobot {

    int getX();
    int getY();
    double getOrientation();

    int getPort();

    Vec2 getPosition();
    Vec2 getTargetPosition();

    String getLeftArmPosition();
    String getRightArmPosition();

    List<CouleurPalet> getElevatorOrNull(RobotSide side);

    List<CouleurVerre> getCouloir(RobotSide side);

    void setElevatorContents(RobotSide side, String[] contents, int startIndex);

    void setCouloirsContents(RobotSide side, String[] contents, int startIndex);
}
