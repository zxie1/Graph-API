package graph;

/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Yuan Xie.
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. Fixed. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _predecessors = new int[G.maxVertex()];
        _weights = new double[G.maxVertex()];
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        return _weights[v - 1];
    }

    @Override
    protected void setWeight(int v, double w) {
        _weights[v - 1] = w;
    }

    @Override
    public int getPredecessor(int v) {
        return _predecessors[v - 1];
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessors[v - 1] = u;
    }

    /** Array representing predecessors, where vertex i's predecessor
     *  is located at index i - 1. */
    private int[] _predecessors;

    /** Array representing the weights of each vertex. Vertex i's weight
     *  is located at index i - 1. */
    private double[] _weights;
}
