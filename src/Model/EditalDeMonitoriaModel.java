package Model;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.CentralDeInformacoesDAO;
import DTO.EditalDeMonitoriaDTO;
import View.Prototype;

public class EditalDeMonitoriaModel implements Prototype{

	private CentralDeInformacoesDAO centralDAO = new CentralDeInformacoesDAO();

	private long id = System.currentTimeMillis();
	private String numeroDoEdital;
	private String qtdDeInscricaoPorAluno;
	private String situacaoDoEdital;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private float pesoCRE;
	private float pesoNota;
	private ArrayList<DisciplinaModel> disciplinas;

	public EditalDeMonitoriaModel() {

	}

	public EditalDeMonitoriaModel(String numeroDoEdital, String qtdDeInscricaoPorAluno, LocalDate dataInicio,
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

	public boolean adicionarEdital(EditalDeMonitoriaDTO dto) {
		return centralDAO.adicionarEdital(dto).editalExiste();
	}

	public EditalDeMonitoriaDTO recuperarEditais(EditalDeMonitoriaDTO dto) {
		return centralDAO.recuperarEditais(dto);
	}
	public boolean editarEdital(EditalDeMonitoriaDTO dto) {
		return centralDAO.editarEdital(dto).editalExiste();
	}

	// Verificando se o edital esta aberto ou fechado
	public String situacao() {
		LocalDate data = LocalDate.now();
		if (dataInicio.isAfter(data)) {
			return "Fechado";
		}
		if ((dataInicio.isBefore(data)) && dataFim.isAfter(data)
				|| (dataInicio.isEqual(data) || dataFim.isEqual(data))) {
			return "Aberto";
		}
		return "Encerrado";
	}

	// Calucular a media da nota do aluno
	public float notaFinal(float notaCRE, float nota) {
		float notaFinal = notaCRE * pesoCRE + nota * pesoNota;
		return notaFinal;

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

	@Override
	public EditalDeMonitoriaModel clone() {
		EditalDeMonitoriaModel editalCopia = new EditalDeMonitoriaModel();
		editalCopia.setNumeroDoEdital(numeroDoEdital);
		editalCopia.setQtdDeInscricaoPorAluno(qtdDeInscricaoPorAluno);
		editalCopia.setSituacaoDoEdital(situacaoDoEdital);
		editalCopia.setDataInicio(dataInicio);
		editalCopia.setDataFim(dataFim);
		editalCopia.setPesoCRE(pesoCRE);
		editalCopia.setPesoNota(pesoNota);
		editalCopia.setDisciplinas(disciplinas);
		
		return editalCopia;
	}

}
