package com.example.engine;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EngineService {
    private List<Engine> engines = new ArrayList<>();
    
    public void addEngine(Engine engine) {
        engines.add(engine);
    }
    
    public void removeEngine(int index) {
        if (index >= 0 && index < engines.size()) {
            engines.remove(index);
        }
    }
    
    public List<Engine> getAllEngines() {
        return new ArrayList<>(engines);
    }
    
    public boolean compareEngines(int index1, int index2) {
        if (index1 >= 0 && index1 < engines.size() && index2 >= 0 && index2 < engines.size()) {
            return engines.get(index1).equals(engines.get(index2));
        }
        return false;
    }
}