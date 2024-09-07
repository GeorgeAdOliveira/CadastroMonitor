package Controller;

import DTO.CoordenadorDTO;

public interface MetodosCoordenadorController {
	
	public boolean criarCoordenador(CoordenadorDTO dto);
	
	public boolean editarCoordenador(CoordenadorDTO dto);
	
	public boolean coordenaodorExiste(CoordenadorDTO dto);
	
	public CoordenadorDTO verCoordenador(CoordenadorDTO dto);
	
	public boolean loginCoordenador(CoordenadorDTO dto);

}
