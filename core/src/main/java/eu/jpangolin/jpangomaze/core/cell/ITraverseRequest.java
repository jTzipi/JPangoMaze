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

package eu.jpangolin.jpangomaze.core.cell;


import java.util.Optional;

public interface ITraverseRequest<C extends ICell> {

    enum RequestResult {

        PERMITTED,
        DENIED_NO_INIT,
        DENIED_BORDER,
        DENIED_MASKED,
        DENIED_WEIGHT,
        DENIED_NO_LEGAL_MOVE;

    }

    RequestResult requestResult();

    /**
     * Return the neighbour cell for the request.
     *
     * @implNote The optional should be empty if the request is denied.
     *           Reason of denial are {@link RequestResult}.
     * @return Optional neighbour cell.
     */
    Optional<C> neighbour();
}