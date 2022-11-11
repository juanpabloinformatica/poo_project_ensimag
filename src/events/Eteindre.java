package events;

import classes.Case;
import classes.Incendie;
import robots.Robot;

public class Eteindre extends Evenement {

    Incendie incendie;
    Robot robot;
    public Eteindre(Integer date,Incendie incendie, Robot robot) {
        super(date);
        this.incendie = incendie;
        this.robot = robot;
        //TODO Auto-generated constructor stub
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        int volume = this.robot.getCurrReservoir();
        this.robot.deverserEau(this.incendie.getIntensite());
        if (robot.getCurrReservoir() > 0) {
            this.incendie.setIntensite(0);
        } else {
            this.incendie.setIntensite(this.incendie.getIntensite() - volume);
        }
    }

    
}
