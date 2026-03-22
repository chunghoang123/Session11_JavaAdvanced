package Bai4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class SecurityDAO {

    // Ham loai bo ky tu dac biet de bao ve he thong
    public String sanitize(String input) {
        if (input == null) return "";
        // Loai bo dau nhay don, dau cham phay va dau gach ngang
        return input.replace("'", "''")
                .replace(";", "")
                .replace("--", "");
    }

    public void searchPatient(String inputName) {
        DBContext db = new DBContext();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            st = conn.createStatement();

            // Lam sach du lieu truoc khi dua vao cau lenh
            String safeName = sanitize(inputName);
            String sql = "SELECT * FROM patients WHERE name = '" + safeName + "'";

            System.out.println("SQL thuc thi an toan: " + sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                // Su dung dung ten cot ban da tao: id, name, age, gender
                System.out.println("ID: " + rs.getInt("id") +
                        " | Ten: " + rs.getString("name") +
                        " | Tuoi: " + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SecurityDAO dao = new SecurityDAO();

        // Test 1: Tim kiem binh thuong
        System.out.println("--- Tim kiem binh thuong ---");
        dao.searchPatient("Nguyen Van A");

        // Test 2: Thu tan cong SQL Injection
        System.out.println("\n--- Thu tan cong voi ' OR '1'='1 ---");
        dao.searchPatient("' OR '1'='1");
    }
}