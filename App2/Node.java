package App2;

import shared.TaxPayer;

public class Node {
    private TaxPayer data;
    private Node left, right;

    public Node() {
    }

    public Node(TaxPayer data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node(TaxPayer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TaxPayer getData() {
        return data;
    }

    public void setData(TaxPayer data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
