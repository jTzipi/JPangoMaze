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
}