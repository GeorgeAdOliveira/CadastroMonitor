package DAO;

import java.util.ArrayList;

import Model.AlunoModel;
import Model.CoordenadorModel;
import Model.EditalDeMonitoriaModel;

public class BancoDeDados {
	
	private CoordenadorModel coordenador;
	private ArrayList<AlunoModel> alunos = new ArrayList<AlunoModel>();
	private ArrayList<EditalDeMonitoriaModel> editais = new ArrayList<EditalDeMonitoriaModel>();
	
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

	public ArrayList<EditalDeMonitoriaModel> getEditais() {
		return editais;
	}

	public void setEditais(ArrayList<EditalDeMonitoriaModel> editais) {
		this.editais = editais;
	}

}
