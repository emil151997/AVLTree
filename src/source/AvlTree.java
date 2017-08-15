package source; /**
 * Created by Ecl1pce on 22.04.2017.
 */
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;





public class AvlTree<T extends Comparable<T>> implements Set<T> {
    public class TreeIterator implements Iterator<T> {
        private final Object[] list;
        private int index = 0;

        public TreeIterator() {
            list = new Object[size];
            index = -1;
        }


        @Override
        public boolean hasNext() {
            return index < list.length - 1;
        }

        @Override
        public T next() {
            index++;
            return ((T) list[index]);
        }

        @Override
        public void remove() {

        }
    }















    // Начальный узел
    public int size = 0;
    private Node<Integer> parent;

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }



    @Override
    public boolean contains(Object o) {
        if (parent == null) {
            return false;
        }
        T t = (T) o;
        Node<T> closest = find(t, (Node<T>) parent);
        return closest != null && closest.key.compareTo(t) == 0;
    }

    private Node<T> find(T key, Node<T> node) {
        if (node == null) {
            return null;
        }
        int comparison = key.compareTo(node.key);
        if (comparison == 0) {
            return node;
        } else if (comparison > 0) {
            return find(key, node.rightChild);
        } else {
            return find(key, node.leftChild);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Iterator it = new TreeIterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next();
            i++;
        }
        return array;}

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            T[] array = (Object) a.getClass() == (Object) Object[].class
                    ? (T[]) new Object[size]
                    : (T[]) Array.newInstance(a.getClass().getComponentType(), size);
            Iterator it = new TreeIterator();
            int i = 0;
            while (it.hasNext()) {
                array[i] = (T) it.next();
                i++;
            }
            return array;
        }
        Iterator it = new TreeIterator();
        int i = 0;
        while (it.hasNext()) {
            a[i] = (T) it.next();
            i++;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
            break;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (add((T) it.next())) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        AvlTree newTree = new AvlTree();
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (contains(o)) {
                if (!newTree.add((T) o)) {
                    changed = true;
                }
            } else {
                changed = true;
            }
        }
        parent = newTree.parent;
        size = newTree.size;
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (remove(it.next())) {
                changed = true;
            }
        }
        return changed;
    }
    @Override
    public void clear() {
        parent = null;
        size = 0;
    }

    // Вставка узла
    public boolean add(int key) {
        size++;
        // Если корень пуст, создаем его с введенным ключевым значением
        if (parent == null)
            parent = new Node(null, key, null);
        else {
            Node<Integer> node = parent;
            Node<Integer> parent;
            while (true) {
                if (node.key == key)
                    return false;

                parent = node;

                //Переменная, определяющая, пойдем ли мы налево или нет
                boolean leftSide = parent.key > key;

                //Если мы должны идти налево, присваиваем значение левого ребенка, иначе правого
                if (leftSide)
                    node = parent.leftChild;
                else
                    node = parent.rightChild;


                // если узла нет
                if (node == null) {
                    // В зависимости от того идем ли мы налево, создаем левого реенка или правого
                    if (leftSide) {
                        parent.leftChild = new Node(null, key, parent);
                    } else {
                        parent.rightChild = new Node(null, key, parent);
                    }
                    //балансировка родителя
                    rebalancing(parent);
                    break;
                }
            }
        }
        return true;
    }

    //Метод удаления узла
    public void delete(int deletingKey) {
        //на случай, если нет корня
        size--;
        if (parent == null)
            return;

        Node<Integer> node = parent;
        Node<Integer> parent = this.parent;
        Node<Integer> deletingNode = null;
        Node<Integer> child = this.parent;

        // Если дочерний узел есть
        while (child != null) {
            // идем вниз
            parent = node;
            node = child;
            // если ключ удаляемого узла больше ключа текущего, идем вправо, иначе идем влево
            if (deletingKey >= node.key)
                child = node.rightChild;
            else
                child = node.leftChild;

            // нахождение удаляемого узла
            if (deletingKey == node.key)
                deletingNode = node;
        }

        // присваиваем удаляемому узлу ключ текущего
        if (deletingNode != null) {
            deletingNode.key = node.key;

            // если  у узла есть левый ребенок, идем туда, иначе идем направо
            if (node.leftChild != null)
                child = node.leftChild;
            else
                child = node.rightChild;

            // если удаляем корневой узел
            if (this.parent.key == deletingKey) {
                this.parent = child;
            } else {
                // Если вы хотите, чтобы удалить узел слева от родительского узла связывает дочерний узел узла, чтобы удалить с левой стороны родительского узла.
                if (parent.leftChild == node) {
                    parent.leftChild = child;
                } else {
                    parent.rightChild = child;
                }
                // балансируем родителя
                rebalancing(parent);
            }
        }
    }

    // Функция выравнивания высоты
    private void rebalancing(Node node) {
        setCorrection(node);

        // Если значение равно -2, значит высота слева больше, крутим вправо
        if (node.correction == -2) {
            if (hgt(node.leftChild.leftChild) >= hgt(node.leftChild.rightChild))
                //малый поворот
                node = rightRotate(node);
            else
                //большой поворот
                node = bigRightRotate(node);

        } // Если значение 2, значит правая высота больше, крутим влево
        else if (node.correction == 2) {
            if (hgt(node.rightChild.rightChild) >= hgt(node.rightChild.leftChild))
                //малый поворот
                node = leftRotate(node);
            else
                //большой поворот
                node = bigLeftRotate(node);
        }

        if (node.parent != null) {
            // продолжаем ребалансить наверх по дереву
            rebalancing(node.parent);
        } else {
            parent = node;
        }
    }

    // Поворот влево (малый) по алгоритму
    private Node leftRotate(Node alpha) {
        Node beta = alpha.rightChild;

        beta.parent = alpha.parent;
        alpha.rightChild = beta.leftChild;

        if (alpha.rightChild != null)
            alpha.rightChild.parent = alpha;

        beta.leftChild = alpha;

        alpha.parent = beta;

        if (beta.parent != null) {
            if (beta.parent.rightChild == alpha) {
                beta.parent.rightChild = beta;
            } else {
                beta.parent.leftChild = beta;
            }
        }
        setCorrection(alpha, beta);
        return beta;
    }

    // Поворот вправо (малый)
    private Node rightRotate(Node a) {
        Node b = a.leftChild;
        b.parent = a.parent;
        a.leftChild = b.rightChild;
        if (a.leftChild != null)
            a.leftChild.parent = a;

        b.rightChild = a;
        a.parent = b;
        if (b.parent != null) {
            if (b.parent.rightChild == a) {
                b.parent.rightChild = b;
            } else {
                b.parent.leftChild = b;
            }
        }
        setCorrection(a, b);
        return b;
    }
    // большой поворот правый
    private Node bigRightRotate(Node node) {
        node.leftChild = leftRotate(node.leftChild);
        return rightRotate(node);
    }
    // большой поворот левый
    private Node bigLeftRotate(Node node) {
        node.rightChild = rightRotate(node.rightChild);
        return leftRotate(node);
    }

    //  метод расчета высоты
    private int hgt(Node node) {
        if (node == null) // условие завершения
            return -1;
        return 1 + Math.max(hgt(node.leftChild), hgt(node.rightChild));
    }

    // оределение дизбаланса сторон
    private void setCorrection(Node... nodes) {
        // По мере увеличения количества входящего узла вычисляет высоту узла.
        for (Node node : nodes)
            node.correction = hgt(node.rightChild) - hgt(node.leftChild);
    }

    // вычисление глубины
    public void calculateDeep() {
        parent.deep = 0;
        calculateDeep(parent.leftChild);
        calculateDeep(parent.rightChild);
    }

    private void calculateDeep(Node node) {
        if(node != null){
            node.deep = node.parent.deep + 1;
            calculateDeep(node.leftChild);
            calculateDeep(node.rightChild);
        }
    }



    // вывод дерева
    public void showTree(int num){
        showTree(parent, num);

    }

    private void showTree(Node node, int num){
        if(node != null){
            if(node.deep == num){
                System.out.print(node.key + " ");
            }
            showTree(node.leftChild, num);
            showTree(node.rightChild, num);
        }
    }


    public static void main(String[] args) {
        AvlTree tree = new AvlTree();


        System.out.println("Вставляем значения от 1 до 25" );
        for (int i = 1; i < 26; i++)
            tree.add(i);

        System.out.println();
        tree.calculateDeep();
        for(int i = 0;i < 6; i++){
            tree.showTree(i);
            System.out.println();
        }

        System.out.println("Удалим пару значений: 8 и 3");
        tree.delete(8);
        tree.delete(3);
        tree.calculateDeep();
        System.out.println("Вывод: ");
        for(int i = 0;i<6;i++){
            tree.showTree(i);
            System.out.println();
        }
    }


}