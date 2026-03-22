package Bai6;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AppointmentRepository repo = new AppointmentRepository();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n========= QUAN LY LICH KHAM =========");
            System.out.println("1. Xem danh sach lich kham");
            System.out.println("2. Them lich kham moi");
            System.out.println("3. Cap nhat trang thai lich kham");
            System.out.println("4. Xoa lich kham");
            System.out.println("5. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        showList();
                        break;
                    case 2:
                        addNew();
                        break;
                    case 3:
                        updateStatus();
                        break;
                    case 4:
                        deleteApp();
                        break;
                    case 5:
                        System.out.println("Tam biet!");
                        break;
                    default:
                        System.out.println("Vui long chon tu 1-5.");
                }
            } catch (Exception e) {
                System.err.println("Loi: Vui long nhap dung dinh dang!");
            }
        }
    }

    // Phuong thuc hien thi danh sach
    private static void showList() {
        System.out.println("\n--- DANH SACH LICH KHAM HIEN TAI ---");
        List<Appointment> list = repo.getAllAppointments();
        if (list.isEmpty()) {
            System.out.println("Empty: Chua co lich kham nao.");
        } else {
            System.out.printf("%-5s | %-20s | %-15s | %-20s | %-15s\n",
                    "ID", "Benh Nhan", "Ngay Kham", "Bac Si", "Trang Thai");
            System.out.println("-------------------------------------------------------------------------------");
            for (Appointment a : list) {
                System.out.printf("%-5d | %-20s | %-15s | %-20s | %-15s\n",
                        a.getId(), a.getPatientName(), a.getAppointmentDate(),
                        a.getDoctorName(), a.getStatus());
            }
        }
    }

    // Phuong thuc them moi
    private static void addNew() {
        System.out.print("Nhap ten benh nhan: "); String name = sc.nextLine();
        System.out.print("Nhap ngay (yyyy-mm-dd): "); String dateStr = sc.nextLine();
        System.out.print("Nhap ten bac si: "); String doc = sc.nextLine();

        Appointment app = new Appointment(0, name, Date.valueOf(dateStr), doc, "Cho kham");
        repo.addAppointment(app);
    }

    // Phuong thuc cap nhat
    private static void updateStatus() {
        System.out.print("Nhap ID lich kham can sua: "); int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap trang thai moi: "); String status = sc.nextLine();

        // Lay thong tin cu de cap nhat
        // Luu y: Trong thuc te nen viet ham getById, o day minh tam thoi dung list
        List<Appointment> list = repo.getAllAppointments();
        for(Appointment a : list) {
            if(a.getId() == id) {
                a.setStatus(status);
                repo.updateAppointment(a);
                return;
            }
        }
        System.out.println("Khong tim thay ID nay.");
    }

    // Phuong thuc xoa
    private static void deleteApp() {
        System.out.print("Nhap ID lich kham can xoa: "); int id = Integer.parseInt(sc.nextLine());
        repo.deleteAppointment(id);
    }
}