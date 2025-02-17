import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();

        if (left != null || right != null) {
        }

        if (left != null) {
            sb.append(left.asIndentedPreOrder(indent + 1));
        }

        if (right != null) {
            sb.append(right.asIndentedPreOrder(indent + 1));
        }

        return sb.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null) {
            result.addAll(left.preOrder());
        }
        if (right != null) {
            result.addAll(right.preOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.inOrder());
        }
        result.add(this);
        if (right != null) {
            result.addAll(right.inOrder());
        }
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) {
            result.addAll(left.postOrder());
        }
        if (right != null) {
            result.addAll(right.postOrder());
        }
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (left != null) {
            left.forEachInOrder(consumer);
        }
        consumer.accept(key);
        if (right != null) {
            right.forEachInOrder(consumer);
        }
    }

    public void printInOrder() {
        forEachInOrder(element ->System.out.println(element));
    }


    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    @Override
    public List<AbstractBinaryTree<E>> dfs() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        Stack<BinaryTree<E>> stack = new Stack<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            BinaryTree<E> current = stack.pop();
            result.add(current);

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> bfs() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        Queue<BinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            BinaryTree<E> current = queue.poll();
            result.add(current);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    public void printTree() {
        List<List<String>> levels = new ArrayList<>();
        fillLevels(levels, 0);
        int maxLevel = levels.size();
        for (int i = 0; i < maxLevel; i++) {
            int spaces = (int) Math.pow(2, maxLevel - i) - 1;
            System.out.print(" ".repeat(spaces));
            for (String s : levels.get(i)) {
                System.out.print(s + " ".repeat(spaces * 2 + 1));
            }
            System.out.println();
        }
    }

    private void fillLevels(List<List<String>> levels, int depth) {
        if (levels.size() <= depth) {
            levels.add(new ArrayList<>());
        }
        if (key != null) {
            levels.get(depth).add(key.toString());
        } else {
            levels.get(depth).add(" ");
        }
        if (left != null) {
            left.fillLevels(levels, depth + 1);
        } else {
            if (depth + 1 < levels.size()) {
                levels.get(depth + 1).add(" ");
            }
        }
        if (right != null) {
            right.fillLevels(levels, depth + 1);
        } else {
            if (depth + 1 < levels.size()) {
                levels.get(depth + 1).add(" ");
            }
        }
    }


}