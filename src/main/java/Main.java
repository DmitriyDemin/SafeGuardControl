import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Main {
    public static void main(String[] args) {
        // Создаем объект DatabaseHandler
        DatabaseHandler dbHandler = new DatabaseHandler();

        // Создаем объект ProtectiveEquipment
        ProtectiveEquipment gloves = new ProtectiveEquipment(
                0, // ID будет автоматически сгенерирован базой данных
                "Диэлектрические перчатки",
                "Перчатки",
                "Годно",
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2024, 10, 1),
                "Склад №1",
                "Используются для работы с напряжением до 1000 В",
                "до 1000 В"
        );

        // Добавляем запись в базу данных
        dbHandler.addEquipment(gloves);

        // Обновляем запись
        gloves.setStatus("Не годно");
        dbHandler.updateEquipment(gloves);

        // Получаем все записи из базы данных
        List<ProtectiveEquipment> equipmentList = dbHandler.getAllEquipment();
        for (ProtectiveEquipment equipment : equipmentList) {
            System.out.println(equipment);
        }

        // Удаляем запись
        dbHandler.deleteEquipment(gloves.getId());
    }
}