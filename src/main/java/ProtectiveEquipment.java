import java.sql.SQLOutput;
import java.time.LocalDate;

public class ProtectiveEquipment {
    private int id; // Уникальный идентификатор
    private String name; // Название средства
    private String type; // Тип средства
    private String status; // Статус (Годно/Не годно)
    private LocalDate inspectionDate; // Дата последней проверки
    private LocalDate expirationDate; // Срок годности
    private String location; // Место хранения
    private String comment; // Комментарии
    private String VoltageLevel; // уровень напряжение до или выше, будет нужно дальше

    // Конструктор по умолчанию
    public ProtectiveEquipment() {
    }
    // Конструктор с параметрами
    public ProtectiveEquipment(int id, String name, String type, String status, LocalDate inspectionDate,
                               LocalDate expirationDate, String location, String comment, String voltage) {
        if (id > 0)
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.inspectionDate = inspectionDate;
        this.expirationDate = expirationDate;
        this.location = location;
        this.comment = comment;
        this.VoltageLevel = voltage;
    }
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >0)
        this.id = id;
        else{
            System.out.println("id не может быть отрицательным");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVoltageLevel() {
        return VoltageLevel;
    }

    public void setVoltageLevel(String voltageLevel) {
        VoltageLevel = voltageLevel;
    }

    // Метод для проверки, истек ли срок годности
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    // Переопределение метода toString для удобного вывода информации
    @Override
    public String toString() {
        return "ProtectiveEquipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", inspectionDate=" + inspectionDate +
                ", expirationDate=" + expirationDate +
                ", location='" + location + '\'' +
                ", comment='" + comment + '\'' +
                ", voltage = '" + VoltageLevel +'\'' +
                '}';
    }
}