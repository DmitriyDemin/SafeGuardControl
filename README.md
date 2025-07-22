

Создание приложения для учета электрозащитных средств на Java — это отличная идея! Давайте разберем основные шаги, которые вам нужно будет выполнить для реализации этого проекта.

1. Определение функциональности приложения

Прежде чем приступать к разработке, определите, какие функции должно поддерживать ваше приложение. Например:

Добавление новых электрозащитных средств (например, перчатки, каски, инструменты).

Учет их состояния (годно/не годно к использованию, срок годности).

Поиск и фильтрация по различным параметрам (тип средства, статус, дата проверки).

Генерация отчетов (например, список средств, которые нужно заменить).

Хранение данных (в базе данных или файлах).

2. Выбор архитектуры приложения

Решите, будет ли ваше приложение консольным или с графическим интерфейсом (GUI). Для GUI можно использовать библиотеки, такие как:

JavaFX (современная и мощная библиотека).

Swing (более старая, но все еще популярная).

3. Проектирование структуры данных

Создайте классы для представления электрозащитных средств. Например:

public class ProtectiveEquipment {
private int id;
private String name; // Название средства (например, "Диэлектрические перчатки")
private String type; // Тип средства (например, "Перчатки")
private String status; // Статус (например, "Годно", "Не годно")
private LocalDate inspectionDate; // Дата последней проверки
private LocalDate expirationDate; // Срок годности

    // Конструктор, геттеры и сеттеры
}

4. Работа с данными

Решите, как вы будете хранить данные:

База данных: Используйте JDBC для подключения к базе данных (например, MySQL, PostgreSQL).

Файлы: Сохраняйте данные в файлы (например, JSON, CSV).

Пример работы с базой данных:

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHandler {
private Connection connection;

    public DatabaseHandler(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEquipment(ProtectiveEquipment equipment) {
        String query = "INSERT INTO equipment (name, type, status, inspection_date, expiration_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());
            stmt.setString(3, equipment.getStatus());
            stmt.setDate(4, java.sql.Date.valueOf(equipment.getInspectionDate()));
            stmt.setDate(5, java.sql.Date.valueOf(equipment.getExpirationDate()));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Другие методы для работы с базой данных
}

5. Реализация пользовательского интерфейса

Если вы используете JavaFX, создайте интерфейс для добавления, редактирования и просмотра данных. Пример:

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
@Override
public void start(Stage primaryStage) {
TextField nameField = new TextField();
TextField typeField = new TextField();
Button addButton = new Button("Добавить");

        addButton.setOnAction(e -> {
            ProtectiveEquipment equipment = new ProtectiveEquipment();
            equipment.setName(nameField.getText());
            equipment.setType(typeField.getText());
            // Добавьте остальные поля и сохраните в базу данных
        });

        VBox root = new VBox(nameField, typeField, addButton);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Учет электрозащитных средств");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

6. Тестирование

Протестируйте приложение, чтобы убедиться, что все функции работают корректно. Используйте unit-тесты (например, JUnit) для тестирования бизнес-логики.

7. Дополнительные функции

Экспорт данных в Excel или PDF.

Уведомления о необходимости проверки средств.

Аутентификация пользователей, если приложение будет использоваться несколькими людьми.

8. Сборка и запуск

Используйте Maven или Gradle для управления зависимостями и сборки проекта. Создайте исполняемый JAR-файл для удобного запуска.

Это базовый план для создания вашего приложения. Если у вас есть конкретные вопросы или нужно больше деталей по какому-то из шагов, дайте знать! 😊





Рассмотрим класс Защитное средство подробнее



Хорошо! Давайте подробно проработаем класс ProtectiveEquipment, который будет представлять электрозащитное средство. Мы добавим все необходимые поля, методы, а также реализуем логику для работы с этим классом.

Класс ProtectiveEquipment

Поля класса

id — уникальный идентификатор средства.

name — название средства (например, "Диэлектрические перчатки").

type — тип средства (например, "Перчатки", "Каска", "Инструмент").

status — текущий статус (например, "Годно", "Не годно", "На проверке").

inspectionDate — дата последней проверки.

expirationDate — срок годности или следующая дата проверки.

location — место хранения или использования средства.

comment — дополнительные комментарии или примечания.

Методы класса

Конструкторы (по умолчанию и с параметрами).

Геттеры и сеттеры для всех полей.

Метод toString() для удобного вывода информации о средстве.

Метод isExpired(), который проверяет, истек ли срок годности.

Реализация класса 
````java
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

    // Конструктор по умолчанию
    public ProtectiveEquipment() {
    }

    // Конструктор с параметрами
    public ProtectiveEquipment(int id, String name, String type, String status, LocalDate inspectionDate,
                              LocalDate expirationDate, String location, String comment) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.inspectionDate = inspectionDate;
        this.expirationDate = expirationDate;
        this.location = location;
        this.comment = comment;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                '}';
    }
}
```` 

Пример использования класса

```java
import java.time.LocalDate;

public class Main {
public static void main(String[] args) {
// Создаем объект ProtectiveEquipment
ProtectiveEquipment gloves = new ProtectiveEquipment(
1,
"Диэлектрические перчатки",
"Перчатки",
"Годно",
LocalDate.of(2023, 10, 1),
LocalDate.of(2024, 10, 1),
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
``` 

Дополнительные улучшения
Валидация данных: Добавьте проверки в сеттеры, чтобы избежать некорректных данных (например, отрицательный ID или пустое название).
Логирование: Используйте библиотеку (например, Log4j или SLF4J) для логирования действий.
Сериализация: Реализуйте интерфейс Serializable, если планируете сохранять объекты в файл.
Сравнение объектов: Переопределите методы equals() и hashCode(), если нужно сравнивать объекты.
Если нужно что-то добавить или изменить, дайте знать! 😊

Добавляем базу данных для хранения данных о СИЗ

Давайте добавим функциональность для хранения данных о защитных средствах в базе данных. Мы будем использовать JDBC (Java Database Connectivity) для взаимодействия с базой данных. В качестве примера будем использовать MySQL, но вы можете адаптировать код для любой другой СУБД (например, PostgreSQL, SQLite).

Шаги для реализации хранения в базе данных

Создание таблицы в базе данных

Настройка подключения к базе данных

Реализация методов для работы с базой данных

Интеграция с классом ProtectiveEquipment

1. Создание таблицы в базе данных

Создадим таблицу protective_equipment в базе данных MySQL. Пример SQL-запроса:

CREATE TABLE protective_equipment (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
type VARCHAR(50) NOT NULL,
status VARCHAR(50) NOT NULL,
inspection_date DATE NOT NULL,
expiration_date DATE NOT NULL,
location VARCHAR(100),
comment TEXT
);

2. Настройка подключения к базе данных

Для работы с базой данных нам понадобится драйвер MySQL. Добавьте зависимость в ваш проект (если используете Maven):

xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

Если вы не используете Maven, скачайте JAR-файл драйвера и добавьте его в classpath.

3. Реализация методов для работы с базой данных

Создадим класс DatabaseHandler, который будет отвечать за взаимодействие с базой данных.

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

````java
// Метод для получения соединения с базой данных
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Метод для добавления записи в таблицу
    public void addEquipment(ProtectiveEquipment equipment) {
        String query = "INSERT INTO protective_equipment (name, type, status, inspection_date, expiration_date, location, comment) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());
            stmt.setString(3, equipment.getStatus());
            stmt.setDate(4, Date.valueOf(equipment.getInspectionDate()));
            stmt.setDate(5, Date.valueOf(equipment.getExpirationDate()));
            stmt.setString(6, equipment.getLocation());
            stmt.setString(7, equipment.getComment());
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
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    // Метод для обновления записи
    public void updateEquipment(ProtectiveEquipment equipment) {
        String query = "UPDATE protective_equipment SET name=?, type=?, status=?, inspection_date=?, expiration_date=?, location=?, comment=? " +
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
}
````
    

4. Интеграция с классом ProtectiveEquipment

Теперь мы можем использовать класс DatabaseHandler для работы с базой данных. Пример использования:

```java
import java.time.LocalDate;

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
                "Используются для работы с напряжением до 1000 В"
        );

        // Добавляем запись в базу данных
        dbHandler.addEquipment(gloves);

        // Получаем все записи из базы данных
        List<ProtectiveEquipment> equipmentList = dbHandler.getAllEquipment();
        for (ProtectiveEquipment equipment : equipmentList) {
            System.out.println(equipment);
        }

        // Обновляем запись
        gloves.setStatus("Не годно");
        dbHandler.updateEquipment(gloves);

        // Удаляем запись
        dbHandler.deleteEquipment(gloves.getId());
    }
}
```


Дополнительные улучшения
Обработка исключений: Добавьте более детальную обработку исключений (например, логирование).
Пул соединений: Используйте пул соединений (например, HikariCP) для повышения производительности.
Транзакции: Реализуйте поддержку транзакций для операций, которые должны выполняться атомарно.
Логирование: Добавьте логирование операций (например, с помощью Log4j или SLF4J).



1. Усовершенствование класса ProtectiveEquipment

Добавим валидацию и бизнес-логику:
````java

public class ProtectiveEquipment {
// ... (существующие поля) ...

    // Валидация при установке срока годности
    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Срок годности не может быть в прошлом");
        }
        this.expirationDate = expirationDate;
    }

    // Проверка необходимости внеочередной проверки
    public boolean needsInspection() {
        return status.equals("На проверке") || 
               inspectionDate.isBefore(LocalDate.now().minusMonths(3));
    }
}
````

```java
public class ProtectiveEquipment {
// ... (существующие поля) ...

    // Валидация при установке срока годности
    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Срок годности не может быть в прошлом");
        }
        this.expirationDate = expirationDate;
    }

    // Проверка необходимости внеочередной проверки
    public boolean needsInspection() {
        return status.equals("На проверке") || 
               inspectionDate.isBefore(LocalDate.now().minusMonths(3));
    }
}
```


2. Работа с базой данных: улучшения

Пулинг соединений (HikariCP)

Добавьте в pom.xml:


Run
```xml
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.0.1</version>
</dependency>
```

Обновите DatabaseHandler:
```java
private static final HikariDataSource dataSource;

static {
HikariConfig config = new HikariConfig();
config.setJdbcUrl(URL);
config.setUsername(USER);
config.setPassword(PASSWORD);
dataSource = new HikariDataSource(config);
}
```
Транзакции для операций
```java
public void updateEquipmentWithTransaction(ProtectiveEquipment equipment) throws SQLException {
try (Connection connection = dataSource.getConnection()) {
connection.setAutoCommit(false);
try {
updateEquipment(equipment, connection);
logAction("Обновлено средство ID: " + equipment.getId());
connection.commit();
} catch (SQLException e) {
connection.rollback();
throw e;
}
}
}
```


3. Логирование (Log4j2)

Добавьте зависимость:

xml

Copy

Download

Run

<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.20.0</version>
</dependency>

Пример использования:

java

Copy

Download
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseHandler {
private static final Logger logger = LogManager.getLogger(DatabaseHandler.class);

    public void addEquipment(ProtectiveEquipment equipment) {
        try {
            // ... код добавления ...
            logger.info("Добавлено средство: {}", equipment.getName());
        } catch (SQLException e) {
            logger.error("Ошибка при добавлении средства", e);
        }
    }
}

```

4. REST API (Spring Boot)

Для веб-интерфейса создадим контроллер:

java

Copy

Download
```java
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
@Autowired
private EquipmentService service;

    @GetMapping
    public List<ProtectiveEquipment> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ProtectiveEquipment equipment) {
        service.add(equipment);
        return ResponseEntity.ok("Средство добавлено");
    }
}
```


5. Графический интерфейс (JavaFX)

Пример таблицы для отображения средств:

java

Copy

Download
````java
ublic class MainView extends Application {
@Override
public void start(Stage stage) {
TableView<ProtectiveEquipment> table = new TableView<>();

        TableColumn<ProtectiveEquipment, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<ProtectiveEquipment, LocalDate> expCol = new TableColumn<>("Срок годности");
        expCol.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        
        table.getColumns().addAll(nameCol, expCol);
        table.setItems(FXCollections.observableList(databaseHandler.getAllEquipment()));
        
        Scene scene = new Scene(new VBox(table), 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
````
p

6. Дополнительные функции

Экспорт в Excel (Apache POI)

````java
public void exportToExcel(List<ProtectiveEquipment> items, String filePath) throws IOException {
Workbook workbook = new XSSFWorkbook();
Sheet sheet = workbook.createSheet("СИЗ");

    // Заголовки
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Название");
    headerRow.createCell(1).setCellValue("Статус");
    
    // Данные
    int rowNum = 1;
    for (ProtectiveEquipment item : items) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(item.getName());
        row.createCell(1).setCellValue(item.getStatus());
    }
    
    workbook.write(new FileOutputStream(filePath));
    workbook.close();
}
````


Напоминания о проверках

java

Copy

Download
```java
public List<ProtectiveEquipment> getExpiringSoon(int days) {
return getAllEquipment().stream()
.filter(e -> e.getExpirationDate().isBefore(LocalDate.now().plusDays(days)))
.collect(Collectors.toList());
}
```


Стек технологий

Компонент

Технологии

Бэкенд

Java 17, Spring Boot, Hibernate

База данных

MySQL/PostgreSQL

Фронтенд

JavaFX

Документирование

Swagger

Сборка

Maven/Gradle



Следующие шаги

Настройте окружение (JDK 17+, MySQL, IDE)

Реализуйте базовые CRUD-операции

Протестируйте с JUnit 5

Добавьте интерфейс (JavaFX или веб)

Настройте автоматическую сборку (CI/CD)

Нужна помощь с конкретным этапом? Готов углубиться в детали! 🛠️

любое защитное средство выдается работнику, давай создадим работника

Отличная идея! Давайте расширим систему, добавив сущность Работника (Employee) и связь между ним и защитными средствами (ProtectiveEquipment). Вот полная реализация:

1. Класс Employee (Работник)

java

Copy

Download
````java
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
````


2. Обновленная таблица в БД

Добавим таблицы для работников и учета выдачи СИЗ:

sql

Copy

Download

-- Таблица работников
CREATE TABLE employees (
id INT AUTO_INCREMENT PRIMARY KEY,
full_name VARCHAR(100) NOT NULL,
position VARCHAR(50) NOT NULL,
department VARCHAR(50) NOT NULL,
hire_date DATE NOT NULL
);

-- Таблица выдачи СИЗ (связь many-to-many)
CREATE TABLE equipment_assignment (
id INT AUTO_INCREMENT PRIMARY KEY,
employee_id INT NOT NULL,
equipment_id INT NOT NULL,
assignment_date DATE NOT NULL,
return_date DATE,
FOREIGN KEY (employee_id) REFERENCES employees(id),
FOREIGN KEY (equipment_id) REFERENCES protective_equipment(id)
);

3. Класс AssignmentRecord (Запись о выдаче)

java

Copy

Download

import java.time.LocalDate;

public class AssignmentRecord {
private int id;
private Employee employee;
private ProtectiveEquipment equipment;
private LocalDate assignmentDate;
private LocalDate returnDate;

    // Конструкторы, геттеры и сеттеры
    // ...
}

4. Дополнение DatabaseHandler

Добавим методы для работы с работниками и выдачей:

```java
public class DatabaseHandler {
// ... (существующие методы) ...

    // Добавление работника
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (full_name, position, department, hire_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getPosition());
            stmt.setString(3, employee.getDepartment());
            stmt.setDate(4, Date.valueOf(employee.getHireDate()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка при добавлении работника", e);
        }
    }

    // Выдача СИЗ работнику
    public void assignEquipment(int employeeId, int equipmentId) {
        String sql = "INSERT INTO equipment_assignment (employee_id, equipment_id, assignment_date) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, equipmentId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка при выдаче СИЗ", e);
        }
    }
}
```
5. Пример использования

java

Copy

Download

public class Main {
public static void main(String[] args) {
DatabaseHandler db = new DatabaseHandler();

        // Создаем работника
        Employee emp = new Employee(0, "Иванов И.И.", "Электрик", "Цех №1", LocalDate.now());
        db.addEmployee(emp);

        // Находим СИЗ
        ProtectiveEquipment gloves = db.getAllEquipment().stream()
                .filter(e -> e.getName().contains("перчатки"))
                .findFirst()
                .orElseThrow();

        // Выдаем СИЗ
        db.assignEquipment(emp.getId(), gloves.getId());
        System.out.println("СИЗ выданы работнику: " + emp.getFullName());
    }
}

6. Валидация при выдаче

Добавим проверки в метод assignEquipment:

java

Copy

Download

public void assignEquipment(int employeeId, int equipmentId) throws SQLException {
// Проверяем, что средство не выдано другому работнику
String checkSql = "SELECT * FROM equipment_assignment WHERE equipment_id = ? AND return_date IS NULL";
try (Connection conn = dataSource.getConnection();
PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
checkStmt.setInt(1, equipmentId);
ResultSet rs = checkStmt.executeQuery();
if (rs.next()) {
throw new IllegalStateException("Средство уже выдано другому работнику");
}
}

    // ... остальная логика выдачи ...
}

7. Отчеты

Метод для получения текущих выдач:

java

Copy

Download

public List<AssignmentRecord> getCurrentAssignments() {
List<AssignmentRecord> records = new ArrayList<>();
String sql = "SELECT e.*, p.name as eq_name FROM equipment_assignment a " +
"JOIN employees e ON a.employee_id = e.id " +
"JOIN protective_equipment p ON a.equipment_id = p.id " +
"WHERE a.return_date IS NULL";

    try (Connection conn = dataSource.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            AssignmentRecord record = new AssignmentRecord();
            // Заполняем данные...
            records.add(record);
        }
    } catch (SQLException e) {
        logger.error("Ошибка при получении выданных СИЗ", e);
    }
    return records;
}

Дальнейшее развитие

Веб-интерфейс: Добавьте страницы для управления работниками.

Уведомления: Рассылка предупреждений о необходимости возврата СИЗ.

QR-коды: Генерация меток для быстрого учета.

Нужна помощь с конкретным функционалом? Готов доработать любой раздел!







