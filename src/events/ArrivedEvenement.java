package events;

import classes.Incendie;
import robots.Robot;

public class ArrivedEvenement extends Evenement {
    Incendie incendie;
    Simulateur simulateur;
    public ArrivedEvenement(Integer date, Robot robot, Incendie incendie) {
        super(date, robot);
        this.incendie = incendie;
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        getRobot().arrivedToIncendie(getDate(), incendie);
    }
}
