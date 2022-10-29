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

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
    }
}
