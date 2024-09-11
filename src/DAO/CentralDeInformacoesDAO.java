package DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import DTO.AlunoDTO;
import DTO.CoordenadorDTO;
import DTO.EditalDeMonitoriaDTO;
import Model.AlunoModel;
import Model.CoordenadorModel;
import Model.EditalDeMonitoriaModel;

public class CentralDeInformacoesDAO implements SearchAluno, SearchCoordenador, SearchEditalDeMonitoria {

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
	@Override
	public AlunoDTO recuperarMatricula(AlunoDTO aluno) {
		bd = Persistencia.getInstance().recuperar();
		for (AlunoModel a : bd.getAlunos()) {
			if (a.getEmail().equals(aluno.getEmail())) {
				aluno.setMatricula(a.getMatricula());
				return aluno;
			}
		}
		
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

	@Override
	public AlunoDTO loginAluno(AlunoDTO aluno) {
		bd = Persistencia.getInstance().recuperar();
		for (AlunoModel a : bd.getAlunos()) {
			if (a.getEmail().equals(aluno.getEmail())) {
				if (a.getSenha().equals(aluno.getSenha())) {
					aluno.setAlunoExiste(true);
					return aluno;
				}
			}
		}

		return aluno;
	}

	// Adicionar Coordenador
	public CoordenadorDTO adicionarCoordenador(CoordenadorDTO coordenador) {
		if (coordenadorExiste(coordenador).getCoordenadorExiste()) {
			return coordenador;
		}
		// criando coordenador
		CoordenadorModel coordenadorModel = new CoordenadorModel();
		coordenadorModel.setNome(coordenador.getNome());
		coordenadorModel.setEmail(coordenador.getEmail());
		coordenadorModel.setSenha(coordenador.getSenha());
		// salvado coordenador no banco de dados
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
		if (bd.getCoordenador() != null) {
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

	// Login coordenador verifica se é o cordenador que está fazendo o login
	public CoordenadorDTO loginCoordenador(CoordenadorDTO coordenador) {
		bd = Persistencia.getInstance().recuperar();
		CoordenadorModel coordenadorModel = bd.getCoordenador();
		if (coordenadorModel.getEmail().equals(coordenador.getEmail())
				&& coordenadorModel.getSenha().equals(coordenador.getSenha())) {
			coordenador.setCoordenadorExiste(true);
		}
		return coordenador;
	}

	@Override
	public EditalDeMonitoriaDTO adicionarEdital(EditalDeMonitoriaDTO edital) {
		bd = Persistencia.getInstance().recuperar();
		ArrayList<EditalDeMonitoriaModel> editais = bd.getEditais();
		for (EditalDeMonitoriaModel ed : editais) {
			if (ed.getId() == edital.getId()) {
				return edital;
			}
			if (ed.getNumeroDoEdital().equals(edital.getNumeroDoEdital())) {
				return edital;
			}
		}
		EditalDeMonitoriaModel editalModel = new EditalDeMonitoriaModel(edital.getNumeroDoEdital(),
				edital.getQtdDeInscricaoPorAluno(), edital.getDataInicio(), edital.getDataFim(), edital.getPesoCRE(),
				edital.getPesoNota(), edital.getDisciplinas());
		bd.getEditais().add(editalModel);
		Persistencia.getInstance().salvar(bd);
		edital.setEditalExiste(true);
		return edital;
	}

	@Override
	public EditalDeMonitoriaDTO editarEdital(EditalDeMonitoriaDTO edital) {
		bd = Persistencia.getInstance().recuperar();
		ArrayList<EditalDeMonitoriaModel> editais = bd.getEditais();
		LocalDate data = LocalDate.now();
		for (EditalDeMonitoriaModel ed : editais) {
			if (ed.getId() == edital.getId()) {
				if (edital.getDataInicio().isAfter(data) || edital.getDataInicio().isEqual(data)) {
					if (edital.getDataFim().isAfter(data) || edital.getDataFim().isEqual(data)) {
						if (Integer.parseInt(ed.getQtdDeInscricaoPorAluno()) <= Integer
								.parseInt(edital.getQtdDeInscricaoPorAluno())) {
							ed.setNumeroDoEdital(edital.getNumeroDoEdital());
							ed.setQtdDeInscricaoPorAluno(edital.getQtdDeInscricaoPorAluno());
							ed.setSituacaoDoEdital(edital.getSituacaoDoEdital());
							ed.setDataInicio(edital.getDataInicio());
							ed.setDataFim(edital.getDataFim());
							ed.setPesoCRE(edital.getPesoCRE());
							ed.setPesoNota(edital.getPesoNota());
							ed.setDisciplinas(edital.getDisciplinas());
							Persistencia.getInstance().salvar(bd);
							edital.setEditalExiste(true);
							return edital;
							
						} else {
							return edital;
						}
					} else {
						return edital;
					}
				} else {
					return edital;
				}
			}
		}
		return edital;
		
	}

	@Override
	public EditalDeMonitoriaDTO verEdital(EditalDeMonitoriaDTO edital) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditalDeMonitoriaDTO excluirEdital(EditalDeMonitoriaDTO edital) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditalDeMonitoriaDTO recuperarEditais(EditalDeMonitoriaDTO edital) {
		bd = Persistencia.getInstance().recuperar();
		edital.setEditais(bd.getEditais());
		return edital;
	}

	

}
