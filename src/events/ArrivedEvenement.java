package events;

import classes.Incendie;
import robots.Robot;
import robots.Robot;

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
     * @param robot - robot logic
     * @param incendie - fire
     */
    public ArrivedEvenement(Integer date, Robot robot, Incendie incendie) {
        super(date, robot);
        this.incendie = incendie;
    }

    /**
     * perform the action of arriving of the robot
     */
    @Override
    public void execute() {
        getRobot().arrivedToIncendie(getDate(), incendie);
    }
}
