package events;

import classes.Incendie;
import robots.Robot;

public class EteindreEvenement extends Evenement {

    Incendie incendie;
    Simulateur simulateur;
    public EteindreEvenement(Integer date, Robot robot, Incendie incendie) {
        super(date, robot);
        this.incendie = incendie;
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public Evenement execute() {
        // TODO Auto-generated method stub
        incendie.setIntensite(incendie.getIntensite()-getRobot().getVolVidage());
        getRobot().setCurrReservoir(getRobot().getCurrReservoir() - getRobot().getVolVidage());
        if (incendie.getIntensite() > 0 && getRobot().getCurrReservoir() >= getRobot().getVolVidage())
            return new EteindreEvenement(getDate()+getRobot().getTempsVidage(),
                                         getRobot(), incendie);

        return new DisponibleEvenement(getDate()+1, getRobot());
    }
}
