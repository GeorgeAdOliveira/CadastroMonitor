package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.AlunoController;
import DTO.AlunoDTO;
import Model.AlunoModel;
import Model.Sexo;

public class TelaCadastroAluno extends TelaPadrao {

	private static final long serialVersionUID = 1L;
	private JTextField tfNome;
	private JTextField tfMatricula;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmarSenha;
	private JComboBox<Sexo> cbSexo;
	private JTable tabela;
	private JButton btCadastrar;
	private JButton btDeletar;
	private JButton btEditar;

	public TelaCadastroAluno() {
		super();

		adicionarLabel();
		adicionarBotoes();
		adicionarCombo(null);
		adicionarTextFields();
		adicionarTabela();
		setVisible(true);

	}

	private class OuvinteDosBotoes implements ActionListener {
		public void actionPerformed(ActionEvent cliqueCadastrar) {
			AlunoDTO alunoDto;
			AlunoController controler = new AlunoController();
			int pessoaSelecionada = tabela.getSelectedRow();
			switch (cliqueCadastrar.getActionCommand()) {
			case "Cadastrar":
				try {
					String email = tfEmail.getText();
					String senha = new String(pfSenha.getPassword());
					String confirmarSenha = new String(pfConfirmarSenha.getPassword());
					if (senha.equals(confirmarSenha)) {
						alunoDto = new AlunoDTO(tfNome.getText(), tfMatricula.getText(), email,
								cbSexo.getSelectedItem().toString(), senha);
						if (controler.criarAluno(alunoDto)) {
							JOptionPane.showMessageDialog(null, "Aluno adicionado!");
							new TelaCadastroAluno();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possivel adicionar! o Aluno ja existe no sistema!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Senhas divergentes!");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				}
				break;
			case "Deletar":
				if (pessoaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Aluno!");
				} else {
					if (JOptionPane.showConfirmDialog(null, "Deseja deletar", "Pense bem",
							JOptionPane.YES_NO_OPTION) == 0) {
						alunoDto = new AlunoDTO();
						alunoDto.setIndiceLista(pessoaSelecionada);
						if (controler.apagarAluno(alunoDto)) {
							new TelaCadastroAluno();
							dispose();
							JOptionPane.showMessageDialog(null, "Aluno deletado!");
						}
					}

				}
				break;
			case "Editar":
				if (pessoaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Aluno para editar!");
				} else {
					String email = tfEmail.getText();
					String senha = new String(pfSenha.getPassword());
					alunoDto = new AlunoDTO(tfNome.getText(), tfMatricula.getText(), email,
							cbSexo.getSelectedItem().toString(), senha);
					alunoDto.setIndiceLista(pessoaSelecionada);
					if (controler.editarAluno(alunoDto)) {
						new TelaCadastroAluno();
						dispose();
						JOptionPane.showMessageDialog(null, "Aluno editado!");
					} else {
						JOptionPane.showMessageDialog(null, "nâo foi possivel editar!");
   
					}
				}

				break;
			}
		}
	}

// So permite ser digitado letra
	public class OuvinteDeTecladoDoCampoNome implements KeyListener {
		public void keyTyped(KeyEvent e) {
			char letra = e.getKeyChar();
			if (Character.isDigit(letra))
				e.consume();
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
	}

// So perminte ser digitado numero
	public class OuvinteDeTecladoDoCampoMatricula implements KeyListener {
		public void keyTyped(KeyEvent e) {
			char letra = e.getKeyChar();
			if (!Character.isDigit(letra))
				e.consume();
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
	}

// Ouvinte para verificar se os campos estão vazios
	public class OuvinteDosCampos implements FocusListener {

		public void focusGained(FocusEvent e) {
			if (!tfNome.getText().equals("") && !tfMatricula.getText().equals("") && !tfEmail.getText().equals("")
					&& !new String(pfSenha.getPassword()).equals("")) {
				btCadastrar.setEnabled(true);
			} else {
				btCadastrar.setEnabled(false);
			}
		}

		// Verificando se os campos estão vazios quando sai dele
		public void focusLost(FocusEvent e) {
			if (!tfNome.getText().equals("") && !tfMatricula.getText().equals("") && !tfEmail.getText().equals("")
					&& !new String(pfSenha.getPassword()).equals("")) {
				btCadastrar.setEnabled(true);
			} else {
				btCadastrar.setEnabled(false);
			}
		}

	}

	public void adicionarBotoes() {
		// ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();
		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(150, 350, 100, 30);
		btCadastrar.addActionListener(ouvinte);
		btCadastrar.setEnabled(false);
		add(btCadastrar);

		JButton btDeletar = new JButton("Deletar");
		btDeletar.setBounds(460, 350, 100, 30);
		btDeletar.addActionListener(ouvinte);
		add(btDeletar);

		JButton btEditar = new JButton("Editar");
		btEditar.setBounds(570, 350, 100, 30);
		btEditar.addActionListener(ouvinte);
		add(btEditar);

	}

	public void adicionarCombo(Sexo sexo) {

		Sexo[] sexos = { Sexo.MASCULINO, Sexo.FEMININO, Sexo.OUTRO };
		cbSexo = new JComboBox<Sexo>(sexos);
		cbSexo.setBounds(110, 205, 120, 20);
		if (sexo != null) {
			cbSexo.setSelectedItem(sexo);
		}
		add(cbSexo);

	}

	private void adicionarTabela() {

		AlunoDTO alunoDto = new AlunoDTO();
		AlunoController controler = new AlunoController();
		ArrayList<AlunoModel> alunos = controler.verAlunos(alunoDto).getAlunos();

		DefaultTableModel modelo = new DefaultTableModel();

		// definir as colunas
		modelo.addColumn("Alunos cadastrados");
		modelo.addColumn("Matricula");

		// Lista com os alunos
		if (alunos != null) {
			for (AlunoModel aluno : alunos) {
				Object[] linha = new Object[2];
				linha[0] = aluno.getNome();
				linha[1] = aluno.getMatricula();
				modelo.addRow(linha);
			}
		}

		tabela = new JTable(modelo);
		JScrollPane jsAlunos = new JScrollPane(tabela);
		jsAlunos.setBounds(385, 70, 350, 260);
		jsAlunos.createVerticalScrollBar();
		add(jsAlunos);
	}

	public void adicionarLabel() {
		Font font = new Font("Georgia", Font.ITALIC, 15);

		JLabel lbNome = new JLabel("Nome: ");
		lbNome.setBounds(45, 80, 100, 30);
		lbNome.setFont(font);
		add(lbNome);

		JLabel lbMatricula = new JLabel("Matricula: ");
		lbMatricula.setBounds(45, 120, 100, 30);
		lbMatricula.setFont(font);
		add(lbMatricula);

		JLabel lbEmail = new JLabel("E-mail: ");
		lbEmail.setFont(font);
		lbEmail.setBounds(45, 160, 100, 30);
		add(lbEmail);

		JLabel lbSexo = new JLabel("Sexo: ");
		lbSexo.setBounds(45, 200, 105, 30);
		lbSexo.setFont(font);
		add(lbSexo);

		JLabel lbSenha = new JLabel("Senha: ");
		lbSenha.setBounds(110, 268, 105, 30);
		lbSenha.setFont(font);
		add(lbSenha);

		JLabel lbConfirmarSenha = new JLabel("Confirmar Senha: ");
		lbConfirmarSenha.setBounds(220, 268, 200, 30);
		lbConfirmarSenha.setFont(font);
		add(lbConfirmarSenha);
	}

	public void adicionarTextFields() {
		OuvinteDosCampos ouvinte = new OuvinteDosCampos();

		tfNome = new JTextField();
		OuvinteDeTecladoDoCampoNome ouvinteNome = new OuvinteDeTecladoDoCampoNome();
		tfNome.setBounds(100, 80, 270, 25);
		tfNome.addFocusListener(ouvinte);
		tfNome.addKeyListener(ouvinteNome);
		add(tfNome);

		tfMatricula = new JTextField();
		OuvinteDeTecladoDoCampoMatricula ouvinteMatricula = new OuvinteDeTecladoDoCampoMatricula();
		tfMatricula.setBounds(120, 120, 140, 25);
		tfMatricula.addFocusListener(ouvinte);
		tfMatricula.addKeyListener(ouvinteMatricula);
		add(tfMatricula);

		tfEmail = new JTextField();
		tfEmail.setBounds(100, 160, 270, 25);
		tfEmail.addFocusListener(ouvinte);
		add(tfEmail);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(60, 295, 140, 25);
		pfSenha.addFocusListener(ouvinte);
		add(pfSenha);

		pfConfirmarSenha = new JPasswordField();
		pfConfirmarSenha.setBounds(210, 295, 140, 25);
		add(pfConfirmarSenha);

	}

	public JTextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(JTextField tfNome) {
		this.tfNome = tfNome;
	}

	public JTextField getTfMatricula() {
		return tfMatricula;
	}

	public void setTfMatricula(JTextField tfMatricula) {
		this.tfMatricula = tfMatricula;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JPasswordField getPfSenha() {
		return pfSenha;
	}

	public void setPfSenha(JPasswordField pfSenha) {
		this.pfSenha = pfSenha;
	}

	public JPasswordField getPfConfirmarSenha() {
		return pfConfirmarSenha;
	}

	public void setPfConfirmarSenha(JPasswordField pfConfirmarSenha) {
		this.pfConfirmarSenha = pfConfirmarSenha;
	}

	public JComboBox<Sexo> getCbSexo() {
		return cbSexo;
	}

	public void setCbSexo(JComboBox<Sexo> cbSexo) {
		this.cbSexo = cbSexo;
	}

	public JButton getBtSalvar() {
		return btCadastrar;
	}

	public void setBtSalvar(JButton btSalvar) {
		this.btCadastrar = btSalvar;
	}

	public JButton getBtDeletar() {
		return btDeletar;
	}

	public void setBtDeletar(JButton btDeletar) {
		this.btDeletar = btDeletar;
	}

	public JButton getBtEditar() {
		return btEditar;
	}

	public void setBtEditar(JButton btEditar) {
		this.btEditar = btEditar;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

}
