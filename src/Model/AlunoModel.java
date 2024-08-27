package Model;

import java.util.ArrayList;

import DAO.CentralDeInformacoesDAO;
import DTO.AlunoDTO;

public class AlunoModel {

	private CentralDeInformacoesDAO centralDAO = new CentralDeInformacoesDAO();

	private String nome;
	private String matricula;
	private String email;
	private String sexo;
	private String senha;

	public boolean salvarAluno(AlunoDTO dto) {
		return centralDAO.adicionarAluno(dto).getAlunoCriado();
	}

	public boolean deletarAluno(AlunoDTO dto) {
		ArrayList<AlunoModel> alunos = visualizarAlunos(dto).getAlunos();
		dto.setMatricula(alunos.get(dto.getIndiceLista()).getMatricula());
		dto.setSenha(alunos.get(dto.getIndiceLista()).getSenha());
		return centralDAO.excluirAluno(dto).getAlunoExiste();
	}

	public boolean modificarAluno(AlunoDTO dto) {
		ArrayList<AlunoModel> alunos = visualizarAlunos(dto).getAlunos();
		if (dto.getMatricula().equals(alunos.get(dto.getIndiceLista()).getMatricula())) {
			return centralDAO.editarAluno(dto).getAlunoExiste();
		}
		return false;
	}

	public AlunoDTO visualizarAlunos(AlunoDTO dto) {
		return centralDAO.listarAlunos(dto);
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

}