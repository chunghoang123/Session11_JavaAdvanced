package Bai5;


import java.sql.SQLException;
import java.util.Scanner;

public class RikkeiCareApp {
    public static void main(String[] args) {
        DoctorDAO dao = new DoctorDAO();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== HE THONG RIKKEI-CARE ===");
            System.out.println("1. Xem danh sach bac si");
            System.out.println("2. Them bac si moi");
            System.out.println("3. Thong ke chuyen khoa");
            System.out.println("4. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        for (Doctor d : dao.getAll()) {
                            System.out.println(d.getId() + " | " + d.getName() + " | " + d.getSpecialty());
                        }
                        break;
                    case 2:
                        System.out.print("Nhap Ma BS: "); String id = sc.nextLine();
                        System.out.print("Nhap Ho Ten: "); String name = sc.nextLine();
                        System.out.print("Nhap Chuyen Khoa: "); String spec = sc.nextLine();
                        int res = dao.addDoctor(new Doctor(id, name, spec));
                        if (res > 0) System.out.println("Them thanh cong!");
                        break;
                    case 3:
                        dao.reportBySpecialty();
                        break;
                    case 4:
                        System.out.println("Tam biet!");
                        break;
                    default:
                        System.out.println("Vui long chon tu 1-4.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Loi: Vui long nhap so!");
            } catch (SQLException e) {
                System.err.println("Loi Database: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Loi: " + e.getMessage());
            }
        }
    }
}
