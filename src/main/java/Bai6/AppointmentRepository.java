package Bai6;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    public void addAppointment(Appointment app) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, app.getPatientName());
            ps.setDate(2, app.getAppointmentDate());
            ps.setString(3, app.getDoctorName());
            ps.setString(4, app.getStatus());
            ps.executeUpdate();
            System.out.println(">>> Them lich kham thanh cong!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateAppointment(Appointment app) {
        String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, app.getPatientName());
            ps.setDate(2, app.getAppointmentDate());
            ps.setString(3, app.getDoctorName());
            ps.setString(4, app.getStatus());
            ps.setInt(5, app.getId());
            if (ps.executeUpdate() > 0) System.out.println(">>> Cap nhat thanh cong!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println(">>> Da xoa lich kham ID: " + id);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Appointment(rs.getInt("id"), rs.getString("patient_name"),
                        rs.getDate("appointment_date"), rs.getString("doctor_name"), rs.getString("status")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}