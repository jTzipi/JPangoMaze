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

import eu.jpangolin.jpangomaze.core.ILocation;
import eu.jpangolin.jpangomaze.core.cell.AbstractCell;

import java.util.Objects;
/**
 * Abstract implementation of a 2D cell.
 * <p>
 *     Here we add accessors for rows and columns and the equals and hash code method.
 * </p>
 */
public abstract class AbstractCell2D extends AbstractCell implements ICell2D {



    // -- Attribute
    final int row;
    final int column;


    /**
     * Abstract Cell 2D.
     * @param row row [{@link ILocation#MIN}..]
     * @param column column [{@link ILocation#MIN} ..]
     * @param gridUniqueId grid unique id
     */
    AbstractCell2D(final long gridUniqueId, final int row, final int column ) {
        super(gridUniqueId);
        this.row = Math.max(ILocation.MIN, row);
        this.column = Math.max(ILocation.MIN ,column);

    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ICell2D that)) return false;
        return row == that.getRow() && column == that.getColumn() && guid() == that.guid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, guid());
    }

    public static class BorderCell2D extends BorderCell implements ICell2D {


        BorderCell2D(long guid) {
            super(guid);
        }

        @Override
        public int getRow() {
            return ILocation.UN_TRAVERSABLE;
        }

        @Override
        public int getColumn() {
            return ILocation.UN_TRAVERSABLE;
        }
    }
}