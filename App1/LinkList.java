package App1;

import shared.FileManage;
import shared.Input;
import shared.TaxPayer;

import java.util.ArrayList;
import java.util.List;

public class LinkList {
    private static class Node {
        TaxPayer infor;
        Node next;

        public Node(TaxPayer infor, Node next) {
            this.infor = infor;
            this.next = next;
        }

        public Node(TaxPayer infor) {
            this(infor, null);
        }

        public TaxPayer getInfor() {
            return infor;
        }

        public void setInfor(TaxPayer infor) {
            this.infor = infor;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private final Input input = new Input();
    private final FileManage fileManage = new FileManage();
    private Node head, tail;
    // Public:
    public LinkList() {
        head = tail = null;
    }
    public void addToEnd() {
        TaxPayer taxPayer = input.getTaxPayer();
        addToEnd(taxPayer);
    }

    public void addToBeginning() {
        TaxPayer taxPayer = input.getTaxPayer();
        Node newNode = new Node(taxPayer);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void displayData() {
        Node current = head;
        while (current != null) {
            System.out.println(current.infor);
            current = current.next;
        }
    }

    public void searchByCode() {
        if (isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        Integer code = input.getInt("Enter code: ", 0, Integer.MAX_VALUE);
        Node result = searchByCode(code);
        if (result != null) {
            System.out.println(result.infor);
            return;
        }
        System.out.println("Not Found");
    }

    public void saveToFile() {
        List<String> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.infor.toString());
            current = current.next;
        }
        fileManage.saveToFile(list);
    }

    public void deleteByCode() {
        if (isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        Integer code = input.getInt("Enter code: ", 0, Integer.MAX_VALUE);
        Node deletedNode = searchByCode(code);
        if (deletedNode != null) {
            delete(deletedNode);
            return;
        }
        System.out.println("Cannot delete code that does not exist");
    }

    public void sortByCode() {
        Node currNode = head;
        while (currNode != null) {
            Node nextNode = currNode.next;
            while (nextNode != null) {
                if (currNode.infor.getCode().compareTo(nextNode.infor.getCode()) > 0) {
                    TaxPayer temp = currNode.infor;
                    currNode.infor = nextNode.infor;
                    nextNode.infor = temp;
                }
                nextNode = nextNode.next;
            }
            currNode = currNode.next;
        }
    }

    public void addAfterPosition() {
        int index = input.getInt("Enter position: ", 1, size()) - 1;
        TaxPayer taxPayer = input.getTaxPayer();
        Node newNode = new Node(taxPayer);
        Node current = head;
        for (int i=0; i < index; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteAt() {
        if (isEmpty()) {
            System.out.println("Empty List");
            return;
        }
        int index = input.getInt("Enter position: ", 1, size()) - 1;
        Node current = head;
        Node prev = null;
        for (int i=0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            head = current.next;
        } else {
            prev.next = current.next;
        }
    }

    public void addToEnd(TaxPayer taxPayer) {
        Node newNode = new Node(taxPayer);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = tail.next;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
    // Private:
    private Node searchByCode(Integer code) {
        Node current = head;
        while(current != null) {
            if (current.getInfor().getCode().equals(code)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void delete(Node node) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.equals(node)) {
                // if the node to delete is the head
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}
