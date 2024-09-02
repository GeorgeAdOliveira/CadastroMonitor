package DAO;

import DTO.AlunoDTO;
import DTO.CoordenadorDTO;
import Model.AlunoModel;
import Model.CoordenadorModel;

public class CentralDeInformacoesDAO implements SearchAluno, SearchCoordenador {

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

	// Adicionar Coordenador
	public CoordenadorDTO adicionarCoordenador(CoordenadorDTO coordenador) {
		if(coordenadorExiste(coordenador).getCoordenadorExiste()) {
			return coordenador;
		}
		// criando coordenador
		CoordenadorModel coordenadorModel = new CoordenadorModel();
		coordenadorModel.setNome(coordenador.getNome());
		coordenadorModel.setEmail(coordenador.getEmail());
		coordenadorModel.setSenha(coordenador.getSenha());
		//salvado coordenador no banco de dados
		bd = Persistencia.getInstance().recuperar();
		bd.setCoordenador(coordenadorModel);
		Persistencia.getInstance().salvar(bd);
		coordenador.setCoordenadorCriado(true);
		return coordenador;
	}

	// Editar o Coordenador
	public CoordenadorDTO editarCoordenador(CoordenadorDTO coordenador) {
		bd = Persistencia.getInstance().recuperar();
		bd.getCoordenador().setNome(coordenador.getNome());
		bd.getCoordenador().setEmail(coordenador.getEmail());
		bd.getCoordenador().setSenha(coordenador.getSenha());
		Persistencia.getInstance().salvar(bd);
		coordenador.setCoordenadorExiste(true);
		return coordenador;
	}

	// verificar se ja existe um coordenador no banco de dados
	public CoordenadorDTO coordenadorExiste(CoordenadorDTO coordenador) {
		bd = Persistencia.getInstance().recuperar();
		if(bd.getCoordenador()!= null) {
			coordenador.setCoordenadorExiste(true);
		}
		return coordenador;
	}

	// retorna os dados do coordenador
	public CoordenadorDTO verCoordenador(CoordenadorDTO coordenador) {
		bd = Persistencia.getInstance().recuperar();
		CoordenadorModel coordenadorModel = bd.getCoordenador();
		coordenador.setNome(coordenadorModel.getNome());
		coordenador.setEmail(coordenadorModel.getEmail());
		coordenador.setSenha(coordenadorModel.getSenha());
		return coordenador;
	}
}
