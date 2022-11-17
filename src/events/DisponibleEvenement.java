package events;

import classes.ChefPompier;
import robots.RobotLogic;

/**
 * the class DisponibleEvenement represent the state free of a robot 
 */
public class DisponibleEvenement extends Evenement {
    private ChefPompier chefPompier;

    /**
     * create the available event that indicate a robot that is not busy
     * @param date - the date of the event
     * @param robotLogic - the robot logic
     */
    public DisponibleEvenement(int date, RobotLogic robotLogic,
                               ChefPompier chefPompier) {
        super(date, robotLogic);
        this.chefPompier = chefPompier;
    }

    /**
     * performs the event of leave a robot without duty
     */
    public void execute() {
        System.out.println("disponible!!");
        getRobotLogic().setOccupied(false);
        // chefPompier.strategieElementaire();
        chefPompier.strategieEvolved();
    }
}
