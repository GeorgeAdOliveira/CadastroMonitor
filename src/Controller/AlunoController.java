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
	
}
