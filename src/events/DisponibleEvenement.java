package events;

import robots.RobotLogic;

/**
 * the class DisponibleEvenement represent the state free of a robot 
 */
public class DisponibleEvenement extends Evenement {

    /**
     * create the available event that indicate a robot that is not busy
     * @param date - the date of the event
     * @param robotLogic - the robot logic
     */
    public DisponibleEvenement(int date, RobotLogic robotLogic) {
        super(date, robotLogic);
    }

    /**
     * performs the event of leave a robot without duty
     */
    public void execute() {
        getRobotLogic().setOccupied(false);
    }
}