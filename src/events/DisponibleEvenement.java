package events;

import robots.Robot;

public class DisponibleEvenement extends Evenement {

    public DisponibleEvenement(int date, Robot robot) {
        super(date, robot);
    }

    public void execute() {
        getRobot().setOccupied(false);
    }
}
