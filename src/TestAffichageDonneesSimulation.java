import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import classes.Carte;
import classes.DonneesSimulation;
import classes.Incendie;
import constants.NatureTerrain;
import gui.*;
import io.LecteurDonnees;
import robots.Robot;

public class TestAffichageDonneesSimulation{
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        // GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        try {
            DonneesSimulation dS  = LecteurDonnees.creerDonneesSimulation(args[0]);
             int widthGui = dS.getCarte().getTailleCases()*dS.getCarte().getNbColonnes();
             int heightGui = dS.getCarte().getTailleCases()*dS.getCarte().getNbLignes();
             GUISimulator gui = new GUISimulator(widthGui,heightGui,Color.black);
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

    public AffichageDonneesSimulation(GUISimulator gui, DonneesSimulation dS) {
        this.gui = gui;
        gui.setSimulable(this);
        drawCarte(dS.getCarte());
        drawIncendies(dS.getIncendies());
        drawRobots(dS.getRobots());
    }
    // private NatureTerrain getNatureTerrain(DonneesdS){

    // }

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
        //resize the image
        NatureTerrain typeTerrainImage = getTypeTerrainImage(carte);
        String imageString = new String();
        try{
            imageString = selectImage(typeTerrainImage);
        }catch(Exception e){
            System.out.println(e);
        }
        // image = resizeImage(image,carte);
        for(int i = 0 ; i< carte.getNbLignes()*carte.getTailleCases() ; i+=carte.getTailleCases()){
            for(int j = 0 ; j<carte.getNbColonnes()*carte.getTailleCases() ; j+=carte.getTailleCases()){
                this.gui.addGraphicalElement(new ImageElement(i, j,imageString,carte.getTailleCases(),carte.getTailleCases(), this.gui));
            }
        }
    }
    private NatureTerrain getTypeTerrainImage(Carte carte){
        return carte.getCase(0, 0).getNatureTerrain();
    }
    private String selectImage(NatureTerrain typeNatureTerrain){
        String selectImage = null;
        try{
            if(typeNatureTerrain == NatureTerrain.EAU){
                selectImage = "img/eau.jpg";
            }else if(typeNatureTerrain == NatureTerrain.FORET){
                selectImage = "img/foret.jpg";
            }else if(typeNatureTerrain == NatureTerrain.HABITAT){
                selectImage = "img/habitat.jpg";
            }else if(typeNatureTerrain == NatureTerrain.TERRAIN_LIBRE){
                selectImage = "img/neymar_animado(terrrain_libre).jpg";
            }else{
                selectImage = "img/roche.jpg";
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return selectImage;
    }
    private  Image resizeImage(Image image,Carte carte){
        return image.getScaledInstance(carte.getTailleCases()/2,carte.getTailleCases()/2,Image.SCALE_DEFAULT);
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
