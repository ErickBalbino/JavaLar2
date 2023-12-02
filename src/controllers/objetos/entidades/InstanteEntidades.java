package controllers.objetos.entidades;

import java.util.HashMap;
import java.util.Map;

public class InstanteEntidades {
	private Map<String, Map<Integer, Integer>> instantes;
	
    public InstanteEntidades() {
        instantes = new HashMap<>();
        instantes.put("Bug", new HashMap<>());
        instantes.put("Desenvolvedor", new HashMap<>());
    }

    public void adicionarInstante(String entidade, int instante, int idInstante) {
        if (instantes.containsKey(entidade)) {
            instantes.get(entidade).put(idInstante, instante);
        }
    }

    public int getInstante(String entidade, int idInstante) {
        return instantes.containsKey(entidade) ? instantes.get(entidade).getOrDefault(idInstante, 0) : 0;
    }
}
