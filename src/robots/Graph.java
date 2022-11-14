package robots;

import classes.Carte;

public class Graph {
    private int adjMatrix[][];
    private int numVertices;
  
    // Initialize the matrix
    public Graph(int numVertices) {
      this.numVertices = numVertices;
      adjMatrix = new int[numVertices][numVertices];
    }
  
    // Add edges
    public void addEdge(int i, int j, int cost) {
      adjMatrix[i][j] = cost;
      adjMatrix[j][i] = cost;
    }
  
    // Remove edges
    public void removeEdge(int i, int j) {
      adjMatrix[i][j] = -1;
      adjMatrix[j][i] = -1;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void fillMatrix(Carte carte) {
        
    }
}
