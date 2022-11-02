package events;

import classes.Case;

public class Eteindre extends Evenement {

    Case caseE;
    public Eteindre(Integer date,Case caseE) {
        super(date);
        this.caseE = caseE;
        //TODO Auto-generated constructor stub
    }
    /*
     * put out the fire in certain case
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }

    
}
