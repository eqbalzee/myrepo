package org.ezaffar.ai;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    
    State state;
    Action action;
    int cost = 0;
    Node parentNode;
    List<Node> childNodes = new ArrayList<Node>();

    public Node(State state, Action action) {
        this.state = state;
        this.action = action;
    }
    
    public int heuristic() {
        return state.getCurrentState().get(Tower.TOWER1).size();
    }

    @Override
    public int compareTo(Node node) {
        return (this.cost + this.heuristic()) - (node.getCost() + node.heuristic());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (state != null ? !state.equals(node.state) : node.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }

    public void addChildNode(Node node) {
       childNodes.add(node);
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
