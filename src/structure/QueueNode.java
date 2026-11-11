package warehouse.structure;

import warehouse.model.Shipments;

/** Node cua hang doi don giao hang. */
public class QueueNode {

    Shipments info;
    QueueNode next;

    public QueueNode(Shipments info) {
        this.info = info;
    }
}
