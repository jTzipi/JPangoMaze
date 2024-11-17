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

import eu.jpangolin.jpangomaze.core.ILocation;
import eu.jpangolin.jpangomaze.core.ILocation2D;
import eu.jpangolin.jpangomaze.core.MazeUtils;
import eu.jpangolin.jpangomaze.core.cell.d2.ICell2DCartesian;


import java.util.List;
import java.util.Objects;

/**
 * A Grid on a 2D plane.
 * <p>
 *     Like a checkerboard , we have rows and columns.
 * </p>
 * @param <C> Subtype of {@link eu.jpangolin.jpangomaze.core.cell.d2.ICell2DCartesian}
 */
public interface IGrid2DCartesian<C extends ICell2DCartesian> extends IGrid2D<C> {


    /**
     * Return rows.
     * @return rows [0 .. ]
     */
    int getRows();

    /**
     * Return columns.
     * @return columns [2 .. ]
     */
    int getColumns();



    /**
     * Return the cell for row and column.
     * @param row row [0 .. {@link #getRows() rows} -1]
     * @param column column [0 .. {@link #getColumns() columns} -1]
     * @return cell
     * @throws IndexOutOfBoundsException if  [{@code row},{@code column}]  are not inbound
     * @see #isInbound(int row, int column)
     */
    C getCell(int row, int column);

    /**
     * Return all cells for a row.
     *
     * @implSpec the returning list should not contain masked cells
     * @param row row [0 .. {@link #getRows() rows} -1]
     * @return List of cells for the {@code row}
     * @throws IndexOutOfBoundsException if {@code row} is not inbound
     */
    List<C> getCellsForRow(int row);

    /**
     * Get the cell at position.
     * @param location2DCartesian cartesian location
     * @return cell on {@code location2DCartesian}
     * @throws NullPointerException if {@code location2DCartesian}
     * @throws IndexOutOfBoundsException if
     */
    default C getCell(ILocation2D location2DCartesian) {
        Objects.requireNonNull(location2DCartesian);

        return getCell(location2DCartesian.row(), location2DCartesian.column());
    }

    /**
     * Return whether coordinates are inbound of this grid.
     * @param location2D  cartesian 2D location
     * @return {@code true} if {@linkplain #isInbound(int row, int column)} is {@code true}
     * @throws NullPointerException if {@code location2D}
     */
    default boolean isInbound(ILocation2D location2D) {
        Objects.requireNonNull(location2D);

        return isInbound(location2D.row(), location2D.column());
    }

    /**
     * Return whether coordinates are inbound of this grid.
     * @param row row
     * @param column column
     * @return {@code true} if {@code row} {@literal >=} {@link ILocation#MIN min} and
     * {@code column} {@literal >=} {@link ILocation#MIN min} and
     * {@code row} {@literal <} {@link #getRows() rows} and
     * {@code row} {@literal <} {@link #getColumns() columns} else {@code false}
     */
    default boolean isInbound(int row, int column) {
        return MazeUtils.isLocationInBound2DCartesian(row, column, getRows(), getColumns());
    }

}