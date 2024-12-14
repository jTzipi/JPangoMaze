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

import eu.jpangolin.jpangomaze.core.cell.ICell;

/**
 * API specification for a 'distance' from one cell to another.
 * <p>
 *  The source cell is not saved here since know from which source cell we want to measure distances to
 *  other cells.
 *  <br />
 *  This is particular useful if we want to know the current cost to visit cells store in
 *  {@link java.util.PriorityQueue}'s. So we can choose the least distance or least edge with
 *  the cumulative weights along a path.
 * </p>
 *
 * @param <C> cell type
 * @author jTzipi
 */
public interface IDistance<C extends ICell> extends Comparable<IDistance<C>> {

    /**
     * The cell to visit.
     * @return destination cell
     */
    C cell();

    /**
     * Weight to travel from a root cell to {@code this} {@code cell}.
     * @return weight from source to cell
     */
    long weight();

    @Override
    default int compareTo(IDistance<C> other) {
        return Long.compare(weight(), other.weight());
    }
}