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
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Aldous-Broder Algorithm on 2D tetragonal plane.
 * @author jTzipi
 */
public class AldousBroder2DTetragon implements IPlantable<Grid2DTetragon, ICell2DTetragon> {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AldousBroder2DTetragon.class);

    @Override
    public void plant(Grid2DTetragon grid) {
        Objects.requireNonNull(grid);

        ICell cell = grid.getRandomCell();
        int unvisited = grid.getSize();

        while (unvisited > 0) {

            LOG.info("AB > Unvisited cells='{}'", unvisited);
            // get a random neighbour which is not a border cell
            // and not masked
            ICell randomNB = MazeUtils.getRandomCell(cell.getTraversableNeighbours());

            if(randomNB.getLinkedNeighbours().isEmpty()) {

                LOG.info("Found unlinked neighbour {}" , randomNB);
                cell.link(randomNB);
                unvisited--;
            }

            cell = randomNB;
        }
    }
}