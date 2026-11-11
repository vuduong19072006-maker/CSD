package warehouse.service;

import warehouse.model.Item;
import warehouse.model.Shipments;
import warehouse.structure.BST;
import warehouse.structure.Node;
import warehouse.structure.ProductLinkedList;
import warehouse.structure.QueueShipments;
import warehouse.structure.TreeNode;

/**
 * Nghiep vu kho: dieu phoi 3 cau truc du lieu va giu chung dong bo.
 * Linked list = luu tru chinh, BST = chi muc tra cuu theo code,
 * Queue = hang doi don giao hang.
 */
public class WarehouseService {

    private final ProductLinkedList list = new ProductLinkedList();
    private final BST bst = new BST();
    private final QueueShipments queue = new QueueShipments();

    // ===== Kho hang =====
    public boolean addItem(Item x) {
        if (x == null || x.getCode() == null || x.getCode().trim().isEmpty()
                || x.getName() == null || x.getName().trim().isEmpty()
                || x.getQuantity() <= 0) {
            System.out.println("Du lieu item khong hop le!");
            return false;
        }

        // 1. Kiem tra code da ton tai chua bang BST: O(log n)
        TreeNode t = bst.search(x.getCode());
        if (t != null) {
            Item old = t.info;
            if (!old.getName().equalsIgnoreCase(x.getName())) {
                System.out.println("Code " + old.getCode()
                        + " da dung cho san pham khac: " + old.getName());
                return false;
            }
            old.setQuantity(old.getQuantity() + x.getQuantity());
            return true;
        }

        // 2. Code moi: them vao cuoi list roi chen vao BST
        Node p = list.append(x);
        bst.insert(p);
        return true;
    }

    public void updateQuantity(String code, int newQty) {
        TreeNode t = bst.search(code);
        if (t == null) {
            System.out.println("Khong tim thay code: " + code);
            return;
        }
        if (newQty <= 0) {
            System.out.println("So luong phai la so duong! (muon bo item thi dung chuc nang xoa)");
            return;
        }
        t.info.setQuantity(newQty);
        System.out.println("Da cap nhat so luong!");
    } // list va BST dung chung Item nen tu dong cap nhat

    public void removeByCode(String code) {
        Node p = bst.delete(code);
        if (p == null) {
            System.out.println("Not found!");
            return;
        }
        list.remove(p);
        System.out.println("Da xoa!");
    } // Xoa dong bo list va BST

    public void displayList() {
        list.display();
    }

    public void displayByCode() {
        bst.display();
    }

    public void lookup(String code) {
        TreeNode t = bst.search(code);
        System.out.println(t == null ? "Khong tim thay code: " + code
                : t.info.toString());
    }

    // ===== Don giao hang =====
    public void createShipment(String code, int qty) {
        TreeNode t = bst.search(code);
        if (t == null) {
            System.out.println("Khong tim thay code: " + code);
            return;
        }
        if (qty <= 0) {
            System.out.println("So luong phai la so duong!");
            return;
        }
        if (qty > t.info.getQuantity()) {
            System.out.println("Khong du hang! Hien co: " + t.info.getQuantity());
            return;
        }
        queue.enqueue(new Shipments(t.info.getCode(), qty));
        System.out.println("Da tao don giao hang!");
    }

    public void displayShipments() {
        queue.display();
    }

    public void displayNextShipment() {
        Shipments s = queue.top();
        System.out.println(s == null ? "Khong co don nao!" : s.toString());
    }

    public void processShipment() {
        Shipments s = queue.top();
        if (s == null) {
            System.out.println("Khong co don nao!");
            return;
        }

        TreeNode t = bst.search(s.getCodeItem());
        if (t == null) {
            System.out.println("Khong tim thay item: " + s.getCodeItem());
            queue.dequeue(); // item da bi xoa, don vo hieu
            return;
        }

        Item item = t.info;
        if (item.getQuantity() < s.getQty()) {
            System.out.println("Khong du hang! Con " + item.getQuantity()
                    + ", don can " + s.getQty());
            return; // giu don trong hang doi
        }

        item.setQuantity(item.getQuantity() - s.getQty()); // tru kho
        queue.dequeue();
        System.out.println("Da giao: " + s);

        if (item.getQuantity() == 0) {
            removeByCode(item.getCode());
        }
    }
}
