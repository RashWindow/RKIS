package com.example.engine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; import java.util.*;
public class EngineApp {
    static class Engine {
        protected double power;
        protected String model;
        
        public Engine() {
            this(0.0, "Unknown");
        }
        
        public Engine(double power, String model) {
            validatePower(power);
            this.power = power;
            this.model = model;
        }
        
        private void validatePower(double power) {
            if (power < 0) {
                throw new IllegalArgumentException("Мощность не может быть отрицательной");
            }
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Engine engine = (Engine) o;
            return Double.compare(engine.power, power) == 0 &&
                    Objects.equals(model, engine.model);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(power, model);
        }
        
        @Override
        public String toString() {
            return "Engine{power=" + power + ", model='" + model + "'}";
        }
    }

    static class InternalCombustionEngine extends Engine {
        protected int cylinders;
        protected String fuelType;
        
        public InternalCombustionEngine() {
            this(0.0, "Unknown", 0, "Gasoline");
        }
        
        public InternalCombustionEngine(double power, String model, int cylinders, String fuelType) {
            super(power, model);
            validateCylinders(cylinders);
            this.cylinders = cylinders;
            this.fuelType = fuelType;
        }
        
        private void validateCylinders(int cylinders) {
            if (cylinders < 0) {
                throw new IllegalArgumentException("Количество цилиндров не может быть отрицательным");
            }
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            InternalCombustionEngine that = (InternalCombustionEngine) o;
            return cylinders == that.cylinders &&
                    Objects.equals(fuelType, that.fuelType);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), cylinders, fuelType);
        }
        
        @Override
        public String toString() {
            return "InternalCombustionEngine{" +
                    "power=" + power +
                    ", model='" + model + '\'' +
                    ", cylinders=" + cylinders +
                    ", fuelType='" + fuelType + '\'' +
                    '}';
        }
    }

    static class DieselEngine extends InternalCombustionEngine {
        private double compressionRatio;
        private String injectionType;
        
        public DieselEngine() {
            this(0.0, "Unknown", 0, "Diesel", 18.0, "Direct");
        }
        
        public DieselEngine(double power, String model, int cylinders, String fuelType, 
                           double compressionRatio, String injectionType) {
            super(power, model, cylinders, fuelType);
            validateCompressionRatio(compressionRatio);
            this.compressionRatio = compressionRatio;
            this.injectionType = injectionType;
        }
        
        private void validateCompressionRatio(double compressionRatio) {
            if (compressionRatio < 0) {
                throw new IllegalArgumentException("Степень сжатия не может быть отрицательной");
            }
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            DieselEngine that = (DieselEngine) o;
            return Double.compare(that.compressionRatio, compressionRatio) == 0 &&
                    Objects.equals(injectionType, that.injectionType);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), compressionRatio, injectionType);
        }
        
        @Override
        public String toString() {
            return "DieselEngine{" +
                    "power=" + power +
                    ", model='" + model + '\'' +
                    ", cylinders=" + cylinders +
                    ", fuelType='" + fuelType + '\'' +
                    ", compressionRatio=" + compressionRatio +
                    ", injectionType='" + injectionType + '\'' +
                    '}';
        }
    }

    static class JetEngine extends Engine {
        private double thrust;
        private String bypassType;
        
        public JetEngine() {
            this(0.0, "Unknown", 0.0, "Low-bypass");
        }
        
        public JetEngine(double power, String model, double thrust, String bypassType) {
            super(power, model);
            validateThrust(thrust);
            this.thrust = thrust;
            this.bypassType = bypassType;
        }
        
        private void validateThrust(double thrust) {
            if (thrust < 0) {
                throw new IllegalArgumentException("Тяга не может быть отрицательной");
            }
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            JetEngine jetEngine = (JetEngine) o;
            return Double.compare(jetEngine.thrust, thrust) == 0 &&
                    Objects.equals(bypassType, jetEngine.bypassType);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), thrust, bypassType);
        }
        
        @Override
        public String toString() {
            return "JetEngine{" +
                    "power=" + power +
                    ", model='" + model + '\'' +
                    ", thrust=" + thrust +
                    ", bypassType='" + bypassType + '\'' +
                    '}';
        }
    }

    static class Logger {
        public static void logMethodCall(String methodName, Object[] args) {
            System.out.println("[LOG] Вызов метода: " + methodName + " с аргументами: " + Arrays.toString(args));
        }
        
        public static void logReturn(String methodName, Object result) {
            System.out.println("[LOG] Метод " + methodName + " вернул: " + result);
        }
        
        public static void logException(String methodName, Exception ex) {
            System.out.println("[LOG] Метод " + methodName + " вызвал исключение: " + ex.getMessage());
        }
    }

    private List<Engine> engines = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    public void run() {
        while (true) {
            printMenu();
            int choice = readIntInput("Выберите пункт меню: ", 1, 5);
            
            switch (choice) {
                case 1:
                    Logger.logMethodCall("addEngine", new Object[]{});
                    addEngine();
                    break;
                case 2:
                    Logger.logMethodCall("removeEngine", new Object[]{});
                    removeEngine();
                    break;
                case 3:
                    Logger.logMethodCall("printEngines", new Object[]{});
                    printEngines();
                    break;
                case 4:
                    Logger.logMethodCall("compareEngines", new Object[]{});
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
            
            engines.add(engine);
            Logger.logReturn("addEngine", "Двигатель добавлен: " + engine);
            System.out.println("Двигатель добавлен: " + engine);
        } catch (IllegalArgumentException e) {
            Logger.logException("addEngine", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void removeEngine() {
        try {
            if (engines.isEmpty()) {
                System.out.println("Список двигателей пуст.");
                return;
            }
            
            printEngines();
            int index = readIntInput("Введите индекс для удаления: ", 0, engines.size() - 1);
            
            Engine removed = engines.remove(index);
            Logger.logReturn("removeEngine", "Удален двигатель: " + removed);
            System.out.println("Удален двигатель: " + removed);
        } catch (Exception e) {
            Logger.logException("removeEngine", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void printEngines() {
        try {
            if (engines.isEmpty()) {
                System.out.println("Список двигателей пуст.");
                return;
            }
            
            System.out.println("\nСписок двигателей:");
            for (int i = 0; i < engines.size(); i++) {
                System.out.println(i + ": " + engines.get(i));
            }
            Logger.logReturn("printEngines", "Выведено " + engines.size() + " двигателей");
        } catch (Exception e) {
            Logger.logException("printEngines", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private void compareEngines() {
        try {
            if (engines.size() < 2) {
                System.out.println("Недостаточно элементов для сравнения.");
                return;
            }
            
            printEngines();
            int index1 = readIntInput("Введите индекс первого элемента: ", 0, engines.size() - 1);
            int index2 = readIntInput("Введите индекс второго элемента: ", 0, engines.size() - 1);
            
            Engine engine1 = engines.get(index1);
            Engine engine2 = engines.get(index2);
            
            boolean isEqual = engine1.equals(engine2);
            Logger.logReturn("compareEngines", "Сравнение элементов: " + isEqual);
            
            if (isEqual) {
                System.out.println("Элементы равны.");
            } else {
                System.out.println("Элементы не равны.");
            }
        } catch (Exception e) {
            Logger.logException("compareEngines", e);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    // Вспомогательные методы для ввода данных
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
        EngineApp app = new EngineApp();
        app.run();
    }
}