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

/**
 * A location in a 2D cartesian coordinate system.
 * consists of a row and a column.
 * @param row row [0 .. max]
 * @param column column [0 .. max]
 * @author jTzipi
 */
public record Location2D(int row, int column) implements ILocation2D {

    /**
     * Constructor.
     * @param row row [0 .. ]
     * @param column column [0 .. ]
     */
    public Location2D {
        if (row < MIN || column < MIN) {
            throw new IllegalArgumentException("Row[=" + row + "] or Column[=" + column + "] < " + MIN);
        }
    }


    /**
     * Create a new Location2DCartesian.
     * @param row row [0 .. ]
     * @param column column [0 .. ]
     * @return instance
     * @throws IllegalArgumentException if {@code row} or {@code colmn} are {@literal < }{@link #MIN}
     */
    public static Location2D of(int row, int column) {
        if(row < MIN || column < MIN) {
            throw new IllegalArgumentException("Row[=" +row+"] or Column[=" + column+ "] < " +MIN );
        }

        return new Location2D(row,column);
    }
}