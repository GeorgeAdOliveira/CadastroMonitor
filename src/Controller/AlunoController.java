package Controller;

import DTO.AlunoDTO;
import Model.AlunoModel;

public class AlunoController {

	private AlunoModel alunoModel = new AlunoModel();

	public boolean criarAluno(AlunoDTO dto) {
		return alunoModel.salvarAluno(dto);
	}

	public boolean apagarAluno(AlunoDTO dto) {
		return alunoModel.deletarAluno(dto);
	}

	public boolean editarAluno(AlunoDTO dto) {
		return alunoModel.modificarAluno(dto);
	}

	public AlunoDTO verAlunos(AlunoDTO dto) {
		return alunoModel.visualizarAlunos(dto);
	}
	
	public boolean loginAluno(AlunoDTO dto) {
		return alunoModel.loginAluno(dto);
	}
	
	public String recuperarMatricula(AlunoDTO aluno) {
		return alunoModel.recuperarMatricula(aluno).getMatricula();
	}
	
	public AlunoDTO recuperarAlunoPelaMtricula(AlunoDTO aluno) {
		return alunoModel.recuperarAlunoPelaMtricula(aluno);
	}
	public AlunoDTO recuperarAlunoModelPelaMtricula(AlunoDTO aluno) {
		return alunoModel.recuperarAlunoModelPelaMtricula(aluno);
	}

	public void atualizarMensagemBD(AlunoDTO alunoDTO) {
		alunoModel.atualizarMensagemBD(alunoDTO);
		
	}
}
