package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TelaMenuAluno extends TelaPadraoImagem {
	public TelaMenuAluno() {
		super("Menu do Aluno", "Menu Aluno");
		adicionarBotoes();
		setVisible(true);
	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Listar Editais":
//				new JanelaListarEditaisAlunos();
				dispose();
				break;
			case "Sair":
				new TelaLogin();
				dispose();
				break;
			case "Editar Informações Pessoais":
//				new JanelaEditarInformacoesAluno(Persistencia.getInstance().recuperar().getAlunoLogado(), "Aluno");
				dispose();
				break;
			}
		}
	}

	public void adicionarBotoes() {
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		Font font = new Font("Georgia", Font.ITALIC, 15);

		// Botão de Listar Editais
		JButton btListarEditais = new JButton("Listar Editais");
		btListarEditais.setBounds(190, 120, 250, 30);
		btListarEditais.setFont(font);
		btListarEditais.addActionListener(ouvinte);
		add(btListarEditais);
		
		// Botão de editar informações pessoais
		JButton btEditarInformacoes = new JButton("Editar Informações Pessoais"); 
		btEditarInformacoes.setBounds(190, 180, 250, 30);
		btEditarInformacoes.setFont(font);
		btEditarInformacoes.addActionListener(ouvinte);
		add(btEditarInformacoes);

		// Botão de Sair
		JButton btSair = new JButton("Sair");
		btSair.setBounds(500, 350, 90, 30);
		btSair.setFont(new Font("Georgia", Font.ITALIC, 15));
		btSair.addActionListener(ouvinte);
		add(btSair);

	}
	

}