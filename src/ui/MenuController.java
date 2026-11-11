package warehouse.ui;

import java.util.Scanner;
import warehouse.model.Item;
import warehouse.service.WarehouseService;

/** Dieu huong chuong trinh: hien menu, doc input, goi service tuong ung. */
public class MenuController {

    private final Scanner sc = new Scanner(System.in);
    private final WarehouseService service = new WarehouseService();

    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt("Chon hanh dong: ");
            handle(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("==========MENU==========");
        System.out.println("1.Them item");
        System.out.println("2.Thay doi so luong san pham");
        System.out.println("3.Hien thi list item (LinkedList)");
        System.out.println("4.Xoa theo code");
        System.out.println("5.Tao don giao hang");
        System.out.println("6.Hien thi don giao hang");
        System.out.println("7.Xem don ke tiep");
        System.out.println("8.Xu li don giao hang");
        System.out.println("9.Tim item theo code (BST)");
        System.out.println("10.Hien thi item theo thu tu code");
        System.out.println("0.Thoat");
    }

    private void handle(int choice) {
        switch (choice) {
            case 1:
                addItem();
                break;
            case 2:
                updateQuantity();
                break;
            case 3:
                service.displayList();
                break;
            case 4:
                service.removeByCode(readLine("Nhap code can xoa: "));
                break;
            case 5:
                createShipment();
                break;
            case 6:
                service.displayShipments();
                break;
            case 7:
                service.displayNextShipment();
                break;
            case 8:
                service.processShipment();
                break;
            case 9:
                service.lookup(readLine("Nhap code can tim: "));
                break;
            case 10:
                service.displayByCode();
                break;
            case 0:
                System.out.println("Tam biet");
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    // ===== Cac thao tac can nhieu input =====
    private void addItem() {
        String code = readLine("Nhap code: ");
        String name = readLine("Nhap ten: ");
        int quantity = readInt("Nhap so luong: ");
        if (service.addItem(new Item(code, name, quantity))) {
            System.out.println("Da them !");
        }
    }

    private void updateQuantity() {
        String code = readLine("Nhap code can sua: ");
        int qty = readInt("Nhap so luong moi: ");
        service.updateQuantity(code, qty);
    }

    private void createShipment() {
        String code = readLine("Nhap code: ");
        int qty = readInt("Nhap so luong: ");
        service.createShipment(code, qty);
    }

    // ===== Doc input =====
    private String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }
}
