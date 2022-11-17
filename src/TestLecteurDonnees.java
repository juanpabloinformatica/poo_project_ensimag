
import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import classes.*;

public class TestLecteurDonnees {

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            LecteurDonnees.lire(args[0]);
            DonneesSimulation ds = LecteurDonnees.creerDonneesSimulation(args[0]);
            System.out.println(ds);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
    }

}

