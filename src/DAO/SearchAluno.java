package src.DAO;

import java.util.List;

import src.DTO.*;

public interface SearchAluno {
	
	// Create
	public AlunoDTO adicionarAluno(AlunoDTO aluno);
	
	// Delete
	public AlunoDTO excluirAluno(AlunoDTO aluno);
	
	// Update
	public AlunoDTO editarAluno(AlunoDTO aluno);
	
	// Read Find All - Listar todos
	public List<AlunoDTO> listarAlunos(AlunoDTO aluno);
	
	//findById
	public AlunoDTO recuperarPorMatricula(AlunoDTO aluno);
}
