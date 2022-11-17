import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import classes.Carte;
import classes.ChefPompier;
import classes.DonneesSimulation;
import classes.Incendie;
import constants.NatureTerrain;
import events.Simulateur;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import io.LecteurDonnees;
import robots.Dijkstra;
import robots.NaivePathCalculator;
import robots.Robot;
import robots.RobotLogic;

public class TestAffichageDonneesSimulation {
  public static void main(String[] args) throws FileNotFoundException {
    // crée la fenêtre graphique dans laquelle dessiner
    // GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
    // crée l'invader, en l'associant à la fenêtre graphique précédente
    try {
      DonneesSimulation dS = LecteurDonnees.creerDonneesSimulation(args[0]);
      // int widthGui = dS.getCarte().getTailleCases() * dS.getCarte().getNbColonnes();
      // int heightGui = dS.getCarte().getTailleCases() * dS.getCarte().getNbLignes();
      GUISimulator gui = new GUISimulator(600, 600, Color.decode("#7ac270"));
      AffichageDonneesSimulation aFS = new AffichageDonneesSimulation(gui, dS);
    } catch (FileNotFoundException e) {
      System.out.println("fichier " + args[0] + " inconnu ou illisible");
    } catch (DataFormatException e) {
      System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
    }
    // DonneesSimulation  = LecteurDonees.creerDoneesSimulation("....map")

  }
}

class AffichageDonneesSimulation implements Simulable {

  private GUISimulator gui;
  private int pixelSizeCase;
  private Simulateur simulateur;
  private DonneesSimulation dS;
  // private Integer dateSimulation;
  // private ArrayList<Evenement>ordonne;
  // private int ordonneIndex;
  private void testDeplacerEvenement() {
    // Robot robot = dS.getRobots()[0];
    // simulateur.addEvenement(new DeplacerEvenement(0, robot, Direction.NORD, dS.getCarte()));
    // simulateur.addEvenement(new DeplacerEvenement(1, robot, Direction.EST, dS.getCarte()));
    // simulateur.addEvenement(new DeplacerEvenement(2, robot, Direction.OUEST, dS.getCarte()));
    // simulateur.addEvenement(new DeplacerEvenement(3, robot, Direction.SUD, dS.getCarte()));
    // simulateur.addEvenement(new DeplacerEvenement(4, robot, Direction.NORD, dS.getCarte()));
    // simulateur.addEvenement(new DeplacerEvenement(5, robot, Direction.NORD, dS.getCarte()));
    // NaivePathCalculator npc = new NaivePathCalculator(simulateur, dS.getCarte());
    // robot.setPathCalculator(npc);
    //npc.computePath(robot,]);
    // robot.propose(dS.getIncendies()[0]);
  }

  public AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
    // super(new Integer(0));
    // secondPointInit();
    firstPointInit(gui, dS);
    //testDeplacerEvenement();
    ChefPompier chefPompier = new ChefPompier(dS.getCarte(), dS.getRobots(), dS.getIncendies());
    simulateur = new Simulateur(gui, chefPompier);
    // NaivePathCalculator npc = new NaivePathCalculator(simulateur, dS.getCarte());
    Dijkstra pc = new Dijkstra(simulateur, dS.getCarte());
    RobotLogic.InitialiateStaticVariables(simulateur, dS.getCarte(), pc, chefPompier);
    //robot.propose(dS.getIncendies()[0]);

    // this.gui = gui;
    // gui.setSimulable(this);
    // Carte carte = dS.getCarte();
    // this.pixelSizeCase = carte.getTailleCases();
    // drawCarte(carte);
    // drawIncendies(dS.getIncendies());
    // drawRobots(dS.getRobots());
    // testingMovement();
  }

  private void firstPointInit(GUISimulator gui, DonneesSimulation dS) {
    this.dS = dS;
    this.gui = gui;
    gui.setSimulable(this);
    int height = gui.getPanelHeight();
    int width = gui.getPanelWidth();
    int min = height > width ? width : height;
    this.pixelSizeCase = 25;
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
    for (int i = 0; i < carte.getNbLignes() * pixelSizeCase; i += pixelSizeCase) {
      for (int j = 0;
          j < carte.getNbColonnes() * pixelSizeCase;
          j += pixelSizeCase) {
        NatureTerrain typeTerrainImage =
            getTypeTerrainImage(carte, i / pixelSizeCase, j / pixelSizeCase);
        try {
          if (typeTerrainImage != NatureTerrain.TERRAIN_LIBRE) {
            String imageString = selectImage(typeTerrainImage);
            this.gui.addGraphicalElement(
                new ImageElement(
                    j, i, imageString, pixelSizeCase, pixelSizeCase, this.gui));
          }
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }

  private NatureTerrain getTypeTerrainImage(Carte carte, int i, int j) {
    return carte.getCase(i, j).getNatureTerrain();
  }

  private String selectImage(NatureTerrain typeNatureTerrain) {
    String selectImage = null;
    try {
      if (typeNatureTerrain == NatureTerrain.EAU) {
        selectImage = "img/water.png";
      } else if (typeNatureTerrain == NatureTerrain.FORET) {
        selectImage = "img/forest.png";
      } else if (typeNatureTerrain == NatureTerrain.HABITAT) {
        selectImage = "img/habitat.png";
      } else if (typeNatureTerrain == NatureTerrain.TERRAIN_LIBRE) {
        selectImage = "img/neymar_animado(terrrain_libre).jpg";
      } else {
        selectImage = "img/roche.png";
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return selectImage;
  }

  private void drawIncendies(Incendie[] incendies) {
    for (Incendie i : incendies) {
      if (i.getIntensite() <= 0)
        continue;
      int lig = i.getPosition().getLigne();
      int col = i.getPosition().getColonne();
      this.gui.addGraphicalElement(
          new ImageElement(
              col * this.pixelSizeCase,
              lig * this.pixelSizeCase,
              "img/fire.png",
              this.pixelSizeCase,
              this.pixelSizeCase,
              this.gui));
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
    switch (robot.getTypeRobot()) {
      case DRONE:
        gui.addGraphicalElement(
            new ImageElement(
                j * pixelSizeCase, i * pixelSizeCase, "img/drone.png", this.pixelSizeCase, this.pixelSizeCase, null));
        break;
      case CHENILLES:
        gui.addGraphicalElement(
            new ImageElement(
                j * pixelSizeCase,
                i * pixelSizeCase,
                "img/chenilles.png",
                this.pixelSizeCase,
                this.pixelSizeCase,
                null));
        break;
      case ROUES:
        gui.addGraphicalElement(
            new ImageElement(
                j * pixelSizeCase, i * pixelSizeCase, "img/roues.png", this.pixelSizeCase, this.pixelSizeCase, null));
        break;
      case PATTES:
        gui.addGraphicalElement(
            new ImageElement(
                j * pixelSizeCase, i * pixelSizeCase, "img/pattes.png", this.pixelSizeCase, this.pixelSizeCase, null));
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
