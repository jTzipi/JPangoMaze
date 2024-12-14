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

import eu.jpangolin.jpangomaze.core.IWeightedEdge;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
 * On page 18 of "Maze for P".
 * </p>
 * <p>
 * Each cell has the following attributes
 * <u>
 *
 * <li>A set of linked neighbours (cells we can visit)</li>
 * <li>masked state</li>
 * <li>border state</li>
 * </u>
 * <br />
 * <h4>Design question</h4>
 * The concept of edges is needed if we want to calculate the shortest path's in a grid.
 * If we have only weights of 1 dann we don't need edges because the only measure of the weight of
 * a path through our grid is the length of steps we need to travel.
 * But with weights &gt; 1 for edges we can have much longer paths which are cheaper than short paths because
 * of the weights or costs to travel those shorter path's.
 * <br />
 * <u>Question:</u>
 * Where to store those weighted edges.
 * I just want to store them here as set of weighted edges to neighbour cells.
 * But this is not a good decision because we can not declare the set of typ {@literal Set<IWeightedEdge<C extends ICell>}.
 * <br />
 * On the other hand all the methods we need to calculate the shortest paths are connected to weighted edges from cells.
 * So it would be natural tha each cell knows it's edges.
 * <br />
 * I try to put as most functionality in this API to avoid conflicts with methods needed only available for
 * special subclasses.
 * </p>
 *
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
        link(other, true, IWeightedEdge.SIMPLE);
    }

    /**
     * Default implementation of link with weight.
     *
     * @param other  other cell
     * @param weight weight
     * @throws NullPointerException     if {@code other}
     * @throws IllegalArgumentException if {@code other} is {@code this}
     */
    default void link(ICell other, long weight) {
        link(other, true, weight);
    }

    /**
     * Link another cell to this cell.
     *
     * @param other  other cell
     * @param bidi   bi directional
     * @param weight weight to travel to this cell
     * @throws NullPointerException     if {@code other}
     * @throws IllegalArgumentException if {@code other} is {@code this}
     */
    void link(ICell other, boolean bidi, long weight);

    /**
     * Unlike this cell from the other.
     * <p>bidirectional by default</p>
     *
     * @param other other cell
     * @throws NullPointerException     if {@code other}
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
     * IllegalArgumentException if {@code other} is {@code this}
     */
    void unlink(ICell other, boolean bidi);

    /**
     * Set a weight/cost to visit a linked neighbour.
     *
     * @param neighbourCell a linked neighbour cell
     * @param weight        weight to visit
     * @throws NullPointerException     if {@code neighbourCell} is null
     * @throws IllegalArgumentException if {@code neighbourCell} is not linked
     */
    void setLinkWeight(ICell neighbourCell, long weight);

    /**
     * Return whether this cell is part of the border of the grid.
     * <p>
     * A border cell is
     *     <ul>
     *         <li>not traversable</li>
     *         <li>masked</li>
     *         <li>has infinitive cost to travel to</li>
     *     </ul>
     *     <br />
     *
     * </p>
     *
     * @return {@code true} if the cell is not part of the maze
     * @implSpec Per convention each border cell have invalid location like {@link eu.jpangolin.jpangomaze.core.ILocation#UN_TRAVERSABLE}
     */
    boolean isBorder();

    /**
     * Return whether we can link this cell.
     * <p>
     * That is the case if {@code this} cell is
     *     <ul>
     *         <li>not a border cell</li>
     *         <li>not masked</li>
     *     </ul>
     * </p>
     *
     * @return {@code true} if {@code this} cell is <u>un</u>masked and not a border cell
     */
    default boolean isLinkable() {
        return !isBorder() && isUnmasked();
    }

    /**
     * Is the cell a neighbour cell of {@code this}.
     * @param cell other cell
     * @return {@code true} if {@code cell} is a neighbour cell
     * @throws NullPointerException if {@code cell}
     */
    default boolean isNeighbour(ICell cell) {
        Objects.requireNonNull(cell);
        return getNeighbours().equals(cell);
    }
    /**
     * Return all neighbour cells along their edge which are linked to this cell.
     *
     * @return Set of neighbour cells linked to this cell
     * @implSpec The Set should be unmodifiable
     */
    default Set<ICell> getLinkedNeighbours() {
        return Collections.unmodifiableSet(getLinkedNeighbourWeightMap().keySet());
    }

    /**
     * Return a map with all <u>linked</u> cells and the cost/weight to visit them.
     *
     * @return map of linked neighbours and their corresponding weight
     */
    Map<ICell, Long> getLinkedNeighbourWeightMap();

    /**
     * The neighbour cells of {@code this} cell.
     * <p>
     * The set of all traversable cells.
     * This set includes
     *     <ul>
     *         <li>border</li>
     *         <li>masked</li>
     *     </ul>
     *     cells.
     *     So this set contains the sole neighbours cells only!
     * </p>
     *
     * @return set of neighbour cells including border and masked cells!
     */
    Set<ICell> getNeighbours();

    /**
     * Return all neighbour cells which we can traverse.
     * <p>
     * That is all neighbour cells which are not
     *     <ul>
     *         <li>masked</li>
     *         <li>border</li>
     *     </ul>
     *     cells.
     * </p>
     *
     * @return set of traversable neighbour cells
     */
    default Set<ICell> getTraversableNeighbours() {
        return getNeighbours()
                .stream()
                .filter(ICell::isLinkable)
                .collect(Collectors.toSet());
    }

    /**
     * Is a given neighbour cell linked to this cell.
     *
     * @param cell neighbour cell
     * @return {@code true} if {@code this} cell is linked with {@code cell}
     * @throws NullPointerException if {@code cell}
     */
    boolean isLinked(ICell cell);

    /**
     * Is this cell not masked.
     * <p>
     * A masked cell can not be visited. This cell is not visible.
     * </p>
     *
     * @return {@code true} if this cell is unmasked and therefore visitable
     * @implNote Every cell out of bound of the grid is <em>per se</em> masked and also each uninitialized cell
     * Like border cells.
     */
    boolean isUnmasked();


    /**
     * Set this cell masked.
     *
     * @param masked masked state
     * @implNote Attention! If the client set this attribute this should only be done from an instance of
     * {@link eu.jpangolin.jpangomaze.core.grid.IGrid grid}. Since there we have the {@code mask} of
     * a grid storing the masked state of our grid.
     */
    void setMasked(boolean masked);


    /**
     * Return the grid unique id.
     *
     * @return grid unique id
     */
    long guid();


}