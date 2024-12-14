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

package eu.jpangolin.jpangomaze.core.distance;


import eu.jpangolin.jpangomaze.core.IWeightedEdge;
import eu.jpangolin.jpangomaze.core.cell.ICell;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * Edgar Dijkstra's modified algo for finding the shortest path in a graph.
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm" alt="Dijkstra Algo" target="_blank">website</a>
 * <br />
 * <br />
 * Modification:
 * <br />
 * Instead of labeling all edges of our maze with infinity cost we add and remove the edges on demand.
 *
 * </p>
 * @implNote This algo can only handle <u>positive</u> weights &ge; 0.
 *
 * @author jTzipi
 */
public class Dijkstras implements IDistanceMeasurer {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Dijkstras.class);


    private Dijkstras() {

    }


    public static Dijkstras of() {
        return new Dijkstras();
    }

    @Override
    public IDistanceResult analyse(ICell rootCell) {
        Objects.requireNonNull(rootCell);


        final Map<ICell, IPathLink> pathMap = new HashMap<>();
        pathMap.put(rootCell, PathLinkRec.ofRoot(rootCell));

        // Our queue of the current least distance from the root cell
        final PriorityQueue<IDistance<ICell>> weightQueue = new PriorityQueue<>();

        // Add the first distance to our queue
        // the root cell with cost of 0
        weightQueue.add(new DistanceRec<>(rootCell, IWeightedEdge.FREE));


        while (!weightQueue.isEmpty()) {
            // the next cell with the lowest cost to travel
            // first cell is root
            //
            IDistance<ICell> leastDist = weightQueue.poll();
            LOG.info("Next Distance = '{}'", leastDist);
            ICell leastCell = leastDist.cell();
            long leastWeight = leastDist.weight();

            // link to the previous cell
            IPathLink prevLink = pathMap.get(leastCell);
            // for all linked cells
            // so no masked and no border cells and linked to the least cell
            // We calculate the cost to travel the edge
            for (ICell nb : leastCell.getLinkedNeighbours()) {
                LOG.info("Neighbour edge of '{}' -> '{}'", leastCell, nb);

                // get the weight to traverse the neighbour or SIMPLE if not set
                long weight = leastCell.getLinkedNeighbourWeightMap().getOrDefault(nb, IWeightedEdge.SIMPLE);
                long travelCost = leastWeight + weight;
                DistanceRec<ICell> nbDist = new DistanceRec<>(nb, travelCost);

                // if did not set the cost for a neighbour
                // we set this here and add the neighbour to the
                // queue too
                if (!pathMap.containsKey(nb)) {

                    LOG.info("New Cell prev link = {}", prevLink);
                    IPathLink old = pathMap.put(nb, new PathLinkRec(nb, travelCost, prevLink, prevLink.steps() + 1));
                    LOG.info("Old was  '{}'", old);
                    boolean added = weightQueue.add(nbDist);
                    LOG.info("Added to queue ? '{}'", added);
                } else {
                    // if we have already labeled a distance to the neighbour
                    // we check whether the new cost the know neighbour and
                    // also known distance
                    // if so we replace the weight for this path!
                    //
                    // AFAIU the edge we travel with this lower cost
                    // can NOT be traveled earlier!
                    // so we add this distance to our distance queue!
                    IPathLink oldLink = pathMap.get(nb);
                    long oldWeight = oldLink.weight();
                    LOG.info("We already know the cell {}, and the old cost = {} and new = {}", nb, oldWeight, travelCost);

                    if (oldWeight > travelCost) {

                        boolean replaced = pathMap.replace(nb, oldLink, new PathLinkRec(nb, travelCost, prevLink, prevLink.steps() + 1));
                        LOG.info("New cost = {} < then {}", travelCost, oldWeight);
                        boolean added = weightQueue.add(nbDist);
                        LOG.info("Added? = {}", added);
                    }
                }


            }

        }

        return new DistanceResultRec(pathMap);
    }

}