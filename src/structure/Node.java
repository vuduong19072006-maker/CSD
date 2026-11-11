package warehouse.structure;

import warehouse.model.Item;

/** Node cua danh sach lien ket doi (kho hang). */
public class Node {

    public Item info;
    public Node next;
    public Node prev;

    public Node(Item info, Node next, Node prev) {
        this.info = info;
        this.next = next;
        this.prev = prev;
    }

    public Node(Item info) {
        this(info, null, null);
    }
}
