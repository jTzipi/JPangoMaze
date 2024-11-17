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
 * Direction is the abstraction of a move to a new place in space.
 * <p>
 *     The simple example is in a 2D cartesian coordinate system.
 *     On a square grid we have for each cell 4 neighbours.
 * <br/>
 *     |---| north neighbour |---|
 *     | west neighbour | cell | east neighbour |
 *     |---| south neighbour | --- |
 * <br/>
 *     Each kind of tiling has its own set of neighbours.
 *     That we may traverse.
 * </p>
 * @author jTzipi
 */
public interface IDirection {
}