package events;

import classes.Incendie;
import robots.Robot;
import robots.RobotLogic;

/**
 * the class EteindreEvenement represent the action of putting out a fire 
 */
public class EteindreEvenement extends Evenement {

    Incendie incendie;
    Simulateur simulateur;

    /**
     * create a eteindreEvenement that receives the date of the event, the robot logic and the fire
     * @param date
     * @param robotLogic
     * @param incendie
     */
    public EteindreEvenement(Integer date, RobotLogic robotLogic, Incendie incendie) {
        super(date, robotLogic);
        this.incendie = incendie;
    }
    /*
     * performs the event of putting out the fire in certain case
     */
    @Override
    public void execute() {
        getRobotLogic().eteindreIncendie(getDate(), incendie);
    }
}