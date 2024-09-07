package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.AlunoController;
import Controller.CoordenadorController;
import DTO.AlunoDTO;
import DTO.CoordenadorDTO;

public class TelaLogin extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfEmail;
	private JPasswordField pfSenha;

	public TelaLogin() {
		super("Fazer Login", "Fazer Login");

		adicionarLabel();
		adicionarTextFields();
		adicionarBotoes();
		setVisible(true);

	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// dados dos campos da tela
			String email = tfEmail.getText();
			String senha = new String(pfSenha.getPassword());
			// coordenador para fazer a verificação
			CoordenadorDTO coordenadorDto = new CoordenadorDTO();
			coordenadorDto.setEmail(email);
			coordenadorDto.setSenha(senha);
			CoordenadorController coordenadorController = new CoordenadorController();
			// aluno para fazer a verificação
			AlunoDTO alunoDto = new AlunoDTO();
			alunoDto.setEmail(email);
			alunoDto.setSenha(senha);
			AlunoController alunoController = new AlunoController();

			switch (e.getActionCommand()) {
			case "Entrar":
				// Verificar se o usuário é o coordenador
				if (coordenadorController.loginCoordenador(coordenadorDto)) {
					new TelaMenuCoordenador();
					dispose();
					break;
					// Verificar se o usuário é aluno
				} else if (alunoController.loginAluno(alunoDto)) {
					new TelaMenuAluno();
					dispose();
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Usuario Não encontrado!");
					break;
				}

				// recuperar senha
			case "Recuperar senha":
				JOptionPane.showMessageDialog(null, "Falta fazer a logica desse botão!");
//					new JanelaRecuperarSenha();
//					dispose();
				break;
			// nova conta
			case "Nova Conta":
				new TelaCadastroAluno();
				dispose();
				break;
			}
		}
	}

	public void adicionarBotoes() {
		// Ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		JButton btSalvar = new JButton("Entrar");
		btSalvar.setBounds(400, 330, 100, 30);
		btSalvar.addActionListener(ouvinte);
		add(btSalvar);

		JButton btRecuperarSenha = new JButton("Recuperar senha");
		btRecuperarSenha.setBounds(220, 205, 140, 20);
		btRecuperarSenha.addActionListener(ouvinte);
		add(btRecuperarSenha);

		JButton btNovaConta = new JButton("Nova Conta");
		btNovaConta.setBounds(220, 230, 100, 20);
		btNovaConta.addActionListener(ouvinte);
		add(btNovaConta);

	}

	public void adicionarTextFields() {
		tfEmail = new JTextField();
		tfEmail.setBounds(220, 120, 270, 25);
		add(tfEmail);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(220, 160, 270, 25);
		add(pfSenha);

	}

	public void adicionarLabel() {
		Font font = new Font("Georgia", Font.ITALIC, 15);

		JLabel lbEmail = new JLabel("E-mail: ");
		lbEmail.setBounds(160, 120, 100, 30);
		lbEmail.setFont(font);
		add(lbEmail);

		JLabel lbSenha = new JLabel("Senha: ");
		lbSenha.setBounds(158, 160, 100, 30);
		lbSenha.setFont(font);
		add(lbSenha);
	}

}