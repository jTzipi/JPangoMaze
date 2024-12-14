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

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree algo.
 *
 * @author jTzipi
 */
public class BinaryTree2DTetragon implements IPlantable<Grid2DTetragon, ICell2DTetragon> {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BinaryTree2DTetragon.class);

    BinaryTree2DTetragon() {
    }

    @Override
    public void plant(Grid2DTetragon grid) {

        // for all cells look for north and east neighbour
        // add them to a list and choose one cell randomly
        // Attention:
        // we have to check the neighbour  masked state
        // but not the mask state of cell because
        // the getCells() method already filters masked cells
        for (ICell2DTetragon cell : grid.getCells()) {
            LOG.info("Visit '{}'", cell);

            // Neighbours to the north and east
            List<ICell> nbList = new ArrayList<>();

            // get north and east edge neighbour
            ICell2DTetragon nn = cell.getNeighbourNorth();
            ICell2DTetragon ne = cell.getNeighbourEast();
            // we can visit north neighbour
            if (nn.isUnmasked()) {
                nbList.add(nn);
            }
            // and east
            if (ne.isUnmasked()) {
                nbList.add(ne);
            }

            // we have at least one neighbour
            // choose one of the list
            // and link it to the cell
            if (!nbList.isEmpty()) {

                ICell linkNB = MazeUtils.getRandomCell(nbList);
                cell.link(linkNB);

            }
        }
    }
}