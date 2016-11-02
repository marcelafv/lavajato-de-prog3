package principal;

import java.util.LinkedList;

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
		}
		
		listaPartidos.add(p);
		return p;
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
}
