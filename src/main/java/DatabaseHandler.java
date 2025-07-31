import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/safeguard";
    private static final String USER = "root";
    private static final String PASSWORD = "Yar_1188";

    // Метод для получения соединения с базой данных
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Метод для добавления записи в таблицу
    public void addEquipment(ProtectiveEquipment equipment) {
        String query = "INSERT INTO protective_equipment (name, type, status, inspection_date, expiration_date, location, comment, VoltageLevel) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());
            stmt.setString(3, equipment.getStatus());
            stmt.setDate(4, Date.valueOf(equipment.getInspectionDate()));
            stmt.setDate(5, Date.valueOf(equipment.getExpirationDate()));
            stmt.setString(6, equipment.getLocation());
            stmt.setString(7, equipment.getComment());
            stmt.setString(8, equipment.getVoltageLevel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения всех записей из таблицы
    public List<ProtectiveEquipment> getAllEquipment() {
        List<ProtectiveEquipment> equipmentList = new ArrayList<>();
        String query = "SELECT * FROM protective_equipment";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ProtectiveEquipment equipment = new ProtectiveEquipment();
                equipment.setId(rs.getInt("id"));
                equipment.setName(rs.getString("name"));
                equipment.setType(rs.getString("type"));
                equipment.setStatus(rs.getString("status"));
                equipment.setInspectionDate(rs.getDate("inspection_date").toLocalDate());
                equipment.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
                equipment.setLocation(rs.getString("location"));
                equipment.setComment(rs.getString("comment"));
                equipment.setVoltageLevel(rs.getString("VoltageLevel"));
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    // Метод для обновления записи
    public void updateEquipment(ProtectiveEquipment equipment) {
        String query = "UPDATE protective_equipment SET name=?, type=?, status=?, inspection_date=?, expiration_date=?, location=?, comment=?, VoltageLevel=? " +
                "WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());
            stmt.setString(3, equipment.getStatus());
            stmt.setDate(4, Date.valueOf(equipment.getInspectionDate()));
            stmt.setDate(5, Date.valueOf(equipment.getExpirationDate()));
            stmt.setString(6, equipment.getLocation());
            stmt.setString(7, equipment.getComment());
            stmt.setInt(8, equipment.getId());
            stmt.setString(9, equipment.getVoltageLevel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления записи по ID
    public void deleteEquipment(int id) {
        String query = "DELETE FROM protective_equipment WHERE id=?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}Коментарий от Степана