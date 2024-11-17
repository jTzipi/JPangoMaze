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

package eu.jpangolin.jpangomaze.core.grid.d2;

import eu.jpangolin.jpangomaze.core.cell.d2.Cell2DTetragon;

import eu.jpangolin.jpangomaze.core.cell.d2.ICell2DTetragon;
import org.slf4j.LoggerFactory;

public class Grid2DTetragon extends AbstractGrid2DCartesian<ICell2DTetragon> {

    // LOG
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Grid2DTetragon.class);
    /**
     * The grid.
     */
    private ICell2DTetragon[][] grid;

    /**
     * Grid2DTetragon.
     * @param rows rows
     * @param columns columns
     */
    Grid2DTetragon(final int rows, int columns) {
        super(rows, columns);

    }

    @Override
    void prepare() {

        this.grid = new ICell2DTetragon[getRows()][getColumns()];
        // Default cell for not existing cells
        ICell2DTetragon borderCell = Cell2DTetragon.nullCell(getUGID());

        for( int ir = 0; ir < getRows(); ir++ ) {
            for(int ic = 0; ic < getColumns(); ic++ ) {

                boolean unmasked = isUnmasked(ir, ic);
                // create new
                ICell2DTetragon tetraCell = unmasked  ? Cell2DTetragon.of(ir,ic,getUGID()) : borderCell;
                grid[ir][ic] = tetraCell;

                // set neighbours
                // since we start at left top we can populate
                // the grid setting only top and left neighbours and vice versa
                // edge case are
                // row == 0, column == 0 and row == getRows() -1 getColumns() -1


                if(0 == ir ) {
                    if(unmasked) {
                        tetraCell.setNeighbourNorth(borderCell);
                    }
                } else {

                    ICell2DTetragon nbn = grid[ir - 1][ic];
                    tetraCell.setNeighbourNorth(nbn);
                    // If north neighbour is not masked we can set the current cell
                    // to the south neighbour
                    if(isUnmasked(nbn)) {
                        nbn.setNeighbourSouth(tetraCell);
                    }
                }

                if(0 == ic) {
                    if(unmasked) {
                        tetraCell.setNeighbourWest(borderCell);
                    }
                } else {

                    // get the west neighbour, set this to this cell neighbour
                    // and vice versa if the cell is not masked
                    ICell2DTetragon nbw = grid[ir][ic - 1];
                    tetraCell.setNeighbourWest(nbw);


                    if(isUnmasked(nbw)) {
                        nbw.setNeighbourEast(tetraCell);
                    }

                }
                // corner case last row
                // set south neighbour
                if (getRows() - 1 == ir) {

                    tetraCell.setNeighbourSouth(borderCell);
                }
                // corner case last col
                // set east neighbour
                if (getColumns() - 1 == ic) {

                    tetraCell.setNeighbourEast(borderCell);
                }

            }
        }

    }

    @Override
    void configure() {

    }

    @Override
    ICell2DTetragon[][] grid() {
        // lazy init
        if(null == grid) {
            prepare();
            configure();
        }

        return grid;
    }

    /**
     * Create a new tetragonal grid.
     * @param rows rows [{@linkplain #MIN_LEN} .. ]
     * @param columns columns [{@linkplain #MIN_LEN} .. ]
     * @return
     */
    public static Grid2DTetragon of(int rows, int columns) {
        if(MIN_LEN > rows) {
            LOG.warn("Rows[='{}'] < {} clamped!", rows, MIN_LEN);
        }
        if(MIN_LEN > columns) {
            LOG.warn("Columns[='{}'] < {}", columns, MIN_LEN);
        }

        // TODO MAX!
        rows = Math.max(MIN_LEN, rows);
        columns = Math.max(MIN_LEN, columns);

        Grid2DTetragon grid2DTetragon = new Grid2DTetragon(rows, columns);
        grid2DTetragon.prepare();
        grid2DTetragon.configure();

        return grid2DTetragon;
    }
}