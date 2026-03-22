package Bai3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BedDAO {

    public void updateBedStatus(String bedId, String status) {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.getConnection();
            // Cau lenh SQL cap nhat trang thai giuong dua tren ID
            String sql = "UPDATE beds SET bed_status = ? WHERE bed_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, bedId);

            // PHAN QUAN TRONG: Lay so dong bi tac dong
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(">>> THANH CONG: Giuong " + bedId + " da chuyen sang: " + status);
            } else {
                // Phan hoi chinh xac cho y ta khi ma giuong sai
                System.err.println(">>> LOI: Ma giuong '" + bedId + "' khong ton tai. Vui long kiem tra lai!");
            }

        } catch (Exception e) {
            System.err.println("Loi ket noi: " + e.getMessage());
        } finally {
            // Dong ket noi de tranh treo he thong (Giai quyet Phan 1 bai dau tien)
            try {
                if (ps != null) ps.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BedDAO dao = new BedDAO();

        // Test 1: Cap nhat ma giuong co that (Gia su ban da tao trong DB)
        dao.updateBedStatus("Bed_001", "Dang su dung");

        // Test 2: Cap nhat ma giuong KHONG ton tai (Loi nhu de bai)
        dao.updateBedStatus("Bed_999", "Dang su dung");
    }
}