package DTO;

public class InscricaoDTO {
	
	private String idAluno;
	private long idEdital;
	private String nomeDisciplina;
	private float notaCRE;
	private float notaDisciplina;
	private float notaFinal;
	private String resultado;
	private boolean inscricaoCriada;

	public InscricaoDTO(String idAluno,long idEdital,String nomeDisciplina, float notaCRE, float notaDisciplina,
			String resultado, float notaFinal) {
		this.idAluno = idAluno;
		this.idEdital = idEdital;
		this.setNomeDisciplina(nomeDisciplina);
		this.notaCRE = notaCRE;
		this.notaDisciplina = notaDisciplina;
		this.notaFinal = notaFinal;
		this.resultado = resultado;

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

	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(String idAluno) {
		this.idAluno = idAluno;
	}

	public long getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(long idEdital) {
		this.idEdital = idEdital;
	}


	public String getNomeDisciplina() {
		return nomeDisciplina;
	}


	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public boolean isInscricaoCriada() {
		return inscricaoCriada;
	}

	public void setInscricaoCriada(boolean inscricaoCriada) {
		this.inscricaoCriada = inscricaoCriada;
	}

}
