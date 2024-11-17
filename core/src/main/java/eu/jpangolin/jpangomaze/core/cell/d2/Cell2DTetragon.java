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


import eu.jpangolin.jpangomaze.core.Direction2DCartesian;
import eu.jpangolin.jpangomaze.core.ILocation;
import eu.jpangolin.jpangomaze.core.IWeightedEdge;

import java.util.EnumSet;
import java.util.Optional;


public class Cell2DTetragon extends AbstractCell2DCartesian implements ICell2DTetragon {

    static final EnumSet<Direction2DCartesian> LEGAL_MOVE_SET = EnumSet.of(Direction2DCartesian.NORTH, Direction2DCartesian.EAST, Direction2DCartesian.WEST, Direction2DCartesian.SOUTH);


    Cell2DTetragon(final int row, final int column, long gridUniqueId ) {
        super(row, column, gridUniqueId);
    }

    public static Cell2DTetragon of(final int row, final int column, final long guid) {

        return new Cell2DTetragon(row, column, guid);
    }

    @Override
    public Optional<IWeightedEdge<ICell2DTetragon>> getNeighbourNorth() {
        return neighbourMap.get(Direction2DCartesian.NORTH);
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
    public void setNeighbourNorth(ICell2DTetragon nbCell, long weight) {

    }

    @Override
    public void setNeighbourEast(ICell2DTetragon nbCell, long weight) {

    }

    @Override
    public void setNeighbourWest(ICell2DTetragon nbCell, long weight) {

    }

    @Override
    public void setNeighbourSouth(ICell2DTetragon nbCell, long weight) {

    }


    public static ICell2DTetragon nullCell(long guid) {
        return new NullCell2DTetragon(guid);
    }

    private static final class NullCell2DTetragon extends Cell2DTetragon {

        private NullCell2DTetragon(final long guid) {
            super(ILocation.UN_TRAVERSABLE, ILocation.UN_TRAVERSABLE, guid);
        }

        @Override
        public Optional<IWeightedEdge<ICell2DTetragon>> getNeighbourNorth() {
            return Optional.empty();
        }



        @Override
        public void setNeighbourNorth(ICell2DTetragon nb, long weight) {
            throw new UnsupportedOperationException("__NA__");
        }

        @Override
        public void setNeighbourEast(ICell2DTetragon nb) {
            throw new UnsupportedOperationException("__NA__");
        }

        @Override
        public void setNeighbourWest(ICell2DTetragon nb, long weight) {
            throw new UnsupportedOperationException("__NA__");
        }

        @Override
        public void setNeighbourSouth(ICell2DTetragon nb, long weight) {
            throw new UnsupportedOperationException("__NA__");
        }
    }

}