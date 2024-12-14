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

package eu.jpangolin.jpangomaze.core.algo.d2;

import eu.jpangolin.jpangomaze.core.MazeUtils;
import eu.jpangolin.jpangomaze.core.algo.IPlantable;
import eu.jpangolin.jpangomaze.core.cell.ICell;
import eu.jpangolin.jpangomaze.core.cell.d2.ICell2DTetragon;
import eu.jpangolin.jpangomaze.core.grid.d2.Grid2DTetragon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Wilson's algorithm for 2D Tetragonal cells, as described on page 60 of "Mazes for Programmers".
 */
public class Wilson2DTetragon implements IPlantable<Grid2DTetragon, ICell2DTetragon> {
    @Override
    public void plant(Grid2DTetragon grid) {

        // Add all cells to the list of
        List<ICell> unvisitedL = new ArrayList<>(grid.getCells());
        int idx = MazeUtils.randomInt(unvisitedL.size());
        unvisitedL.remove(idx);

        while(!unvisitedL.isEmpty()) {
            ICell next = MazeUtils.getRandomCell(unvisitedL);
            List<ICell> pathL = new LinkedList<>();
            // until we found first
            //
            while(unvisitedL.contains(next)) {
                // random neighbour cell of next
                // look whether we already contain this new cell
                // if so remove the sub path until we found the loop
                next = MazeUtils.getRandomCell(next.getTraversableNeighbours());

                int foundIdx = pathL.indexOf(next);
                if( foundIdx >= 0) {
                    pathL = pathL.subList(0, foundIdx);
                } else {
                    pathL.add(next);
                }
            }
            // When we found a whole new path
            // we link each part of this part
            // and delete them from the unvisited list
            for( int i = 0; i < pathL.size() - 2; i++) {

                ICell cellA = pathL.get(i);
                ICell cellB = pathL.get(i+1);
                cellA.link(cellB);
                unvisitedL.remove(cellA);
            }
        }

    }
}