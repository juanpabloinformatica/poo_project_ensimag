package events;

import classes.Case;
import robots.Robot;

public class Intervenir extends Evenement {

    // private Case caseI;
    private Robot robot;
    public Intervenir(Integer date, Robot robot) {
        super(date);
        this.robot = robot;
        // this.caseI = caseI;
        //TODO Auto-generated constructor stub
    }

    /*
     * marc the case that the robot has made something
     */
    @Override
    public void execute() {
        
        // TODO Auto-generated method stub
        
    }
    
}
