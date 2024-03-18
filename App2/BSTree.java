package App2;

import shared.FileManage;
import shared.Input;
import shared.TaxPayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTree {
    private final FileManage fileManage = new FileManage();
    private final Input input = new Input();
    private Node root;
    private int count = 0;
    // Public:
    public BSTree() {
        this.root = null;
    }

    public BSTree(TaxPayer data) {
        this.root = new Node(data);
    }

    public Node getRoot() {
        return root;
    }

    public void insert() {
        TaxPayer taxPayer = input.getTaxPayer();
        insert(taxPayer);
    }

    public void insert(TaxPayer taxPayer) {
        if (contain(taxPayer.getCode())) return;
        root = insert(root, taxPayer);
        count++;
    }

    public void inOrderTraverse() {
        inOrder(root);
    }

    public void preOrderTraverse() {
        preOrder(root);
    }

    public void breadthFirstTraverse() {
        breadthFirst(root);
    }

    public void searchByCode() {
        Integer searchCode = input.getInt("Enter Search Code: ", 0, Integer.MAX_VALUE);
        search(root, searchCode);
    }

    public void deleteCode() {
        Integer deleteCode = input.getInt("Enter Delete Code: ", 0, Integer.MAX_VALUE);
        delete(root, deleteCode);
    }

    public void balance() {
        //Solution: traverse nodes in Inorder and one by one insert into a self-balancing BST
        root = balanceBST(root);
        preOrderTraverse();
    }

    public void inOrderTraverseToFile() {
        List<String> taxPayerList = new ArrayList<>();
        inOrderToFile(root, taxPayerList);
        fileManage.saveToFile(taxPayerList);
    }

    public void count() {
        System.out.println(count);
    }

    // Private:
    private Node insert(Node node, TaxPayer data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data.getCode().compareTo(node.getData().getCode()) > 0) {
                node.setLeft(insert(node.getLeft(), data));
            } else {
                node.setRight(insert(node.getRight(), data));
            }
        }
        return node;
    }

    private boolean contain(Node node, Integer code) {
        if (node == null || node.getData() == null) return false;
        int result = code.compareTo(node.getData().getCode());
        if (result < 0) {
            return contain(node.getLeft(), code);
        } else if (result > 0) {
            return contain(node.getRight(), code);
        } else {
            return true;
        }
    }

    private boolean contain(Integer code) {
        return contain(root, code);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node.getData() + " ");
        inOrder(node.getRight());
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.getData() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    private void breadthFirst(Node node) {
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.println(node.getData() + " ");

            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }

            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
    }

    private Node search(Node node, Integer searchCode) {
        if (node == null || node.getData().getCode().equals(searchCode)) {
            return node;
        }

        if (node.getData().getCode().compareTo(searchCode) < 0) {
            return search(node.getRight(), searchCode);
        }

        return search(node.getLeft(), searchCode);
    }

    private Node delete(Node node, Integer deleteCode) {
        if (node == null) return node;

        if (deleteCode.compareTo(node.getData().getCode()) < 0) {
            node.setLeft(delete(node.getLeft(), deleteCode));
        } else if (deleteCode.compareTo(node.getData().getCode()) > 0) {
            node.setRight(delete(node.getRight(), deleteCode));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setData(minValue(node.getRight()));
            node.setRight(delete(node.getRight(), node.getData().getCode()));
        }
        return node;
    }

    // Utility method to find the minimum value in a subtree
    private TaxPayer minValue(Node node) {
        TaxPayer minValue = node.getData();
        while (node.getLeft() != null) {
            minValue = node.getLeft().getData();
            node = node.getLeft();
        }
        return minValue;
    }

    private void storeNode(Node node, List<Node> nodes) {
        if (node == null) return;
        storeNode(node.getLeft(), nodes);
        nodes.add(node);
        storeNode(node.getRight(), nodes);
    }

    private Node constructSelfBalanceBST(List<Node> nodes, int start, int end) {
        if (start < end) return null;

        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        node.setLeft(constructSelfBalanceBST(nodes, start, mid-1));
        node.setRight(constructSelfBalanceBST(nodes, mid+1, start));

        return node;
    }

    private Node balanceBST(Node node) {
        List<Node> nodes = new ArrayList<>();
        storeNode(node, nodes);
        int size = nodes.size();
        return constructSelfBalanceBST(nodes, 0, size-1);
    }

    private void inOrderToFile(Node node, List<String> stringList) {
        if (node == null) return;
        inOrderToFile(node.getLeft(), stringList);
        stringList.add(node.toString());
        inOrderToFile(node.getRight(), stringList);
    }
}

