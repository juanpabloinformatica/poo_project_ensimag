package events;
import robots.Robot;
import robots.RobotLogic;

public class RemplirEvenement extends Evenement {
    public RemplirEvenement(Integer date, RobotLogic robot) {
        super(date, robot);
    }
    /*
     * remplir the robot water bag
     */
    @Override
    public void execute() {
        Robot robot = getRobotLogic().getRobot();
        robot.setCurrReservoir(robot.getRESERVOIR());
    }
    
}
