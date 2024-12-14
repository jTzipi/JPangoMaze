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

package eu.jpangolin.jpangomaze.core.distance;

import eu.jpangolin.jpangomaze.core.IWeightedEdge;
import eu.jpangolin.jpangomaze.core.cell.ICell;


/**
 * Path Link.
 * <p>
 *     This is a kind of linked list element with weights.
 *     Each cell in a maze , analysed by a {@link IDistanceMeasurer}, produces weights for each
 *     cell to travel. To make it easier to get the <em>shortest</em> path from A to B we
 *     use this as the link between cells alongside such a path.
 * </p>
 * @author jTzipi
 */
public interface IPathLink  {


    /**
     * Node (cell) alongside a path.
     * @return node or cell
     */
    ICell node();
    /**
     * Return how many cells was visited until we reach {@code this} cell.
     * @return steps to visit {@code this} cell &ge;0
     */
    int steps();


    /**
     * Accumulated weight for the path up to this cell.
     * @return accumulated weight/cost to {@code this} cell
     */
    long weight();

    /**
     * The link to the previous cell.
     * @return link
     */
    IPathLink link();



    enum NullLink implements IPathLink {
        SINGLETON;

        @Override
        public ICell node() {
            return null;
        }

        @Override
        public int steps() {
            return -1;
        }

        @Override
        public long weight() {
            return IWeightedEdge.INF;
        }

        @Override
        public IPathLink link() {
            return null;
        }
    }
}