package Model;

import DAO.CentralDeInformacoesDAO;
import DTO.CoordenadorDTO;

/*
 * @author George
 */

public class CoordenadorModel extends PessoaModel {

	private CentralDeInformacoesDAO centralDAO = new CentralDeInformacoesDAO();
	
	public boolean salvarCoordenador(CoordenadorDTO dto) {
		return centralDAO.adicionarCoordenador(dto).getCoordenadorCriado();
	}
	
	public boolean editarCoordenador(CoordenadorDTO dto) {
		return centralDAO.editarCoordenador(dto).getCoordenadorExiste();
	}
	
	public boolean coordenadorExiste(CoordenadorDTO dto) {
		return centralDAO.coordenadorExiste(dto).getCoordenadorExiste();
	}
	
	public CoordenadorDTO verCoordenador(CoordenadorDTO dto) {
		return centralDAO.verCoordenador(dto);
	}
	
	public boolean loginCoordenador(CoordenadorDTO dto) {
		return centralDAO.loginCoordenador(dto).getCoordenadorExiste();
	}
	
}
