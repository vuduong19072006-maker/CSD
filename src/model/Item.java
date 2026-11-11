package warehouse.model;

/** San pham trong kho: code, name, quantity. */
public class Item {

    private String code;
    private String name;
    private int quantity;

    public Item() {
    }

    public Item(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Code: " + code + "   Name: " + name + "   Quantity: " + quantity;
    }
}
