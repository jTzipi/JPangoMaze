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

package eu.jpangolin.jpangomaze.core.cell.d2;

import eu.jpangolin.jpangomaze.core.ILocation;
import eu.jpangolin.jpangomaze.core.ILocation2D;
import eu.jpangolin.jpangomaze.core.Location2D;
import eu.jpangolin.jpangomaze.core.cell.ICell;

import java.util.Objects;

/**
 * A cell on a 2D plane.
 * <p>
 *     Each cell have a unique
 *     <ul>
 *         <li>row</li>
 *         <li>column</li>
 *     </ul>
 * </p>
 * @author jTzipi
 */
public interface ICell2D extends ICell {

    /**
     * Return row of this cell.
     * @return row &ge; 0
     */
    int getRow();

    /**
     * Return column of this cell.
     * @return column &ge; 0
     */
    int getColumn();
    /**
     * Convert a 2D cell  to its 2D location.
     * @param cell2D cell
     * @return {@link ILocation2D location} of {@code cell2D}
     */
    static ILocation2D toLocation(ICell2D cell2D) {
        Objects.requireNonNull(cell2D);
        return Location2D.of(cell2D.getRow(), cell2D.getColumn());
    }

    @Override
    default boolean isBorder() {
        return getRow() < ILocation.MIN || getColumn() < ILocation.MIN;
    }
}