package App1;

import App2.Node;
import shared.FileManage;
import shared.Input;
import shared.TaxPayer;

import java.util.LinkedList;

public class LinkList <E extends Comparable<E>>{
    public static class Node<E> {
        TaxPayer infor;
        public Node next;

        public Node(TaxPayer infor, Node next) {
            this.infor = infor;
            this.next = next;
        }
        public Node() {
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


    private final FileManage fileManage = new FileManage();
    private final Input input = new Input();


    public Node<E> head;
    Node<E> tail;
    Node<E> sorted;

    public void linkList() {
        head = tail = null;
    }

    // check linked is empty or not
    public boolean isEmptyBoolean() {
        return (head == null);
    }

    // clear linked list
    public void clear() {
        head = tail = null;
    }
    // Public:
    public void addToEnd() {
        TaxPayer taxPayer = input.getTaxPayer();
        Node q = new Node(taxPayer);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }
//    private boolean contain(Integer code) {
//        Node currNode = head;
//        while (currNode != null) {
//            if (currNode.getInfor().getCode().equals(code)) {
//                return true; // Code found in the linked list
//            }
//            currNode = currNode.getNext();
//        }
//        return false; // Code not found in the linked list
//    }

    public void addToBeginning() {
        TaxPayer x = input.getTaxPayer();
        //Node x = new Node(taxPayer);
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
        }
    }

    public Node searchByCode0() {
        String code = input.getString("Enter code: ");
        if (code == null || code.isEmpty()) {
            // Handle invalid input
            System.out.println("Invalid code input.");
            return null;
        }

        Node<E> currNode = head;
        // Traverse the linked list to find the node with the given code
        while (currNode != null) {
            if (currNode.getInfor().getCode().equals(code)) {
                // Return the node if the code matches
                return currNode;
            }
            currNode = currNode.getNext();
        }
        System.out.println("Code doesn't exist");
        // If code is not found, you may choose to return null or throw an exception
        // In this example, returning null if code is not found
        return null;
    }

    public void searchByCode(){
        Node res = searchByCode0();
        if(res != null){
            TaxPayer taxPayer = res.getInfor();
            System.out.println(taxPayer.toString());
        }
    }
   // Integer code, String name, double income, double deduct, double tax
//    public  void searchByPCode(LinkedList<TaxPayer> mp){
//
//        int pCode = input.getInt("Enter code: ", 1, Integer.MAX_VALUE);
//
//        TaxPayer p;  //Define a Product
//        if((p = mp.search(new TaxPayer(pCode,"", 0,0,0))) == null){
//            System.err.println("Not found!");
//            return;
//        }
//        System.out.println(p.toString());
//    }
    
    public void deleteByCode() {
        String code = input.getString("Enter Code: ");
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }

        Node<E> currNode = head;
        Node<E> prevNode = null;

        while (currNode != null) {
            if (currNode.getInfor().getCode().equals(code)) {
                if (prevNode == null) {
                    // If the node to delete is the head
                    head = currNode.getNext();
                } else {
                    prevNode.setNext(currNode.getNext());
                }
                return; // Node found and deleted
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        // Node with the given code not found
        System.out.println("Node with code " + code + " not found in the list.");
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
    int size() {
        int size = 0;
        Node p = head;
        while (p.next != null) {
            size++;
            p = p.next;
        }
        return size;
    }
    public void addAfterPosition() {
        int k = input.getInt("Enter position: ", Integer.MIN_VALUE, size());
        TaxPayer taxPayer = input.getTaxPayer();
        Node newNode = new Node(taxPayer);
        Node currNode = head;
        for (int i = 1; i < k; i++) {
            if (currNode == null) {
                System.out.println("Position not found");
                return;
            }
            currNode = currNode.next;
        }
        newNode.next = currNode.next;
        currNode.next = newNode;
    }

    public void deleteAt() {
        int k = input.getInt("Enter position: ", Integer.MIN_VALUE, size());
        Node currNode = head;
        Node prevNode = null;
        for (int i = 1; i < k; i++) {
            if (currNode == null) {
                System.out.println("Position not found");
                return;
            }
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (prevNode == null) {
            head = currNode.next;
        } else {
            prevNode.next = currNode.next;
        }
    }

    public void addToEnd(TaxPayer taxPayer) {}

    public boolean isEmpty() {
        return head == null && tail == null;
    }

        public static void displayData(LinkList<TaxPayer> list) {
            Node currNode = list.head;
            while (currNode != null) {
                TaxPayer taxPayer = currNode.getInfor();
                System.out.println(taxPayer.toString()); // Assuming TaxPayer has a meaningful toString() implementation
                currNode = currNode.getNext();
            }
        }


}
