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
import gui.Rectangle;
import io.LecteurDonnees;
import robots.Dijkstra;
import robots.NaivePathCalculator;
import robots.Robot;
import robots.RobotLogic;

public class TestAffichageDonneesSimulation {
  
  /** 
   * initialize the program receiving arguments 
   * @param args - arguments 
   * @throws FileNotFoundException exception is the file doesn't exists
   */
  public static void main(String[] args) throws FileNotFoundException {
    // crée la fenêtre graphique dans laquelle dessiner
    // GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
    // crée l'invader, en l'associant à la fenêtre graphique précédente

    try {
      DonneesSimulation dS = LecteurDonnees.creerDonneesSimulation(args[0]);
      // int widthGui = dS.getCarte().getTailleCases() * dS.getCarte().getNbColonnes();
      // int heightGui = dS.getCarte().getTailleCases() * dS.getCarte().getNbLignes();
      GUISimulator gui = new GUISimulator(600, 600, Color.decode("#d9c582"));
      AffichageDonneesSimulation aFS = new AffichageDonneesSimulation(gui, dS);
    } catch (FileNotFoundException e) {
      System.out.println("fichier " + args[0] + " inconnu ou illisible");
    } catch (DataFormatException e) {
      System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
    }
    // DonneesSimulation  = LecteurDonees.creerDoneesSimulation("....map")

  }
}

/**
 * this class AffichageDonneesSimulation represents the images associated to the data recolected 
 * and its possible events
 * 
 */
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
  /**
   * create the grpahical representation of the data recolected and its possible events
   * @param gui - graphical user interface
   * @param dS - donnee simulation
   */
  public AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
    // super(new Integer(0));
    // secondPointInit();
    firstPointInit(gui, dS);
    //testDeplacerEvenement();
    ChefPompier chefPompier = new ChefPompier(dS.getCarte(), dS.getRobots(), dS.getIncendies());
    simulateur = new Simulateur(gui, chefPompier);
    RobotLogic.InitStaticVariables(simulateur, dS.getCarte(), chefPompier);
    // NaivePathCalculator npc = new NaivePathCalculator(simulateur, dS.getCarte());
    // Dijkstra pc = new Dijkstra(simulateur, dS.getCarte());
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
    draw();
    // testingMovement();
  }

  @Override
  public void next() {
    simulateur.next();
    draw();
  }

  @Override
  public void restart() {
    simulateur.restart();
    for(Incendie i: dS.getIncendies())
      i.restart();
    draw();
  }

  private void draw() {
    gui.reset();
    int height = gui.getHeight()-170; //panel height
    int width = gui.getWidth();
    int min = height > width ? width : height;
    this.pixelSizeCase = min / dS.getCarte().getNbColonnes();
    drawGrid(dS.getCarte());
    drawCarte(dS.getCarte());
    drawIncendies(dS.getIncendies());
    drawRobots(dS.getRobots());
  }

  private void drawGrid(Carte carte) {
    for (int i = 0; i < carte.getNbLignes(); i++) {
      for (int j = 0; j < carte.getNbColonnes(); j++) {
        gui.addGraphicalElement(
            new Rectangle(
                j * pixelSizeCase + pixelSizeCase/2, // bc its drew at the center
                i * pixelSizeCase + pixelSizeCase/2, Color.BLACK,
                Color.decode("#7ac270"),
                this.pixelSizeCase, this.pixelSizeCase));

      }
    }
  }
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
      } else if (typeNatureTerrain == NatureTerrain.ROCHE) {
        selectImage = "img/roche.png";
      } else {
        throw new Exception("Not image found for Nature Terrain : " + typeNatureTerrain);
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
}
