package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.EditalController;
import DTO.EditalDeMonitoriaDTO;
import Model.DisciplinaModel;
import Model.EditalDeMonitoriaModel;

public class TelaListarEditaisAlunos extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EditalController editalControler = new EditalController();
	private ArrayList<EditalDeMonitoriaModel> editais;
	private JTable tabela;
	private String matricula;

	public TelaListarEditaisAlunos(String matricula) {
		super("Listar editais", "Listar Editais");
		this.editais = editalControler.recuperarEditais(new EditalDeMonitoriaDTO()).getEditais();
		this.matricula = matricula;
		adicionarBotoes();
		adicionarTabela();
		setVisible(true);

	}

	class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				dispose();
				new TelaMenuAluno(matricula);
				break;
			case "Detalhar Edital":
				int linhaSelecionada = tabela.getSelectedRow();
				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Edital!");
				} else {
					EditalDeMonitoriaModel edital = editais.get(linhaSelecionada);
					//verifica a situaç~co do edital
					if (edital.getSituacaoDoEdital().equals("Resultado Preliminar")) {
			
						for (DisciplinaModel disciplina : edital.getDisciplinas()) {
							disciplina.calcularResultado();
						}
						EditalDeMonitoriaDTO editalDto = new EditalDeMonitoriaDTO(edital.getNumeroDoEdital(),
								edital.getQtdDeInscricaoPorAluno(), edital.getDataInicio(), edital.getDataFim(), edital.getPesoCRE(),
								edital.getPesoNota(), edital.getDisciplinas());
						editalDto.setId(edital.getId());
						editalDto.setSituacaoDoEdital(edital.getSituacaoDoEdital());
						
						editalControler.editarEdital(editalDto);
						
						// Essa tela mostra o resultado preliminar do edital
						new TelaDetalharEditalResultado("Resultado Preliminar", "Resultado Preliminar",matricula,
								edital);
						dispose();

					} else if (edital.getSituacaoDoEdital().equals("Resultado Final")) {
						// Essa tela mostra o resultado final do edital
						new TelaDetalharEditalResultado("Resultado Final", "Resultado Final", matricula, edital);
						dispose();
					} else if (edital.getSituacaoDoEdital().equals("Aberto")
							|| edital.getSituacaoDoEdital().equals("")) {
						if (edital.situacao().equals("Aberto")) {
							dispose();
							// Essa tela mostra o edital em aberto
							new TelaDetalharEditalSemResultadoAluno(edital, matricula);
						} else {
							JOptionPane.showMessageDialog(null, "Selecione um Edital Aberto!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Edital Aberto!");
					}
				}
				break;
			}
		}
	}

	private void adicionarTabela() {

		DefaultTableModel modelo = new DefaultTableModel();
		// definir as colunas
		modelo.addColumn("Numero do Edital");
		modelo.addColumn("Situação");
		// preencher lihas colunas
		if (editais != null) {
			for (EditalDeMonitoriaModel edital : editais) {
				Object[] linha = new Object[2];
				linha[0] = edital.getNumeroDoEdital();
				if (edital.getSituacaoDoEdital() == "") {
					linha[1] = edital.situacao();
				} else {
					linha[1] = edital.getSituacaoDoEdital();
				}
				modelo.addRow(linha);
			}
		}

		tabela = new JTable(modelo);
		JScrollPane jsDisciplinas = new JScrollPane(tabela);
		jsDisciplinas.setBounds(150, 130, 360, 170);
		jsDisciplinas.createVerticalScrollBar();
		add(jsDisciplinas);

	}

	public void adicionarBotoes() {
		// Ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		JButton btSalvar = new JButton("Detalhar Edital");
		btSalvar.setBounds(380, 350, 120, 30);
		btSalvar.addActionListener(ouvinte);
		add(btSalvar);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);

	}
}