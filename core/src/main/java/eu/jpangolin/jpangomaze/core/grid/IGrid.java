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

package eu.jpangolin.jpangomaze.core.grid;

import eu.jpangolin.jpangomaze.core.cell.ICell;

import java.util.List;

/**
 * Grid Interface.
 * This covers methods usable for all kind of grids.
 * Less than
 * described in the Book on page 20.
 * Because this is introduced for a two-dimensional grid.
 * @param <C> Arbitrary cell type
 */
public interface IGrid<C extends ICell> {


    /**
     * Return a random cell of this grid.
     * @implSpec cell should be not masked
     * @return random cell
     */
    C getRandomCell();

    /**
     * Return all cells living in this grid.
     * <p>
     *     <em>Warning</em>: We only collect not <em>masked</em> cells.
     * </p>
     * @implSpec Implementations should return <u>only</u> cells who are not masked!
     * @return list of cells
     */
    List<C> getCells();

    /**
     * Return whether a cell is unmasked.
     *
     * @implSpec of course should {@code cell} part of this grid
     * @return {@code true} if the cell for is <b>un</b>masked
     * @throws NullPointerException if {@code cell} is {@code null}
     */
    boolean isUnmasked(C cell);

    /**
     * Return the size of the grid.
     * That is all cells living in this grid.
     * For a cartesian 2D grid this is
     * {@code rows x columns} minus all masked cells.
     * @implSpec Count only cells who are not masked
     * @return size of this grid &ge; 0
     */
    int getSize();


    /**
     * Return unique grid id.
     * @return unique grid id
     */
    long getUGID();
}