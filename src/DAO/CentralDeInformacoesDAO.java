package DAO;

import DTO.AlunoDTO;
import Model.AlunoModel;

public class CentralDeInformacoesDAO implements SearchAluno {

	private BancoDeDados bd;

	// Adicionar aluno no Banco de Dados
	public AlunoDTO adicionarAluno(AlunoDTO aluno) {

		// Verificando de o Aluno ja existe no Banco de dados
		if (alunoExiste(aluno).getAlunoExiste()) {
			return aluno;
		}
		// criando aluno
		AlunoModel aluno1 = new AlunoModel();
		aluno1.setNome(aluno.getNome());
		aluno1.setMatricula(aluno.getMatricula());
		aluno1.setEmail(aluno.getEmail());
		aluno1.setSenha(aluno.getSenha());
		aluno1.setSexo(aluno.getSexo());
		// Salvando o Aluno no Banco de Dados
		bd = Persistencia.getInstance().recuperar();
		bd.getAlunos().add(aluno1);
		Persistencia.getInstance().salvar(bd);
		aluno.setAlunoCriado(true);
		return aluno;

	}

	// Verificando de o Aluno ja existe no Banco de Dados
	public AlunoDTO alunoExiste(AlunoDTO aluno) {
		bd = Persistencia.getInstance().recuperar();
		for (AlunoModel a : bd.getAlunos()) {
			if (a.getMatricula().equals(aluno.getMatricula())) {
				aluno.setAlunoExiste(true);
				return aluno;
			}
		}
		aluno.setAlunoExiste(false);
		return aluno;
	}

	// Excluir Aluno
	public AlunoDTO excluirAluno(AlunoDTO aluno) {
		bd = Persistencia.getInstance().recuperar();
		for (AlunoModel a : bd.getAlunos()) {
			if (a.getMatricula().equals(aluno.getMatricula())) {
				bd.getAlunos().remove(a);
				Persistencia.getInstance().salvar(bd);
				aluno.setAlunoExiste(true);
				return aluno;
			}
		}
		aluno.setAlunoExiste(false);
		return aluno;
	}

	// editar aluno
	public AlunoDTO editarAluno(AlunoDTO aluno) {
		bd = Persistencia.getInstance().recuperar();
		for (AlunoModel a : bd.getAlunos()) {
			if (a.getMatricula().equals(aluno.getMatricula())) {
				a.setNome(aluno.getNome());
				a.setMatricula(aluno.getMatricula());
				a.setEmail(aluno.getEmail());
				a.setSenha(aluno.getSenha());
				a.setSexo(aluno.getSexo());
				Persistencia.getInstance().salvar(bd);
				aluno.setAlunoExiste(true);
				return aluno;
			}
		}
		aluno.setAlunoExiste(false);
		return aluno;
	}

	// Listar alunos
	public AlunoDTO listarAlunos(AlunoDTO aluno) {
		aluno.setAlunos(Persistencia.getInstance().recuperar().getAlunos());
		return aluno;
	}
}
