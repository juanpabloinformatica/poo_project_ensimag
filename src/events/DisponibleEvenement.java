package events;

import robots.RobotLogic;

public class DisponibleEvenement extends Evenement {

    public DisponibleEvenement(int date, RobotLogic robotLogic) {
        super(date, robotLogic);
    }

    public void execute() {
        getRobotLogic().setOccupied(false);
    }
}
