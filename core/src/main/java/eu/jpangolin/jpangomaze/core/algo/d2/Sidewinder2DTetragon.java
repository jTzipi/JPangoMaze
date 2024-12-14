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
import eu.jpangolin.jpangomaze.core.cell.d2.ICell2DTetragon;
import eu.jpangolin.jpangomaze.core.grid.d2.Grid2DTetragon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sidewinder Algorithm.
 * <p>
 *     The idea is to go for each cell of each row of our grid.
 *     We check whether we are at the eastern boundary or not at
 *     the northern boundary and throw a coin.
 *     <br />
 *     In that case we link this cell to it's north neighbour if possible.
 *     In all other case we link the current cell to the east cell if possible.
 *     <be />
 *     Described on page 28 of Mazes for Programmers.
 * </p>
 * @implNote The Sidewinder Algo can not handle masked cells in some circumstances!
 *           TODO: handle those
 *
 *
 */
public class Sidewinder2DTetragon implements IPlantable<Grid2DTetragon, ICell2DTetragon> {
    @Override
    public void plant(Grid2DTetragon grid) {
        Objects.requireNonNull(grid);

        // from last row upward
        for( int ir = grid.getRows() - 1; ir >= 0; ir-- ) {

            // cells we maybe link
            List<ICell2DTetragon> candidateL = new ArrayList<>();

            // All cells of the row (unmasked)
            for( ICell2DTetragon cell : grid.getCellsForRow(ir) ) {

                candidateL.add(cell);

                boolean northBorder = cell.getNeighbourNorth().isBorder();
                boolean eastBorder = cell.getNeighbourEast().isBorder();

                boolean close = eastBorder || (!northBorder && MazeUtils.randomInt(2) == 0);

                if(close) {
                    ICell2DTetragon cellTolink = MazeUtils.getRandomCell(candidateL);
                    // we can link to the north?
                    // do so
                    // and clear the candidate list
                    if(cellTolink.getNeighbourNorth().isLinkable()) {
                        cellTolink.link(cellTolink.getNeighbourNorth());
                    }
                    candidateL.clear();
                } else {

                    // If we have a linkable cell to the east we
                    // link them
                    if(cell.getNeighbourEast().isLinkable()) {
                        cell.link(cell.getNeighbourEast());
                    }
                }
            }
        }
    }
}