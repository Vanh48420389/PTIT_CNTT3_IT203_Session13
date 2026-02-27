import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Bai5 {

    private static List<Patient> patientList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static class Patient {
        private String id;
        private String fullName;
        private int age;
        private String diagnosis;

        public Patient(String id, String fullName, int age, String diagnosis) {
            this.id = id;
            this.fullName = fullName;
            this.age = age;
            this.diagnosis = diagnosis;
        }

        public String getId() { return id; }
        public String getFullName() { return fullName; }
        public int getAge() { return age; }
        public String getDiagnosis() { return diagnosis; }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        @Override
        public String toString() {
            return String.format("| ID : %s | FullName : %s | Age : %d | Diagnosis : %s |",
                    id, fullName, age, diagnosis);
        }
    }

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n||==========================MENU==========================||");
            System.out.println("||                      1. Tiếp nhận bệnh nhân            ||");
            System.out.println("||                      2. Cập nhật chẩn đoán             ||");
            System.out.println("||                      3. Xuất viện                      ||");
            System.out.println("||                      4. Sắp xếp danh sách bệnh nhân    ||");
            System.out.println("||                      5. Hiển thị danh sách bệnh nhân   ||");
            System.out.println("||                      6. Thoát                          ||");
            System.out.println("||========================================================||");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    updateDiagnosis();
                    break;
                case 3:
                    dischargePatient();
                    break;
                case 4:
                    sortPatients();
                    break;
                case 5:
                    displayPatients();
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 6);
    }

    private static void addPatient() {
        System.out.print("Nhập ID bệnh nhân: ");
        String id = scanner.nextLine();

        for (Patient p : patientList) {
            if (p.getId().equals(id)) {
                System.out.println("ID bệnh nhân đã tồn tại, vui lòng nhập ID khác");
                return;
            }
        }

        System.out.print("Nhập tên bệnh nhân: ");
        String fullName = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập chẩn đoán: ");
        String diagnosis = scanner.nextLine();

        patientList.add(new Patient(id, fullName, age, diagnosis));
        System.out.println("Bệnh nhân đã được thêm thành công.");
    }

    private static void updateDiagnosis() {
        System.out.print("Nhập ID bệnh nhân để cập nhật chẩn đoán: ");
        String id = scanner.nextLine();

        for (Patient p : patientList) {
            if (p.getId().equals(id)) {
                System.out.print("Nhập chẩn đoán mới: ");
                String newDiagnosis = scanner.nextLine();
                p.setDiagnosis(newDiagnosis);
                System.out.println("Chẩn đoán đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy bệnh nhân với ID đã cho.");
    }

    private static void dischargePatient() {
        System.out.print("Nhập ID bệnh nhân để xuất viện: ");
        String id = scanner.nextLine();

        boolean isRemoved = patientList.removeIf(p -> p.getId().equals(id));

        if (isRemoved) {
            System.out.println("Bệnh nhân đã xuất viện thành công.");
        } else {
            System.out.println("Không tìm thấy bệnh nhân với ID đã cho.");
        }
    }

    private static void sortPatients() {
        Collections.sort(patientList, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                if (p1.getAge() != p2.getAge()) {
                    return p2.getAge() - p1.getAge();
                }
                return p1.getFullName().compareToIgnoreCase(p2.getFullName());
            }
        });
        System.out.println("Danh sách bệnh nhân đã được sắp xếp.");
    }

    private static void displayPatients() {
        System.out.println("====================Danh sách bệnh nhân====================");
        if (patientList.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            for (Patient p : patientList) {
                System.out.println(p.toString());
            }
        }
    }
}