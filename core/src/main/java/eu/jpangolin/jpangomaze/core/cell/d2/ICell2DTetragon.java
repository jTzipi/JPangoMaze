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


/**
 * A tetragon - rectangular - cell in 2D.
 * <p>
 *     A tetragonal cell have
 *     <ul>
 *         <li><b>N</b>orth</li>
 *         <li><b>E</b>ast</li>
 *         <li><b>W</b>est</li>
 *         <li><b>S</b>outh</li>
 *     </ul>
 *     Neighbour.
 * </p>
 * @author jTzipi
 */
public interface ICell2DTetragon extends ICell2DCartesian {

    /**
     * Return edge to the north neighbour.
     * @apiNote This edge is always present! Even if the edge points to a cell which is not visitable.
     * @return north edge
     */
    ICell2DTetragon getNeighbourNorth();
    /**
     * Return edge to the east neighbour.
     * @apiNote This edge is always present! Even if the edge points to a cell which is not visitable.
     * @return east edge
     */
    ICell2DTetragon getNeighbourEast();
    /**
     * Return edge to the west neighbour.
     * @apiNote This edge is always present! Even if the edge points to a cell which is not visitable.
     * @return west edge
     */
    ICell2DTetragon getNeighbourWest();
    /**
     * Return edge to the south neighbour.
     * @apiNote This edge is always present! Even if the edge points to a cell which is not visitable.
     * @return south edge
     */
    ICell2DTetragon getNeighbourSouth();

    /**
     * Set north neighbour.
     * @param neighbourNorth north neighbour
     */
    void setNeighbourNorth(ICell2DTetragon neighbourNorth);
    /**
     * Set east neighbour.
     * @param neighbourEast east neighbour
     */
    void setNeighbourEast(ICell2DTetragon neighbourEast);
    /**
     * Set west neighbour.
     * @param neighbourWest west neighbour
     */
    void setNeighbourWest(ICell2DTetragon neighbourWest);
    /**
     * Set south neighbour.
     * @param neighbourSouth south neighbour
     */
    void setNeighbourSouth(ICell2DTetragon neighbourSouth);
}