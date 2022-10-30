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
}
