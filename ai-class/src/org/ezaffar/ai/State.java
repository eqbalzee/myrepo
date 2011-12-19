package org.ezaffar.ai;

import java.util.*;

public class State {
    private Map<Tower, List<Ring>> currentState = new EnumMap<Tower, List<Ring>>(Tower.class);

    public Map<Tower, List<Ring>> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Map<Tower, List<Ring>> currentState) {
        this.currentState = currentState;
    }
    
    public Ring topRingOn(Tower tower) {
        List<Ring> allRings = currentState.get(tower);
        if (allRings.size() > 0) {
            return allRings.get(0);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (currentState != null ? !currentState.equals(state.currentState) : state.currentState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return currentState != null ? currentState.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "State{" +
                "currentState=" + currentState +
                '}';
    }
}
