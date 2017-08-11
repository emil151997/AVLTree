package source;

/**
 * Created by Ecl1pce on 22.04.2017.
 */

public class Node  <T extends Comparable>{


    public T value;
    public int deep;
    public String name;
    public int key;

    // значение дизбаласа
    public int correction;


    //левыое и правое поддеревья и родитель
    public Node<T> leftChild, rightChild, parent;



    public Node(String name, int key, Node<T> parent) {
        this.name = name;
        this.parent = parent;
        this.key = key;
        if(parent != null) {
            this.deep = parent.deep + 1;
        }
        else {
            this.deep = 0;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "deep=" + deep +
                ", key=" + key +
                '}';
    }
}