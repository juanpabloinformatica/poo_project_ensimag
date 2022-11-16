package events;

import classes.Incendie;
import robots.Robot;
import robots.RobotLogic;

public class ArrivedEvenement extends Evenement {
    Incendie incendie;
    Simulateur simulateur;
    public ArrivedEvenement(Integer date, RobotLogic robotLogic, Incendie incendie) {
        super(date, robotLogic);
        this.incendie = incendie;
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        getRobotLogic().arrivedToIncendie(getDate(), incendie);
    }
}