package graph;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Yuan Xie.
 */
public class GraphTest {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void basicFunctionality() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }

        assertEquals(4, g.vertexSize());
        assertEquals(4, g.maxVertex());

        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(4);
        assertEquals(result, g.myVertices());

        g.remove(3);
        result.remove(2);
        assertEquals(result, g.myVertices());

        g.add();
        result.add(2, 3);
        assertEquals(result, g.myVertices());

        Iteration<Integer> vertices = g.vertices();
        assertEquals(1, (int) vertices.next());
        assertEquals(2, (int) vertices.next());
    }

    @Test
    public void testDirectedGraph() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }

        assertEquals(4, g.edgeSize());
        assertEquals(4, g.outDegree(1));
        assertEquals(1, g.inDegree(1));
        assertTrue(g.contains(1, 2));
        assertFalse(g.contains(2, 1));
        assertEquals(2, g.add(1, 2));
        assertEquals(2, g.edgeId(1, 2));
        assertEquals(4, g.edgeId(1, 4));

        g.remove(1, 2);
        assertFalse(g.contains(1, 2));

        g.remove(1);
        assertEquals(0, g.edgeSize());
    }

    @Test
    public void testUndirectedGraph() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }
        assertEquals(4, g.edgeSize());
        assertEquals(g.inDegree(1), g.outDegree(1));
        assertEquals(4, g.inDegree(1));
        assertTrue(g.contains(1, 2));
        assertTrue(g.contains(2, 1));

        g.remove(1);
        assertFalse(g.contains(1, 2));
        assertFalse(g.contains(2, 1));

        g.add();
        g.add();
        assertTrue(g.contains(1));
        assertTrue(g.contains(5));

        g.add(1, 5);
        assertEquals(5, g.edgeId(1, 5));

        g.add(2, 3);
        assertEquals(2, g.edgeSize());
        assertEquals(5, g.maxVertex());
    }

    @Test
    public void testIterationsDirected() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }

        Iteration<Integer> successors = g.successors(1);
        assertEquals(1, (int) successors.next());
        assertEquals(2, (int) successors.next());
        assertEquals(3, (int) successors.next());
        assertEquals(4, (int) successors.next());
        assertFalse(successors.hasNext());

        Iteration<Integer> predecessors = g.predecessors(4);
        assertEquals(1, (int) predecessors.next());
        assertFalse(predecessors.hasNext());

        g.add();
        g.add(5, 3);
        predecessors = g.predecessors(3);
        assertEquals(1, (int) predecessors.next());
        assertEquals(5, (int) predecessors.next());
        assertFalse(predecessors.hasNext());
    }

    @Test
    public void testIterationsUndirected() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }

        Iteration<Integer> successors = g.successors(1);
        assertEquals(1, (int) successors.next());
        assertEquals(2, (int) successors.next());
        assertEquals(3, (int) successors.next());
        assertEquals(4, (int) successors.next());
        assertFalse(successors.hasNext());

        Iteration<Integer> predecessors = g.predecessors(1);
        assertEquals(1, (int) predecessors.next());
        assertEquals(2, (int) predecessors.next());
        assertEquals(3, (int) predecessors.next());
        assertEquals(4, (int) predecessors.next());
        assertFalse(predecessors.hasNext());
    }

    @Test
    public void testCheckMyVertex() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }

        g.checkMyVertex(1);
    }

    @Test
    public void testAdd() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 4; i += 1) {
            g.add();
        }
        g.remove(1);
        assertFalse(g.contains(1));

        g.add();
        assertTrue(g.contains(1));

        g.remove(3);
        g.add();
        g.add();
        assertTrue(g.contains(3));
        assertTrue(g.contains(5));
    }

    @Test
    public void random() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(0, g.vertexSize());
    }

    @Test
    public void random1() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        assertEquals(1, g.vertexSize());
    }

    @Test
    public void random2() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        assertEquals(2, g.vertexSize());
    }

}
