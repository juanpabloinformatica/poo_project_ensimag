package robots;

import java.util.ArrayList;
import java.util.HashSet;

import classes.Carte;
import classes.Case;
import classes.Incendie;
import constants.Direction;
import events.DeplacerEvenement;
import events.DisponibleEvenement;
import events.ArrivedEvenement;
import events.Evenement;
import events.OccupiedEvenement;
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
    private Direction calculateDirection(Case prev, Case next) {
        try {
            int diff_i = prev.getLigne() - next.getLigne();
            int diff_j = prev.getColonne() - next.getColonne();
            if (diff_i == 0) {
                if (diff_j == -1)
                    return Direction.EST;
                else if (diff_j == 1)
                    return Direction.OUEST;
                else
                    throw new Exception("CalculateDirection: Cases non contigues");
            } else if (diff_j == 0) {
                if (diff_i == -1)
                    return Direction.SUD;
                else if (diff_i == 1)
                    return Direction.NORD;
                else
                    throw new Exception("CalculateDirection: Cases non contigues");
            } else {
                    throw new Exception("CalculateDirection: Cases non contigues");
            }

        } catch (Exception e) {
            return null;
        }
    }

    private Integer calculateArrivalDate(Robot robot, Case curr, int date, int sizeCase) {
        return date + (int)(sizeCase/robot.getVitesseNature(curr.getNatureTerrain()));
    }
    // Convertit le tableau des Cases en Path (directions et date d'arrivee)
    private Path convertToPath(Robot robot, Integer currentDate) {
        Path path = new Path();
        Case prev = nextCases.get(0);
        int prevDate = getSimulateur().getDateSimulation();
        for (int i = 1; i < nextCases.size(); i++) {
            Case curr = nextCases.get(i);
            Direction d = calculateDirection(prev, curr);
            Integer date = calculateArrivalDate(robot, curr, prevDate, getCarte().getTailleCases());
            prevDate = date;
            path.addNextMove(d, date);
            prev = curr;
        }
        return path;
    }
    // Renvoie le premiere chemin trouve mais pas forcement le plus court
    // OU null si aucun chemin a ete trouve
    @Override
    public Path computePath(Robot r, Incendie i) {
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
        return convertToPath(r, getSimulateur().getDateSimulation());
    }

    @Override
    public void addPathEventsToSimulateur(Robot r, Incendie incendie, Path path) {
        ArrayList<Integer> dates = path.getDates();
        ArrayList<Direction> directions = path.getNextMoves();
        getSimulateur().addEvenement(new OccupiedEvenement(dates.get(0), r));
        for(int i = 0; i < dates.size(); i++) {
            getSimulateur().addEvenement(new DeplacerEvenement(dates.get(i), r, directions.get(i), getCarte()));
        }
        getSimulateur().addEvenement(new ArrivedEvenement(dates.get(dates.size()-1)+1, r, incendie));
    }
}
