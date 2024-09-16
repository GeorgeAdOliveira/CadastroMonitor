package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Controller.AlunoController;
import DTO.AlunoDTO;
import Model.AlunoModel;
import Model.Sexo;

public class TelaEditarInformacoesAluno extends TelaPadraoInformacoesAluno {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AlunoDTO aluno;
	private String usuario;

	public TelaEditarInformacoesAluno(AlunoDTO aluno, String usuario) {
		super("Editar Informações Pessoais", "Editar Informações");
		this.aluno = aluno;
		this.usuario = usuario;
		adicionarTextFields(aluno.getNome(), aluno.getMatricula(), aluno.getEmail(), aluno.getSenha());
		if (aluno.getSexo().equals("MASCULINO")) {
			adicionarCombo(Sexo.MASCULINO);
		} else if (aluno.getSexo().equals("FEMININO")) {
			adicionarCombo(Sexo.FEMININO);
		} else {
			adicionarCombo(Sexo.OUTRO);
		}
		setVisible(true);

		
	}

	private class OuvinteDoBotaoSalvar2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				// verifica quem é o usuario e abre a tela corespondente
				if (usuario.equals("Coordenador")) {
					new TelaMenuCoordenador();
				} else {
					new TelaMenuAluno(usuario);
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
					Sexo sexo = (Sexo) getCbSexo().getSelectedItem();

					AlunoController alunoController = new AlunoController();
			
					aluno.setNome(nome);
					aluno.setMatricula(matricula);
					aluno.setEmail(email);
					aluno.setSenha(senha);
					
					if (sexo.equals(Sexo.MASCULINO)) {
						aluno.setSexo("MASCULINO");
					} else if (sexo.equals(Sexo.FEMININO)) {
						aluno.setSexo("FEMININO");
					} else {
						aluno.setSexo("OUTRO");
					}
					
					
					if (alunoController.editarAluno(aluno)) {
						JOptionPane.showMessageDialog(null, "Alterações Salvas!");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possivel salvar!");
					}

					if (usuario.equals("Coordenador")) {
						AlunoModel aluno1 = new AlunoModel();
						aluno1.setNome(aluno.getNome());
						aluno1.setMatricula(aluno.getMatricula());
						aluno1.setEmail(aluno.getEmail());
						aluno1.setSenha(aluno.getSenha());
						
						aluno1.setSexo(aluno.getSexo());
						aluno1.setMensagem(aluno.getMensagem());
						new TelaPerfilDoAluno(aluno1);
					} else {
						new TelaMenuAluno(matricula);

					}
					dispose();
					break;
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage());

				}
			}
		}
	}

	public void adicionarBotoes() {
		// ouvinte interno
		OuvinteDoBotaoSalvar2 ouvinte = new OuvinteDoBotaoSalvar2();
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