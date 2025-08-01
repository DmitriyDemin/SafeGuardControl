public enum TypeProtectiveEquipment {

    // Основные категории средств защиты
    HELMET("Каска", "Защита головы от механических повреждений"),
    GOGGLES("Очки", "Защита глаз от пыли, химикатов, УФ-излучения"),
    RESPIRATOR("Респиратор", "Защита органов дыхания от пыли и газов"),
    GLOVES("Перчатки", "Защита рук от механических/химических повреждений"),
    SAFETY_SHOES("Спецобувь", "Защита ног от ударов, проколов, скольжения"),
    EARPLUGS("Беруши", "Защита слуха от шума"),
    OVERALL("Комбинезон", "Защита тела от загрязнений и химикатов");

    private final String name;
    private final String description;

    // Конструктор
    ProtectionType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Дополнительный метод (пример)
    public static ProtectionType getByDescription(String keyword) {
        for (ProtectionType type : values()) {
            if (type.description.contains(keyword)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Не найдено СИЗ с описанием: " + keyword);
    }
}