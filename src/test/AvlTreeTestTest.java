package test;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;
import source.AvlTree;
import source.Node;

import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by User on 29.06.2017.
 */
public class AvlTreeTestTest {
    AvlTree tree = new AvlTree();

    @Test
    public void add() throws Exception {
        for (int i = 1; i < 11; i++) {
            tree.add(i);
        }
        if (tree.contains(5)) {
            System.out.println("Test is passed");
        } else {
            fail("Test is not passed");        }
    }
    @Test
    public void delete() throws Exception {
        for (int i = 1; i < 11; i++) {
            tree.add(i);
        }
        tree.delete(7);
        if (tree.size() == 9) {
            System.out.println("Test is passed");
        } else {
            fail("Test is not passed");        }


    }

    @Test
    public void testRemoveAll() {
        System.out.println("RemoveAll test #1");
        AvlTree<Integer> tree1 = new AvlTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AvlTree<Integer> tree2 = new AvlTree();
        tree2.add(4);
        tree2.add(5);
        tree2.add(6);
        boolean res = tree1.removeAll(tree2);
        if (!res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    @Test
    public void testRetainAll() {
        System.out.println("RetainAll test #1");
        AvlTree<Integer> tree1 = new AvlTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AvlTree<Integer> tree2 = new AvlTree();
        tree2.add(1);
        tree2.add(2);
        tree2.add(3);
        boolean res = tree1.removeAll(tree2);
        if (!res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    @Test
    public void testContains() throws Exception {
        System.out.println("Contains test #1");
        AvlTree tree = new AvlTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        if (tree.contains(10)) {
            System.out.println("Test is passed");
        } else {
            fail("Test is not passed");
        }
    }

    @Test
    public void testContainsAll() {
        System.out.println("ContainsAll test #2");
        AvlTree<Integer> tree1 = new AvlTree();
        tree1.add(10);
        tree1.add(20);
        tree1.add(5);
        AvlTree<Integer> tree2 = new AvlTree();
        tree2.add(3);
        tree2.add(4);
        if (!tree1.containsAll(tree2)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }
    @Test
    public void orderTest() {
        System.out.println("Order test #1");
        AvlTree<Integer> tree = new AvlTree();
        for (int i = 0; i < 10; i++) {
            tree.add((int) (Math.random() * 100));
        }
        Iterator it = tree.iterator();
        Object o1 = null, o2 = null;
        if (it.hasNext()) {
            o1 = it.next();
        }
        while (it.hasNext()) {
            o2 = it.next();
            if ((int) o1 >= (int) o2) {
                fail("Test is not passed");
            }
            o1 = o2;
        }
    }
    @Test
    public void testHasNextIterator() {
        System.out.println("test for hasNext()");
        AvlTree<Integer> tree = new AvlTree();
        Iterator it = tree.iterator();
        if (it.hasNext()) {
            fail("Test is not passed");
        } else {
            System.out.println("Test is passed");
        }
    }
    @Test
    public void testClear() {
        System.out.println("Clear test #1");
        AvlTree<Integer> tree = new AvlTree<>();
        tree.add(1);
        tree.add(10);
        tree.add(100);
        tree.clear();
        if (tree.isEmpty()) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }
    @Test
    public void testSize() {
        System.out.println("Size test #1");
        AvlTree<Integer> tree = new AvlTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        if (tree.size() == 3) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }
    @Test
    public void testNextIterator() {
        System.out.println("test for next()");
        AvlTree<Integer> tree = new AvlTree();
        tree.add(1234);
        Iterator it = tree.iterator();
        try {
            it.next();
            System.out.println("Test is passed");
        } catch (Exception e) {
            fail("Test is not passed");
        }
    }

    @Test
    public void testToArray() {
        AvlTree tree = new AvlTree();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        Object[] arr = tree.toArray();
        if (arr instanceof Object[]) {
            System.out.println("Test is passed");
        } else {
            fail("Test is not passed");
        }
    }
}