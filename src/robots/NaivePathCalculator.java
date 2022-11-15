package robots;

import java.util.ArrayList;
import java.util.HashSet;

import classes.Carte;
import classes.Case;
import classes.Incendie;
import constants.Direction;
import constants.NatureTerrain;
import constants.TypeRobot;
import events.DeplacerEvenement;
import events.DisponibleEvenement;
import events.ArrivedEvenement;
import events.Evenement;
import events.OccupiedEvenement;
import events.RemplirEvenement;
import events.Simulateur;

public class NaivePathCalculator extends PathCalculator {
    ArrayList<Case> nextCases;
    public NaivePathCalculator(Simulateur sim, Carte carte) {
        super(sim, carte);
    }

    HashSet<Case> seenCases;
    private boolean searchPath(Robot r, Case current, Case target) {
        if (current == target)
            return true;
        //(-1, 0), (0, -1), (0, 1), (1, 0)
        int i_moves[] = {-1, 0, 0, 1};
        int j_moves[] = {0, -1, 1, 0};
        int curr_i = current.getLigne();
        int curr_j = current.getColonne();
        seenCases.add(current);
        for (int i = 0; i < 4; i++) {
            int next_i = curr_i + i_moves[i];
            int next_j = curr_j + j_moves[i];
            if (next_i >= 0 && next_i < getCarte().getNbLignes()
                && next_j >= 0 && next_j < getCarte().getNbColonnes()) {
                Case nextCase = this.getCarte().getCase(next_i, next_j);
                if (!seenCases.contains(nextCase) && r.canGo(nextCase)) {
                    nextCases.add(nextCase);
                    if (searchPath(r, nextCase, target))
                        return true;
                    // chemin non trouve
                    nextCases.remove(nextCase);
                }
            }
        }
        return false;
    }

    private boolean searchPathToWater(Robot r, Case current) {
        if (r.getTypeRobot() == TypeRobot.DRONE && current.getNatureTerrain() == NatureTerrain.EAU)
            return true;

        //(-1, 0), (0, -1), (0, 1), (1, 0)
        int i_moves[] = {-1, 0, 0, 1};
        int j_moves[] = {0, -1, 1, 0};
        int curr_i = current.getLigne();
        int curr_j = current.getColonne();
        seenCases.add(current);
        for (int i = 0; i < 4; i++) {
            int next_i = curr_i + i_moves[i];
            int next_j = curr_j + j_moves[i];
            if (next_i >= 0 && next_i < getCarte().getNbLignes()
                && next_j >= 0 && next_j < getCarte().getNbColonnes()) {
                Case nextCase = this.getCarte().getCase(next_i, next_j);
                if (!seenCases.contains(nextCase) && (r.canGo(nextCase) ||
                                                      nextCase.getNatureTerrain() == NatureTerrain.EAU)) {
                    if (r.getTypeRobot() != TypeRobot.DRONE
                         && nextCase.getNatureTerrain() == NatureTerrain.EAU)
                        return true;
                    nextCases.add(nextCase);
                    if (searchPathToWater(r, nextCase))
                        return true;
                    // chemin non trouve
                    nextCases.remove(nextCase);
                }
            }
        }
        return false;
    }


    // Renvoie le premiere chemin trouve mais pas forcement le plus court
    // OU null si aucun chemin a ete trouve
    @Override
    public ArrayList<Case> computePath(Robot r, Incendie i) {
        // REVIEW MONKEY PATCH
        if (r.getPosition() == i.getPosition())
            return null;

        nextCases = new ArrayList<Case>();
        seenCases = new HashSet<>();
        Case start = r.getPosition();
        Case target = i.getPosition();
        nextCases.add(r.getPosition());
        if (!searchPath(r, start, target))
            return null;
        return nextCases;
    }

    @Override
    // retourne le premier chemin vers une case d'eau
    public ArrayList<Case> computePathToWater(Robot r) {
        nextCases = new ArrayList<Case>();
        seenCases = new HashSet<>();
        nextCases.add(r.getPosition());
        if (!searchPathToWater(r, r.getPosition()))
            return null;
        return nextCases;
    }

}
