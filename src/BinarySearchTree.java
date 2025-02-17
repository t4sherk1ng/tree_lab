import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    @Override
    public void insert(E element) {
        if (root == null) {
            root = new Node<>(element);
            return;
        }

        Node<E> current = root;

        while (true) {
            if (element.compareTo(current.value) < 0) {
                if (current.leftChild == null) {
                    current.leftChild = new Node<>(element);
                    break;
                } else {
                    current = current.leftChild;
                }
            } else if (element.compareTo(current.value) > 0) {
                if (current.rightChild == null) {
                    current.rightChild = new Node<>(element);
                    break;
                } else {
                    current = current.rightChild;
                }
            } else {
                break;
            }
        }
    }

    @Override
    public boolean contains(E element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<E> root, E element) {
        if (root == null) {
            return false;
        }

        if (element.compareTo(root.value) == 0) {
            return true;
        }

        return element.compareTo(root.value) < 0 ? containsRec(root.leftChild, element) : containsRec(root.rightChild, element);
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> result = searchRec(root, element);
        BinarySearchTree<E> subtree = new BinarySearchTree<>();
        subtree.root = result;
        return subtree;
    }

    private Node<E> searchRec(Node<E> root, E element) {
        if (root == null || element.compareTo(root.value) == 0) {
            return root;
        }

        return element.compareTo(root.value) < 0 ? searchRec(root.leftChild, element) : searchRec(root.rightChild, element);
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getLeft() {
        return root != null ? root.leftChild : null;
    }

    @Override
    public Node<E> getRight() {
        return root != null ? root.rightChild : null;
    }

    @Override
    public E getValue() {
        return root != null ? root.value : null;
    }

    public void printTree() {
        List<List<String>> levels = new ArrayList<>();
        fillLevels(levels, root, 0);
        int maxLevel = levels.size();
        int nodeWidth = 3;

        for (int i = 0; i < maxLevel; i++) {
            int spaces = (int) Math.pow(2, maxLevel - i - 1) - 1;
            int interNodeSpaces = (int) Math.pow(2, maxLevel - i) - 1;
            System.out.print(" ".repeat(spaces * nodeWidth));

            for (String s : levels.get(i)) {
                System.out.print(String.format("%-" + nodeWidth + "s", s));
                System.out.print(" ".repeat(interNodeSpaces * nodeWidth));
            }
            System.out.println();

            if (i < maxLevel - 1) {
                System.out.print(" ".repeat(spaces * nodeWidth));
                for (int j = 0; j < levels.get(i).size(); j++) {
                    if (2 * j < levels.get(i + 1).size() && !levels.get(i + 1).get(2 * j).trim().isEmpty()) {
                        System.out.print("/");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print(" ".repeat(nodeWidth - 1));

                    if (2 * j + 1 < levels.get(i + 1).size() && !levels.get(i + 1).get(2 * j + 1).trim().isEmpty()) {
                        System.out.print("\\");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print(" ".repeat(interNodeSpaces * nodeWidth - 1));
                }
                System.out.println();
            }
        }
    }

    private void fillLevels(List<List<String>> levels, Node<E> node, int depth) {
        if (levels.size() <= depth) {
            levels.add(new ArrayList<>());
        }
        if (node != null) {
            String value = String.format("%-" + 3 + "s", node.value.toString());
            levels.get(depth).add(value);
            fillLevels(levels, node.leftChild, depth + 1);
            fillLevels(levels, node.rightChild, depth + 1);
        } else {
            levels.get(depth).add(" ".repeat(3));
        }
    }

    public static String removeDuplicateWords(String text) {
        String[] words = text.split("\\s+");
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile("[^a-zA-Zа-яА-Я'-]");

        for (String word : words) {
            String cleanedWord = pattern.matcher(word).replaceAll("").toLowerCase();
            if (!cleanedWord.isEmpty() && !bst.contains(cleanedWord)) {
                bst.insert(cleanedWord);
            }
        }

        return String.join(" ", result);
    }
}