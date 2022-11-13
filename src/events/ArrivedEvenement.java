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
    public Evenement execute() {
        // TODO Auto-generated method stub
        if (incendie.getIntensite() <= 0) {
            return new DisponibleEvenement(getDate()+1, getRobot());
        }

        int tempsVidageIntervention = getRobot().getTempsVidage();
        return new EteindreEvenement(getDate()+tempsVidageIntervention,
                                    getRobot(), incendie);

    }
}
