package controllers.objetos.planetas;

import java.util.ArrayList;
import java.util.List;

import controllers.objetos.entidades.Bug;
import controllers.objetos.entidades.Desenvolvedor;

public abstract class Planeta {
	protected String nome;
	protected int posX;
	protected int posY;
	protected int velocidadeTranslacao;
	protected int velocidadeRotacao;
	protected Number tempoParaCompletarUmDia;
	protected int anos;
	protected int dias;
    private List<Bug> bugsColididos = new ArrayList<>();
    private List<Desenvolvedor> devsColididos = new ArrayList<>();

	public Planeta(String nome, int posX, int posY, int velocidadeTranslacao, int velocidadeRotacao,
			Number tempoParaCompletarUmDia, int anos, int dias) {
		this.nome = nome;
		this.posX = posX;
		this.posY = posY;
		this.velocidadeTranslacao = velocidadeTranslacao;
		this.velocidadeRotacao = velocidadeRotacao;
		this.tempoParaCompletarUmDia = tempoParaCompletarUmDia;
		this.anos = anos;
		this.dias = dias;
	}
	
	public abstract void mover(int instantes);

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getVelocidadeTrans() {
    	return this.velocidadeTranslacao;
    }

    public void setAnos(int anos) {
    	this.anos = anos;
    }
    
    public int getAnos() {
    	return this.anos;
    }
    
    public void setDias(int dias) {
    	this.dias = dias;
    }
    
    public int getDias() {
    	return this.dias;
    }
  
    public void adicionarBugColidido(Bug bug) {
        bugsColididos.add(bug);
    }
    
    public int getNumeroDeBugsColididos() {
        return bugsColididos.size();
    }
    
    public void adicionarDevColididos(Desenvolvedor dev) {
        devsColididos.add(dev);
    }
    
    public int getNumeroDeDevsColididos() {
        return devsColididos.size();
    }
    
    public void aumentarVelocidade() {
        this.velocidadeTranslacao++;
    }

    public void diminuirVelocidade() {
        if (this.velocidadeTranslacao > 0) {
            this.velocidadeTranslacao--;
        } else {
            // Lógica para lidar com a velocidade zero
            // Por exemplo, explodir ou realizar alguma ação específica
        }
    }    
}
