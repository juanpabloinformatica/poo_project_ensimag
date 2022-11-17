package events;

import classes.ChefPompier;
import robots.RobotLogic;

public class DisponibleEvenement extends Evenement {
    private ChefPompier chefPompier;

    public DisponibleEvenement(int date, RobotLogic robotLogic,
                               ChefPompier chefPompier) {
        super(date, robotLogic);
        this.chefPompier = chefPompier;
    }

    public void execute() {
        System.out.println("disponible!!");
        getRobotLogic().setOccupied(false);
        chefPompier.strategieElementaire();
    }
}
