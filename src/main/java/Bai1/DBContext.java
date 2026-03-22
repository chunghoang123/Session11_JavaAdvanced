package Bai1; // Khai báo đúng tên thư mục chứa file

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    // Hằng số cấu hình
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASS = "Londeocan1";

    public Connection getConnection() throws Exception {
        // Nạp driver (tùy phiên bản MySQL connector, dòng này có thể không bắt buộc nhưng nên có)
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}