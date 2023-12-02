package apresentacao.componentes;

import controllers.objetos.planetas.Planeta;
import controllers.objetos.planetas.Python;
import controllers.objetos.planetas.Ruby;
import model.post.PostJavalar;
import controllers.objetos.entidades.*;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.objetos.planetas.C;
import controllers.objetos.planetas.CPlusPlus;
import controllers.objetos.planetas.CSharp;
import controllers.objetos.planetas.InstantesPlanetas;
import controllers.objetos.planetas.Javascript;
import controllers.objetos.planetas.PHP;

public class Botoes {
	private JPanel botoesPanel;
	private Plano plano;
	private InstantesPlanetas instantesPlanetas;
	private InstanteEntidades InstanteEntidades;
	private int ultimoIdLidoInstante = 1;
	private List<Planeta> planetas;
	private FileReader fileReader;
	private String fileName;

	public Botoes(Plano plano) {
		this.planetas = plano.getPlanetas();

		this.plano = plano;
		this.instantesPlanetas = new InstantesPlanetas();
		this.InstanteEntidades = new InstanteEntidades();

		botoesPanel = new JPanel();
		botoesPanel.setLayout(new BoxLayout(botoesPanel, BoxLayout.Y_AXIS));
		botoesPanel.setBackground(Color.LIGHT_GRAY);

		Font buttonFont = new Font("Arial", Font.PLAIN, 14);
		Color buttonColor = new Color(0, 102, 204);
		Color textColor = Color.WHITE;

		JButton btnProcessar = createButton("Processar próximo instante", buttonFont, buttonColor, textColor);
		btnProcessar.addActionListener(e -> processarProximoInstante());
		botoesPanel.add(btnProcessar);

		botoesPanel.add(Box.createVerticalStrut(10));

		JButton btnLerArquivo = createButton("Ler novo arquivo de entrada", buttonFont, buttonColor, textColor);
		btnLerArquivo.addActionListener(e -> lerArquivoDeEntrada());
		botoesPanel.add(btnLerArquivo);

		botoesPanel.add(Box.createVerticalStrut(10));

		JButton btnGravarRelatorio = createButton("Gravar relatório", buttonFont, buttonColor, textColor);
		btnGravarRelatorio.addActionListener(e -> gravarRelatorio());
		botoesPanel.add(btnGravarRelatorio);

		botoesPanel.add(Box.createVerticalStrut(10));

		JButton btnLerDados = createButton("Ler dado de outros participantes", buttonFont, buttonColor, textColor);
		btnLerDados.addActionListener(
				e -> JOptionPane.showMessageDialog(null, "Ler dado de outros participantes clicado"));
		botoesPanel.add(btnLerDados);

		botoesPanel.add(Box.createVerticalStrut(10));

		JButton btnGravarSaida = createButton("Gravar arquivo de saída", buttonFont, buttonColor, textColor);
		btnGravarSaida.addActionListener(e -> JOptionPane.showMessageDialog(null, "Gravar arquivo de saída clicado"));
		botoesPanel.add(btnGravarSaida);

		botoesPanel.add(Box.createVerticalStrut(10));
	}

	private void lerArquivoDeEntrada() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./resources/instantes"));

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try {
				fileName = selectedFile.getName();
				fileReader = new FileReader(selectedFile);
			} catch (FileNotFoundException e) {
				System.out.println("entrou no error");
				e.printStackTrace();
			}

			processarArquivo(selectedFile);

		}
	}

	private void processarArquivo(File arquivoSelecionado) {
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivoSelecionado))) {
			String line;
			boolean primeiraLinha = true;
			String[] titulos = null;

			while ((line = reader.readLine()) != null) {
				if (primeiraLinha) {
					titulos = line.split(",");
					primeiraLinha = false;
					continue;
				}
				processarLinhaArquivo(titulos, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processarLinhaArquivo(String[] titulos, String linha) {
		String[] partes = linha.split(",");

		int quantidadeInstantes = Integer.parseInt(partes[0].trim());
		int quantidadeBugs = Integer.parseInt(partes[8].trim());
		int quantidadeDevs = Integer.parseInt(partes[9].trim());

		for (int i = 1; i < partes.length && i <= 8; i++) {
			if (i < titulos.length) {
				String planeta = titulos[i];
				int instante = Integer.parseInt(partes[i].trim());
				instantesPlanetas.adicionarInstante(planeta, instante, quantidadeInstantes);
			}
		}

		InstanteEntidades.adicionarInstante("Bug", quantidadeBugs, quantidadeInstantes);
		InstanteEntidades.adicionarInstante("Desenvolvedor", quantidadeDevs, quantidadeInstantes);
	}

	private void processarProximoInstante() {
		int instantePython = instantesPlanetas.getInstante("Python", ultimoIdLidoInstante);
		int instanteJavascript = instantesPlanetas.getInstante("JavaScript", ultimoIdLidoInstante);
		int instanteRuby = instantesPlanetas.getInstante("Ruby on Rails", ultimoIdLidoInstante);
		int instantePHP = instantesPlanetas.getInstante("PHP", ultimoIdLidoInstante);
		int instanteCSharp = instantesPlanetas.getInstante("C#", ultimoIdLidoInstante);
		int instanteCPlusPlus = instantesPlanetas.getInstante("C++", ultimoIdLidoInstante);
		int instanteC = instantesPlanetas.getInstante("C", ultimoIdLidoInstante);

		int quantidadeDevs = InstanteEntidades.getInstante("Desenvolvedor", ultimoIdLidoInstante);
		int quantidadeBugs = InstanteEntidades.getInstante("Bug", ultimoIdLidoInstante);

		// Mover cada planeta de acordo com o instante lido
		planetas.forEach(planeta -> {
			if (planeta instanceof Python) {
				planeta.mover(instantePython);
			} else if (planeta instanceof Javascript) {
				planeta.mover(instanteJavascript);
			} else if (planeta instanceof Ruby) {
				planeta.mover(instanteRuby);
			} else if (planeta instanceof PHP) {
				planeta.mover(instantePHP);
			} else if (planeta instanceof CSharp) {
				planeta.mover(instanteCSharp);
			} else if (planeta instanceof CPlusPlus) {
				planeta.mover(instanteCPlusPlus);
			} else if (planeta instanceof C) {
				planeta.mover(instanteC);
			}
		});

		for (int i = 0; i < quantidadeDevs; i++) {
			adicionarDesenvolvedorAleatorio();
		}

		for (int i = 0; i < quantidadeBugs; i++) {
			adicionarBugAleatorio();
		}

		plano.avancarInstante();

		ultimoIdLidoInstante++;
	}

	private void gravarRelatorio() {
		String nome = "Erick Balbino da Silva";
		String matricula = "536551";
		String nome_arquivo = fileName;
		int bug_python = 0;
		int bug_javascript = 0;
		int bug_ruby = 0;
		int bug_php = 0;
		int bug_csharp = 0;
		int bug_cmais = 0;
		int bug_c = 0;
		int dev_python = 0;
		int dev_javascript = 0;
		int dev_ruby = 0;
		int dev_php = 0;
		int dev_csharp = 0;
		int dev_cmais = 0;
		int dev_c = 0;
		int v_python = 0;
		int v_javascript = 0;
		int v_ruby = 0;
		int v_php = 0;
		int v_csharp = 0;
		int v_cmais = 0;
		int v_c = 0;
		int d_python = 0;
		int d_javascript = 0;
		int d_ruby = 0;
		int d_php = 0;
		int d_csharp = 0;
		int d_cmais = 0;
		int d_c = 0;
		int a_python = 0;
		int a_javascript = 0;
		int a_ruby = 0;
		int a_php = 0;
		int a_csharp = 0;
		int a_cmais = 0;
		int a_c = 0;
		int bug_q1 = 0;
		int bug_q2 = 0;
		int bug_q3 = 0;
		int bug_q4 = 0;
		int dev_q1 = 0;
		int dev_q2 = 0;
		int dev_q3 = 0;
		int dev_q4 = 0;
	
		
		for (Planeta planeta : planetas) {
	        String nomePlaneta = planeta.getNome();

	        // Preenchimento dos dados de acordo com o planeta
	        switch (nomePlaneta) {
	            case "Python":
	                bug_python = planeta.getNumeroDeBugsColididos();
	                dev_python = planeta.getNumeroDeDevsColididos();
	                v_python = planeta.getVelocidadeTrans();
	                d_python = planeta.getDias();
	                a_python = planeta.getAnos();
	                break;
	            case "JavaScript":
	            	bug_javascript = planeta.getNumeroDeBugsColididos();
	                dev_javascript = planeta.getNumeroDeDevsColididos();
	                v_javascript = planeta.getVelocidadeTrans();
	                d_javascript = planeta.getDias();
	                a_javascript = planeta.getAnos();
	                break;
	            case "Ruby":
	            	bug_ruby = planeta.getNumeroDeBugsColididos();
	                dev_ruby = planeta.getNumeroDeDevsColididos();
	                v_ruby = planeta.getVelocidadeTrans();
	                d_ruby = planeta.getDias();
	                a_ruby = planeta.getAnos();
	                break;
	            case "C#":
	            	bug_csharp = planeta.getNumeroDeBugsColididos();
	                dev_csharp = planeta.getNumeroDeDevsColididos();
	                v_csharp = planeta.getVelocidadeTrans();
	                d_csharp = planeta.getDias();
	                a_csharp = planeta.getAnos();
	                break;
	            case "C++":
	            	bug_cmais = planeta.getNumeroDeBugsColididos();
	                dev_cmais = planeta.getNumeroDeDevsColididos();
	                v_cmais = planeta.getVelocidadeTrans();
	                d_cmais = planeta.getDias();
	                a_cmais = planeta.getAnos();
	                break;
	            case "C":
	            	bug_c = planeta.getNumeroDeBugsColididos();
	                dev_c = planeta.getNumeroDeDevsColididos();
	                v_c = planeta.getVelocidadeTrans();
	                d_c = planeta.getDias();
	                a_c = planeta.getAnos();
	                break;
	            default:
	                break;
	        }
	    }

	    try {
	        PostJavalar.inserirRelatorioJavalar(nome, matricula, nome_arquivo, 
	                bug_python, bug_javascript, bug_ruby, bug_php, bug_csharp, bug_cmais, bug_c, 
	                dev_python, dev_javascript, dev_ruby, dev_php, dev_csharp, dev_cmais, dev_c, 
	                v_python, v_javascript, v_ruby, v_php, v_csharp, v_cmais, v_c, 
	                d_python, d_javascript, d_ruby, d_php, d_csharp, d_cmais, d_c, 
	                a_python, a_javascript, a_ruby, a_php, a_csharp, a_cmais, a_c, 
	                bug_q1, bug_q2, bug_q3, bug_q4, 
	                dev_q1, dev_q2, dev_q3, dev_q4);

	        JOptionPane.showMessageDialog(null, "Relatório gravado com sucesso!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao gravar o relatório!");
	    }
	}

	private void adicionarBugAleatorio() {
		int posX, posY;
		int maxCoordinate = 12;

		do {
			posX = (int) (Math.random() * (maxCoordinate * 2 + 1)) - maxCoordinate;
			posY = (int) (Math.random() * (maxCoordinate * 2 + 1)) - maxCoordinate;
		} while (plano.posicaoOcupada(posX, posY));

		if (plano.verificarPlaneta(posX, posY)) {
			Planeta planeta = plano.getPlaneta(posX, posY);
			planeta.diminuirVelocidade();
			if (planeta.getVelocidadeTrans() <= 0) {
				plano.removerPlaneta(planeta);
			}
		} else {
			plano.adicionarBug(posX, posY);
		}
	}

	private void adicionarDesenvolvedorAleatorio() {
		int posX, posY;
		int maxCoordinate = 12;

		do {
			posX = (int) (Math.random() * (maxCoordinate * 2 + 1)) - maxCoordinate;
			posY = (int) (Math.random() * (maxCoordinate * 2 + 1)) - maxCoordinate;
		} while (plano.posicaoOcupada(posX, posY));

		if (plano.verificarPlaneta(posX, posY)) {
			Planeta planeta = plano.getPlaneta(posX, posY);
			planeta.aumentarVelocidade();
		} else {
			plano.adicionarDesenvolvedor(posX, posY);
		}
	}

	public JPanel getBotoesPanel() {
		return botoesPanel;
	}

	private JButton createButton(String text, Font font, Color bgColor, Color textColor) {
		JButton button = new JButton(text);
		button.setFont(font);
		button.setBackground(bgColor);
		button.setForeground(textColor);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(Short.MAX_VALUE, button.getPreferredSize().height));
		button.setFocusPainted(false);
		button.setBorderPainted(false);

		return button;
	}
}
