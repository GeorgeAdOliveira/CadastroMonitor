package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.DisciplinaModel;
import Model.EditalDeMonitoriaModel;
import Model.InscricaoModel;

public class TelaInscricaoNaDisciplinaDoEdital extends TelaPadraoImagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditalDeMonitoriaModel edital;
	private DisciplinaModel disciplina;
	private JTextField tfNotaCRE;
	private JTextField tfNotaDisciplina;
	private String usuario;
	
	public TelaInscricaoNaDisciplinaDoEdital(EditalDeMonitoriaModel edital,DisciplinaModel disciplina, String usuario ) {
		super("Inscrever na Disciplina", "Inscrever na Disciplina");
		this.edital = edital;
		this.disciplina = disciplina;
		this.usuario = usuario;
		adicionarLabel();
		adicionarTextFields();
		adicionarBotoes();
		setVisible(true);
	}
	
	private class OuvinteDosBotoes implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "Voltar":
				dispose();
				new TelaDetalharEditalSemResultadoAluno(edital, usuario);
				break;
			case "Inscrever":
				try {
					//verificar se as notas são validas
					float notaCRE = Float.parseFloat(tfNotaCRE.getText());
					float notaDisciplina = Float.parseFloat(tfNotaDisciplina.getText());
					if(notaCRE < 0 || notaCRE > 100 || notaDisciplina < 0 || notaDisciplina > 100) {
						JOptionPane.showMessageDialog(null, "Notas Invalidas!");
					} else {
						
				
					
					
					ArrayList<DisciplinaModel> disciplinas = edital.getDisciplinas();
					DisciplinaModel disciplinaf = new DisciplinaModel();
					for( DisciplinaModel d : disciplinas) {
						if(d.getNomeDaDisciplina().equals(disciplina)) {
							disciplinaf = d;
						}
						
					}
					//Realiza Inscrição 
					InscricaoModel inscricao = new InscricaoModel(null, disciplinaf, notaCRE, notaDisciplina, "pendente",0);
							
							
					JOptionPane.showMessageDialog(null, "Inscrição Realizada com Sucesso!");
					
					//salvando na percistencia	
					
					//new JanelaDetalharEditalSemResultadoAluno(editalP);
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
		
		JLabel lbNomeDaDisciplina = new JLabel("Nome da Disciplina: "+ disciplina.getNomeDaDisciplina());
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
		//Ouvinte interno
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();
		
		JButton btInscrever = new JButton("Inscrever"); 
		btInscrever.setBounds(380,350,120,30);
		btInscrever.addActionListener(ouvinte);
		add(btInscrever);
		
		JButton btVoltar = new JButton("Voltar"); 
		btVoltar.setBounds(520,350,90,30);
		btVoltar.addActionListener(ouvinte);
		add(btVoltar);
		
	}

}