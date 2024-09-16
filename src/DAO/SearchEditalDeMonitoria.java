package DAO;

import DTO.EditalDeMonitoriaDTO;

public interface SearchEditalDeMonitoria {
	
	public EditalDeMonitoriaDTO adicionarEdital (EditalDeMonitoriaDTO edital);
		
	public EditalDeMonitoriaDTO editarEdital (EditalDeMonitoriaDTO edital);
	
	public EditalDeMonitoriaDTO verEdital (EditalDeMonitoriaDTO edital);
	
	public EditalDeMonitoriaDTO excluirEdital (EditalDeMonitoriaDTO edital);
	
	public EditalDeMonitoriaDTO recuperarEditais (EditalDeMonitoriaDTO edital);

}
