package Controller;

import DTO.CoordenadorDTO;
import View.TelaCadastroAluno;

public class Programa {
	public static void main(String[] args) {
		// new TelaCadastroAluno();

		CoordenadorDTO coordenadorDto = new CoordenadorDTO();
		coordenadorDto.setNome("Cleyton");
		coordenadorDto.setEmail("Cleyton@gmail.com");
		coordenadorDto.setSenha("1234");

		CoordenadorController coordenadorControler = new CoordenadorController();

		// verificar se ja existe um coordenador
		if (coordenadorControler.coordenaodorExiste(coordenadorDto)) {
			System.out.println("Coordenador ja existe!");
		} else {
			System.out.println("Não existe coordenador, criando coordenador!");
			//criando coordenador
			if (coordenadorControler.criarCoordenador(coordenadorDto)) {
				System.out.println("Coordenador adicionado com sucesso!");
			}
		}

		CoordenadorDTO coordenadorDto2 = new CoordenadorDTO();
		coordenadorDto2 = coordenadorControler.verCoordenador(coordenadorDto2);
		System.out.println(coordenadorDto2.getNome());
		System.out.println(coordenadorDto2.getEmail());
		System.out.println(coordenadorDto2.getSenha());

	}
}
