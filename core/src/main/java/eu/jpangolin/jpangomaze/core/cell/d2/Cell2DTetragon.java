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


import eu.jpangolin.jpangomaze.core.*;
import eu.jpangolin.jpangomaze.core.cell.ICell;


import java.util.*;

/**
 * A tetragonal cell on 2D plane.
 * <p>
 *
 *
 * </p>
 * @apiNote I decided now to store each neighbour node as an own instance. Not storing them dynamically in the neighbour
 * map.
 *
 */
public class Cell2DTetragon extends AbstractCell2DCartesian implements ICell2DTetragon {

    // -- Neighbours
    private ICell2DTetragon nn;
    private ICell2DTetragon en;
    private ICell2DTetragon wn;
    private ICell2DTetragon sn;
    // -- Linked Neighbour Cell Weight Map
    private final Map<ICell, Long> linkWeightMap = new HashMap<>();

    /**
     * C.
     * @param gridUID grid unique id
     * @param row row &ge; 0
     * @param column column &ge; 0
     */
    Cell2DTetragon(final long gridUID, final int row, final int column ) {
        super(gridUID, row, column);
    }

    /**
     * Create a tetragonal cell.
     * @param gridUID grid uid
     * @param row row &ge; 0
     * @param column column &ge; 0
     * @return tetragonal cell
     * @throws IllegalArgumentException if {@code row}|{@code column} are not inbound
     */
    public static Cell2DTetragon of(final long gridUID, final int row, final int column) {
        MazeUtils.throwIfIllegalPosition2D(row, column);
        return new Cell2DTetragon(gridUID, row, column);
    }



    @Override
    public String toString() {
        return "Cell2DTetragon{"
                + "row='" + row
                + "', column='" + column
                + "', nn='" + nn
                + "', en='" + en
                + "', wn='" + wn
                + "', sn='" + sn
                + "', guid='" + guid
                + "', masked='" + masked
                + "'}";
    }

    @Override
    protected void init() {

        ICell2DTetragon borderCell = borderCell(guid());
        // - set all neighbours to border cells
        nn = borderCell;
        wn = borderCell;
        en = borderCell;
        sn = borderCell;
    }

    @Override
    public ICell2DTetragon getNeighbourNorth() {

        return nn;
    }

    @Override
    public ICell2DTetragon getNeighbourEast() {
        return en;
    }

    @Override
    public ICell2DTetragon getNeighbourWest() {
        return wn;
    }

    @Override
    public ICell2DTetragon getNeighbourSouth() {
        return sn;
    }

    @Override
    public void setNeighbourNorth(ICell2DTetragon neighbourNorth) {
        Objects.requireNonNull(neighbourNorth);
        this.nn = neighbourNorth;
    }

    @Override
    public void setNeighbourEast(ICell2DTetragon neighbourEast) {
        this.en = Objects.requireNonNull(neighbourEast);
    }

    @Override
    public void setNeighbourWest(ICell2DTetragon neighbourWest) {
    this.wn = Objects.requireNonNull(neighbourWest);
    }

    @Override
    public void setNeighbourSouth(ICell2DTetragon neighbourSouth) {
    this.sn = Objects.requireNonNull(neighbourSouth);
    }

    @Override
    public void setLinkWeight(ICell neighbourCell, long weight) {
        // TODO relax check whether the cell is a neighbour cell and
        // If so set the link here
        if(!getLinkedNeighbours().contains(neighbourCell)) {
            throw new IllegalArgumentException("");
        }

        linkWeightMap.put(neighbourCell, weight);
    }

    @Override
    public Map<ICell, Long> getLinkedNeighbourWeightMap() {
        return linkWeightMap;
    }

    @Override
    public Set<ICell> getNeighbours() {
        return Set.of(nn, wn, en, sn);
    }

    public static ICell2DTetragon borderCell(long guid) {

        return new BorderCell2DTetragon(guid);
    }

    public static final class BorderCell2DTetragon extends BorderCell2D implements ICell2DTetragon {

        BorderCell2DTetragon(long guid) {
            super(guid);
        }

        @Override
        public ICell2DTetragon getNeighbourNorth() {
            return null;
        }

        @Override
        public ICell2DTetragon getNeighbourEast() {
            return null;
        }

        @Override
        public ICell2DTetragon getNeighbourWest() {
            return null;
        }

        @Override
        public ICell2DTetragon getNeighbourSouth() {
            return null;
        }

        @Override
        public void setNeighbourNorth(ICell2DTetragon neighbourNorth) {

        }

        @Override
        public void setNeighbourEast(ICell2DTetragon neighbourEast) {

        }

        @Override
        public void setNeighbourWest(ICell2DTetragon neighbourWest) {

        }

        @Override
        public void setNeighbourSouth(ICell2DTetragon neighbourSouth) {

        }
    }
}