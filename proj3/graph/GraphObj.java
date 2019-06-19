package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Yuan Xie.
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _myVertices = new ArrayList<>();
        _myEdges = new ArrayList<>();
        _edgeID = 0;
    }

    @Override
    public int vertexSize() {
        return _myVertices.size();
    }

    @Override
    public int maxVertex() {
        return _myVertices.get(_myVertices.size() - 1);
    }

    @Override
    public int edgeSize() {
        return _myEdges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (_myVertices.contains(v)) {
            int result = 0;
            for (Edge e: _myEdges) {
                if (e.edge[0] == v) {
                    result += 1;
                }
                if (!isDirected() && e.edge[0] != v && e.edge[1] == v) {
                    result += 1;
                }
            }
            return result;
        } else {
            return 0;
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _myVertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        for (Edge e: _myEdges) {
            if (e.edge[0] == u && e.edge[1] == v) {
                return true;
            }
            if (!isDirected() && e.edge[0] == v && e.edge[1] == u) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        if (_myVertices.size() == 0 || _myVertices.get(0) != 1) {
            _myVertices.add(0, 1);
            return 1;
        }
        for (int i = 0; i < _myVertices.size() - 1; i += 1) {
            if (_myVertices.get(i + 1) != _myVertices.get(i) + 1) {
                _myVertices.add(i + 1, _myVertices.get(i) + 1);
                return _myVertices.get(i + 1);
            }
        }
        _myVertices.add(maxVertex() + 1);
        return maxVertex();
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        for (Edge e: _myEdges) {
            if (e.edge[0] == u && e.edge[1] == v) {
                return e.edgeID;
            }
            if (!isDirected() && e.edge[0] == v && e.edge[1] == u) {
                return e.edgeID;
            }
        }
        int[] e = {u, v};
        _edgeID += 1;
        Edge edge = new Edge(e, _edgeID);
        _myEdges.add(edge);
        return edge.edgeID;
    }

    @Override
    public void remove(int v) {
        ArrayList<Edge> remove = new ArrayList<>();
        if (_myVertices.contains(v)) {
            int index = _myVertices.indexOf(v);
            _myVertices.remove(index);
            for (Edge e: _myEdges) {
                if (e.edge[0] == v || e.edge[1] == v) {
                    remove.add(e);
                }
            }
            for (Edge e: remove) {
                _myEdges.remove(e);
            }
        }
    }

    @Override
    public void remove(int u, int v) {
        ArrayList<Edge> remove = new ArrayList<>();
        for (Edge e: _myEdges) {
            if (e.edge[0] == u && e.edge[1] == v) {
                remove.add(e);
            }
            if (!isDirected() && e.edge[0] == v && e.edge[1] == u) {
                remove.add(e);
            }
        }
        for (Edge e: remove) {
            _myEdges.remove(e);
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_myVertices);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Edge e: _myEdges) {
            if (e.edge[0] == v) {
                result.add(e.edge[1]);
            }
            if (!isDirected() && e.edge[0] != v && e.edge[1] == v) {
                result.add(e.edge[0]);
            }
        }
        return Iteration.iteration(result);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> result = new ArrayList<>();
        for (Edge e: _myEdges) {
            result.add(e.edge);
        }
        return Iteration.iteration(result);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        for (Edge e: _myEdges) {
            if (e.edge[0] == u && e.edge[1] == v) {
                return e.edgeID;
            }
            if (!isDirected() && e.edge[0] == v && e.edge[1] == u) {
                return e.edgeID;
            }
        }
        return 0;
    }

    /** Package-Private method that returns my vertices. */
    ArrayList<Integer> myVertices() {
        return _myVertices;
    }

    /** Package-Private method that returns my edges. */
    ArrayList<Edge> myEdges() {
        return _myEdges;
    }

    /** ArrayList representation of my vertices. */
    private ArrayList<Integer> _myVertices;


    /** ArrayList containing Edge. */
    private ArrayList<Edge> _myEdges;

    /** Int representing next edgeID to be assigned. */
    private int _edgeID;

    /** Package-Private class representing an edge. */
    class Edge {

        /** Integer array representing an edge. */
        private int[] edge;

        /** Integer representation for edgeID. */
        private int edgeID;

        /** Constructor that takes in E and EID. */
        Edge(int[] e, int eID) {
            this.edge = e;
            this.edgeID = eID;
        }

        /** Method that returns my edge. */
        int[] myEdge() {
            return edge;
        }

        /** Method that returns my edgeID. */
        int myEdgeID() {
            return edgeID;
        }
    }

}
