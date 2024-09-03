package DAO;

import java.util.ArrayList;

import Model.AlunoModel;
import Model.CoordenadorModel;

public class BancoDeDados {
	
	private CoordenadorModel coordenador;
	private ArrayList<AlunoModel> alunos = new ArrayList<AlunoModel>();
	
	public ArrayList<AlunoModel> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<AlunoModel> alunos) {
		this.alunos = alunos;
	}

	public CoordenadorModel getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(CoordenadorModel coordenador) {
		this.coordenador = coordenador;
	}

}
