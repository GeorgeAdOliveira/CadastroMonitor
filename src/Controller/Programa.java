package Controller;

import DTO.CoordenadorDTO;
import View.TelaCadastroAluno;

public class Programa {
	public static void main(String[] args) {
		//new TelaCadastroAluno();
		
		CoordenadorDTO coordenadorDto = new CoordenadorDTO();
		coordenadorDto.setNome("Cleyton");
		coordenadorDto.setEmail("Cleyton@gmail.com");
		coordenadorDto.setSenha("1234");
		
		CoordenadorController coordenadorControler = new CoordenadorController();
		
		if(coordenadorControler.criarCoordenador(coordenadorDto)) {
			System.out.println("Coordenador adicionado com sucesso!");
		} else {
			System.out.println("Coordenador ja existe!");
			
			CoordenadorDTO coordenadorDto2 = new CoordenadorDTO();
			coordenadorDto2 = coordenadorControler.verCoordenador(coordenadorDto2);
			System.out.println(coordenadorDto2.getNome());
			System.out.println(coordenadorDto2.getEmail());
			System.out.println(coordenadorDto2.getSenha());
		}
		
		//Test GitHub OK
	}
}
