import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String fullName; // ФИО
    private String position; // Должность
    private String department; // Подразделение
    private LocalDate hireDate; // Дата приема
    private List<ProtectiveEquipment> assignedEquipment; // Закрепленные СИЗ

    // Конструкторы
    public Employee() {
        this.assignedEquipment = new ArrayList<>();
    }

    public Employee(int id, String fullName, String position, String department, LocalDate hireDate) {
        this();
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.department = department;
        this.hireDate = hireDate;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ... (аналогично для остальных полей) ...

    public List<ProtectiveEquipment> getAssignedEquipment() {
        return assignedEquipment;
    }

    // Метод для выдачи СИЗ работнику
    public void assignEquipment(ProtectiveEquipment equipment) {
        if (equipment.getStatus().equals("Не годно")) {
            throw new IllegalStateException("Нельзя выдать средство с статусом 'Не годно'");
        }
        this.assignedEquipment.add(equipment);
    }

    // Метод для возврата СИЗ
    public void returnEquipment(ProtectiveEquipment equipment) {
        this.assignedEquipment.remove(equipment);
    }

    @Override
    public String toString() {
        return String.format("Employee[id=%d, name=%s, position=%s]", id, fullName, position);
    }
}