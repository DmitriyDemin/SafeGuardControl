import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Создаем объект ProtectiveEquipment
        ProtectiveEquipment gloves = new ProtectiveEquipment(
                1,
                "Диэлектрические перчатки",
                "Перчатки",
                "Годно",
                LocalDate.of(2024, 10, 1),
                LocalDate.of(2025, 10, 1),
                "Склад №1",
                "Используются для работы с напряжением до 1000 В"
        );

        // Выводим информацию о средстве
        System.out.println(gloves);

        // Проверяем, истек ли срок годности
        if (gloves.isExpired()) {
            System.out.println("Срок годности истек!");
        } else {
            System.out.println("Срок годности в порядке.");
        }
    }
}