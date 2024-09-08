package View;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Model.AlunoModel;

public interface StrategyListarAlunos {
	
	public DefaultTableModel ordenarLista(ArrayList<AlunoModel> alunos);

}
