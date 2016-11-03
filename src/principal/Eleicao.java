package principal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Eleicao {
	
	private static Eleicao instancia = null;
	
	private LinkedList<Candidato> listaCandidatos;
	private LinkedList<Partido>   listaPartidos;
	private LinkedList<Coligacao> listaColigacoes;
	
	private Eleicao(){
		listaCandidatos =   new LinkedList<Candidato>();
		listaPartidos   = 	new LinkedList<Partido>();	
		listaColigacoes =   new LinkedList<Coligacao>();	
	}
	
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
		}else{
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
	
	public List<Partido> getPartidos(){
		List<Partido> partidos = (LinkedList<Partido>) this.listaPartidos.clone();	
		Collections.sort(partidos);		
		return partidos;
	   
	}
	
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

	public static Eleicao getInstance(){
		if(instancia == null){
			instancia = new Eleicao();
		}
		
		return instancia;
	}
	
	public int getNumVagas(){
			int vagas = 0;
		    for (Candidato aux : listaCandidatos) {
		    	if(aux.isEleito()) vagas++;
		    }
		    return vagas;
	}
	
	public List<Candidato> getEleitos(){
		
		LinkedList<Candidato> vereadores = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> eleitos = new LinkedList<Candidato>();
		
		for(Candidato aux : vereadores){
			if(aux.isEleito()) eleitos.add(aux);
		}
		return eleitos;
	}	
	
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
	
	public List<Candidato> getBeneficiados(){
		//analisa os eleitos que não estão entre os N (numero de vagas) mais votados
		LinkedList<Candidato> vereadores = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> beneficiados = new LinkedList<Candidato>();
		
		Collections.sort(vereadores);
			
		int vagas = this.getNumVagas()+1;
		
		for(Candidato aux : vereadores){
			vagas--;
			if(vagas >0) continue;
			if(aux.isEleito()) beneficiados.add(aux);
			vagas--;
		}
		return beneficiados;
	}
	
	public List<Candidato> getPrejudicados(){
		//analisa os não-eleitos que estão entre os N (numero de vagas) mais votados
		LinkedList<Candidato> eleitos = (LinkedList<Candidato>) this.getListaCandidatos().clone();
		List<Candidato> prejudicados = new LinkedList<Candidato>();
		
		Collections.sort(eleitos);
			
		int vagas = this.getNumVagas();
		for(Candidato aux : eleitos){
			if(vagas <=0) break;
			if(!aux.isEleito()) prejudicados.add(aux);
			vagas--;
		}
		return prejudicados;
	}
	
}
