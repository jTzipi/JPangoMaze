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
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utils for Mazes.
 *
 * @author jTzipi
 */
public final class MazeUtils {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MazeUtils.class);
    //
    private MazeUtils() {
       throw new AssertionError("\n???");
    }

    /**
     * Return a random cell from the list of cells.
     * @param cellList cell list
     * @return random item
     * @param <C> cell subtype
     * @throws NullPointerException if {@code cellList}
     * @throws IllegalArgumentException if {@code cellList} is empty
     */
    public static <C extends ICell> C getRandomCell(List<C> cellList) {
        Objects.requireNonNull(cellList);
        if(cellList.isEmpty()) {
            throw new IllegalArgumentException("The cell list is empty!");
        }
        int pos = TLR.nextInt(cellList.size());
        LOG.warn("Next cell for pos {} of {}", pos, cellList.size());
        return cellList.get(pos);
    }

    /**
     * Clamp a value to [{@code min} .. {@code max}].
     *
     * @param val value
     * @param min minimal
     * @param max maximal
     * @param <T> subtype of comparable
     * @return value clamped
     */
    public static <T extends Comparable<? super T>> T clamp( T val, T min, T max ) {

        Objects.requireNonNull( val, "value is null" );
        Objects.requireNonNull( min, "min value is null" );
        Objects.requireNonNull( max, "max value is null" );

        final T ret;

        if ( max.compareTo( val ) < 0 ) {

            ret = max;
        } else if ( min.compareTo( val ) > 0 ) {
            ret = min;
        } else {
            ret = val;
        }

        return ret;
    }


    /**
     * Return whether a row is in bounds.
     * @param row row
     * @param maxRows max rows for grid
     * @return {@code true} if {@code row} &ge; {@linkplain ILocation#MIN} and {@code row} &lt; {@code maxRows}
     */
    public static boolean isRowOutInBound2DCartesian(int row, int maxRows) {
        return ILocation.MIN <= row && row < maxRows;
    }

    /**
     * Is the location [row, column] inbound?.
     * @param row row
     * @param column column
     * @param maxRows max rows
     * @param maxColumns max column
     * @return {@code true} if {@code row} &ge; {@link ILocation#MIN min} and
     *      {@code column} &ge; {@link ILocation#MIN min} and
     *       {@code row} &lt; {@code maxRows} and
     *       {@code column} &lt; {@code maxColumns} else {@code false}
     */
    public static boolean isLocationInBound2DCartesian(int row, int column, int maxRows, int maxColumns ) {
    return ILocation.MIN <= row && ILocation.MIN <= column && row < maxRows && column < maxColumns;
    }

    
    public static void throwIfRowOutOfBounds2DCartesian(int row, int maxRows ) {
        if(!isRowOutInBound2DCartesian(row, maxRows)) {
            throw new IndexOutOfBoundsException("Row[=" + row + "] is out of bound[" + ILocation.MIN + ", " + maxRows+"]");
        }
    }

    /**
     * Throw a {@link IndexOutOfBoundsException} if row or column are not in bounds of a 2D cartesian grid.
     * @param row row
     * @param column column
     * @param maxRows max rows
     * @param maxColumns max columns
     * @throws IndexOutOfBoundsException if
     */
    public static void throwIfOutOfBounds2DCartesian( int row, int column, int maxRows, int maxColumns) {
            if( !isLocationInBound2DCartesian(row, column, maxRows, maxColumns) ) {
                throw new IndexOutOfBoundsException("["+ row+","+column+"] is not in [0,"+maxRows+"][0,"+maxColumns+"]" );
            }
    }

    public static long generateGUID() {
        return System.nanoTime();
    }
}