package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.DisciplinaModel;
import Model.EditalDeMonitoriaModel;

public class EditalDeMonitoriaDTO {

	private long id = System.currentTimeMillis();
	private String numeroDoEdital;
	private String qtdDeInscricaoPorAluno;
	private String situacaoDoEdital;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private float pesoCRE;
	private float pesoNota;
	private ArrayList<DisciplinaModel> disciplinas;
	private boolean editalExiste;
	private ArrayList<EditalDeMonitoriaModel> editais;
	private EditalDeMonitoriaModel editalDeMonitoriaModel;

	public EditalDeMonitoriaDTO() {

	}

	public EditalDeMonitoriaDTO(String numeroDoEdital, String qtdDeInscricaoPorAluno, LocalDate dataInicio,
			LocalDate dataFim, float pesoCRE, float pesoNota, ArrayList<DisciplinaModel> disciplinas) {
		this.numeroDoEdital = numeroDoEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.qtdDeInscricaoPorAluno = qtdDeInscricaoPorAluno;
		this.pesoCRE = pesoCRE;
		this.pesoNota = pesoNota;
		this.disciplinas = disciplinas;
		this.situacaoDoEdital = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumeroDoEdital() {
		return numeroDoEdital;
	}

	public void setNumeroDoEdital(String numeroDoEdital) {
		this.numeroDoEdital = numeroDoEdital;
	}

	public String getQtdDeInscricaoPorAluno() {
		return qtdDeInscricaoPorAluno;
	}

	public void setQtdDeInscricaoPorAluno(String qtdDeInscricaoPorAluno) {
		this.qtdDeInscricaoPorAluno = qtdDeInscricaoPorAluno;
	}

	public String getSituacaoDoEdital() {
		return situacaoDoEdital;
	}

	public void setSituacaoDoEdital(String situacaoDoEdital) {
		this.situacaoDoEdital = situacaoDoEdital;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public float getPesoCRE() {
		return pesoCRE;
	}

	public void setPesoCRE(float pesoCRE) {
		this.pesoCRE = pesoCRE;
	}

	public float getPesoNota() {
		return pesoNota;
	}

	public void setPesoNota(float pesoNota) {
		this.pesoNota = pesoNota;
	}

	public ArrayList<DisciplinaModel> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<DisciplinaModel> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public boolean editalExiste() {
		return editalExiste;
	}

	public void setEditalExiste(boolean editalExiste) {
		this.editalExiste = editalExiste;
	}

	public ArrayList<EditalDeMonitoriaModel> getEditais() {
		return editais;
	}

	public void setEditais(ArrayList<EditalDeMonitoriaModel> editais) {
		this.editais = editais;
	}

	public EditalDeMonitoriaModel getEditalDeMonitoriaModel() {
		return editalDeMonitoriaModel;
	}

	public void setEditalDeMonitoriaModel(EditalDeMonitoriaModel editalDeMonitoriaModel) {
		this.editalDeMonitoriaModel = editalDeMonitoriaModel;
	}
}
