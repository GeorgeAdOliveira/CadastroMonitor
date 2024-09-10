package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TelaMenuCoordenador extends TelaPadraoImagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelaMenuCoordenador() {
		super("Menu do coordenador", "Menu Coordenador");
		adicionarBotoes();
		setVisible(true);
		repaint();
	}
		
		private class OuvinteDosBotoes implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {
				case "Cadastrar Edital de Monitoria":
					new TelaCadastrarEdital(null,"Novo edital de Monitoria");
					dispose();
					break;
				case "Listar Editais":
					new TelaListarEditais();
					dispose();
					break;
				case "Listar Todos os Alunos":
					new TelaListarTodosAlunos();
					dispose();
					break;
				case "Sair":
					new TelaLogin();
					dispose();
					break;
				}
			}
		}

		public void adicionarBotoes() {
			OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

			// Botão de Cadastrar Edital de Monitoria
			JButton btCadastrarEdital = new JButton("Cadastrar Edital de Monitoria");
			btCadastrarEdital.setBounds(190, 120, 250, 30);
			btCadastrarEdital.setFont(new Font("Georgia", Font.ITALIC, 15));
			btCadastrarEdital.addActionListener(ouvinte);
			add(btCadastrarEdital);

			// Botão de Listar Editais
			JButton btListarEditais = new JButton("Listar Editais");
			btListarEditais.setBounds(190, 170, 250, 30);
			btListarEditais.setFont(new Font("Georgia", Font.ITALIC, 15));
			btListarEditais.addActionListener(ouvinte);
			add(btListarEditais);
			
			// Botão de Listar todos os Alunos
			JButton btListarAlunos = new JButton("Listar Todos os Alunos"); 
			btListarAlunos.setBounds(190, 220, 250, 30);
			btListarAlunos.setFont(new Font("Georgia", Font.ITALIC, 15));
			btListarAlunos.addActionListener(ouvinte);
			add(btListarAlunos);

			// Botão de Sair
			JButton btSair = new JButton("Sair");
			btSair.setBounds(500, 350, 90, 30);
			btSair.setFont(new Font("Georgia", Font.ITALIC, 15));
			btSair.addActionListener(ouvinte);
			add(btSair);
		
	}
		public static void main(String[] args) {
			new TelaMenuCoordenador();
		}

}
