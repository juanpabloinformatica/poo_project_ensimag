package events;

import robots.RobotLogic;

/**
 * the class OccupiedEvenement represent the state busy of a robot 
 */
public class OccupiedEvenement extends Evenement {

    /**
     * create the busy event that indicate a robot that is  busy
     * @param date - the date of the event
     * @param robotLogic - the robot logic
     */
    public OccupiedEvenement (int date, RobotLogic robotLogic) {
        super(date, robotLogic);
    }
    /**
     * performs the event of putting a dutty to a robot
     */
    public void execute() {
        getRobotLogic().setOccupied(true);
    }
}