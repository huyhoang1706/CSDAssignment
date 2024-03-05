package App1;

import shared.Input;
import shared.TaxPayer;

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
    private Node head, tail;
    // Public:
    public void addToEnd() {
        TaxPayer taxPayer = input.getTaxPayer();
    }

    public void addToBeginning() {
        TaxPayer taxPayer = input.getTaxPayer();
    }

    public void searchByCode() {}

    public void deleteByCode() {}

    public void sortByCode() {}

    public void addAfterPosition() {}

    public void deleteAt() {}

    public void addToEnd(TaxPayer taxPayer) {}

    public boolean isEmpty() {
        return head == null && tail == null;
    }
    // Private:
}
