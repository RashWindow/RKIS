package com.example.engine;
import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableAspectJAutoProxy 
public class EngineApp implements CommandLineRunner {
    
    @Autowired
    private EngineService engineService;
    
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        this.runApplication();
    }
    
    public void runApplication() {
        while (true) {
            printMenu();
            int choice = readIntInput("Выберите пункт меню: ", 1, 5);
            
            switch (choice) {
                case 1:
                    addEngine();
                    break;
                case 2:
                    removeEngine();
                    break;
                case 3:
                    printEngines();
                    break;
                case 4:
                    compareEngines();
                    break;
                case 5:
                    System.out.println("Завершение работы...");
                    return;
            }
        }
    }
    
    private void printMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Добавить новый элемент");
        System.out.println("2. Удалить элемент по индексу");
        System.out.println("3. Вывод всех элементов");
        System.out.println("4. Сравнение двух элементов на равенство");
        System.out.println("5. Завершение работы");
    }
    
    private void addEngine() {
        try {
            System.out.println("\nВыберите тип двигателя:");
            System.out.println("1. Обычный двигатель");
            System.out.println("2. Двигатель внутреннего сгорания");
            System.out.println("3. Дизельный двигатель");
            System.out.println("4. Реактивный двигатель");
            
            int type = readIntInput("Тип: ", 1, 4);
            
            double power = readDoubleInput("Мощность: ");
            String model = readStringInput("Модель: ");
            
            Engine engine;
            switch (type) {
                case 1:
                    engine = new Engine(power, model);
                    break;
                case 2:
                    int cylinders = readIntInput("Количество цилиндров: ", 0, Integer.MAX_VALUE);
                    String fuelType = readStringInput("Тип топлива: ");
                    engine = new InternalCombustionEngine(power, model, cylinders, fuelType);
                    break;
                case 3:
                    cylinders = readIntInput("Количество цилиндров: ", 0, Integer.MAX_VALUE);
                    double compressionRatio = readDoubleInput("Степень сжатия: ");
                    String injectionType = readStringInput("Тип впрыска: ");
                    engine = new DieselEngine(power, model, cylinders, "Diesel", compressionRatio, injectionType);
                    break;
                case 4:
                    double thrust = readDoubleInput("Тяга: ");
                    String bypassType = readStringInput("Тип bypass: ");
                    engine = new JetEngine(power, model, thrust, bypassType);
                    break;
                default:
                    throw new IllegalArgumentException("Неверный тип двигателя");
            }
            
            engineService.addEngine(engine);
            System.out.println("Двигатель добавлен: " + engine);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void removeEngine() {
        try {
            List<Engine> engines = engineService.getAllEngines();
            if (engines.isEmpty()) {
                System.out.println("Список двигателей пуст.");
                return;
            }
            
            printEngines();
            int index = readIntInput("Введите индекс для удаления: ", 0, engines.size() - 1);
            
            engineService.removeEngine(index);
            System.out.println("Удален двигатель по индексу: " + index);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void printEngines() {
        try {
            List<Engine> engines = engineService.getAllEngines();
            if (engines.isEmpty()) {
                System.out.println("Список двигателей пуст.");
                return;
            }
            
            System.out.println("\nСписок двигателей:");
            for (int i = 0; i < engines.size(); i++) {
                System.out.println(i + ": " + engines.get(i));
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void compareEngines() {
        try {
            List<Engine> engines = engineService.getAllEngines();
            if (engines.size() < 2) {
                System.out.println("Недостаточно элементов для сравнения.");
                return;
            }
            
            printEngines();
            int index1 = readIntInput("Введите индекс первого элемента: ", 0, engines.size() - 1);
            int index2 = readIntInput("Введите индекс второго элемента: ", 0, engines.size() - 1);
            
            boolean isEqual = engineService.compareEngines(index1, index2);
            
            if (isEqual) {
                System.out.println("Элементы равны.");
            } else {
                System.out.println("Элементы не равны.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private int readIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                int value = Integer.parseInt(input);
                
                if (value < min || value > max) {
                    System.out.println("Значение должно быть между " + min + " и " + max + ".");
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
    
    private double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        System.setProperty("logging.level.com.example.engine", "TRACE");
        SpringApplication.run(EngineApp.class, args);
    }
}