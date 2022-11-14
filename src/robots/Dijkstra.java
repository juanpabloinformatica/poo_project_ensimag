package robots;

public class Dijkstra {
    public static void dijkstra(Graph graph, int source) {
        int count = graph.getNumVertices();
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++) {
          visitedVertex[i] = false;
          distance[i] = Integer.MAX_VALUE;
        }
    
        // Distance of self loop is zero
        distance[source] = 0;
        for (int i = 0; i < count; i++) {
    
          // Update the distance between neighbouring vertex and source vertex
          int u = findMinDistance(distance, visitedVertex);
          visitedVertex[u] = true;
    
          // Update all the neighbouring vertex distances
          for (int v = 0; v < count; v++) {
            if (!visitedVertex[v] && graph.getAdjMatrix()[u][v] != 0 && (distance[u] + graph.getAdjMatrix()[u][v] < distance[v])) {
              distance[v] = distance[u] + graph.getAdjMatrix()[u][v];
            }
          }
        }
      }
    
      // Finding the minimum distance
      private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
          if (!visitedVertex[i] && distance[i] < minDistance) {
            minDistance = distance[i];
            minDistanceVertex = i;
          }
        }
        return minDistanceVertex;
      }
    
}
