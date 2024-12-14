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
import java.util.Map;

/**
 * Result of a {@link IDistanceMeasurer}.
 * @author jTzipi
 */
public interface IDistanceResult {

    /**
     * Map of cells of a grid and its {@linkplain IPathLink} to the previous cell.
     * <p>
     *     With this information we can build a list or path of cells forming a <em>shortest path</em>
     *     for each cell we visited during the analysis of the grid.
     * </p>
     * @return Map of cells and its previous cell
     */
    Map<ICell, IPathLink> pathLinkMap();
}