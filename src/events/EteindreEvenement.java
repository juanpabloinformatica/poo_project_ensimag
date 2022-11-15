package events;

import classes.Incendie;
import robots.Robot;
import robots.RobotLogic;

public class EteindreEvenement extends Evenement {

    Incendie incendie;
    Simulateur simulateur;
    public EteindreEvenement(Integer date, RobotLogic robotLogic, Incendie incendie) {
        super(date, robotLogic);
        this.incendie = incendie;
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        getRobotLogic().eteindreIncendie(getDate(), incendie);
    }
}
