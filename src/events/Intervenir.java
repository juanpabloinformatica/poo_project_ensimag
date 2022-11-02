package events;

import classes.Case;

public class Intervenir extends Evenement {

    private Case caseI;
    public Intervenir(Integer date, Case caseI) {
        super(date);
        this.caseI = caseI;
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
