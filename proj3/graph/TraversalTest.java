package graph;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Unit tests for the Traversal class.
 *  @author Yuan Xie.
 */
public class TraversalTest {

    @Test
    public void testTraversal() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i += 1) {
            g.add();
        }
        for (int i = 1; i < 5; i += 1) {
            g.add(1, i);
        }
        g.add(2, 5);

        Traversal traversal = new BreadthFirstTraversal(g);
        traversal.traverse(1);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        result.add(4);
        result.add(5);
        assertEquals(result, traversal.myVisited());

        g.add();
        g.add(3, 6);
        traversal = new DepthFirstTraversal(g);
        traversal.traverse(1);
        result = new ArrayList<>();
        result.add(1);
        result.add(4);
        result.add(3);
        result.add(6);
        result.add(2);
        result.add(5);
        assertEquals(result, traversal.myVisited());
    }

}
