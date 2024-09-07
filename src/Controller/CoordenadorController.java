package Controller;

import DTO.CoordenadorDTO;
import Model.CoordenadorModel;

public class CoordenadorController implements MetodosCoordenadorController{

	private CoordenadorModel coordenadorModel = new CoordenadorModel();

	
	public boolean criarCoordenador(CoordenadorDTO dto) {
		return coordenadorModel.salvarCoordenador(dto);
	}

	
	public boolean editarCoordenador(CoordenadorDTO dto) {
		return coordenadorModel.editarCoordenador(dto);
	}

	public CoordenadorDTO verCoordenador(CoordenadorDTO dto) {
		return coordenadorModel.verCoordenador(dto) ;
	}

	public boolean coordenaodorExiste(CoordenadorDTO dto) {
		return coordenadorModel.coordenadorExiste(dto);
	}

	public boolean loginCoordenador(CoordenadorDTO dto) {
		return coordenadorModel.loginCoordenador(dto);
	}
}
