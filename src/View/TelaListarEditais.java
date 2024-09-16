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
import Model.InscricaoModel;

public class TelaListarEditais extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EditalController editalControler = new EditalController();

	private ArrayList<EditalDeMonitoriaModel> editais;
	private JTable tabela;

	public TelaListarEditais() {
		super("Listar editais", "Listar Editais");
		this.editais = editalControler.recuperarEditais(new EditalDeMonitoriaDTO()).getEditais();
		adicionarBotoes();
		adicionarTabela();
		setVisible(true);
	}

	class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "Voltar":
				dispose();
				new TelaMenuCoordenador();
				break;
			// Aqui ele verifica a situação do edital e execulta a determinada
			case "Detalhar":
				int editalSelecionado = tabela.getSelectedRow();
				if (editalSelecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Edital!");
				} else {
					EditalDeMonitoriaModel edital = editais.get(editalSelecionado);
					if (edital.getSituacaoDoEdital().equals("Resultado Preliminar")) {

						// Calcula o resultado do edital
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
						new TelaDetalharEditalResultado("Resultado Preliminar",
								"Resultado Preliminar", "coordenador",edital);
						dispose();
						// reabrir o edital encerrado
					} else if (edital.getSituacaoDoEdital().equals("Encerrado")) {

						int op = JOptionPane.showConfirmDialog(null, "Deseja reabrir o edital?", "Reabrir",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (op == JOptionPane.YES_OPTION) {
							EditalDeMonitoriaDTO editalDto = new EditalDeMonitoriaDTO(edital.getNumeroDoEdital(),
									edital.getQtdDeInscricaoPorAluno(), edital.getDataInicio(), edital.getDataFim(), edital.getPesoCRE(),
									edital.getPesoNota(), edital.getDisciplinas());
							editalDto.setId(edital.getId());
							editalDto.setSituacaoDoEdital("Aberto");
							
							editalControler.editarEdital(editalDto);
							new TelaListarEditais();
							dispose();
						}

					} else if (edital.getSituacaoDoEdital().equals("Resultado Final")) {
						// Essa tela mostra o resultado final do edital
						new TelaDetalharEditalResultado("Resultado Final", "Resultado Final", "coordenador", edital);
						dispose();
					} else if (edital.getSituacaoDoEdital().equals("Aberto")
							|| edital.getSituacaoDoEdital().equals("")) {
						if (edital.situacao().equals("Aberto")) {				
							// Essa tela mostra o edital aberto
							new TelaDetalharEditalSemResultado(edital);
							dispose();
						} else {
							String[] dataInicio = edital.getDataInicio().toString().split("-");
							String dataDeInicio = dataInicio[2] + "/" + dataInicio[1] + "/" + dataInicio[0];
							// ele so entra aqui se o edital tiver Fechado
							int op = JOptionPane.showConfirmDialog(null,
									"Edital Fechado so abre dia: " + dataDeInicio + "\n"
											+ "Deseja editar a data do Edital?",
									"Reabrir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (op == JOptionPane.YES_OPTION) {
								// abre a tela de editar para editar a data
								new TelaCadastrarEdital(edital, "Editar Edital de Monitoria");
								dispose();
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Edital Aberto!");
					}
				}
				break;
			case "Gerar Resultado":
				editalSelecionado = tabela.getSelectedRow();
				if (editalSelecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Edital!");
				} else {
					EditalDeMonitoriaModel edital = editais.get(editalSelecionado);
					edital.setSituacaoDoEdital("Resultado Preliminar");
					//Calculando a nota final de cada disciplina
					for (DisciplinaModel disciplina : edital.getDisciplinas()) {
						for (InscricaoModel i : disciplina.getInscricoes()) {
							i.setNotaFinal(edital.notaFinal(i.getNotaCRE(), i.getNotaDisciplina()));
						}

					}
					//calculando o resultado
					for (DisciplinaModel disciplina : edital.getDisciplinas()) {
						disciplina.calcularResultado();
					}

					EditalDeMonitoriaDTO editalDto = new EditalDeMonitoriaDTO(edital.getNumeroDoEdital(),
							edital.getQtdDeInscricaoPorAluno(), edital.getDataInicio(), edital.getDataFim(), edital.getPesoCRE(),
							edital.getPesoNota(), edital.getDisciplinas());
					editalDto.setId(edital.getId());
					editalDto.setSituacaoDoEdital(edital.getSituacaoDoEdital());
					
					editalControler.editarEdital(editalDto);
					new TelaListarEditais();
					dispose();
				}
				break;
			}
	}

	}

	// Listar os editais na tabela
	public void adicionarTabela() {

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Editais");
		modelo.addColumn("Situação");

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

		JButton btSalvar = new JButton("Detalhar");
		btSalvar.setBounds(415, 350, 90, 30);
		btSalvar.addActionListener(ouvinte);
		add(btSalvar);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);

		JButton btGerarResultado = new JButton("Gerar Resultado");
		btGerarResultado.setBounds(270, 350, 130, 30);
		btGerarResultado.addActionListener(ouvinte);
		add(btGerarResultado);

	}
}