package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.AlunoModel;

public class TelaListarTodosAlunos extends TelaPadraoImagem {

	private ArrayList<AlunoModel> alunos;
	private ArrayList<AlunoModel> listaAlunos;
	private JTable tabela;
	private JTextField tfNome;

	public TelaListarTodosAlunos() {
		super("Listar todos os Alunos", "Todos Os Alunos");
		adicionarTabela();
		adicionarBotoes();
		adicionarLabel();
		adicionarTextFields();
		setVisible(true);
	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				new TelaMenuCoordenador();
				dispose();
				break;
			case "Filtrar":
				filtrarAlunos();
				break;
			case "Visualizar Perfil":
				int pessoaSelecionada = tabela.getSelectedRow();
				if (pessoaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Aluno!");
				} else {
					AlunoModel aluno = listaAlunos.get(pessoaSelecionada);
					//new JanelaPerfilDoAluno(aluno);
					dispose();
				}
				break;
			}
		}
	}

	// Filtrar aluno
	public void filtrarAlunos() {
		listaAlunos = new ArrayList<AlunoModel>();

		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Alunos");
		for (AlunoModel aluno : alunos) {
			if (aluno.getNome().toLowerCase().startsWith(tfNome.getText().toLowerCase())) {
				listaAlunos.add(aluno);
				Object[] linha = new Object[1];
				linha[0] = aluno.getNome();
				modelo.addRow(linha);
			}

		}
		//atualizando a tabela
		tabela.setModel(modelo);
		tabela.repaint();
	}

	public void adicionarTabela() {
		listaAlunos = new ArrayList<AlunoModel>();

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Alunos");
		//Lista com os alunos
		if (alunos != null) {
			for (AlunoModel aluno : alunos) {
				listaAlunos.add(aluno);
				Object[] linha = new Object[1];
				linha[0] = aluno.getNome();
				modelo.addRow(linha);
			}
		}

		tabela = new JTable(modelo);
		JScrollPane jsNomes = new JScrollPane(tabela);
		jsNomes.setBounds(185, 90, 295, 180);
		jsNomes.createVerticalScrollBar();
		add(jsNomes);

	}

	public void adicionarBotoes() {
		// Ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		JButton btSalvar = new JButton("Visualizar Perfil");
		btSalvar.setBounds(370, 350, 135, 30);
		btSalvar.addActionListener(ouvinte);
		add(btSalvar);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);

		JButton btFiltrar = new JButton("Filtrar");
		btFiltrar.setBounds(430, 290, 90, 30);
		btFiltrar.addActionListener(ouvinte);
		add(btFiltrar);

	}

	public void adicionarTextFields() {

		tfNome = new JTextField();
		tfNome.setBounds(310, 290, 110, 30);
		add(tfNome);

	}

	public void adicionarLabel() {
		Font font = new Font("Georgia", Font.ITALIC, 15);

		JLabel lbNome = new JLabel("Filtrar por nome:");
		lbNome.setBounds(185, 290, 125, 30);
		lbNome.setFont(font);
		add(lbNome);
	}

}