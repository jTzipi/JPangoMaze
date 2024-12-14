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

package eu.jpangolin.jpangomaze.core;

import eu.jpangolin.jpangomaze.core.cell.ICell;

/**
 * Record for weighted edge.
 *
 * @param host host cell
 * @param neighbour neighbour cell
 * @param weight weight
 * @param <C> cell type
 *
 * @author jTzipi
 */
public record WeightedEdgeRec<C extends ICell>(C host, C neighbour, long weight ) implements IWeightedEdge<C> {


    public static <C extends ICell> IWeightedEdge<ICell> toCellWeightedEdge(IWeightedEdge<C> weightedEdge) {

        return new WeightedEdgeRec<>(weightedEdge.host(), weightedEdge.neighbour(), weightedEdge.weight());
    }
    /**
     * Create an edge to a not visitable cell.
     * <p>This edge has infinity cost</p>
     * @param host host cell
     * @return edge
     * @param <C> cell type
     */
    public static <C extends ICell> IWeightedEdge<C>  nullEdge(C host) {
        return new WeightedEdgeRec<>(host, null, IWeightedEdge.INF);
    }
}