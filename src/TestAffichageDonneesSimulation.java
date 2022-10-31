import gui.GUISimulator;
import gui.Simulable;
import gui.Rectangle;
import gui.ImageElement;
import io.LecteurDonnees;
import classes.*;
import robots.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.zip.DataFormatException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class TestAffichageDonneesSimulation{
    public static void main(String[] args) throws FileNotFoundException {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 800, Color.WHITE);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        try {
            DonneesSimulation dS = LecteurDonnees.creerDonneesSimulation(args[0]);
            System.out.println(dS);
            AffichageDonneesSimulation aFS = new AffichageDonneesSimulation(gui, dS);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }
}

class AffichageDonneesSimulation implements Simulable {
    private GUISimulator gui;
    private int sizeCase;
    public AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
        this.gui = gui;
        gui.setSimulable(this);
        this.sizeCase = 50;
        drawCarte(dS.getCarte());
        drawIncendies(dS.getIncendies());
        drawRobots(dS.getRobots());
    }
    @Override
    public void next() {
    }

    @Override
    public void restart() {
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
        for (int i = 0; i < robots.length; i++) {
            drawRobot(robots[i]);
        }
    }
    private void drawRobot(Robot robot) {
        int x = robot.getPosition().getLigne();
        int y = robot.getPosition().getColonne();
        Color color = Color.decode("#f2ff28");

        // ImageObserverDemo obs = new ImageObserverDemo();
        switch(robot.getTypeRobot()) {
            case DRONE:
                gui.addGraphicalElement(new ImageElement(x * sizeCase,
                                                         y * sizeCase, "img/drone.png", this.sizeCase, this.sizeCase, null));
                break;
            case CHENILLES:
                gui.addGraphicalElement(new ImageElement(x * sizeCase,
                                                         y * sizeCase, "img/chenilles.png", this.sizeCase, this.sizeCase, null));
                break;
            case ROUES:
                gui.addGraphicalElement(new ImageElement(x * sizeCase,
                                                         y * sizeCase, "img/roues.png", this.sizeCase, this.sizeCase, null));
                break;
            case PATTES:
                gui.addGraphicalElement(new ImageElement(x * sizeCase,
                                                         y * sizeCase, "img/pattes.png", this.sizeCase, this.sizeCase, null));
                break;
        }
    }
}
