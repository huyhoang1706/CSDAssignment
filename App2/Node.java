package App2;

import shared.TaxPayer;

public class Node {
    private TaxPayer data;
    private Node left, right;

    private int height;
    public Node() {
    }

    public Node(TaxPayer data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    public Node(TaxPayer data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
