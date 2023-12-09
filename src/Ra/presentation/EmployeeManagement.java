package Ra.presentation;

import Ra.BusinessImp.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagement {
    //Khoi tao mang
    private static final Employee[] arrEmployee = new Employee[100];
    private static int indexEmployee = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Khoi tao doi tuong
        EmployeeManagement emp = new EmployeeManagement();
        //Hien thi menu
        do {
            System.out.println("************ MENU ************");
            System.out.println("1. Nhập thông tin cho n nhân viên");
            System.out.println("2. Hiển thị thông tin nhân viên");
            System.out.println("3. Tính lương cho các nhân viên");
            System.out.println("4. Tìm kiếm nhân viên theo tên nhân viên");
            System.out.println("5. Cập nhật thông tin nhân viên");
            System.out.println("6. Xóa nhân viên theo mã nhân viên");
            System.out.println("7. Sắp xếp nhân viên theo lương tăng dần");
            System.out.println("8. Sắp xếp nhân viên theo tên nhân viên giảm dần");
            System.out.println("9. Sắp xếp nhân viên theo năm sinh tăng dần");
            System.out.println("10. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    emp.inputEmployee(scanner);
                    break;
                case 2:
                    emp.displayEmployee();
                    break;
                case 3:
                    emp.calSalary();
                    break;
                case 4:
                    emp.findEmpByName(scanner);
                    break;
                case 5:
                    emp.updateEmployee(scanner);
                    break;
                case 6:
                    emp.deleteEmployee(scanner);
                    break;
                case 7:
                    Arrays.sort(arrEmployee, 0, indexEmployee);
                    System.out.println("Đã sắp xếp nhân viên tăng dần theo lương");
                    break;
                case 8:
                    Arrays.sort(arrEmployee, 0, indexEmployee, Comparator.comparing(Employee::getName).reversed());
                    System.out.println("Đã sắp xếp nhân viên giảm dần theo tên nhân viên");
                    break;
                case 9:
                    Arrays.sort(arrEmployee, 0, indexEmployee, Comparator.comparingInt(Employee::getYear));
                    System.out.println("Đã sắp xếp nhân viên tăng dần theo năm sinh");
                    break;
                case 10:
                    System.out.println("Chương trình kết thúc, Xin tạm biệt!");
                    System.exit(0);
                default:
                    System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
            }
        } while (true);
    }

    public void inputEmployee(Scanner scanner) {
        System.out.println("Nhập vào số nhân viên cần nhập thông tin: ");
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < number; i++) {
            arrEmployee[indexEmployee] = new Employee();
            arrEmployee[indexEmployee].inputData(scanner);
            indexEmployee++;
        }
    }

    public void displayEmployee() {
        System.out.println("Thông tin nhân viên: ");
        for (int i = 0; i < indexEmployee; i++) {
            arrEmployee[i].displayData();
        }
    }

    public void calSalary() {
        System.out.println("Lương nhân viên: ");
        for (int i = 0; i < indexEmployee; i++) {
            arrEmployee[i].calSalary();
            System.out.printf("%s - %.2f\n", arrEmployee[i].getId(), arrEmployee[i].getSalary());
        }
    }

    public void findEmpByName(Scanner scanner) {
        System.out.print("Nhập vào tên nhân viên bạn muốn tìm theo tên: ");
        String nameEmpFind = scanner.nextLine();
        System.out.println("Nhân viên có tên bạn tìm:");
        boolean checkFindForEmployee = false;
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getName().equalsIgnoreCase(nameEmpFind)) {
                checkFindForEmployee = true;
                arrEmployee[i].displayData();
            }
        }
        if (!checkFindForEmployee) {
            System.out.print("Không có nhân viên có tên bạn cần tìm!");
        }
    }

    public void updateEmployee(Scanner scanner) {
        System.out.println("Nhập mã nhân viên cần cập nhật:");
        String empId = scanner.nextLine();
        boolean checkUpdateEmp = false;
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getId().equalsIgnoreCase(empId)) {
                checkUpdateEmp = true;
                arrEmployee[i].inputData(scanner);
            }
        }
        if (!checkUpdateEmp) {
            System.out.print("Không có nhân viên có mã cần cập nhật!");
        }
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên cần xóa:");
        String empId = scanner.nextLine();
        int indexDelete = getIndexById(empId);
        if (indexDelete >= 0) {
            for (int i = indexDelete; i < indexEmployee; i++) {
                arrEmployee[i] = arrEmployee[i + 1];
            }
            arrEmployee[indexEmployee - 1] = null;
            indexEmployee--;
        } else {
            System.err.println("Mã nhân viên không tồn tại!");
        }
    }

    public int getIndexById(String id) {
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
