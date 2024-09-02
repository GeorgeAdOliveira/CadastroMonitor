package Controller;

import DTO.CoordenadorDTO;
import Model.CoordenadorModel;

public class CoordenadorController implements MetodosCoordenadorController{

	private CoordenadorModel coordenadorModel = new CoordenadorModel();

	@Override
	public boolean criarCoordenador(CoordenadorDTO dto) {
		return coordenadorModel.salvarCoordenador(dto);
	}

	@Override
	public boolean editarCoordenador(CoordenadorDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CoordenadorDTO verCoordenador(CoordenadorDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean coordenaodorExiste(CoordenadorDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}
}
