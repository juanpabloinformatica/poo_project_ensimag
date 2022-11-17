package robots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import classes.Carte;
import classes.Case;
import constants.NatureTerrain;
import constants.TypeRobot;
import events.Simulateur;;

public class Dijkstra extends PathCalculator {

  int timeNeeded;
  ArrayList<Case> shortestPath;
  
  public Dijkstra(Simulateur simulateur, Carte carte) {
    super(carte);
  }

  public boolean dijkstra(Robot robot, Case destination) {
    Map<Case,Integer> costs = new HashMap<Case, Integer>();
    Set<Case> visited = new HashSet<Case>();
    //ArrayList<Case> shortestPath = new ArrayList<Case>();
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
      if (curr == destination) break;
      if (curr == null) return false;
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
    timeNeeded = costs.get(destination);
    return true;
  }

  public Case getLowestDistance(Map<Case,Integer> costs, Set<Case> visited) {
    int lowest = Integer.MAX_VALUE;
    Case min = null;
    for (Case cell : costs.keySet()) {
      if ((!visited.contains(cell)) && costs.get(cell) <= lowest) {
        lowest = costs.get(cell);
        min = cell;
      }
    }
    if (lowest == Integer.MAX_VALUE) {
      return null;
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
  public ArrayList<Case> computePath(Robot r, Case target) {
    shortestPath = new ArrayList<Case>();
    timeNeeded = Integer.MAX_VALUE;
    if (dijkstra(r, target)) {
      assert (shortestPath.size() != 0);
      return shortestPath;
    }
    return null;
  }

  @Override
  public ArrayList<Case> computePathToWater(Robot r) {
    ArrayList<Case> closestPathWater = new ArrayList<Case>();
    int smallestTime = Integer.MAX_VALUE;
    int nbLignes = this.getCarte().getNbLignes();
    int nbColonnes = this.getCarte().getNbColonnes();
    if (r.getTypeRobot() == TypeRobot.DRONE) {
      for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            if (this.getCarte().getCase(i, j).getNatureTerrain() == NatureTerrain.EAU) {
              shortestPath = new ArrayList<Case>();
              boolean found = dijkstra(r, this.getCarte().getCase(i, j));
              if (found && this.timeNeeded < smallestTime) {
                smallestTime = this.timeNeeded;
                closestPathWater = this.shortestPath;
              }
          }
        }
      }
    } else {
      for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            if (this.getCarte().getCase(i, j).getNatureTerrain() == NatureTerrain.EAU) {
              int i_moves[] = {-1, 0, 0, 1};
              int j_moves[] = {0, -1, 1, 0};
              for (int k = 0; k < 4; k++) {
                int next_i = i + i_moves[k];
                int next_j = j + j_moves[k];
                if (next_i >= 0 && next_i < nbLignes && next_j >= 0 && next_j < nbColonnes) {
                  shortestPath = new ArrayList<Case>();
                  boolean found = dijkstra(r, this.getCarte().getCase(next_i, next_j));
                  if (found && this.timeNeeded < smallestTime) {
                    smallestTime = this.timeNeeded;
                    closestPathWater = this.shortestPath;
                  }
                }
              }
          }
        }
      }
    }
    if (smallestTime == Integer.MAX_VALUE) {
      System.out.println(":( max value");
      return null;
    }
    System.out.println("closesetPathWater : " + closestPathWater);
    return closestPathWater;
  }

  @Override
  public double getTimeToCase(Robot r, Case target) {
    shortestPath = new ArrayList<Case>();
    boolean pathFound = dijkstra(r, target);
    if (pathFound) {
      return (double) this.timeNeeded;
    }
    return Double.POSITIVE_INFINITY;
  }
}
