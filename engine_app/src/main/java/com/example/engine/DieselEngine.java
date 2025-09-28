package com.example.engine;

import java.util.Objects;

public class DieselEngine extends InternalCombustionEngine {
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