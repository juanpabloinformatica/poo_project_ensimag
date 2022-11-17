package robots;

import java.util.*;

import classes.Carte;
import classes.Case;

public class Node {

    private Case cell;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    
    /** 
     * @param destination
     * @param distance
     */
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(Case cell) {
        this.cell = cell;
    }

    
    /** 
     * @return List<Node>
     */
    public List<Node> getShortestPath() {
        return shortestPath;
    }

    
    /** 
     * @param shortestPath
     */
    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    
    /** 
     * @return Integer
     */
    public Integer getDistance() {
        return distance;
    }

    
    /** 
     * @param distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    
    /** 
     * @return Map<Node, Integer>
     */
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    
    /** 
     * @param adjacentNodes
     */
    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    
    /** 
     * @return Case
     */
    public Case getCell() {
        return cell;
    }

    
    /** 
     * @param cell
     */
    public void setCell(Case cell) {
        this.cell = cell;
    }

    
    /** 
     * @param evaluationNode
     * @param edgeWeigh
     * @param sourceNode
     */
    private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    
    /** 
     * @param graph
     * @param source
     * @return Graph
     */
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);
    
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
    
        unsettledNodes.add(source);
    
        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair: 
              currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();@
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    
    /** 
     * @param unsettledNodes
     * @return Node
     */
    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    /** 
     * @param carte
     * @param robot
     * @return Graph
     */
    private static Graph carteToGraph(Carte carte, Robot robot) {

        
    }


}
