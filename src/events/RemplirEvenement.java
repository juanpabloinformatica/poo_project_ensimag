package events;
import robots.Robot;

public class RemplirEvenement extends Evenement {
    public RemplirEvenement(Integer date, Robot robot) {
        super(date, robot);
    }
    /*
     * remplir the robot water bag
     */
    @Override
    public void execute() {
        getRobot().setCurrReservoir(getRobot().getRESERVOIR());
    }
    
}
