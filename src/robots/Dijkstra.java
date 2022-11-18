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

/**
 * the Dijkstra class represent the algorithm that allows 
 * certain robot to find the shortest path to certain destination
 */
public class Dijkstra extends PathCalculator {

  int timeNeeded;
  ArrayList<Case> shortestPath;
  
  /**
   * create dijkstra that receives a map
   * 
   * @param carte - map
   */
  public Dijkstra(Carte carte) {
    super(carte);
  }

  
  /** 
   * implement the dijkstra algorithm to find the shortest path to 
   * certain case
   * @param robot - robot
   * @param destination - case to arrive
   * @return boolean
   */
  public boolean dijkstra(Robot robot, Case destination) {

    //this map has the case and the cost(distance+weight)
    Map<Case,Integer> costs = new HashMap<Case, Integer>();
    Set<Case> visited = new HashSet<Case>();

    //this variable will stock the path in inverse order
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

  
  /** 
   * this return the lowest distance from a case to another
   * @param costs - cost (distance+weight)
   * @param visited - visited case
   * @return Case
   */
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

  
  /** 
   * get the adjacent cases of a certain case
   * @param carte - map
   * @param curr - actual case
   * @param robot - robot
   * @param sizeCase - size of a case
   * @return Map<Case, Integer>
   */
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

  
  /** 
   * update the cost if is possible to arrive to a case with less efort
   * in this case less time
   * @param costs - cost (distance+weight)
   * @param adjacent - adjacent case
   * @param distance - distance
   * @param curr - actual case
   * @param backtracker - inverse path list
   */
  public void updateHashMap(Map<Case,Integer> costs, Case adjacent, int distance, Case curr, Map<Case,Case> backtracker) {
    int sourceDistance = costs.get(curr);
    if (sourceDistance + distance < costs.get(adjacent)) {
        costs.put(adjacent, sourceDistance + distance);
        backtracker.put(adjacent, curr);
    }
  }

  
  /** 
   * get the time to traverse all the shortest path
   * @param r - robot
   * @param target - destination case
   * @return ArrayList<Case>
   */
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

  
  /** 
   * @param r
   * @return ArrayList<Case>
   */
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
      return null;
    }
    return closestPathWater;
  }

  
  /** 
   * @param r
   * @param target
   * @return double
   */
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
