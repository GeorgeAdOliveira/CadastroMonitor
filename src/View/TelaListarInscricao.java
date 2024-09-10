package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.DisciplinaModel;
import Model.EditalDeMonitoriaModel;
import Model.InscricaoModel;

public class TelaListarInscricao extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditalDeMonitoriaModel edital;
	private JTable tabela;
	
	public TelaListarInscricao(EditalDeMonitoriaModel edital) {
		super("Lista de Inscrição", "Lista de Inscrição");
		this.edital = edital;

		adicionarBotoes();
		adicionarTabela();
		setVisible(true);
	}
	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				dispose();
				new TelaDetalharEditalSemResultado(edital);
				break;
			}
		}
	}

	private void adicionarTabela() {

		DefaultTableModel modelo = new DefaultTableModel();
		// definir as colunas
		modelo.addColumn("Aluno");
		modelo.addColumn("Disciplina");
		modelo.addColumn("Média Resultado");
		modelo.addColumn("Situação");

		for (DisciplinaModel disciplina : edital.getDisciplinas()) {
			for (InscricaoModel i : disciplina.getInscricoes()) {
				Object[] linha = new Object[4];
				linha[0] = i.getAluno().getNome();
				linha[1] = i.getDisciplina().getNomeDaDisciplina();
				linha[2] = edital.notaFinal(i.getNotaCRE(),i.getNotaDisciplina()); 
				linha[3] = i.getResultado();
				modelo.addRow(linha);
			}
		}

		tabela = new JTable(modelo);
		JScrollPane jsInscricoes = new JScrollPane(tabela);
		jsInscricoes.setBounds(90, 130, 470, 170);
		jsInscricoes.createVerticalScrollBar();
		add(jsInscricoes);

	}

	public void adicionarBotoes() {

		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);
	}
}