package DAO;

import DTO.CoordenadorDTO;

public interface SearchCoordenador {
	
	public CoordenadorDTO adicionarCoordenador (CoordenadorDTO coordenador);

	public CoordenadorDTO editarCoordenador (CoordenadorDTO coordenador);
	
	public CoordenadorDTO coordenadorExiste (CoordenadorDTO coordenador);
	
	public CoordenadorDTO verCoordenador (CoordenadorDTO coordenador);
	
	public CoordenadorDTO loginCoordenador (CoordenadorDTO coordenador);
	
}
