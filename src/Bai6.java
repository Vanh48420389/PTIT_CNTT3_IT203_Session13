import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai6 {

    public static class Medicine {
        private String drugId;
        private String drugName;
        private double unitPrice;
        private int quantity;

        public Medicine(String drugId, String drugName, double unitPrice, int quantity) {
            this.drugId = drugId;
            this.drugName = drugName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getDrugId() { return drugId; }
        public void setDrugId(String drugId) { this.drugId = drugId; }

        public String getDrugName() { return drugName; }
        public void setDrugName(String drugName) { this.drugName = drugName; }

        public double getUnitPrice() { return unitPrice; }
        public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    private static List<Medicine> prescriptionList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n||==========================MENU==========================||");
            System.out.println("||                      1. Thêm thuốc vào đơn             ||");
            System.out.println("||                      2. Điều chỉnh số lượng            ||");
            System.out.println("||                      3. Xóa thuốc                      ||");
            System.out.println("||                      4. In hóa đơn                     ||");
            System.out.println("||                      5. Tìm thuốc giá rẻ               ||");
            System.out.println("||                      6. Thoát                          ||");
            System.out.println("||========================================================||");
            System.out.print("Chọn lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1: addMedicine(); break;
                case 2: adjustQuantity(); break;
                case 3: removeMedicine(); break;
                case 4: printInvoice(); break;
                case 5: findCheapMedicines(); break;
                case 6: System.out.println("Đã thoát chương trình."); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 6);
    }

    private static void addMedicine() {
        System.out.print("Nhập mã thuốc: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên thuốc: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá thuốc: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập số lượng: ");
        int qty = Integer.parseInt(scanner.nextLine());

        Medicine existingMedicine = null;
        for (Medicine m : prescriptionList) {
            if (m.getDrugId().equals(id)) {
                existingMedicine = m;
                break;
            }
        }

        if (existingMedicine != null) {
            existingMedicine.setQuantity(existingMedicine.getQuantity() + qty);
        } else {
            prescriptionList.add(new Medicine(id, name, price, qty));
        }
        System.out.println("Thêm thuốc thành công !");
    }

    private static void adjustQuantity() {
        System.out.print("Nhập mã thuốc: ");
        String id = scanner.nextLine();

        Medicine existingMedicine = null;
        for (Medicine m : prescriptionList) {
            if (m.getDrugId().equals(id)) {
                existingMedicine = m;
                break;
            }
        }

        if (existingMedicine == null) {
            System.out.println("Thuốc không tồn tại trong đơn.");
            return;
        }

        System.out.print("Nhập số lượng mới: ");
        int newQty = Integer.parseInt(scanner.nextLine());

        if (newQty <= 0) {
            prescriptionList.remove(existingMedicine);
        } else {
            existingMedicine.setQuantity(newQty);
        }
        System.out.println("Cập nhật thuốc thành công !");
    }

    private static void removeMedicine() {
        System.out.print("Nhập mã thuốc cần xóa: ");
        String id = scanner.nextLine();

        boolean isRemoved = prescriptionList.removeIf(m -> m.getDrugId().equals(id));

        if (isRemoved) {
            System.out.println("Xóa thuốc thành công !");
        } else {
            System.out.println("Id thuốc không tồn tại !");
        }
    }

    private static void printInvoice() {
        if (prescriptionList.isEmpty()) {
            System.out.println("Đơn thuốc hiện đang trống!");
            return;
        }

        System.out.printf("%-10s %-20s %-15s %-10s\n", "Mã Thuốc", "Tên Thuốc", "Đơn Giá", "Số Lượng");
        double totalAmount = 0;

        for (Medicine m : prescriptionList) {
            System.out.printf("%-10s %-20s %-15.2f %-10d\n",
                    m.getDrugId(), m.getDrugName(), m.getUnitPrice(), m.getQuantity());

            totalAmount += (m.getUnitPrice() * m.getQuantity());
        }

        System.out.printf("Tổng tiền: %.2f VNĐ\n", totalAmount);

        prescriptionList.clear();
    }

    private static void findCheapMedicines() {
        boolean found = false;
        System.out.printf("%-10s %-20s %-15s\n", "Mã Thuốc", "Tên Thuốc", "Đơn Giá");

        for (Medicine m : prescriptionList) {
            if (m.getUnitPrice() < 50000) {
                System.out.printf("%-10s %-20s %-15.2f\n",
                        m.getDrugId(), m.getDrugName(), m.getUnitPrice());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy loại thuốc nào có giá dưới 50.000 VNĐ.");
        }
    }
}