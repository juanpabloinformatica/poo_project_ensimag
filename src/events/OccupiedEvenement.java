package events;

import robots.RobotLogic;

public class OccupiedEvenement extends Evenement {

    public OccupiedEvenement (int date, RobotLogic robotLogic) {
        super(date, robotLogic);
    }

    public void execute() {
        getRobotLogic().setOccupied(true);
    }
}