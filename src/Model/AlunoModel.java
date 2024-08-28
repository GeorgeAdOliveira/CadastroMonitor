package Model;

import java.util.ArrayList;

import DAO.CentralDeInformacoesDAO;
import DTO.AlunoDTO;

public class AlunoModel extends PessoaModel {

	private CentralDeInformacoesDAO centralDAO = new CentralDeInformacoesDAO();

	private String matricula;
	private String sexo;

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}