package controllers.objetos.planetas;

public class Java extends Planeta {
    private static final String NOME = "Java";
    private static final int VELOCIDADE_TRANS = 0;
    private static final int VELOCIDADE_ROT = 0; 
    
    public Java() {
        super(NOME, 0, 0, VELOCIDADE_TRANS, VELOCIDADE_ROT, 0, 0, 0);
    }

    @Override
    public void mover(int instantes) {
    }
}