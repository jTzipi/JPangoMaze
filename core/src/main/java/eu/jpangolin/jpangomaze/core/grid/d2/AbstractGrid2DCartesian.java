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

package eu.jpangolin.jpangomaze.core.grid.d2;

import eu.jpangolin.jpangomaze.core.ILocation;
import eu.jpangolin.jpangomaze.core.ILocation2D;
import eu.jpangolin.jpangomaze.core.Location2D;
import eu.jpangolin.jpangomaze.core.MazeUtils;

import eu.jpangolin.jpangomaze.core.cell.d2.ICell2DCartesian;

import java.util.*;
import java.util.stream.Stream;

import static eu.jpangolin.jpangomaze.core.MazeUtils.clamp;
import static eu.jpangolin.jpangomaze.core.MazeUtils.throwIfOutOfBounds2DCartesian;

/**
 * Abstract skeletal implementation of {@link IGrid2DCartesian}.
 * @param <C> subtype of {@linkplain ICell2DCartesian}
 *
 * @author jTzipi
 */
public abstract class AbstractGrid2DCartesian<C extends ICell2DCartesian> implements IGrid2DCartesian<C> {

    /**
     * Minimal length for rows and columns.
     * TODO: maybe MIN_LEN = DIM
     *       maybe LAX_LEN = Integer.MAX_VALUE / getDim() - getDim()
     */
    static final int MIN_LEN = 2;
    static final int MAX_LEN = Integer.MAX_VALUE / 2 - 2;

    // -- Attribute
    final int rows;     // rows
    final int cols;     // columns
    final Mask2DCartesian mask2D;
    final long guid;

    /**
     * Abstract 2D Cartesian grid.
     *
     * Attention: we clamp {@code rows},{@code columns} to [{@linkplain #MIN_LEN},{@linkplain #MAX_LEN}]
     * @param rows rows [{@linkplain #MIN_LEN} .. {@linkplain #MAX_LEN}]
     * @param columns columns [{@linkplain  #MIN_LEN .. {@linkplain  #MAX_LEN}}
     */
    AbstractGrid2DCartesian( final int rows, final int columns ) {
        this.rows = clamp(rows, MIN_LEN, MAX_LEN);
        this.cols = clamp(columns, MIN_LEN, MAX_LEN);
        this.mask2D = new Mask2DCartesian(this.rows, this.cols);
        this.guid = MazeUtils.generateGUID();
    }

    /**
     * Create and prepare the grid.
     */
    abstract void prepare();

    /**
     * Configure the grid.
     */
    abstract void configure();

    /**
     * Return the - prepared and configured - 2D array grid.
     * @return grid
     * @implSpec The grid should be {@linkplain #prepare() prepared} and {@linkplain #configure() configured}.
     * @see #prepare()
     * @see #configure()
     */
    abstract C[][] grid();


    @Override
    public IMask2D getMask() {
        return mask2D;
    }

    @Override
    public int getColumns() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }


    @Override
    public C getRandomCell() {
        return MazeUtils.getRandomCell(getCells());
    }

    @Override
    public int getSize() {
        return getRows() * getColumns() - getMask().getMaskedCells();
    }


    @Override
    public List<C> getCells() {
        return Stream.of(grid())
                .flatMap(Stream::of)
                .filter( this::isUnmasked)
                .toList();
    }

    @Override
    public List<C> getCellsForRow(int row) {
        if(ILocation.MIN > row || row >= getRows()) {
            throw new IndexOutOfBoundsException("Row[=" + row + "] is not inbound" );
        }

        return Stream.of(grid()[row])
                .filter(this::isUnmasked)
                .toList();
    }

    @Override
    public C getCell(int row, int column) {
        if(!isInbound(row , column)) {
            throw new IndexOutOfBoundsException("Row[=" +row+"] or Column[=" + column+ "] < " + ILocation.MIN);
        }

        return grid()[row][column];
    }

    @Override
    public long getUGID() {
        return guid;
    }

    public static class Mask2DCartesian implements IGrid2D.IMask2D {

        final Set<ILocation2D> maskS = new HashSet<>();
        final int rows;
        final int cols;
        protected Mask2DCartesian(final int r, final int c) {
    this.rows = r;
    this.cols = c;
        }

        @Override
        public int getMaskedCells() {
            return maskS.size();
        }

        @Override
        public boolean isUnmasked(int row, int column) {
            throwIfOutOfBounds2DCartesian(row, column, rows, cols);
            return !maskS.contains(Location2D.of(row, column));
        }

        @Override
        public boolean mask(int row, int column) {
            throwIfOutOfBounds2DCartesian(row, column, rows, cols);
            Location2D l2D = Location2D.of(row, column);
            return maskS.add(l2D);
        }

        @Override
        public boolean unmask(int row, int column) {
            throwIfOutOfBounds2DCartesian(row,column, rows, cols);
            Location2D l2d = Location2D.of(row, column);
            return maskS.remove(l2d);
        }
    }
}