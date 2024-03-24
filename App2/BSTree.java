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

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert() {
        TaxPayer taxPayer = input.getTaxPayer();
        if (contain(taxPayer.getCode())) {
            System.out.println("This code is already exist");
            return;
        }
        insert(taxPayer);
    }

    public void insert(TaxPayer taxPayer) {
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
        Node result = search(root, searchCode);
        if (result != null) {
            System.out.println(result);
        }
    }

    public void deleteCode() {
        Integer deleteCode = input.getInt("Enter Delete Code: ", 0, Integer.MAX_VALUE);
        if (!contain(deleteCode)) {
            System.out.println("There is no node have this code!");
            return;
        }
        delete(root, deleteCode);
        count--;
    }

    public void inOrderTraverseToFile() {
        List<String> taxPayerList = new ArrayList<>();
        inOrderToFile(root, taxPayerList);
        fileManage.saveToFile(taxPayerList);
    }

    public void balance(){
        List <Node> list = new ArrayList<>();
        inOrderToList(list,root);
        int n = list.size();
        BSTree tempTree = new BSTree();
        tempTree.balance(list, 0, n-1);
        root = tempTree.getRoot();
        breadthFirstTraverse();
    }

    private void inOrderToList(List<Node> list, Node node) {
        if(node==null) return;
        inOrderToList(list,node.getLeft());
        list.add(node);
        inOrderToList(list,node.getRight());
    }
    private void balance(List<Node> list, int lower, int upper) {
        if(lower > upper) return;
        int mid=(lower+upper)/2;
        TaxPayer data = list.get(mid).getData();
        insert(data);
        balance(list, lower,mid-1);
        balance(list, mid+1, upper);
    }

    public void count() {
        System.out.println(count);
    }

    // Private:
    private Node insert(Node node, TaxPayer data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data.getCode().compareTo(node.getData().getCode()) < 0) {
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
            System.out.println(tempNode.getData());

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

    // Delete by Copying
    private Node delete(Node node, Integer deleteCode) {
        if (node == null) return node;

        if (deleteCode.compareTo(node.getData().getCode()) < 0) {
            node.setLeft(delete(node.getLeft(), deleteCode));
            return node;
        } else if (deleteCode.compareTo(node.getData().getCode()) > 0) {
            node.setRight(delete(node.getRight(), deleteCode));
            return node;
        }
        // If one of the children is empty
        if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        }
        // If both children exist
        else {
            Node successorParent = node;
            Node successor = node.getRight();
            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }
            if (successorParent != node) {
                successorParent.setLeft(successor.getRight());
            } else {
                successorParent.setRight(successor.getRight());
            }
            node.setData(successor.getData());
            return node;
        }

    }

    private void inOrderToFile(Node node, List<String> stringList) {
        if (node == null) return;
        inOrderToFile(node.getLeft(), stringList);
        stringList.add(node.toString());
        inOrderToFile(node.getRight(), stringList);
    }
}

