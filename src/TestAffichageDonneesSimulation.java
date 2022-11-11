
import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;
import gui.ImageElement;
import io.LecteurDonnees;
import classes.*;
import robots.*;
import constants.NatureTerrain;
import constants.Direction;
import events.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.awt.Color;

public class TestAffichageDonneesSimulation{
    public static void main(String[] args) throws FileNotFoundException {
        // crée la fenêtre graphique dans laquelle dessiner
        // GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        try {
            DonneesSimulation dS  = LecteurDonnees.creerDonneesSimulation(args[0]);
            int widthGui = dS.getCarte().getTailleCases()*dS.getCarte().getNbColonnes();
            int heightGui = dS.getCarte().getTailleCases()*dS.getCarte().getNbLignes();
            GUISimulator gui = new GUISimulator(widthGui,heightGui,Color.decode("#7ac270"));
            AffichageDonneesSimulation aFS = new AffichageDonneesSimulation(gui,dS);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch(DataFormatException e){
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
        // DonneesSimulation  = LecteurDonees.creerDoneesSimulation("....map")
        
    }
}

class AffichageDonneesSimulation implements Simulable {

    private GUISimulator gui;
    private int sizeCase;
    private Simulateur simulateur;
    private DonneesSimulation dS;
    // private Integer dateSimulation;
    // private ArrayList<Evenement>ordonne;
    // private int ordonneIndex;
    private void testDeplacerEvenement() {
        Robot robot = dS.getRobots()[0];

        simulateur.addEvenement(new DeplacerEvenement(0, robot, Direction.NORD, dS.getCarte()));
        simulateur.addEvenement(new DeplacerEvenement(1, robot, Direction.EST, dS.getCarte()));
        simulateur.addEvenement(new DeplacerEvenement(2, robot, Direction.OUEST, dS.getCarte()));
        simulateur.addEvenement(new DeplacerEvenement(3, robot, Direction.SUD, dS.getCarte()));
        simulateur.addEvenement(new DeplacerEvenement(4, robot, Direction.NORD, dS.getCarte()));
        simulateur.addEvenement(new DeplacerEvenement(5, robot, Direction.NORD, dS.getCarte()));
    }

    public AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
        // super(new Integer(0));
        // secondPointInit();
        firstPointInit(gui,dS);
        simulateur = new Simulateur(gui);
        testDeplacerEvenement();

        // this.gui = gui;
        // gui.setSimulable(this);
        // Carte carte = dS.getCarte();
        // this.sizeCase = carte.getTailleCases();
        // drawCarte(carte);
        // drawIncendies(dS.getIncendies());
        // drawRobots(dS.getRobots());
        // testingMovement();
    }
    private void firstPointInit(GUISimulator gui,DonneesSimulation dS){
        this.dS = dS;
        this.gui = gui;
        gui.setSimulable(this);
        this.sizeCase = dS.getCarte().getTailleCases();
        draw();
        // testingMovement();
    }

    @Override
    public void next() {
        simulateur.incrementeDate();
        draw();
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub

    }
    private void draw() {
        gui.reset();
        drawCarte(dS.getCarte());
        drawIncendies(dS.getIncendies());
        drawRobots(dS.getRobots());
    }
    // private void secondPointInit(){
    //     this.dateSimulation = new Integer(0);
    //     this.ordonne = new ArrayList<>();
    //     this.ordonneIndex = new Integer(0);
    //     this.ordonne.add(this.ordonneIndex,this);
    //     this.incrementDate();
    // }


    /*
     * Affiche la carte avec une image differente pour chaque natureTerrain
     */
    private void drawCarte(Carte carte) {
        for(int i = 0 ; i< carte.getNbLignes()*carte.getTailleCases() ; i+=carte.getTailleCases()){
            for(int j = 0 ; j<carte.getNbColonnes()*carte.getTailleCases() ; j+=carte.getTailleCases()){
                NatureTerrain typeTerrainImage = getTypeTerrainImage(carte,i/carte.getTailleCases(),j/carte.getTailleCases());
                try{
                    if (typeTerrainImage != NatureTerrain.TERRAIN_LIBRE) {
                        String imageString = selectImage(typeTerrainImage);
                        this.gui.addGraphicalElement(new ImageElement(j, i,imageString,carte.getTailleCases(),carte.getTailleCases(), this.gui));
                    }
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    private NatureTerrain getTypeTerrainImage(Carte carte,int i,int j){
        return carte.getCase(i,j).getNatureTerrain();
    }
    private String selectImage(NatureTerrain typeNatureTerrain){
        String selectImage = null;
        try{
            if(typeNatureTerrain == NatureTerrain.EAU){
                selectImage = "img/water.png";
            }else if(typeNatureTerrain == NatureTerrain.FORET){
                selectImage = "img/forest.png";
            }else if(typeNatureTerrain == NatureTerrain.HABITAT){
                selectImage = "img/habitat.png";
            }else if(typeNatureTerrain == NatureTerrain.TERRAIN_LIBRE){
                selectImage = "img/neymar_animado(terrrain_libre).jpg";
            }else{
                selectImage = "img/roche.png";
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return selectImage;
    }
    private void drawIncendies(Incendie[] incendies) {
        for (Incendie i : incendies) {
            int lig = i.getPos().getLigne();
            int col = i.getPos().getColonne();
            this.gui.addGraphicalElement(new ImageElement(col * this.sizeCase, lig * this.sizeCase, "img/fire.png", this.sizeCase, this.sizeCase, this.gui));
        }

    }
    private void drawRobots(Robot[] robots) {
        for (int i = 0; i < robots.length; i++) {
            drawRobot(robots[i]);
        }
    }
    private void drawRobot(Robot robot) {
        int i = robot.getPosition().getLigne();
        int j = robot.getPosition().getColonne();
        Color color = Color.decode("#f2ff28");
        switch(robot.getTypeRobot()) {
            case DRONE:
                gui.addGraphicalElement(new ImageElement(j * sizeCase,
                                                         i * sizeCase, "img/drone.png", this.sizeCase, this.sizeCase, null));
                break;
            case CHENILLES:
                gui.addGraphicalElement(new ImageElement(j * sizeCase,
                                                         i * sizeCase, "img/chenilles.png", this.sizeCase, this.sizeCase, null));
                break;
            case ROUES:
                gui.addGraphicalElement(new ImageElement(j * sizeCase,
                                                         i * sizeCase, "img/roues.png", this.sizeCase, this.sizeCase, null));
                break;
            case PATTES:
                gui.addGraphicalElement(new ImageElement(j * sizeCase,
                                                         i * sizeCase, "img/pattes.png", this.sizeCase, this.sizeCase, null));
                break;
        }
    }

    // public void ajouteEvenement(Evenement e){
    //     this.ordonne.add(e);
    // }
    // private void incrementDate(){
    //     this.ordonneIndex++;
    // }
    /*
     * this 
     */
    // private boolean simulationTerminee(){
    //     for(Evenement e:this.ordonne){
    //         if(e.getEventDone()==false){
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    /*
     * this function just will test that the objects are moving
     */
    // private void testingMovement(){
    

    // }

    /*
     * this method run the first event
     */
    

   
}
