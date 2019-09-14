package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Yuan Xie.
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        if (myVertices().contains(v)) {
            int result = 0;
            for (Edge e: myEdges()) {
                if (e.myEdge()[1] == v) {
                    result += 1;
                }
            }
            return result;
        } else {
            return 0;
        }
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Edge e: myEdges()) {
            if (e.myEdge()[1] == v) {
                result.add(e.myEdge()[0]);
            }
        }
        return Iteration.iteration(result);
    }

}
