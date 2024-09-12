package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.AlunoController;
import DTO.AlunoDTO;
import Model.AlunoModel;
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
	private ArrayList<AlunoModel> alunos = new ArrayList<>();

	public TelaListarInscricao(EditalDeMonitoriaModel edital) {
		super("Lista de Inscrição", "Lista de Inscrição");
		this.edital = edital;
		for (DisciplinaModel d : edital.getDisciplinas()) {
			if (d != null) {
				for (InscricaoModel i : d.getInscricoes()) {
					if (i != null) {
						adicionarAluno(i.getAluno());
					}

				}
			}

		}

		adicionarBotoes();
		adicionarTabela();
		setVisible(true);
	}

	public void adicionarAluno(AlunoModel mensageiro) {
		alunos.add(mensageiro);
	}

	public void removerAluno(Mensageiro mensageiro) {
		alunos.remove(mensageiro);
	}

	public void enviarMensagem(String mensagem) {
		for (Mensageiro m : alunos) {
			m.atualizarMensagem(mensagem);

		}
		AlunoController alunoController = new AlunoController();
		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO.setAlunos(alunos);
		alunoDTO.setMensagem(mensagem);
		
		alunoController.atualizarMensagemBD(alunoDTO);
	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				dispose();
				new TelaDetalharEditalSemResultado(edital);
				break;
			case "Enviar Mensagem":
				String mensagem = JOptionPane.showInputDialog("Digite a mensagem para os Alunos!");
				if (mensagem.equals("")) {

				} else {

					enviarMensagem(mensagem);

					JOptionPane.showMessageDialog(null, "Mensagem Enviada");
				}
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
				linha[2] = edital.notaFinal(i.getNotaCRE(), i.getNotaDisciplina());
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

		JButton btMensagem = new JButton("Enviar Mensagem");
		btMensagem.setBounds(360, 350, 140, 30);
		btMensagem.addActionListener(ouvinte);
		add(btMensagem);
	}

}