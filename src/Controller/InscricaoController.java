package Controller;

import DTO.InscricaoDTO;
import Model.InscricaoModel;

public class InscricaoController {
	
	InscricaoModel inscricaoModel = new InscricaoModel();
	
	public boolean realizarInscricaoNoEdital(InscricaoDTO dto) {
		return inscricaoModel.realizarInscricaoNoEdital(dto);
	}

}
