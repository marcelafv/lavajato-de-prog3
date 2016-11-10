package principal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* @ ELEIÇÃO
 * Classe com as listas de candidatos, partidos e coligações
 */

public class Eleicao {
	
	private static Eleicao instancia = null;		// Definição do Singleton
	
	private LinkedList<Candidato> listaCandidatos;
	private LinkedList<Partido>   listaPartidos;
	private LinkedList<Coligacao> listaColigacoes;
	
	private Eleicao(){
		listaCandidatos =   new LinkedList<Candidato>();
		listaPartidos   = 	new LinkedList<Partido>();	
		listaColigacoes =   new LinkedList<Coligacao>();	
	}
	
	/* Inclusão de um candidato caso ele ainda não exista na lista */
	public boolean addCandidato(Candidato c){
		if(listaCandidatos.contains(c)){
			return true;
		}		
		else return listaCandidatos.add(c);
	}
	
	
	public Partido addPartido(Partido p){
		if(listaPartidos.contains(p)){
			int index = listaPartidos.indexOf(p);
			Partido p0 = listaPartidos.get(index);
			
			for(Candidato c : p.getCandidatos()){
				p0.addCandidato(c);
			}
			
			return p0;
			
		} else {
			listaPartidos.add(p);
			return p;
		}
	}
	
	
	public Partido containsPartido(String s){
		Partido p = new Partido(s);
		for(Partido partido : listaPartidos){
			if(partido.equals(p)){
				return partido;
			}
		}
		this.addPartido(p);
		return p;
	}
	
	public Coligacao containsColigacao(String s){
		Coligacao c = new Coligacao(s);
		for(Coligacao coligacao : listaColigacoes){
			if(coligacao.getNome().equals(c.getNome())){
				return coligacao;
			}
		}
		this.addColigacao(c);
		return c;
	}
	
	/* Retorna uma shadow copy da lista de partidos em ordem decrescente por número de votos */
	public List<Partido> getPartidos(){
		List<Partido> partidos = (LinkedList<Partido>) this.listaPartidos.clone();	
		Collections.sort(partidos);		
		return partidos;   
	}
	
	/* Retorna a lista de coligações em ordem decrescente por número de votos */
	public List<Coligacao> getColigacoes(){
		Collections.sort(this.listaColigacoes);
		return this.listaColigacoes;
	}
	
	
	public Coligacao addColigacao(Coligacao c){
		if(listaColigacoes.contains(c)){
			int index = listaColigacoes.indexOf(c);
			Coligacao c0 = listaColigacoes.get(index);
			
			for(Partido p : c.getPartidos()){
				c0.addPartido(p);
			}
			
			return c0;
		}	
		listaColigacoes.add(c);
		return c;
	}
	
	public LinkedList<Candidato> getListaCandidatos(){
		return listaCandidatos;
	}

	/* Criação do Singleton */
	public static Eleicao getInstance(){
		if(instancia == null){
			instancia = new Eleicao();
		}
		
		return instancia;
	}
	
	/* ----- GERAÇÃO DE RELATÓRIOS ------ */
	
	/* Cálculo no número de vagas */
	public int getNumVagas(){
			int vagas = 0;
		    for (Candidato aux : listaCandidatos) {
		    	if(aux.isEleito()) vagas++;
		    }
		    return vagas;
	}
	
	/* Criação da lista de candidatos eleitos */
	public List<Candidato> getEleitos(){
		
		LinkedList<Candidato> vereadores = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> eleitos = new LinkedList<Candidato>();
		
		for(Candidato aux : vereadores){
			if(aux.isEleito()) eleitos.add(aux);
		}
		return eleitos;
	}	
	
	/* Criação da lista de candidatos mais votados */
	public List<Candidato> getMaisVotados(){
		List<Candidato> maisVotados = (LinkedList<Candidato>)  this.getListaCandidatos().clone();	
		Collections.sort(maisVotados);		
		int index = 1;
		for(Candidato c : maisVotados){
			c.setIndex(index);
			index++;
		}
		return maisVotados;
	   
	}
	
	/* Criação da lista de candidatos eleitos que não estão entre os N (número de vagas) mais votados */
	public List<Candidato> getBeneficiados(){
		LinkedList<Candidato> vereadores = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> beneficiados = new LinkedList<Candidato>();
		
		Collections.sort(vereadores);	// Ordenação dos vereadores por número decrescente de votos
			
		int vagas = this.getNumVagas()+1;
		
		for(Candidato aux : vereadores){
			vagas--;
			if(vagas > 0) continue;		// Os N mais votados não se encaixam na definição
			if(aux.isEleito()) beneficiados.add(aux);
			vagas--;
		}
		return beneficiados;
	}
	
	/* Analisa os não-eleitos que estão entre os N (número de vagas) mais votados */
	public List<Candidato> getPrejudicados(){
		
		LinkedList<Candidato> eleitos = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> prejudicados = new LinkedList<Candidato>();
		
		Collections.sort(eleitos);
			
		int vagas = this.getNumVagas();
		for(Candidato aux : eleitos){
			if(vagas <=0) break;	// Os candidatos que não estão nas N primeiras posições não se encaixam na definição
			if(!aux.isEleito()) prejudicados.add(aux);
			vagas--;
		}
		return prejudicados;
	}
	
	/* Cálculo do total de votos a partir do número de votos recebidos pelos partidos */
	public int totalVotosNominais() {
		int total = 0;
		
		for (Partido p : listaPartidos) {
			total += p.getVotos();
		}
		
		return total;
	}
	
}

