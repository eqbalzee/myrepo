package org.ezaffar.ai;

import java.util.*;

public class Game {
    State initState;
    State goalState;
    Node startNode;
    List<Action> actions = new ArrayList<Action>();
    Map<State, Node> stateNodeMap = new HashMap<State, Node>();
    
    Frontier frontier;
    
    Explored explored;
            

    public Game() {
        actions.add(new Action(Tower.TOWER1, Tower.TOWER2));
        actions.add(new Action(Tower.TOWER1, Tower.TOWER3));
        actions.add(new Action(Tower.TOWER2, Tower.TOWER1));
        actions.add(new Action(Tower.TOWER2, Tower.TOWER3));
        actions.add(new Action(Tower.TOWER3, Tower.TOWER1));
        actions.add(new Action(Tower.TOWER3, Tower.TOWER2));

        
        List<Ring> allRings = new ArrayList<Ring>();
        allRings.add(Ring.RINGONE);
        allRings.add(Ring.RINGTWO);
        allRings.add(Ring.RINGTHREE);
        allRings.add(Ring.RINGFOUR);
        Map<Tower, List<Ring>> initMapping = new EnumMap<Tower, List<Ring>>(Tower.class);
        initMapping.put(Tower.TOWER1, allRings);
        initMapping.put(Tower.TOWER2, new ArrayList<Ring>());
        initMapping.put(Tower.TOWER3, new ArrayList<Ring>());
        
        initState = new State();
        initState.setCurrentState(initMapping);

        startNode = new Node(initState, null);
        startNode.setParentNode(null);
        stateNodeMap.put(initState, startNode);

        // goalstate

        Map<Tower, List<Ring>> goalMapping = new EnumMap<Tower, List<Ring>>(Tower.class);
        goalMapping.put(Tower.TOWER1, new ArrayList<Ring>());
        goalMapping.put(Tower.TOWER2, new ArrayList<Ring>());
        goalMapping.put(Tower.TOWER3, allRings);

        goalState = new State();
        goalState.setCurrentState(goalMapping);
        
        generateStateSpace(startNode);
        
        frontier = new Frontier(startNode);
        explored = new Explored();
        
    }

    public void generateStateSpace(Node startNode) {
        // iterator over list of actions
        // if state returned is not null
        // and if state is not same as passed state
        // add a new state to state space, generate a new node
        for (Action action: actions) {
            State newState = action.act(startNode.getState());
            if(newState != null)  {
                Node node = stateNodeMap.get(newState);
                if (node == null) {
                    node = new Node(newState, action);
                    stateNodeMap.put(newState, node);
                    generateStateSpace(node);
                }
                startNode.addChildNode(node);

            }
        }
    }
    
    public Node doAStarSearch() {
        int count = 0;
        while(true) {
            System.out.printf("loop count %d\n", count++);
            if (frontier.isEmpty()) return null;
            Node node = frontier.popFromQueue();
            explored.addNode(node);
            Node goalNode = stateNodeMap.get(goalState);
            if (node.equals(goalNode)) return node;
            for(Node childNode : node.getChildNodes()) {

                if(!explored.memberOf(childNode) && !frontier.memberOf(childNode)) {
                    childNode.setCost(node.getCost() + 1);
                    childNode.setParentNode(node);
                    frontier.addToQueue(childNode);
                }
            }
        }
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public State getInitState() {
        return initState;
    }

    public void setInitState(State initState) {
        this.initState = initState;
    }

    public State getGoalState() {
        return goalState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    public Map<State, Node> getStateNodeMap() {
        return stateNodeMap;
    }

    public void setStateNodeMap(Map<State, Node> stateNodeMap) {
        this.stateNodeMap = stateNodeMap;
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.printf("State space size: %d\n", game.getStateNodeMap().size());
        int noOfSteps = 0;
        Node goalNode = game.doAStarSearch();
        
        if (goalNode != null) {
            Node parentNode = goalNode.getParentNode();
            while(parentNode != null) {
                noOfSteps++;
                parentNode = parentNode.getParentNode();
            }
        }

        System.out.printf("cost of getting to goal: %d\n" , goalNode.getCost());
        System.out.printf("No of steps to goal state: %d\n", noOfSteps);
    }
}
