package events;

import robots.Robot;

public class OccupiedEvenement extends Evenement {

    public OccupiedEvenement (int date, Robot robot) {
        super(date, robot);
    }

    public void execute() {
        getRobot().setOccupied(true);
    }
}
