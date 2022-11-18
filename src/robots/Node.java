package robots;

import java.util.Comparator;
import classes.Case;

public class Node implements Comparator<Node>{
    private Case curr;
    private int cost;

    public Case getCurr() {
        return curr;
    }

    public Node()
    {
    }
 
    public Node(Case curr, int cost)
    {
        this.curr = curr;
        this.cost = cost;
    }
 
    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
