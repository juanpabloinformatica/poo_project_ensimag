package robots;

import classes.Carte;
import classes.Case;
import classes.Incendie;
import events.Simulateur;

import java.util.*;;

public class Dijkstra extends PathCalculator {
  
  public Dijkstra(Simulateur simulateur, Carte carte) {
    super(simulateur, carte);
  }

  public ArrayList<Case> dijkstra(Robot robot, Case destination) {
    Map<Case,Integer> costs = new HashMap<Case, Integer>();
    Set<Case> visited = new HashSet<Case>();
    ArrayList<Case> shortestPath = new ArrayList<Case>();
    Map<Case,Case> backtracker = new HashMap<Case,Case>();
    int nbLignes = this.getCarte().getNbLignes();
    int nbColonnes = this.getCarte().getNbColonnes();
    for (int i = 0; i < nbLignes; i++) {
      for (int j = 0; j < nbColonnes; j++) {
        costs.put(this.getCarte().getCase(i, j), Integer.MAX_VALUE);
      }
    }
    costs.put(robot.getPosition(), 0);
    while (visited.size() < nbLignes * nbColonnes) {
      Case curr = getLowestDistance(costs, visited);
      if (curr == null) {
        return null;
      }
      visited.add(curr);
      for (Map.Entry<Case, Integer> couple : getAdjacentNodes(this.getCarte(), curr, robot, this.getCarte().getTailleCases()).entrySet()) {
        Case adjacent = couple.getKey();
        int distance = couple.getValue();
        if (!(visited.contains(adjacent))) {
          updateHashMap(costs, adjacent, distance, curr, backtracker);
        }
      }
    }
    Case previous = destination;
    while (!previous.equals(robot.getPosition())) {
      shortestPath.add(0, previous);
      previous = backtracker.get(previous);
    }
    shortestPath.add(0, previous);
    return shortestPath;
  }

  public Case getLowestDistance(Map<Case,Integer> costs, Set<Case> visited) {
    int lowest = Integer.MAX_VALUE;
    Case min = null;
    for (Case cell : costs.keySet()) {
      if ((!visited.contains(cell)) && costs.get(cell) < lowest) {
        lowest = costs.get(cell);
        min = cell;
      }
    }
    return min;
  }

  public Map<Case,Integer> getAdjacentNodes(Carte carte, Case curr, Robot robot, int sizeCase) {
    Map<Case,Integer> adjacentNodes = new HashMap<Case,Integer>();
    int ligne = curr.getLigne();
    int colonne = curr.getColonne();
    int time = (int)(sizeCase/robot.getVitesseNature(curr.getNatureTerrain()));
    if (ligne != 0) {
      if (robot.canGo(carte.getCase(ligne - 1, colonne))) {
        adjacentNodes.put(carte.getCase(ligne - 1, colonne), time);
      }
    }
    if (ligne != carte.getNbLignes() - 1) {
      if (robot.canGo(carte.getCase(ligne + 1, colonne))) {
        adjacentNodes.put(carte.getCase(ligne + 1, colonne), time);
      }
    }
    if (colonne != 0) {
      if (robot.canGo(carte.getCase(ligne, colonne - 1))) {
        adjacentNodes.put(carte.getCase(ligne, colonne - 1), time);
      }
    }
    if (colonne != carte.getNbColonnes() - 1) {
      if (robot.canGo(carte.getCase(ligne, colonne + 1))) {
        adjacentNodes.put(carte.getCase(ligne, colonne + 1), time);
      }
    }
    return adjacentNodes;
  }

  public void updateHashMap(Map<Case,Integer> costs, Case adjacent, int distance, Case curr, Map<Case,Case> backtracker) {
    int sourceDistance = costs.get(curr);
    if (sourceDistance + distance < costs.get(adjacent)) {
        costs.put(adjacent, sourceDistance + distance);
        backtracker.put(adjacent, curr);
    }
  }

  @Override
  public ArrayList<Case> computePath(Robot r, Incendie i) {
    return dijkstra(r, i.getPosition());
  }

  @Override
  public ArrayList<Case> computePathToWater(Robot r) {
    // TODO Auto-generated method stub
    return null;
  }
}