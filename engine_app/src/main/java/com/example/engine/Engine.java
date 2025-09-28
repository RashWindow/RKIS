package com.example.engine;

import java.util.Objects;

public class Engine {
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