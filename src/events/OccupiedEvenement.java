package events;

import robots.Robot;

/**
 * the class OccupiedEvenement represent the state busy of a robot 
 */
public class OccupiedEvenement extends Evenement {

    /**
     * create the busy event that indicate a robot that is  busy
     * @param date - the date of the event
     * @param robot - the robot logic
     */
    public OccupiedEvenement (int date, Robot robot) {
        super(date, robot);
    }
    /**
     * performs the event of putting a dutty to a robot
     */
    public void execute() {
        getRobot().setOccupied(true);
    }
}
