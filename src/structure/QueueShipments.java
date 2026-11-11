package warehouse.structure;

import warehouse.model.Shipments;

/** Hang doi FIFO cho cac don giao hang. */
public class QueueShipments {

    private QueueNode head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Shipments s) {
        QueueNode p = new QueueNode(s);
        if (isEmpty()) {
            head = tail = p;
            return;
        }
        tail.next = p;
        tail = p;
    }

    public Shipments dequeue() {
        if (isEmpty()) {
            return null;
        }
        Shipments s = head.info;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return s;
    }

    public Shipments top() {
        if (isEmpty()) {
            return null;
        }
        return head.info;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Empty !");
            return;
        }
        for (QueueNode p = head; p != null; p = p.next) {
            System.out.println(p.info);
        }
    }
}
