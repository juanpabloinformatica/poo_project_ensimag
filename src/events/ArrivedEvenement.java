package events;

import classes.Incendie;
import robots.Robot;
import robots.RobotLogic;

/**
 * the class arrivedEvenement represent the arrive action of a robot
 */
public class ArrivedEvenement extends Evenement {
    Incendie incendie;
    Simulateur simulateur;

    /**
     * create a arrive event which receives the robot who arrive, the date,the fire to be attacked,
     * and the robot logic
     * @param date - date of the arrive event
     * @param robotLogic - robot logic 
     * @param incendie - fire
     */
    public ArrivedEvenement(Integer date, RobotLogic robotLogic, Incendie incendie) {
        super(date, robotLogic);
        this.incendie = incendie;
    }

    /**
     * perform the action of arriving of the robot
     */
    @Override
    public void execute() {
        getRobotLogic().arrivedToIncendie(getDate(), incendie);
    }
}