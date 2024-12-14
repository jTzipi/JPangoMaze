/*
 * Copyright (c) 2024 Tim Langhammer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.jpangolin.jpangomaze.core.distance;

import eu.jpangolin.jpangomaze.core.IWeightedEdge;
import eu.jpangolin.jpangomaze.core.cell.ICell;

import java.util.*;


/**
 * Simple distance setter.
 * <p>
 *     We here label each cell with the cost to this cell plus 1.
 *     As described on page 38 of "Mazes for Programmers".
 * </p>
 * @author jTzipi
 */
public class SimpleDistance implements IDistanceMeasurer {

    SimpleDistance() {

    }

    public static SimpleDistance create() {
        return new SimpleDistance();
    }
    /**
     * Set distances for each cell we can reach from the root cell.
     * @param rootCell cell to start
     */
    @Override
    public IDistanceResult analyse(ICell rootCell) {
        Objects.requireNonNull(rootCell);
        final Map<ICell, IPathLink> distMap = new HashMap<>();

        distMap.put(rootCell, new PathLinkRec(rootCell, IWeightedEdge.FREE, null, 0));

        List<ICell> frontierL = new ArrayList<>();
        frontierL.add(rootCell);

        while (!frontierL.isEmpty()) {
            List<ICell> activeL = new ArrayList<>();

            for(ICell labeledCell : frontierL ) {
                IPathLink oldLink = distMap.get(labeledCell);
                long oldWeight = oldLink.weight();
                int oldStep = oldLink.steps();
                for( ICell linkedCell : labeledCell.getLinkedNeighbours() ) {


                    IPathLink pathLink = new PathLinkRec(linkedCell, oldWeight + 1, oldLink, oldStep + 1 );
                    distMap.putIfAbsent(linkedCell, pathLink);
                    activeL.add(linkedCell);
                }

            }

            frontierL = activeL;
        }

        return new DistanceResultRec(distMap);
    }
}