package org.ezaffar.ai;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Frontier {
    Set<Node> nodes = new HashSet<Node>();
    Queue<Node> nodeQueue = new PriorityQueue<Node>();
    
    public Frontier(Node startNode) {
        nodes.add(startNode);
        nodeQueue.add(startNode);
    }

    public boolean isEmpty() {
        return nodeQueue.isEmpty();
    }

    public boolean memberOf(Node node) {
        return nodes.contains(node);
    }
    
    public void addToQueue(Node node) {
        nodes.add(node);
        nodeQueue.add(node);
    }
    
    public Node popFromQueue() {
        return nodeQueue.poll();
    }
}
