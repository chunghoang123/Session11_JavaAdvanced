package Bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyDAO {

    public void printPharmacyCatalogue() {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            // Truy van lay Ten thuoc va So luong ton kho
            String sql = "SELECT medicine_name, stock_quantity FROM Pharmacy";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("--- DANH MUC THUOC KIEM KE ---");
            System.out.println(String.format("%-20s | %-10s", "Ten Thuoc", "So Luong"));
            System.out.println("------------------------------------------");

            // THAY "if" BANG "while" DE DOC TOAN BO DANH SACH
            int count = 0;
            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int quantity = rs.getInt("stock_quantity");

                System.out.println(String.format("%-20s | %-10d", name, quantity));
                count++;
            }

            if (count == 0) {
                System.out.println("Kho thuoc hien dang trong!");
            }

        } catch (Exception e) {
            System.err.println("Loi truy xuat: " + e.getMessage());
        } finally {
            // Luon luon dong ket noi de tranh treo he thong
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PharmacyDAO dao = new PharmacyDAO();
        dao.printPharmacyCatalogue();
    }
}