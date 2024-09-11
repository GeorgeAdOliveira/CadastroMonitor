 package DTO;

import java.util.ArrayList;

import Model.AlunoModel;

public class AlunoDTO {

	private String nome;
	private String matricula;
	private String email;
	private String sexo;
	private String senha;
	private int indiceLista;
	private boolean alunoExiste;
	private boolean alunoCriado;
	private ArrayList<AlunoModel> alunos;
	private String mensagem;

	public AlunoDTO() {

	}
	
	public AlunoDTO(String nome, String matricula, String email, String sexo,String senha){
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.sexo = sexo;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getAlunoExiste() {
		return alunoExiste;
	}

	public void setAlunoExiste(boolean alunoExiste) {
		this.alunoExiste = alunoExiste;
	}

	public ArrayList<AlunoModel> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<AlunoModel> alunos) {
		this.alunos = alunos;
	}

	public boolean getAlunoCriado() {
		return alunoCriado;
	}

	public void setAlunoCriado(boolean alunoCriado) {
		this.alunoCriado = alunoCriado;
	}

	public int getIndiceLista() {
		return indiceLista;
	}

	public void setIndiceLista(int indiceLista) {
		this.indiceLista = indiceLista;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
