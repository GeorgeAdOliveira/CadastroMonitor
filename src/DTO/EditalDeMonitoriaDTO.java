package src.DTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.Model.DisciplinaModel;
import src.Model.EditalDeMonitoriaModel;

public class EditalDeMonitoriaDTO {
    private int id;
	private int numeroDoEdital;
    private int qtdDeInscricaoPorAluno;
	private String situacaoDoEdital;
	private Date dataInicio;
	private Date dataFim;
	private float pesoCRE;
	private float pesoNota;
	private List<Integer> disciplinas;
	private boolean editalExiste;
	private ArrayList<EditalDeMonitoriaModel> editais;

	public EditalDeMonitoriaDTO() {

	}

	public EditalDeMonitoriaDTO(int numeroDoEdital, int qtdDeInscricaoPorAluno, Date dataInicio,
			Date dataFim, float pesoCRE, float pesoNota, List<Integer> disciplinas) {
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

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroDoEdital() {
		return numeroDoEdital;
	}

	public void setNumeroDoEdital(int i) {
		this.numeroDoEdital = i;
	}

	public int getQtdDeInscricaoPorAluno() {
		return qtdDeInscricaoPorAluno;
	}

	public void setQtdDeInscricaoPorAluno(int qtdDeInscricaoPorAluno) {
		this.qtdDeInscricaoPorAluno = qtdDeInscricaoPorAluno;
	}

	public String getSituacaoDoEdital() {
		return situacaoDoEdital;
	}

	public void setSituacaoDoEdital(String situacaoDoEdital) {
		this.situacaoDoEdital = situacaoDoEdital;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
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

	public ArrayList<Integer> getDisciplinas() {
		return (ArrayList<Integer>) disciplinas;
	}

	public void setDisciplinas(ArrayList<Integer> disciplinas) {
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

	public String getCodigo() {
		// TODO Auto-generated method stub
		return null;
	}

}
