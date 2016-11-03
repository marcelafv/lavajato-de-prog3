package principal;

import java.util.LinkedList;


public class Partido implements Comparable<Partido>{
	
	private String nome;
	private int votos;
	private LinkedList<Candidato> candidatos;
	
	public Partido(){
		candidatos = new LinkedList<Candidato>();
	}
	
	public Partido(String s){
		this.nome = s;
		candidatos = new LinkedList<Candidato>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVotos() {
		int nvotos = 0;
		for (Candidato candidato : this.candidatos){
				nvotos = nvotos + candidato.getNumVotos();
		}
		return nvotos;
	}
	
	public void addVotos(int votos) {
		this.votos = this.votos + votos;
	}
	
	public boolean addCandidato(Candidato c){
		this.addVotos(c.getNumVotos());
		return candidatos.add(c);
	}
	
	public LinkedList<Candidato> getCandidatos(){
		return (LinkedList<Candidato>) candidatos.clone();
	}
	
	public int getNEleitos(){
		int eleitos = 0;
		
		for (Candidato candidato : this.candidatos){
			if(candidato.isEleito()) eleitos++;
		}
		
		return eleitos;
	}
	
	public boolean equals(Partido c){
		if(this.getNome().equals(c.getNome())) return true;
		else return false;			
	}
	
	@Override
	public String toString() {
		return getNome()+ ", " + this.getVotos() + " votos , " + getNEleitos()+ " candidatos eleitos";
	}

	// Comparador por votos
	@Override
	public int compareTo(Partido p) {
	        return  p.getVotos() - this.votos;
	}

}
