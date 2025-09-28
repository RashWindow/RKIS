package com.example.engine;

import java.util.Objects;

public class InternalCombustionEngine extends Engine {
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