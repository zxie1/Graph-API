package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/** The shortest paths through an edge-weighted graph.
 *  By overriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Yuan Xie.
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        for (int v: _G.vertices()) {
            setWeight(v, Double.POSITIVE_INFINITY);
            setPredecessor(v, 0);
        }

        setWeight(_source, 0);
        TreeSet<Integer> fringe = new TreeSet<>(new CustomComparator());
        fringe.add(_source);

        while (!fringe.isEmpty()) {
            int u = fringe.pollFirst();
            if (u == _dest) {
                break;
            }
            for (int v: _G.successors(u)) {
                double combinedWeight = getWeight(u) + getWeight(u, v);
                if (combinedWeight < getWeight(v)) {
                    setWeight(v, combinedWeight);
                    setPredecessor(v, u);
                    fringe.remove(v);
                    fringe.add(v);
                }
            }
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(v);
        int predecessor = getPredecessor(v);
        while (predecessor != _source) {
            result.add(0, predecessor);
            predecessor = getPredecessor(predecessor);
        }
        result.add(0, predecessor);
        return result;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;

    /** The starting vertex. */
    private final int _source;

    /** The target vertex. */
    private final int _dest;

    /** A CustomComparator for the TreeSet that orders the set based on
     *  the sum of getWeight and estimatedDistance. If estimatedDistance
     *  is 0.0, gives Dijkstra's. */
    class CustomComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer u, Integer v) {
            double uEstimate = getWeight(u) + estimatedDistance(u);
            double vEstimate = getWeight(v) + estimatedDistance(v);

            if (uEstimate <= vEstimate) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
