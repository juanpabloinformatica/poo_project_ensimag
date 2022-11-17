package events;
import robots.Robot;
import robots.RobotLogic;

/**
 * the class RemplirEvenement represent the action on refilling a tank of a robot
 */
public class RemplirEvenement extends Evenement {

    /**
     * cretae the event of refill that receives the date of the event and a robot
     * @param date - the date of the event
     * @param robot - the robot
     */
    public RemplirEvenement(Integer date, RobotLogic robot) {
        super(date, robot);
    }
    /*
     * performs the event of refill the tank of a robot
     */
    @Override
    public void execute() {
        Robot robot = getRobotLogic().getRobot();
        robot.setCurrReservoir(robot.getRESERVOIR());
    }
}
