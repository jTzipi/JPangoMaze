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

import eu.jpangolin.jpangomaze.core.IWeightedEdge;


import java.util.Optional;

/**
 * A tetragon - rectangular - cell in 2D.
 */
public interface ICell2DTetragon extends ICell2DCartesian {

    /**
     * Return an optional edge to the north neighbour.
     * @return
     */
    Optional<IWeightedEdge<ICell2DTetragon>> getNeighbourNorth();

    ICell2DTetragon getNeighbourEast();

    ICell2DTetragon getNeighbourWest();

    ICell2DTetragon getNeighbourSouth();

    default void setNeighbourNorth(ICell2DTetragon nb) {
        setNeighbourNorth(nb, IWeightedEdge.SIMPLE);
    }

    void setNeighbourNorth(ICell2DTetragon nbCell, long weight);

    default void setNeighbourEast(ICell2DTetragon nbCell ) {
        setNeighbourEast(nbCell, IWeightedEdge.SIMPLE);
    }

    void setNeighbourEast(ICell2DTetragon nbCell, long weight);


    default void setNeighbourWest(ICell2DTetragon nbCell) {
        setNeighbourWest(nbCell, IWeightedEdge.SIMPLE);
    }

    void setNeighbourWest(ICell2DTetragon nbCell, long weight);

    default void setNeighbourSouth(ICell2DTetragon nbCell ) {
        setNeighbourSouth(nbCell, IWeightedEdge.SIMPLE);
    }

    void setNeighbourSouth(ICell2DTetragon nbCell, long weight);



}