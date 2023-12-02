package apresentacao.pages;

import apresentacao.componentes.Botoes;
import apresentacao.componentes.Plano;

import javax.swing.*;
import java.awt.*;

public class Interface {
    private JFrame frame;

    public void createInterface() {
        frame = new JFrame("Sistema JavaLar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Plano plano = new Plano();
        Botoes botoes = new Botoes(plano);

        frame.add(plano.getPlanoPanel(), BorderLayout.WEST);
        frame.add(botoes.getBotoesPanel(), BorderLayout.EAST);

        frame.setPreferredSize(new Dimension(900, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
