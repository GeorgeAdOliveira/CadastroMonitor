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

import Controller.AlunoController;
import DTO.AlunoDTO;
import Model.AlunoModel;

public class TelaListarTodosAlunos extends TelaPadraoImagem {

	private StrategyListarAlunos estrategia;

	private ArrayList<AlunoModel> alunos;
	private ArrayList<AlunoModel> listaAlunos;
	private JTable tabela;
	private JTextField tfNome;

	public TelaListarTodosAlunos() {
		super("Listar todos os Alunos", "Todos Os Alunos");
		recuperarAlunos();
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

			case "Listar Por Nome":
				setEstrategia(new ListarAlunosNome());
				executeStrategy();
				break;
			case "Listar Por Matricula":
				setEstrategia(new ListarAlunosMatricula());
				executeStrategy();
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
					new TelaPerfilDoAluno(aluno);
					dispose();
				}
				break;
			}
		}
	}

	public void executeStrategy() {
		this.listaAlunos = alunos;
		if (alunos != null) {
			DefaultTableModel modelo = estrategia.ordenarLista(alunos);
			// atualizando a tabela
			tabela.setModel(modelo);
			tabela.repaint();
		}

	}

	// recuperando a lista de alunos
	public void recuperarAlunos() {
		AlunoController alunoController = new AlunoController();
		AlunoDTO alunoDto = new AlunoDTO();
		alunoDto = alunoController.verAlunos(alunoDto);
		this.alunos = alunoDto.getAlunos();
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
		// atualizando a tabela
		tabela.setModel(modelo);
		tabela.repaint();
	}

	public void adicionarTabela() {
		listaAlunos = new ArrayList<AlunoModel>();

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Alunos");
		// Lista com os alunos
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

		JButton btListarNome = new JButton("Listar Por Nome");
		btListarNome.setBounds(490, 130, 130, 30);
		btListarNome.addActionListener(ouvinte);
		add(btListarNome);

		JButton btListar = new JButton("Listar Por Matricula");
		btListar.setBounds(205, 350, 150, 30);
		btListar.addActionListener(ouvinte);
		add(btListar);

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

	public StrategyListarAlunos getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(StrategyListarAlunos estrategia) {
		this.estrategia = estrategia;
	}

	public static void main(String[] args) {
		new TelaListarTodosAlunos();
	}

}