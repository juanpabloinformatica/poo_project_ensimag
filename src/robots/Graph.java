package robots;

import java.util.Set;
import java.util.HashSet;

public class Graph {

    private Set<Node> nodes = new HashSet<>();
    
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
