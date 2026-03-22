package Bai5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private DBContext db = new DBContext();

    // 1. Xem danh sach
    public List<Doctor> getAll() throws Exception {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Doctor(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }
        return list;
    }

    // 2. Them moi (Xu ly loi trung ma/do dai tai day)
    public int addDoctor(Doctor d) throws Exception {
        String sql = "INSERT INTO doctors VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());
            return ps.executeUpdate();
        }
    }

    // 3. Thong ke chuyen khoa
    public void reportBySpecialty() throws Exception {
        String sql = "SELECT specialty, COUNT(*) FROM doctors GROUP BY specialty";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- THONG KE CHUYEN KHOA ---");
            while (rs.next()) {
                System.out.println(rs.getString(1) + ": " + rs.getInt(2) + " bac si");
            }
        }
    }
}