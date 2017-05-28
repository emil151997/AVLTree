/**
 * Created by Ecl1pce on 22.04.2017.
 */

public class AvlTree {

    // Начальный узел
    private static Node<Integer> root;

    // Вставка узла
    public boolean add(int key) {

        // Если корень пуст, создаем его с введенным ключевым значением
        if (root == null)
            root = new Node(null, key, null);
        else {
            Node<Integer> node = root;
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
        if (root == null)
            return;

        Node<Integer> node = root;
        Node<Integer> parent = root;
        Node<Integer> deletingNode = null;
        Node<Integer> child = root;

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
            if (root.key == deletingKey) {
                root = child;
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
            root = node;
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
        root.deep = 0;
        calculateDeep(root.leftChild);
        calculateDeep(root.rightChild);
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
        showTree(root, num);
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

//демонстрация работы дерева
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        System.out.println("Вставляем значения от 1 до 25");
        for (int i = 1; i < 26; i++)
            tree.add(i);

        System.out.println("Вывод дерева: ");
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