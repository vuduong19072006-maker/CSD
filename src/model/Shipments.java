package warehouse.model;

/** Don giao hang: ma san pham va so luong can giao. */
public class Shipments {

    private String codeItem;
    private int qty;

    public Shipments() {
    }

    public Shipments(String codeItem, int qty) {
        this.codeItem = codeItem;
        this.qty = qty;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Code: " + codeItem + "   Soluong: " + qty;
    }
}
