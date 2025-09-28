package com.example.engine;

import java.util.Objects;

public class JetEngine extends Engine {
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