package Bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO {

    public void listAllPatients() {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM Patients";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("--- Danh sach benh nhan ---");
            while (rs.next()) {
                // Lay du lieu cot thu 2 (thuong la ten benh nhan)
                System.out.println("- " + rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println("Loi he thong: " + e.getMessage());
        } finally {
            // Giai quyet Phan 1: Luon dong ket noi trong finally
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println(">>> Da dong ket noi an toan.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Ham chay chuong trinh
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();
        dao.listAllPatients();
    }
}