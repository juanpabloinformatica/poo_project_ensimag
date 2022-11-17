package robots;

import java.util.Set;
import java.util.HashSet;

public class Graph {

    private Set<Node> nodes = new HashSet<>();
    
    
    /** 
     * @param nodeA
     */
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    
    /** 
     * @return Set<Node>
     */
    public Set<Node> getNodes() {
        return nodes;
    }

    
    /** 
     * @param nodes
     */
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
