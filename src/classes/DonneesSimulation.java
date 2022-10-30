package classes;
import robots.*;

public class DonneesSimulation {
    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;


    public DonneesSimulation(Carte carte, Incendie[] incendies, Robot[] robots){
        // REVIEW: have we to hard copy arrays???
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }
    public Incendie[] getIncendies() {
        return incendies;
    }
    public Robot[] getRobots() {
        return robots;
    }

    @Override
    public String toString() {
        String res = "Carte\n" + carte.toString() + "\n Incendies : \n";
        for (int i = 0; i < incendies.length; i++)
            res += incendies[i].toString();

        res += "\n";
        for (int i = 0; i < robots.length; i++)
            res += robots[i].toString();
        return res;
    }
}
