package DAO;

import DTO.CoordenadorDTO;

public interface SearchCoordenador {
	
	public CoordenadorDTO adicionarCoordenador (CoordenadorDTO coordenador);

	public CoordenadorDTO editarCoordenador (CoordenadorDTO coordenador);
	
	public CoordenadorDTO verCoordenador (CoordenadorDTO coordenador);
	
	public CoordenadorDTO excluirCoordenador (CoordenadorDTO coordenador);
	
}
