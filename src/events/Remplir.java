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
        // TODO: HOW assert that the robot is next to a water case?
        // int[] dec = {0, 1, -1};
        // boolean isNextWater = false;
        // for (int i: dec) {
        //     for (int j: dec) {
        //         if
        //     }
        // }
        robot.remplirReservoir();

        // update the reservoir according to the tempsRemplissage
        // TODO Auto-generated method stub
        
    }
    
}
