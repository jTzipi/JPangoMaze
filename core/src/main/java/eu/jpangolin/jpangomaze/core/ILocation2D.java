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
 * A location in a  - 2D - coordinate system.
 * <p>
 *     For a <em>cartesian coordinate system</em> we have
 *     <ul>
 *         <li>row or x &ge; 0</li>
 *         <li>column or y &ge; 0</li>
 *     </ul>
 *     coordinate.
 *     <br/>
 *     <br/>
 *     For a polar coordinate system we have
 *     <ul>
 *         <li>row or circle &ge; 0</li>
 *         <li>column or part of the circle &ge; 0</li>
 *     </ul>
 *     coordinate.
 * </p>
 * <p>
 *     This interface is related to the {@link eu.jpangolin.jpangomaze.core.cell.ICell}
 *     interface but since a cell is <u>not</u> a location but has a location cell
 *     should not extend this.
 * </p>
 * @author jTzipi
 */
public interface ILocation2D extends ILocation {

    /**
     * Row or X coordinate of this location.
     * @return row {@literal >= 0}
     */
    int row();

    /**
     * Column or Y coordinate of this location.
     * @return column {@literal >= 0}
     */
    int column();

    /**
     * Null Object Pattern for ILocation2D.
     */
    enum NullLocation2D implements ILocation2D {

        SINGLETON;

        @Override
        public int row() {
            return UN_TRAVERSABLE;
        }

        @Override
        public int column() {
            return UN_TRAVERSABLE;
        }
    }
}