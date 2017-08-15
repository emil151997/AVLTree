package source;

public class Node  <T extends Comparable>{


    public int deep;
    public String name;
    public T key;

    // значение дизбаласа
    public int correction;


    //левыое и правое поддеревья и родитель
    public Node<T> leftChild, rightChild, parent;



    public Node(String name, T key, Node<T> parent) {
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