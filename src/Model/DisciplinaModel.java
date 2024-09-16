package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisciplinaModel {
	
	private String nomeDaDisciplina;
	private String qtdVagasVoluntario;
	private String qtdVagasRemunerada;
	private ArrayList<InscricaoModel> inscricoes;
	
	public DisciplinaModel() {
		
	}
	
	public DisciplinaModel(String nomeDaDisciplina, String qtdVagasVoluntario, String qtdVagasRemunerada) {
		this.nomeDaDisciplina = nomeDaDisciplina;
		this.qtdVagasVoluntario = qtdVagasVoluntario;
		this.qtdVagasRemunerada = qtdVagasRemunerada;
		this.inscricoes = new ArrayList<>();
	}

	// Calculando o resultado do edital
		public void calcularResultado() {
			Collections.sort(inscricoes, Comparator.comparingDouble(inscricoes -> inscricoes.getNotaFinal()));
			Collections.reverse(inscricoes);
			int voluntario = Integer.parseInt(qtdVagasVoluntario);
			int remunerado = Integer.parseInt(qtdVagasRemunerada);
			for (InscricaoModel i : inscricoes) {
				if (!i.getResultado().equals("Desistiu")) {
					if (remunerado > 0) {
						i.setResultado("Classificado com Bolsa");
						remunerado--;
					} else if (voluntario > 0) {
						i.setResultado("Classificado como Voluntario");
						voluntario--;
					} else {
						i.setResultado("Lista de Espera");
					}
				}
			}
		}
	public String getNomeDaDisciplina() {
		return nomeDaDisciplina;
	}

	public void setNomeDaDisciplina(String nomeDaDisciplina) {
		this.nomeDaDisciplina = nomeDaDisciplina;
	}

	public String getQtdVagasVoluntario() {
		return qtdVagasVoluntario;
	}

	public void setQtdVagasVoluntario(String qtdVagasVoluntario) {
		this.qtdVagasVoluntario = qtdVagasVoluntario;
	}

	public String getQtdVagasRemunerada() {
		return qtdVagasRemunerada;
	}

	public void setQtdVagasRemunerada(String qtdVagasRemunerada) {
		this.qtdVagasRemunerada = qtdVagasRemunerada;
	}

	public ArrayList<InscricaoModel> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(ArrayList<InscricaoModel> inscricoes) {
		this.inscricoes = inscricoes;
	}

}
