package controllers.objetos.planetas;

import java.util.HashMap;
import java.util.Map;

public class InstantesPlanetas {
    private Map<String, Map<Integer, Integer>> instantes;

    public InstantesPlanetas() {
        instantes = new HashMap<>();
        instantes.put("Python", new HashMap<>());
        instantes.put("JavaScript", new HashMap<>());
        instantes.put("Ruby on Rails", new HashMap<>());
        instantes.put("PHP", new HashMap<>());
        instantes.put("C#", new HashMap<>());
        instantes.put("C++", new HashMap<>());
        instantes.put("C", new HashMap<>());
    }

    public void adicionarInstante(String planeta, int instante, int idInstante) {
        if (instantes.containsKey(planeta)) {
            instantes.get(planeta).put(idInstante, instante);
        }
    }

    public int getInstante(String planeta, int idInstante) {
        return instantes.containsKey(planeta) ? instantes.get(planeta).getOrDefault(idInstante, 0) : 0;
    }
}
