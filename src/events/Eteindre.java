package events;

import classes.Case;
import classes.Incendie;
import robots.Robot;

public class Eteindre extends Evenement {

    Incendie incendie;
    public Eteindre(Integer date,Incendie incendie, Robot robot) {
        super(date, robot);
        this.incendie = incendie;
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        int volume = this.getRobot().getCurrReservoir();
        this.getRobot().deverserEau(this.incendie.getIntensite());
        if (getRobot().getCurrReservoir() > 0) {
            this.incendie.setIntensite(0);
        } else {
            this.incendie.setIntensite(this.incendie.getIntensite() - volume);
        }
    }

    
}
