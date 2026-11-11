package warehouse.structure;

import warehouse.model.Item;

/** Linked list doi: luu tru chinh cua kho, giu thu tu nhap hang. */
public class ProductLinkedList {

    private Node head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    /** Them item vao cuoi list, tra ve node vua tao de BST tham chieu. */
    public Node append(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            p.prev = tail;
            tail = p;
        }
        return p;
    }

    /** Go node p ra khoi list (O(1) nho con tro prev/next). */
    public void remove(Node p) {
        if (p == null) {
            return;
        }
        if (p.prev != null) {
            p.prev.next = p.next;
        } else {
            head = p.next;
        }
        if (p.next != null) {
            p.next.prev = p.prev;
        } else {
            tail = p.prev;
        }
    }

    /** Duyet tu head den null, in theo thu tu nhap. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Empty!");
            return;
        }
        for (Node p = head; p != null; p = p.next) {
            System.out.println(p.info);
        }
    }
}
