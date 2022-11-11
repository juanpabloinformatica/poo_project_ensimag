package events;
import robots.Robot;

public class Remplir extends Evenement {
    
    private Robot robot;

    public Remplir(Integer date, Robot robot) {
        super(date);
        this.robot = robot;
    }
    /*
     * remplir the robot water bag
     */
    @Override
    public void execute() {
        this.robot.setCurrReservoir(this.robot.getRESERVOIR());
    }
    
}
