package Controller;

import DTO.CoordenadorDTO;
import View.TelaCadastrarCoordenador;
import View.TelaLogin;

public class Main {
	public static void main(String[] args) {

		CoordenadorDTO coordenadorDto = new CoordenadorDTO();
		CoordenadorController coordenadorControler = new CoordenadorController();

		if (coordenadorControler.coordenaodorExiste(coordenadorDto)) {
			new TelaLogin();
		} else {
			new TelaCadastrarCoordenador();
		}

	}

}
