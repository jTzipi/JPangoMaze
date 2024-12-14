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

import java.util.Objects;

/**
 * Record of a {@linkplain IPathLink}.
 * @param node node
 * @param weight accumulated weight
 * @param link previous path link
 * @param steps steps until here
 */
public record PathLinkRec(ICell node, long weight, IPathLink link, int steps) implements IPathLink, Comparable<PathLinkRec> {

    public static PathLinkRec ofRoot(ICell rootCell) {
        Objects.requireNonNull(rootCell);
        return new PathLinkRec(rootCell, IWeightedEdge.FREE, NullLink.SINGLETON, 0);
    }

    @Override
    public int compareTo(PathLinkRec other) {
        int wcomp = Long.compare(weight, other.weight);
        return 0 == wcomp
                ? Integer.compare(steps, other.steps)
                : wcomp;
    }
}