package events;

import robots.Robot;

public class OccupiedEvenement extends Evenement {

    public OccupiedEvenement (int date, Robot robot) {
        super(date, robot);
    }

    public Evenement execute() {
        getRobot().setOccupied(true);
        return null;
    }
}
