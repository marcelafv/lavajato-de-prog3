package principal;

import java.util.LinkedList;
import java.util.List;

public class Coligacao implements Comparable<Coligacao>{
	
	private String nome;
	private int votos;
	private LinkedList<Partido> partidos;
	
	public Coligacao(){
		partidos = new LinkedList<Partido>();
	}
	
	public Coligacao(String s){
		this.nome = s;
		partidos = new LinkedList<Partido>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVotos() {
		int nvotos = 0;
		for (Partido partido : partidos){
			for (Candidato candidato : partido.getCandidatos()){
					nvotos = nvotos + candidato.getNumVotos();
			}
		}
		return nvotos;
	}
	
	public void addVotos(int votos) {
		this.votos = this.votos + votos;
	}
	
	public boolean addPartido(Partido p){
		for(Partido partido : this.partidos){
			if(partido.getNome().equals(p.getNome())){	
				return true;
			}
		}
		partidos.add(p);
		return true;
	}
	
	public List<Partido> getPartidos(){
		return (List<Partido>) partidos.clone();
	}
	
	public int getNEleitos(){
		int eleitos = 0;
		
		for (Partido partido : this.partidos){
			eleitos = eleitos + partido.getNEleitos();
		}
		
		return eleitos;
	}
	
	public boolean equals(Coligacao c){
		if(this.getNome().equals(c.getNome())) return true;
		else return false;		
	}
	
	@Override
	public String toString() {
		return getNome()+ ", " + getVotos() + ", " + getNEleitos();			
	}
	
	@Override
	public int compareTo(Coligacao p) {
	        return  p.getVotos() - this.getVotos();
	}

}
