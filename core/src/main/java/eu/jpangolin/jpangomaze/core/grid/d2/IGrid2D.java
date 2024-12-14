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

import eu.jpangolin.jpangomaze.core.cell.d2.ICell2D;
import eu.jpangolin.jpangomaze.core.grid.IGrid;

import java.util.Objects;

/**
 * Grid on a 2D plane.
 * <p>
 *
 * </p>
 * @param <C> subtype of {@linkplain ICell2D}
 */
public interface IGrid2D<C extends ICell2D> extends IGrid<C> {



    /**
     * Return the Mask for this grid.
     * @return grid Mask
     */
    IMask2D getMask();

    @Override
    default boolean isUnmasked(C cell) {
        Objects.requireNonNull(cell);
        return getMask().isUnmasked(cell);
    }

    /**
     * Return whether a cell located [{@code row},{@code column}] is unmasked.
     * @param row row [0 &le; row &lt; maxRow]
     * @param column column [0 &le; column &lt; maxColumn]
     * @return
     */
    default boolean isUnmasked(int row, int column) {
        return getMask().isUnmasked(row, column);
    }
    /**
     * Grid Mask.
     * <p>
     *     A mask disable and enable some cells for traversal.
     *     Described on page
     * </p>
     */
    interface IMask2D {

        /**
         * How many cells are masked.
         * @return how many cells are masked
         */
        int getMaskedCells();

        /**
         * Return whether the cell for row and column is unmasked.
         *
         * @param row row
         * @param column column
         * @return {@code true} if the cell [{@code row},{@code column}] is unmasked
         * @throws IndexOutOfBoundsException if [{@code row},{@code column}] is out of bounds
         */
        boolean isUnmasked(int row, int column);

        /**
         * Return whether the cell is unmasked.
         * That is the location of the cell in the grid.
         *
         * @apiNote Since we test for the location only this did not say anything
         * about the status of the cell. Whether the cell is part of this grid.
         * @param cell cell
         * @return {@code true} if [{@link ICell2D#getRow() row},{@link ICell2D#getColumn() column}] is unmasked
         * @throws NullPointerException if {@code cell}
         */
        default boolean isUnmasked(ICell2D cell ) {
            Objects.requireNonNull(cell);
            return isUnmasked(cell.getRow(), cell.getColumn());
        }

        /**
         * Mask a location on the grid.
         * @param row row &ge; 0
         * @param column column &ge; 0
         * @throws IndexOutOfBoundsException if [{@code row},{@code column}] is out of bounds
         */
        boolean mask( int row, int column);

        /**
         * Mask the cell.
         * that is the location of that cell.
         *
         * @param cell cell
         * @throws NullPointerException if {@code cell}
         * @throws IndexOutOfBoundsException if [{@link ICell2D#getRow() row},{@link ICell2D#getColumn() column}] is out of bounds
         */
        default boolean mask( ICell2D cell ) {
            Objects.requireNonNull(cell);
            return mask(cell.getRow(), cell.getColumn());
        }

        /**
         * Unmask a cell on the grid.
         * @param row row &ge; 0
         * @param column column &ge; 0
         * @throws IndexOutOfBoundsException if [{@code row},{@code column}] is out of bound
         */
        boolean unmask(int row, int column);

        /**
         * Unmask the location for a cell.
         * That is the location of that cell.
         * @apiNote Since we test for the location only this did not say anything
         * about the status of the cell. Whether the cell is part of this grid.
         * @param cell cell to unmask
         * @throws NullPointerException if {@code cell}
         * @throws IndexOutOfBoundsException if [{@link ICell2D#getRow() row},{@link ICell2D#getColumn() column}] is out of bounds
         */
        default boolean unmask(ICell2D cell ) {
            Objects.requireNonNull(cell);
            return unmask(cell.getRow(), cell.getColumn());
        }
    }

    }