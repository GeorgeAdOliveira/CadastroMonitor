package DAO;

import java.util.ArrayList;

import Model.AlunoModel;

public class BancoDeDados {
	
	private ArrayList<AlunoModel> alunos = new ArrayList<AlunoModel>();

	public ArrayList<AlunoModel> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<AlunoModel> alunos) {
		this.alunos = alunos;
	}

}
