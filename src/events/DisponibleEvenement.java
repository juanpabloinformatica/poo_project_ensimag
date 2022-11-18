package events;

import classes.ChefPompier;
import robots.Robot;

/**
 * the class DisponibleEvenement represent the state free of a robot 
 */
public class DisponibleEvenement extends Evenement {
    private ChefPompier chefPompier;

    /**
     * create the available event that indicate a robot that is not busy
     * @param date - the date of the event
     * @param robot - the robot logic
     */
    public DisponibleEvenement(int date, Robot robot,
                               ChefPompier chefPompier) {
        super(date, robot);
        this.chefPompier = chefPompier;
    }

    /**
     * performs the event of leave a robot without duty
     */
    public void execute() {
        getRobot().setOccupied(false);
        chefPompier.strategie();
    }
}
