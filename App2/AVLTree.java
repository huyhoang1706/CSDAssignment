package App2;

import shared.TaxPayer;

import java.util.ArrayList;
import java.util.List;

public class AVLTree extends BSTree {

    private Node root;
    public AVLTree() {
        root = null;
    }

    public AVLTree(TaxPayer data) {
        super(data);
    }

    // A utility function to get height of the tree
    int height(Node N)
    {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    // A utility function to get maximum of two integers
    int max(int a, int b)
    {
        return Math.max(a, b);
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y)
    {
        if (y == null || y.getLeft() == null) {
            return y;
        }
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x)
    {
        if (x == null || x.getRight() == null) {
            return x;
        }
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // Update heights
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N)
    {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    private Node insert(Node node, TaxPayer taxPayer)
    {
        /* 1. Perform the normal BST rotation */
        if (node == null)
            return (new Node(taxPayer));

        if (taxPayer.getCode().compareTo(node.getData().getCode()) < 0) {
            node.setLeft(insert(node.getLeft(), taxPayer));
        }
        else if (taxPayer.getCode().compareTo(node.getData().getCode()) > 0)
            node.setRight(insert(node.getRight(), taxPayer));
        else // Equal keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setHeight(1 + max(height(node.getLeft()),
                height(node.getRight())));

        /* 3. Get the balance factor of this ancestor
        node to check whether this node became
        unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then
        // there are 4 cases:
        // Left-Left Case
        if (balance > 1 && taxPayer.getCode().compareTo(node.getLeft().getData().getCode()) < 0)
            return rightRotate(node);

        // Right-Right Case
        if (balance < -1 && taxPayer.getCode().compareTo(node.getRight().getData().getCode()) > 0)
            return leftRotate(node);

        // Left-Right Case
        if (balance > 1 && taxPayer.getCode().compareTo(node.getLeft().getData().getCode()) > 0)
        {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right-Left Case
        if (balance < -1 && taxPayer.getCode().compareTo(node.getRight().getData().getCode()) < 0)
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public void insert() {
        super.insert();

        root = super.getRoot();
        List<TaxPayer> taxPayerList = new ArrayList<>();
        storeNodes(super.getRoot(), taxPayerList);

        for (TaxPayer taxPayer : taxPayerList) {
            root = insert(root, taxPayer);
        }
        super.setRoot(root);
    }


    public void deleteCode() {
        super.deleteCode();

        List<TaxPayer> taxPayerList = new ArrayList<>();
        storeNodes(super.getRoot(), taxPayerList);

        for (TaxPayer taxPayer : taxPayerList) {
            root = insert(root, taxPayer);
        }

        super.setRoot(root);
    }

    private void storeNodes(Node node, List<TaxPayer> taxPayerList) {
        if (node == null) {
            return;
        }

        storeNodes(node.getLeft(), taxPayerList);
        taxPayerList.add(node.getData());
        storeNodes(node.getRight(), taxPayerList);
    }

}

