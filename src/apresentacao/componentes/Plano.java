package apresentacao.componentes;

import javax.imageio.ImageIO;
import javax.swing.*;

import controllers.objetos.planetas.Java;
import controllers.objetos.planetas.Planeta;
import controllers.objetos.planetas.Python;
import controllers.objetos.planetas.Javascript;
import controllers.objetos.planetas.Ruby;
import controllers.objetos.planetas.PHP;
import controllers.objetos.planetas.CSharp;
import controllers.objetos.planetas.CPlusPlus;
import controllers.objetos.entidades.Bug;
import controllers.objetos.entidades.Desenvolvedor;
import controllers.objetos.planetas.C;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plano {
	private JPanel planoPanel;
	private Map<String, Image> linguagens;
	private List<Planeta> planetas;
	private List<Bug> bugs;
	private List<Desenvolvedor> desenvolvedores;

	private static final String PATH_BUG_IMAGE = "resources/images/bug.png";
	private static final String PATH_DEV_IMAGE = "resources/images/desenvolvedor.png";

	private Image bugImage;
	private Image devImage;

	public Plano() {
		linguagens = new HashMap<>();
		planetas = new ArrayList<>();
		bugs = new ArrayList<>();
		desenvolvedores = new ArrayList<>();

		planoPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				int width = getWidth();
				int height = getHeight();
				int cellSize = Math.min(width, height) / 25;

				int numQuadrados = 25;
				for (int i = 0; i < numQuadrados; i++) {
					for (int j = 0; j < numQuadrados; j++) {
						g.setColor(Color.BLACK);
						g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
					}
				}

				for (Planeta planeta : planetas) {
					int centralX = 12 * cellSize + planeta.getPosX() * cellSize;
					int centralY = 12 * cellSize - planeta.getPosY() * cellSize;

					Image linguagemImage = linguagens.get(planeta.getNome());
					if (linguagemImage != null) {
						g.drawImage(linguagemImage, centralX, centralY, cellSize, cellSize, null);
					}
				}

				desenharBugsEGerarDesenvolvedores(g, cellSize);
			}
		};

		planoPanel.setPreferredSize(new Dimension(640, 480));
		planoPanel.setBackground(Color.WHITE);

		try {
			linguagens.put("Java", ImageIO.read(new File("resources/images/java.png")));
			linguagens.put("Python", ImageIO.read(new File("resources/images/python.png")));
			linguagens.put("Javascript", ImageIO.read(new File("resources/images/javascript.png")));
			linguagens.put("Ruby", ImageIO.read(new File("resources/images/ruby.png")));
			linguagens.put("PHP", ImageIO.read(new File("resources/images/php.png")));
			linguagens.put("C#", ImageIO.read(new File("resources/images/csharp.png")));
			linguagens.put("C++", ImageIO.read(new File("resources/images/cplusplus.png")));
			linguagens.put("C", ImageIO.read(new File("resources/images/c.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bugImage = ImageIO.read(new File(PATH_BUG_IMAGE));
			devImage = ImageIO.read(new File(PATH_DEV_IMAGE));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initPlanetas();
	}

	private void initPlanetas() {
		int centroX = 0; // Coordenada X do centro
		int centroY = 0; // Coordenada Y do centro

		Planeta java = new Java();
		java.setPosX(centroX);
		java.setPosY(centroY);
		planetas.add(java);

		Planeta python = new Python(centroX, centroY + 1);
		planetas.add(python);

		Planeta javascript = new Javascript(centroX, centroY + 2);
		planetas.add(javascript);

		Planeta ruby = new Ruby(centroX, centroY + 3);
		planetas.add(ruby);

		Planeta php = new PHP(centroX, centroY + 4);
		planetas.add(php);

		Planeta csharp = new CSharp(centroX, centroY + 5);
		planetas.add(csharp);

		Planeta cplusplus = new CPlusPlus(centroX, centroY + 6);
		planetas.add(cplusplus);

		Planeta c = new C(centroX, centroY + 7);
		planetas.add(c);
	}

	public void adicionarBug(int posX, int posY) {
		if (!posicaoOcupada(posX, posY)) {
			Bug bug = new Bug(posX, posY);
			bugs.add(bug);
			planoPanel.repaint();
		}
	}

	public void adicionarDesenvolvedor(int posX, int posY) {
		if (!posicaoOcupada(posX, posY)) {
			Desenvolvedor dev = new Desenvolvedor(posX, posY);
			desenvolvedores.add(dev);
			planoPanel.repaint();
		}
	}

	private void desenharBugsEGerarDesenvolvedores(Graphics g, int cellSize) {
		for (Bug bug : bugs) {
			int centralX = 12 * cellSize + bug.getPosX() * cellSize;
			int centralY = 12 * cellSize - bug.getPosY() * cellSize;

			g.drawImage(bugImage, centralX, centralY, cellSize, cellSize, null);
		}

		for (Desenvolvedor dev : desenvolvedores) {
			int centralX = 12 * cellSize + dev.getPosX() * cellSize;
			int centralY = 12 * cellSize - dev.getPosY() * cellSize;

			g.drawImage(devImage, centralX, centralY, cellSize, cellSize, null);
		}
	}

	public void removerPlaneta(Planeta planeta) {
		planetas.remove(planeta);
	}

	public boolean posicaoOcupada(int posX, int posY) {
		for (Planeta planeta : planetas) {
			if (planeta.getPosX() == posX && planeta.getPosY() == posY) {
				return true;
			}
		}

		for (Bug bug : bugs) {
			if (bug.getPosX() == posX && bug.getPosY() == posY) {
				return true;
			}
		}

		for (Desenvolvedor dev : desenvolvedores) {
			if (dev.getPosX() == posX && dev.getPosY() == posY) {
				return true;
			}
		}

		return false;
	}

	public boolean posicaoOcupadaPlaneta(int x, int y) {
		for (Planeta planeta : planetas) {
			if (planeta.getPosX() == x && planeta.getPosY() == y) {
				return true;
			}
		}
		return false;
	}

	public boolean verificarPlaneta(int x, int y) {
		for (Planeta planeta : planetas) {
			if (planeta.getPosX() == x && planeta.getPosY() == y) {
				return true;
			}
		}
		return false;
	}

	public Planeta getPlaneta(int x, int y) {
		for (Planeta planeta : planetas) {
			if (planeta.getPosX() == x && planeta.getPosY() == y) {
				return planeta;
			}
		}
		return null;
	}

	public List<Planeta> getPlanetas() {
		return planetas;
	}

	public double getVelocidadeMediaTranslacao(Planeta planeta) {
		return planeta.getVelocidadeTrans();
	}

	public int getNumeroDeBugsColididos(Planeta planeta) {
		return planeta.getNumeroDeBugsColididos();
	}

	public void avancarInstante() {
		planoPanel.repaint();
	}

	public JPanel getPlanoPanel() {
		return planoPanel;
	}
}
