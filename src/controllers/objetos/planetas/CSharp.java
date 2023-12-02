package controllers.objetos.planetas;

public class CSharp extends Planeta {
	private static final String NOME = "C#";
	private static final int VELOCIDADE_TRANS = 1;
	private static final int VELOCIDADE_ROT = 4;
	private static final int ORBITA = 11;

	private int diasPassados;
	private int anosPassados;

	public CSharp(int posX, int posY) {
		super(NOME, posX, posY, VELOCIDADE_TRANS, VELOCIDADE_ROT, 4, 0, 0);
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

		setAnos(this.anosPassados);
		setDias(this.diasPassados);
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