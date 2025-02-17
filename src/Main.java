public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<>(1);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> right = new BinaryTree<>(3);
        BinaryTree<Integer> leftLeft = new BinaryTree<>(4);
        BinaryTree<Integer> leftRight = new BinaryTree<>(5);
        BinaryTree<Integer> rightLeft = new BinaryTree<>(6);
        BinaryTree<Integer> rightRight = new BinaryTree<>(7);

        root.setLeft(left);
        root.setRight(right);
        left.setLeft(leftLeft);
        left.setRight(leftRight);
        right.setLeft(rightLeft);
        right.setRight(rightRight);

        root.printTree();

        System.out.println("In-order:");
        root.inOrder().forEach(tree -> System.out.print(tree.getKey() + " "));
        System.out.println();
        System.out.println("Pre-order:");
        root.preOrder().forEach(tree -> System.out.print(tree.getKey() + " "));
        System.out.println();
        System.out.println("Post-order:");
        root.postOrder().forEach(tree -> System.out.print(tree.getKey() + " "));
        System.out.println();
        System.out.println("DFS");
        root.dfs().forEach(tree -> System.out.print(tree.getKey() + " "));
        System.out.println();
        System.out.println("BFS");
        root.bfs().forEach(tree -> System.out.print(tree.getKey() + " "));
        System.out.println();
        root.printTree();

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Contains 30: " + bst.contains(30));
        System.out.println("Contains 90: " + bst.contains(9));
        bst.printTree();

        AbstractBinarySearchTree<Integer> subtree = bst.search(30);
        System.out.println(subtree.getValue());
        String text = "Apple apple banana BANANA Cherry cherry apple";
        String result = BinarySearchTree.removeDuplicateWords(text);
        System.out.println(result);
    }
}
