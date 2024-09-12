package Model;

import DAO.CentralDeInformacoesDAO;
import DTO.InscricaoDTO;

public class InscricaoModel {

	private CentralDeInformacoesDAO centralDAO = new CentralDeInformacoesDAO();
	
	private AlunoModel aluno;
	private DisciplinaModel disciplina;
	private float notaCRE;
	private float notaDisciplina;
	private float notaFinal;
	private String resultado;

	public InscricaoModel() {
		
	}
	public InscricaoModel(AlunoModel aluno, DisciplinaModel disciplina, float notaCRE, float notaDisciplina,
			String resultado, float notaFinal) {
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.notaCRE = notaCRE;
		this.notaDisciplina = notaDisciplina;
		this.notaFinal = notaFinal;
		this.resultado = resultado;

	}
	
	public boolean realizarInscricaoNoEdital(InscricaoDTO dto) {
		dto = centralDAO.realizarInscricaoNoEdital(dto);
		return dto.isInscricaoCriada();
	}

	public AlunoModel getAluno() {
		return aluno;
	}

	public void setAluno(AlunoModel aluno) {
		this.aluno = aluno;
	}

	public DisciplinaModel getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(DisciplinaModel disciplina) {
		this.disciplina = disciplina;
	}

	public float getNotaCRE() {
		return notaCRE;
	}

	public void setNotaCRE(float notaCRE) {
		this.notaCRE = notaCRE;
	}

	public float getNotaDisciplina() {
		return notaDisciplina;
	}

	public void setNotaDisciplina(float notaDisciplina) {
		this.notaDisciplina = notaDisciplina;
	}

	public float getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(float notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
