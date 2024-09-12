package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.AlunoController;
import DTO.AlunoDTO;

public class TelaMenuAluno extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;

	public TelaMenuAluno(String matricula) {
		super("Menu do Aluno", "Menu Aluno");
		this.matricula = matricula;
		adicionarBotoes();
		setVisible(true);
	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			AlunoDTO alunoDto = new AlunoDTO();
			alunoDto.setMatricula(matricula);;
			AlunoController alunoController = new AlunoController();
			alunoDto = alunoController.recuperarAlunoPelaMtricula(alunoDto);
			switch (e.getActionCommand()) {
			case "Listar Editais":
				new TelaListarEditaisAlunos(matricula);
				dispose();
				break;
			case "Sair":
				new TelaLogin();
				dispose();
				break;
			case "Editar Informações Pessoais":
				new TelaEditarInformacoesAluno(alunoDto, matricula);
				dispose();
				break;
			case "Ver Mensagens":
				JOptionPane.showMessageDialog(null,alunoDto.getMensagem() );
				//new TelaListarMrnsagens(alunoDto.getMensagem());
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
		
		// Botão ver Mensagem
		JButton btVerMensagem = new JButton("Ver Mensagens");
		btVerMensagem.setBounds(190, 240, 250, 30);
		btVerMensagem.setFont(font);
		btVerMensagem.addActionListener(ouvinte);
		add(btVerMensagem);

		// Botão de Sair
		JButton btSair = new JButton("Sair");
		btSair.setBounds(500, 350, 90, 30);
		btSair.setFont(new Font("Georgia", Font.ITALIC, 15));
		btSair.addActionListener(ouvinte);
		add(btSair);

	}
}