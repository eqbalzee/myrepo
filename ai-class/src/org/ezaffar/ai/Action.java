package org.ezaffar.ai;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Action {
    Tower towerSource;
    Tower towerDest;

    public Action(Tower towerSource, Tower towerDest) {
        this.towerSource = towerSource;
        this.towerDest = towerDest;
    }

    public State act(State originState) {
        Ring topRingOnSource = originState.topRingOn(towerSource);
        Ring topRingOnDest = originState.topRingOn(towerDest);
        if (topRingOnSource != null) {
            if (topRingOnDest == null || topRingOnDest.compareTo(topRingOnSource) > 0){
                State result = new State();
                
                Map<Tower, List<Ring>> originStateMapping = originState.getCurrentState();
                Map<Tower, List<Ring>> newStateMappingSource = new EnumMap<Tower, List<Ring>>(originStateMapping);

                // source mapping
                List<Ring> updatedRingsOnSource = new ArrayList<Ring>();
                updatedRingsOnSource.addAll(originStateMapping.get(towerSource));
                Ring ringToMove = updatedRingsOnSource.remove(0);

                // destination mapping
                List<Ring> updateRingsOnDest = new ArrayList<Ring>();
                updateRingsOnDest.add(ringToMove);
                updateRingsOnDest.addAll(originStateMapping.get(towerDest));

                newStateMappingSource.put(towerSource, updatedRingsOnSource);
                newStateMappingSource.put(towerDest, updateRingsOnDest);
                result.setCurrentState(newStateMappingSource);
                
                return result;
                

            }
        }

        return null;
    }
}
