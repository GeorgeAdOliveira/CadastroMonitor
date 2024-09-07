package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import DTO.AlunoDTO;

public class TelaEditarInformacoesAluno extends TelaCadastroAluno {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AlunoDTO aluno;
	private String usuario;

	public TelaEditarInformacoesAluno(){//AlunoDTO aluno, String usuario) {
		super();//"Editar Informações Pessoais", "Editar Informações");
		this.aluno = aluno;
		this.usuario = usuario;
//		adicionarTextFields(aluno.getNome(), aluno.getMatricula(), aluno.getEmail(), aluno.getSenha());
//		adicionarCombo(aluno.getSexo());
		adicionarBotoes();
		setVisible(true);

		
	}
	private class OuvinteDoBotaoSalvar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				//verifica quem é o usuario e abre a tela corespondente
				if(usuario.equals("Aluno")) {
					  new TelaMenuAluno();
                }
                dispose();
                break;
			case "Salvar":
				try {
					 String nome = getTfNome().getText();
                     String matricula = getTfMatricula().getText();
                     String email = getTfEmail().getText();
                     String senha = new String(getPfSenha().getPassword());
                     String confirmarSenha = new String(getPfConfirmarSenha().getPassword());
                     String sexo = (String) getCbSexo().getSelectedItem();
                     
                     aluno.setNome(nome);
                     aluno.setMatricula(matricula);
                     aluno.setEmail(email);
                     aluno.setSenha(senha);
                     aluno.setSexo(sexo);
                   //  boolean sucesso = alunoController.

//					String email = getTfEmail().getText();
//					String senha = new String(getPfSenha().getPassword());
//					String confirmarSenha = new String(getPfConfirmarSenha().getPassword());
//					// validações
//					Validacao validacao = new Validacao();
//					validacao.validarEmail(email);
//					validacao.validarSenha(senha, confirmarSenha);
//
//					CentralDeInformacoes central = Persistencia.getInstance().recuperar();
//					Aluno alunoEditar = central.recuperarAlunoPorEmail(aluno.getEmail());
//					// fazendo as alterações
//					alunoEditar.setNome(getTfNome().getText());
//					alunoEditar.setMatricula(getTfMatricula().getText());
//					alunoEditar.setEmail(email);
//					alunoEditar.setSenha(senha);
//					alunoEditar.setSexo((Sexo) getCbSexo().getSelectedItem());
//
//					Persistencia.getInstance().salvar(central);
//					JOptionPane.showMessageDialog(null, "As Informações foram Atualizadas!");
//					//Vai definir Qual Janela Abrir
					if(usuario.equals("Aluno")) {
						new TelaMenuAluno();
					} //else {
//						new JanelaPerfilDoAluno(alunoEditar);
//					}
					dispose();
					break;
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage());

				}
			}
		}

	public void adicionarBotoes() {
		// ouvinte interno
		OuvinteDoBotaoSalvar ouvinte = new OuvinteDoBotaoSalvar();
		setBtSalvar(new JButton("Salvar"));
		getBtSalvar().setBounds(410, 350, 90, 30);
		getBtSalvar().addActionListener(ouvinte);
		getBtSalvar().setEnabled(false);
		add(getBtSalvar());

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);

	}
	}
	public static void main(String[] args) {
		new TelaEditarInformacoesAluno();
		
	}

}