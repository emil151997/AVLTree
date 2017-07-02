package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import source.AvlTree;
import source.Node;

import static org.junit.Assert.*;

/**
 * Created by User on 29.06.2017.
 */
public class AvlTreeTestTest {
    AvlTree tree = new AvlTree();
    @Test
    public void add() throws Exception{
        for (int i = 1; i < 11; i++)
        {tree.add(i);}
        if (tree.size()==10){
            System.out.println("Success");
        }
        else {
            System.out.println("Fail");
        }
    }


    @Test
    public void delete() throws Exception {
        for (int i = 1; i < 11; i++)
        {tree.add(i);}
        tree.delete(7);
        if (tree.size()==9){
            System.out.println("Success");
        }
        else {
            System.out.println("Fail");
        }

    }



}