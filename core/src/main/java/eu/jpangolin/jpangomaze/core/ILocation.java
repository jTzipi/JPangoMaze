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
 * Location of a cell in space.
 * <p>
 * More precisely
 * <ul>
 *     <li>a location on a grid.</li>
 *     <li>a cell's spacial position</li>
 * </ul>
 * </p>
 * <p>
 *     E.G. a 2D cell has a row and a column as it's location.
 * </p>
 */
public interface ILocation {
    /**
     * A not legal location.
     */
    int UN_TRAVERSABLE = -1;

    int MIN = 0;

    /**
     * Null Location.
     * Null Object Pattern.
     */
    enum NullLocation implements ILocation {
        /**
         * Singleton instance.
         */
        SINGLETON;
    }
}