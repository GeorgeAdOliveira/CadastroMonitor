package View;

import java.awt.Color;
import javax.swing.JFrame;


public class TelaPadrao extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaPadrao(String titulo) {
		setTitle(titulo);
		setSize(650, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		Color minhaCor = new Color(229, 229, 229);
		getContentPane().setBackground(minhaCor);
	}
//	public TelaPadrao() {
//		
//		JLabel lbTitulo = new JLabel("Cadastrar Aluno");
//		lbTitulo.setBounds(70, 25, 300, 30);
//		lbTitulo.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 20));
//		lbTitulo.setHorizontalAlignment(JLabel.CENTER);
//		add(lbTitulo);
//		//setTitle(titulo);
//		
//		JLabel lTitulo = new JLabel("Todos os Alunos");
//		lTitulo.setBounds(400, 25, 300, 30);
//		lTitulo.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 20));
//		lTitulo.setHorizontalAlignment(JLabel.CENTER);
//		add(lTitulo);
//		
//		setSize(800, 450);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(null);
//		setResizable(false);
//		setLocationRelativeTo(null);
//		Color minhaCor = new Color(229, 229, 229);
//		getContentPane().setBackground(minhaCor);
//	}
}
