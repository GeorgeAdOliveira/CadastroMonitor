package src.DAO;

import java.util.List;

import src.DTO.EditalDeMonitoriaDTO;

public interface SearchEditalDeMonitoria {
	
	public EditalDeMonitoriaDTO adicionarEdital (EditalDeMonitoriaDTO edital);
		
	public EditalDeMonitoriaDTO editarEdital (EditalDeMonitoriaDTO edital);
	
	public EditalDeMonitoriaDTO verEdital (EditalDeMonitoriaDTO edital);
	
	public EditalDeMonitoriaDTO excluirEdital (EditalDeMonitoriaDTO edital);
	
	public List<EditalDeMonitoriaDTO> recuperarEditais (EditalDeMonitoriaDTO edital);

}
