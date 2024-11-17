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

package eu.jpangolin.jpangomaze.core.cell;

import eu.jpangolin.jpangomaze.core.IDirection;

import java.util.Map;
import java.util.Set;

/**
 * Basic Cell Concept.
 * <p>
 * A cell is a spacial entity with at least two coordinates.
 * </p>
 * <p>
 * Belonging to a maze this is a place in the maze with zero
 * or more neighbour cells.
 * </p>
 * <p>
 *     On page 18 of "Maze for P".
 * </p>
 * @author jTzipi
 */
public interface ICell {


    /**
     * Default implementation of link.
     * Bidirectional is true.
     *
     * @param other other cell
     * @throws NullPointerException     if {@code other}
     * @throws IllegalArgumentException if {@code other} is {@code this}
     */
    default void link(ICell other) {
        if(this == other) {
            throw new IllegalArgumentException("Try to link to yourself!");
        }
        link(other, true);
    }

    /**
     * Link another cell to this cell.
     *
     * @param other other cell
     * @param bidi  bi directional
     * @throws NullPointerException     if {@code other}
     * @throws IllegalArgumentException if {@code other} is {@code this}
     */
    void link(ICell other, boolean bidi);

    /**
     * Unlike this cell from the other.
     * <p>bidirectional by default</p>
     *
     * @param other other cell
     * @throws NullPointerException if {@code other}
     * @throws IllegalArgumentException if {@code other} is {@code this}
     */
    default void unlink(ICell other) {
        if(this == other) {
            throw new IllegalArgumentException("Try to unlink yourself!");
        }
        unlink(other, false);
    }

    /**
     * Unlink another cell to this cell.
     *
     * @param other other
     * @param bidi  bi directional
     * @throws NullPointerException if {@code other}
     */
    void unlink(ICell other, boolean bidi);

    /**
     * Return all neighbour cells which are linked to this cell.
     * @implSpec The Set should be unmodifiable
     * @return Set of neighbour cells linked to this cell
     */
    Set<ICell> getLinkedNeighbours();

    /**
     * Is a given neighbour cell linked to this cell.
     * @param cell neighbour cell
     * @return {@code true} if {@code this} cell is linked with {@code cell}
     * @throws NullPointerException if {@code cell}
     */
    boolean isLinked(ICell cell);

    /**
     * Return whether you can link to this cell.
     * F.E. you can <u>never</u> link a cell to a border cell
     * @return {@code true} if {@code this} cell can be linked from others
     */
    boolean isLinkable();

    /**
     * Return the grid unique id.
     * @return grid unique id
     */
    long guid();
}