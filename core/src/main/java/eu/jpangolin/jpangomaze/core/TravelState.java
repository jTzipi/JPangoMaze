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

import java.util.EnumSet;
import java.util.function.BooleanSupplier;

public enum TravelState implements BooleanSupplier {


    PERMITTED(true),
    MASKED(false),
    WEIGHT(false),
    NOT_LEGAL_MOVE(false),
    BORDER(false),
    __NA__(false);
    ;
    // Travel to cell allowed
    final boolean accept;

    TravelState(boolean accept) {
        this.accept = accept;
    }

    @Override
    public boolean getAsBoolean() {
        return accept;
    }

    public static EnumSet<TravelState> DENIED_STATES = EnumSet.of(MASKED, WEIGHT, NOT_LEGAL_MOVE, BORDER, __NA__);
}