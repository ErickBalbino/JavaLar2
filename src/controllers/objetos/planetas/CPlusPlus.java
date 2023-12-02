package controllers.objetos.planetas;

public class CPlusPlus extends Planeta {
	private static final String NOME = "C++";
	private static final int VELOCIDADE_TRANS = 2;
	private static final int VELOCIDADE_ROT = 0;
	private static final int ORBITA = 13;

	private int diasPassados;
	private int anosPassados;

	public CPlusPlus(int posX, int posY) {
		super(NOME, posX, posY, VELOCIDADE_TRANS, VELOCIDADE_ROT, 0.5, 0, 0);
		this.diasPassados = 0;
		this.anosPassados = 0;
	}

	@Override
	public void mover(int instantes) {
		int movimento = (int) (VELOCIDADE_TRANS * Math.pow(instantes, 2)); // Movimento quadrático

		// Calcular a posição considerando a órbita e o movimento
		int novaPosX = (posX + movimento) % ORBITA;
		int novaPosY = posY;

		setPosX(novaPosX);
		setPosY(novaPosY);

		this.diasPassados += instantes;
		if (this.diasPassados >= 365) {
			this.anosPassados += this.diasPassados / 365;
			this.diasPassados %= 365;
		}
		
		setAnos(this.diasPassados);
		setDias(this.anosPassados);
	}

	public int getDiasPassados() {
		return diasPassados;
	}

	public void setDiasPassados(int diasPassados) {
		this.diasPassados = diasPassados;
	}

	public int getAnosPassados() {
		return anosPassados;
	}

	public void setAnosPassados(int anosPassados) {
		this.anosPassados = anosPassados;
	}
}