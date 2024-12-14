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

import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Abstract implementation of {@linkplain ICell}.
 * <p>
 *     Each abstract cell on any planes or dimension have
 *     <ul>
 *         <li>Grid unique Id</li>
 *         <li>Set of neighbour cells</li>
 *         <li>masked attribute</li>
 *     </ul>
 * </p>
 *
 * @author jTzipi
 */
public abstract class AbstractCell implements ICell {


    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AbstractCell.class);

    // -- Attributes!
    protected final long guid;              // Grid Unique Id
    protected boolean masked;       // cell masked state

    /**
     * C.
     * @param gridUID unique grid id
     */
    protected AbstractCell(final long gridUID) {
        this.guid = gridUID;
        this.masked = false;
    }

    /**
     * Initialize this cell.
     * <p>
     *     E.G. set the default neighbour cells.
     * </p>
     */
    protected abstract void init();

    @Override
    public boolean isLinked(ICell cell) {
        Objects.requireNonNull(cell, "Cell is null!");
        return getLinkedNeighbours().contains(cell);
    }

    @Override
    public void link(ICell other, boolean bidi, long weight) {
        Objects.requireNonNull(other);
        // link to self !?
        if(this == other) {
            throw new IllegalArgumentException("Try to link to yourself!");
        }
        // already linked !?
        if(isLinked(other)) {
            LOG.info("The cell '{}' is linked to '{}' already!", other, this);
            return;
        }
        // other cell must be a neighbour cell
        if(!isNeighbour(other)) {
            LOG.warn("Cell link '{}' is not a neighbour of this '{}'!", other, this);
            return;
        }
        getLinkedNeighbourWeightMap().put(other, weight);
        LOG.warn("Link '{}' to '{}'", other, this);
        if(bidi) {
            other.link(this, false, weight);
        }
    }

    @Override
    public void unlink(ICell other, boolean bidi) {
        Objects.requireNonNull(other);
        //
        if(this == other) {
            throw new IllegalArgumentException("Try to unlink from yourself!");
        }
        // not linked !?
        if(!isLinked(other)) {
            LOG.info("The cell '{}' is unlinked from '{}' already!", other, this);
            return;
        }
        // other cell must be a neighbour cell
        if(!isNeighbour(other)) {
            LOG.warn("Cell unlink '{}' is not a neighbour of this '{}'!", other, this);
            return;
        }
        getLinkedNeighbourWeightMap().remove(other);
        LOG.warn("UnLink '{}' from '{}'", other, this );
        if(bidi) {
            other.unlink(this, false);
        }
    }

    @Override
    public boolean isUnmasked() {
        return !masked;
    }

    @Override
    public void setMasked(boolean masked) {
        this.masked = masked;
    }

    @Override
    public long guid() {
        return guid;
    }


    public static class BorderCell extends AbstractCell {

        protected BorderCell(long guid) {
            super(guid);
            setMasked(true);
        }
        @Override
        protected void init() {

        }



        @Override
        public void setLinkWeight(ICell neighbourCell, long weight) {
            throw new UnsupportedOperationException("BorderCell!!");
        }

        @Override
        public boolean isBorder() {
            return true;
        }

        @Override
        public Map<ICell, Long> getLinkedNeighbourWeightMap() {
            return Map.of();
        }

        @Override
        public Set<ICell> getNeighbours() {
            return Set.of();
        }
    }

}