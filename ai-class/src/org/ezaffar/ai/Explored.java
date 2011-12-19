package org.ezaffar.ai;

import java.util.HashSet;
import java.util.Set;

public class Explored {
    Set<Node> exploredNodes = new HashSet<Node>();

    public boolean memberOf(Node node) {
        return exploredNodes.contains(node);
    }
    
    public void addNode(Node node) {
        exploredNodes.add(node);
    }
}
