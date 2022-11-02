package events;

import robots.Robot;

public class Deplacer extends Evenement {

    private Robot robot;

    public Deplacer(Integer date,Robot robot) {
        super(date);
        this.robot = robot;
        //TODO Auto-generated constructor stub
    }

    /*
     * Deplacer le robot 
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }

    
}
