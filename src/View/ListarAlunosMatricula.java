package View;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.AlunoModel;

public class ListarAlunosMatricula implements StrategyListarAlunos {

	public DefaultTableModel ordenarLista(ArrayList<AlunoModel> alunos) {
		
		ArrayList<AlunoModel> listaAlunos = new ArrayList<AlunoModel>();

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Alunos");
		// Lista com os alunos
		for (AlunoModel aluno : alunos) {
			listaAlunos.add(aluno);
			Object[] linha = new Object[1];
			linha[0] = aluno.getMatricula();
			modelo.addRow(linha);
		}

		return modelo;
	}

}
