package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.AlunoController;
import Controller.EditalController;
import DAO.CentralDeInformacoesDAO;
import DTO.AlunoDTO;
import DTO.EditalDeMonitoriaDTO;
import Model.AlunoModel;
import Model.DisciplinaModel;
import Model.EditalDeMonitoriaModel;
import Model.InscricaoModel;

public class TelaInscricaoNaDisciplinaDoEdital extends TelaPadraoImagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditalDeMonitoriaModel edital;
	private DisciplinaModel disciplina;
	private JTextField tfNotaCRE;
	private JTextField tfNotaDisciplina;
	private String usuario;

	public TelaInscricaoNaDisciplinaDoEdital(EditalDeMonitoriaModel edital, DisciplinaModel disciplina,
			String usuario) {
		super("Inscrever na Disciplina", "Inscrever na Disciplina");
		this.edital = edital;
		this.disciplina = disciplina;
		this.usuario = usuario;
		adicionarLabel();
		adicionarTextFields();
		adicionarBotoes();
		setVisible(true);
	}

	private class OuvinteDosBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Voltar":
				dispose();
				new TelaDetalharEditalSemResultadoAluno(edital, usuario);
				break;
			case "Inscrever":
				try {
					// verificar se as notas são validas
					float notaCRE = Float.parseFloat(tfNotaCRE.getText());
					float notaDisciplina = Float.parseFloat(tfNotaDisciplina.getText());
					if (notaCRE < 0 || notaCRE > 100 || notaDisciplina < 0 || notaDisciplina > 100) {
						JOptionPane.showMessageDialog(null, "Notas Invalidas!");
					} else {

						ArrayList<DisciplinaModel> disciplinas = edital.getDisciplinas();
						DisciplinaModel disciplinaf = new DisciplinaModel();
						for (DisciplinaModel d : disciplinas) {
							if (d.getNomeDaDisciplina().equals(disciplina.getNomeDaDisciplina())) {
								disciplinaf = d;
							}
						}
						// Realiza Inscrição
						AlunoDTO alunoDTO = new AlunoDTO();
						alunoDTO.setMatricula(usuario);
						AlunoController alunoController = new AlunoController();

						alunoDTO = alunoController.recuperarAlunoPelaMtricula(alunoDTO);

						AlunoModel aluno = new AlunoModel();
						aluno.setNome(alunoDTO.getNome());
						aluno.setMatricula(alunoDTO.getMatricula());
						aluno.setEmail(alunoDTO.getEmail());
						aluno.setSenha(alunoDTO.getSenha());
						aluno.setSexo(alunoDTO.getSexo());
						aluno.setMensagem(alunoDTO.getMensagem());

						InscricaoModel inscricao = new InscricaoModel(aluno, disciplinaf, notaCRE, notaDisciplina,
								"pendente", 0);
						disciplinaf.getInscricoes().add(inscricao);
						edital.setDisciplinas(disciplinas);

						EditalController editalController = new EditalController();
						EditalDeMonitoriaDTO editalMonitoriaDTO = new EditalDeMonitoriaDTO();
						editalMonitoriaDTO.setId(edital.getId());
						editalMonitoriaDTO.setNumeroDoEdital(edital.getNumeroDoEdital());
						editalMonitoriaDTO.setQtdDeInscricaoPorAluno(edital.getQtdDeInscricaoPorAluno());
						editalMonitoriaDTO.setSituacaoDoEdital(edital.getSituacaoDoEdital());
						editalMonitoriaDTO.setDataInicio(edital.getDataInicio());
						editalMonitoriaDTO.setDataFim(edital.getDataFim());
						editalMonitoriaDTO.setPesoCRE(edital.getPesoCRE());
						editalMonitoriaDTO.setPesoNota(edital.getPesoNota());
						editalMonitoriaDTO.setDisciplinas(edital.getDisciplinas());
						editalController.editarEdital(editalMonitoriaDTO);
						JOptionPane.showMessageDialog(null, "Inscrição Realizada com Sucesso!");

						new TelaDetalharEditalSemResultadoAluno(edital, usuario);
						dispose();
						break;
					}

				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Digite uma nota Valida!");
				}
			}
		}
	}

	public void adicionarTextFields() {

		tfNotaCRE = new JTextField();
		tfNotaCRE.setBounds(305, 170, 50, 30);
		add(tfNotaCRE);

		tfNotaDisciplina = new JTextField();
		tfNotaDisciplina.setBounds(305, 200, 50, 30);
		add(tfNotaDisciplina);

	}

	private void adicionarLabel() {
		Font font = new Font("Georgia", Font.ITALIC, 15);

		JLabel lbNomeDaDisciplina = new JLabel("Nome da Disciplina: " + disciplina.getNomeDaDisciplina());
		lbNomeDaDisciplina.setBounds(190, 110, 400, 30);
		lbNomeDaDisciplina.setFont(font);
		add(lbNomeDaDisciplina);

		JLabel lbAjuda = new JLabel("*Digite o Valor de 0 a 100 Respectivamente");
		lbAjuda.setBounds(190, 140, 300, 30);
		lbAjuda.setFont(font);
		add(lbAjuda);

		JLabel lbNotaCRE = new JLabel("Digite seu CRE: ");
		lbNotaCRE.setBounds(190, 170, 115, 30);
		lbNotaCRE.setFont(font);
		add(lbNotaCRE);

		JLabel lbNota = new JLabel("Digite sua Nota: ");
		lbNota.setBounds(190, 200, 115, 30);
		lbNota.setFont(font);
		add(lbNota);

	}

	public void adicionarBotoes() {
		// Ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();

		JButton btInscrever = new JButton("Inscrever");
		btInscrever.setBounds(380, 350, 120, 30);
		btInscrever.addActionListener(ouvinte);
		add(btInscrever);

		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(520, 350, 90, 30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);

	}

}