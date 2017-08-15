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
        if (tree.size() == 10) {
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