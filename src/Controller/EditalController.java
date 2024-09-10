package Controller;

import DTO.EditalDeMonitoriaDTO;
import Model.EditalDeMonitoriaModel;

public class EditalController {
	EditalDeMonitoriaModel edital = new EditalDeMonitoriaModel();
	
	public boolean adicionarEdital(EditalDeMonitoriaDTO dto) {
		return edital.adicionarEdital(dto);
	}
	
	public EditalDeMonitoriaDTO recuperarEditais(EditalDeMonitoriaDTO dto) {
		return edital.recuperarEditais(dto);
	}
	
	public boolean editarEdital(EditalDeMonitoriaDTO dto) {
		return edital.editarEdital(dto);
	}

}
