package App2;

import shared.TaxPayer;

import java.util.LinkedList;

public class AVLTree extends BSTree {

    private Node root;

    boolean shorter = true;
    public AVLTree() {
    }

    public AVLTree(TaxPayer data) {
        super(data);
    }

    boolean isEmpty() {
        return (root == null);
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

    private Node balance_insert(Node node, TaxPayer taxPayer)
    {

        /* 2. Update height of this ancestor node */
        node.setHeight(1 + max(height(node.getLeft()),
                height(node.getRight())));

        /* 3. Get the balance factor of this ancestor
        node to check whether this node became
        unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then
        // there are 4 cases Left Left Case
        if (balance > 1 && taxPayer.getCode().compareTo(node.getLeft().getData().getCode()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && taxPayer.getCode().compareTo(node.getRight().getData().getCode()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && taxPayer.getCode().compareTo(node.getLeft().getData().getCode()) > 0)
        {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && taxPayer.getCode().compareTo(node.getRight().getData().getCode()) < 0)
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    Node minValueNode(Node node)
    {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
            current = current.getLeft();

        return current;
    }

    private Node balance_deleteNode(Node root)
    {

        // UPDATE HEIGHT OF THE CURRENT NODE
        root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);

        // GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    public void insert(TaxPayer taxPayer) {
        super.insert(taxPayer);
        root = balance_insert(root, taxPayer);

    }

    public void deleteCode() {
        super.deleteCode();

        root = balance_deleteNode(root);
    }

}
