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
import eu.jpangolin.jpangomaze.core.cell.ICell;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 */
public abstract class AbstractCell2D implements ICell2D {

    protected static final org.slf4j.Logger CELL_LOG = LoggerFactory.getLogger(AbstractCell2D.class);

    // -- Attribute
    final int row;
    final int column;
    final long guid;
    final Set<ICell> linkedNeighbourSet = new HashSet<>();

    /**
     * Abstract Cell 2D.
     * @param row row [{@link ILocation#MIN}..]
     * @param column column [{@link ILocation#MIN} ..]
     * @param gridUniqueId grid unique id
     */
    AbstractCell2D(final int row, final int column, long gridUniqueId) {
        this.row = Math.max(ILocation.MIN, row);
        this.column = Math.max(ILocation.MIN ,column);
        this.guid = gridUniqueId;
    }

    @Override
    public void link(ICell other, boolean bidi) {
        if(isLinked(other)) {
            CELL_LOG.info("The cell '{}' is linked to '{}' already!", other, this);
            return;
        }

        boolean added = getLinkedNeighbours().add(other);
        CELL_LOG.warn("Link '{}' to '{}' done = {}!", other, this, added);
        if(bidi) {
            other.link(this, false);
        }
    }

    @Override
    public void unlink(ICell other, boolean bidi) {
        if(!isLinked(other)) {
            CELL_LOG.info("The cell '{}' is unlinked from '{}' already!", other, this);
            return;
        }
        boolean rem = getLinkedNeighbours().remove(other);
        CELL_LOG.warn("UnLink '{}' from '{}' done = {}!", other, this, rem);
        if(bidi) {
            other.unlink(this, false);
        }
    }

    @Override
    public boolean isLinked(ICell cell) {
        Objects.requireNonNull(cell);
        return getLinkedNeighbours().contains(cell);
    }

    @Override
    public Set<ICell> getLinkedNeighbours() {
        return linkedNeighbourSet;
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
    public final long guid() {
        return guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ICell2D that)) return false;
        return row == that.getRow() && column == that.getColumn() && guid == that.guid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, guid);
    }
}