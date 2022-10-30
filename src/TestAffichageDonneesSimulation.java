import java.awt.Color;
import gui.GUISimulator;
import gui.Simulable;
import robots.Robot;

public class TestAffichageDonneesSimulation{
    // public static void main(String[] args) {
    //     // crée la fenêtre graphique dans laquelle dessiner
    //     GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
    //     // crée l'invader, en l'associant à la fenêtre graphique précédente
    //     DonneesSimulation  = LecteurDonees.creerDoneesSimulation("....map");
    //     AffichageDonneesSimulation aFS = new AffichageDonneesSimulation(gui);
    // }
}

class AffichageDonneesSimulation implements Simulable {

    private GUISimulator gui;

    public  AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
        this.gui = gui;
        gui.setSimulable(this);
        drawCarte(dS.getCarte());
        drawIncendies(dS.getIncendies());
        drawRobots(dS.getRobots());
    }

    /*
     * Affiche la carte avec une image differente pour chaque natureTerrain
     */
    private void drawCarte(Carte carte) {
        // TODO: drawCarte with images if possible (cf. ImageElement in the doc folder)
        // cf.TestInvader
        // for (int i ) {
        //     for (int j) {
        //     }
        // }
    }
    private void drawIncendies(Incendie[] incendies) {
        // TODO: drawIncendies with images if possible (cf. ImageElement in the doc folder)
        // cf.TestInvader
        // for (int i ) {
        // }

    }
    private void drawRobots(Robot[] robots) {
        // TODO: drawRobots with images if possible
        // every robot should had a different images
        // (cf. ImageElement in the doc folder)
        // cf.TestInvader
    }

    @Override
    public void next() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub
        
    }
}
